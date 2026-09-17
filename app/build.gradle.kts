plugins {
    id("com.android.application")
}

android {
    namespace = "com.cornerstone.agency"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.cornerstone.agency"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "0.1-agency"

        // Endpoints copied from the shipped BuildConfig (public defaults, NOT secrets).
        // The on-device Bearer pairing key was never in the binary and must not be added here.
        buildConfigField("String", "HERMES_GATEWAY_URL", "\"http://127.0.0.1:9120\"")
        buildConfigField("String", "HERMES_CONFIG_URL", "\"http://127.0.0.1:9120\"")
        buildConfigField("String", "HERMES_DISPLAY_NAME", "\"cornerstone-agency\"")
        buildConfigField("String", "HERMES_API_KEY", "\"\"")
        buildConfigField("boolean", "HERMES_AUTO_CONNECT", "false")
    }

    buildTypes {
        debug {
            // Matches the shipped applicationId ai.nous.aurora.debug so it can coexist
            // with a future release install.
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }

    buildFeatures {
        buildConfig = true
        // Sources are decompiled Java that calls the Compose runtime directly;
        // no Kotlin compose-compiler pass is needed.
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    lint {
        abortOnError = false
    }
}

// Decompiled sources can produce thousands of errors on the first pass;
// lift javac's default 100-error cap so we can see the real picture.
tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.addAll(listOf("-Xmaxerrs", "20000", "-Xmaxwarns", "20000"))
}

configurations.all {
    // AGP 9.2.1's embedded Kotlin compiler is 2.2.x and cannot read the
    // 2.4.x metadata that newer artifacts carry; pin the whole kotlin-stdlib
    // family to a version it can consume.
    resolutionStrategy {
        force(
            "org.jetbrains.kotlin:kotlin-stdlib:2.2.21",
            "org.jetbrains.kotlin:kotlin-stdlib-jdk7:2.2.21",
            "org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.2.21"
        )
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2026.06.01")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.2.21")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

    // Networking
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // AndroidX core / lifecycle / activity
    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.activity:activity:1.11.0")
    implementation("androidx.activity:activity-ktx:1.11.0")
    implementation("androidx.activity:activity-compose:1.11.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.4")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.9.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.9.4")
    implementation("androidx.lifecycle:lifecycle-service:2.9.4")
    implementation("androidx.lifecycle:lifecycle-process:2.9.4")
    implementation("androidx.lifecycle:lifecycle-livedata:2.9.4")
    implementation("androidx.savedstate:savedstate-ktx:1.3.3")
    implementation("androidx.autofill:autofill:1.3.0")

    // Persistence / storage / background
    implementation("androidx.room:room-runtime:2.8.2")
    implementation("androidx.room:room-ktx:2.8.2")
    implementation("androidx.datastore:datastore:1.2.0")
    implementation("androidx.datastore:datastore-preferences:1.2.0")
    implementation("androidx.work:work-runtime-ktx:2.11.0")
    // Last security-crypto line that still ships EncryptedSharedPreferences.
    implementation("androidx.security:security-crypto:1.1.0-alpha06")

    // Compose (consumed as plain Java call-sites by the decompiled sources)
    implementation(composeBom)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-unit")
    implementation("androidx.compose.ui:ui-util")
    implementation("androidx.compose.ui:ui-text")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.foundation:foundation-layout")
    implementation("androidx.compose.runtime:runtime")
    implementation("androidx.compose.runtime:runtime-saveable")
    implementation("androidx.compose.animation:animation")
    implementation("androidx.compose.animation:animation-core")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.material:material-ripple")

    // Foldable / cover display
    implementation("androidx.window:window:1.4.0")

    // Misc androidx pulled in by the original merged manifest
    implementation("androidx.profileinstaller:profileinstaller:1.4.1")
    implementation("androidx.startup:startup-runtime:1.2.0")
    implementation("androidx.emoji2:emoji2:1.6.0")
    implementation("androidx.tracing:tracing:1.3.0")
    implementation("androidx.sqlite:sqlite:2.5.2")
    implementation("androidx.sqlite:sqlite-framework:2.5.2")

    // Termux terminal emulator/view is vendored as decompiled sources under
    // app/src/main/java/com/termux (no public Maven artifact matches the fork
    // bundled in the APK).
}
