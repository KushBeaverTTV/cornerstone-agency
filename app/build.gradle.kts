plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.cornerstone.agency"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.cornerstone.agency"
        minSdk = 26
        targetSdk = 35
        versionCode = 13
        versionName = "1.3.0"
    }

    // Release signing reuses the KBP release key. Values come from the environment
    // (Cornerstone_Secrets/signing.env, loaded by the release script); nothing is committed.
    val releaseKeystore = System.getenv("KBP_RELEASE_KEYSTORE_PATH")
    signingConfigs {
        if (!releaseKeystore.isNullOrBlank()) {
            create("release") {
                storeFile = file(releaseKeystore)
                storePassword = System.getenv("KBP_RELEASE_KEYSTORE_PASSWORD")
                keyAlias = System.getenv("KBP_RELEASE_KEY_ALIAS")
                keyPassword = System.getenv("KBP_RELEASE_KEY_PASSWORD")
            }
        }
    }

    buildTypes {
        debug {
            // Debug build: default debug signing, dev seed intents enabled.
        }
        release {
            isMinifyEnabled = false
            signingConfigs.findByName("release")?.let { signingConfig = it }
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.process)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.datastore.preferences)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.core)
    implementation(libs.androidx.compose.ui.tooling.preview)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp)

    // Scheduled Feed issues run as a CoroutineWorker so they survive the Activity.
    implementation(libs.androidx.work.runtime.ktx)

    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)

    // Literal coordinate — same as the sibling project, not yet in the catalog.
    implementation("androidx.security:security-crypto:1.1.0-alpha07")

    // Markdown rendering for assistant bubbles.
    // 0.42+ declares minCompileSdk 37; 0.40.2 is the newest that compiles against SDK 36.
    implementation("com.mikepenz:multiplatform-markdown-renderer-m3:0.40.2")
}
