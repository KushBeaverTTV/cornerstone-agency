package ai.nous.aurora.data.local.db;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* JADX INFO: compiled from: AuroraDatabase.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&¨\u0006\t"}, d2 = {"Lai/nous/aurora/data/local/db/AuroraDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "sessionDao", "Lai/nous/aurora/data/local/db/SessionDao;", "messageDao", "Lai/nous/aurora/data/local/db/MessageDao;", "Companion", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class AuroraDatabase extends RoomDatabase {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static volatile AuroraDatabase instance;
    private static final Migration[] migrations;

    public abstract MessageDao messageDao();

    public abstract SessionDao sessionDao();

    /* JADX INFO: compiled from: AuroraDatabase.kt */
    @Metadata(d1 = {"\u0000#\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\r"}, d2 = {"Lai/nous/aurora/data/local/db/AuroraDatabase$Companion;", "", "<init>", "()V", "instance", "Lai/nous/aurora/data/local/db/AuroraDatabase;", "migrations", "", "ai/nous/aurora/data/local/db/AuroraDatabase$Companion$migrations$1$1", "[Lai/nous/aurora/data/local/db/AuroraDatabase$Companion$migrations$1$1;", "getInstance", "context", "Landroid/content/Context;", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AuroraDatabase getInstance(Context context) {
            AuroraDatabase auroraDatabase;
            Intrinsics.checkNotNullParameter(context, "context");
            AuroraDatabase auroraDatabase2 = AuroraDatabase.instance;
            if (auroraDatabase2 != null) {
                return auroraDatabase2;
            }
            synchronized (this) {
                auroraDatabase = AuroraDatabase.instance;
                if (auroraDatabase == null) {
                    Context applicationContext = context.getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                    RoomDatabase.Builder builderDatabaseBuilder = Room.databaseBuilder(applicationContext, AuroraDatabase.class, "aurora.db");
                    Migration[] nullableArr = AuroraDatabase.migrations;
                    RoomDatabase roomDatabaseBuild = builderDatabaseBuilder.addMigrations((Migration[]) Arrays.copyOf(nullableArr, nullableArr.length)).build();
                    AuroraDatabase it = (AuroraDatabase) roomDatabaseBuild;
                    Companion companion = AuroraDatabase.INSTANCE;
                    AuroraDatabase.instance = it;
                    auroraDatabase = (AuroraDatabase) roomDatabaseBuild;
                }
            }
            return auroraDatabase;
        }
    }

    static {
        Iterable $this$map$iv = new IntRange(1, 4);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        Iterator<Integer> it = $this$map$iv.iterator();
        while (it.hasNext()) {
            final int item$iv$iv = ((IntIterator) it).nextInt();
            destination$iv$iv.add(new Migration(item$iv$iv, item$iv$iv + 1) {
                @Override // androidx.room.migration.Migration
                public void migrate(SupportSQLiteDatabase db) {
                    Intrinsics.checkNotNullParameter(db, "db");
                    AuroraMigrationSql.INSTANCE.migrateToV5(db);
                }
            });
        }
        Collection thisCollection$iv = (List) destination$iv$iv;
        migrations = (Migration[]) thisCollection$iv.toArray(new Migration[0]);
    }
}
