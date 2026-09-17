package ai.nous.aurora.data.local.db;

import android.database.Cursor;
import androidx.autofill.HintConstants;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AuroraDatabase.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u001b\u0010\r\u001a\u00020\u00052\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH\u0000¢\u0006\u0002\b\u0010J\u001b\u0010\u0011\u001a\u00020\u00052\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH\u0000¢\u0006\u0002\b\u0012J&\u0010\u0013\u001a\u00020\u00052\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005H\u0002J\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lai/nous/aurora/data/local/db/AuroraMigrationSql;", "", "<init>", "()V", "CREATE_SESSIONS", "", "CREATE_MESSAGES", "migrateToV5", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "migrateSessions", "migrateMessages", "sessionCopySql", "columns", "", "sessionCopySql$app_debug", "messageCopySql", "messageCopySql$app_debug", "value", HintConstants.AUTOFILL_HINT_NAME, "fallback", "tableColumns", "table", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AuroraMigrationSql {
    public static final int $stable = 0;
    private static final String CREATE_MESSAGES = "\n        CREATE TABLE IF NOT EXISTS `messages` (\n            `id` TEXT NOT NULL,\n            `sessionId` TEXT NOT NULL,\n            `ownerKey` TEXT NOT NULL,\n            `role` TEXT NOT NULL,\n            `content` TEXT NOT NULL,\n            `timestamp` INTEGER NOT NULL,\n            `deliveryStatus` TEXT NOT NULL,\n            `clientMsgId` TEXT,\n            PRIMARY KEY(`id`, `ownerKey`)\n        )\n    ";
    private static final String CREATE_SESSIONS = "\n        CREATE TABLE IF NOT EXISTS `sessions` (\n            `id` TEXT NOT NULL,\n            `ownerKey` TEXT NOT NULL,\n            `title` TEXT NOT NULL,\n            `source` TEXT,\n            `model` TEXT,\n            `messageCount` INTEGER NOT NULL,\n            `lastActiveMs` INTEGER NOT NULL,\n            `preview` TEXT,\n            PRIMARY KEY(`id`, `ownerKey`)\n        )\n    ";
    public static final AuroraMigrationSql INSTANCE = new AuroraMigrationSql();

    private AuroraMigrationSql() {
    }

    public final void migrateToV5(SupportSQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        migrateSessions(db);
        migrateMessages(db);
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_messages_sessionId_ownerKey` ON `messages` (`sessionId`, `ownerKey`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_messages_ownerKey` ON `messages` (`ownerKey`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_messages_deliveryStatus` ON `messages` (`deliveryStatus`)");
    }

    private final void migrateSessions(SupportSQLiteDatabase db) {
        Set<String> setTableColumns = tableColumns(db, "sessions");
        if (setTableColumns.isEmpty()) {
            db.execSQL(CREATE_SESSIONS);
            return;
        }
        db.execSQL("ALTER TABLE `sessions` RENAME TO `sessions_legacy`");
        db.execSQL(CREATE_SESSIONS);
        db.execSQL(sessionCopySql$app_debug(setTableColumns));
        db.execSQL("DROP TABLE `sessions_legacy`");
    }

    private final void migrateMessages(SupportSQLiteDatabase db) {
        Set<String> setTableColumns = tableColumns(db, "messages");
        if (setTableColumns.isEmpty()) {
            db.execSQL(CREATE_MESSAGES);
            return;
        }
        db.execSQL("ALTER TABLE `messages` RENAME TO `messages_legacy`");
        db.execSQL(CREATE_MESSAGES);
        db.execSQL(messageCopySql$app_debug(setTableColumns));
        db.execSQL("DROP TABLE `messages_legacy`");
    }

    public final String sessionCopySql$app_debug(Set<String> columns) {
        Intrinsics.checkNotNullParameter(columns, "columns");
        return StringsKt.trimIndent("\n        INSERT INTO `sessions` (\n            `id`, `ownerKey`, `title`, `source`, `model`, `messageCount`, `lastActiveMs`, `preview`\n        ) SELECT\n            " + value(columns, "id", "''") + ",\n            " + value(columns, "ownerKey", "''") + ",\n            " + value(columns, "title", "''") + ",\n            " + value(columns, "source", value(columns, "profileId", "NULL")) + ",\n            " + value(columns, "model", "NULL") + ",\n            " + value(columns, "messageCount", "0") + ",\n            " + value(columns, "lastActiveMs", value(columns, "lastModified", "0")) + ",\n            " + value(columns, "preview", "NULL") + "\n        FROM `sessions_legacy`\n    ");
    }

    public final String messageCopySql$app_debug(Set<String> columns) {
        Intrinsics.checkNotNullParameter(columns, "columns");
        return StringsKt.trimIndent("\n        INSERT INTO `messages` (\n            `id`, `sessionId`, `ownerKey`, `role`, `content`, `timestamp`, `deliveryStatus`, `clientMsgId`\n        ) SELECT\n            " + value(columns, "id", "''") + ",\n            " + value(columns, "sessionId", "''") + ",\n            " + value(columns, "ownerKey", "''") + ",\n            " + value(columns, "role", "''") + ",\n            " + value(columns, "content", "''") + ",\n            " + value(columns, "timestamp", "0") + ",\n            " + value(columns, "deliveryStatus", "'sent'") + ",\n            " + value(columns, "clientMsgId", "NULL") + "\n        FROM `messages_legacy`\n    ");
    }

    private final String value(Set<String> columns, String name, String fallback) {
        return columns.contains(name) ? "`" + name + "`" : fallback;
    }

    private final Set<String> tableColumns(SupportSQLiteDatabase db, String table) {
        Cursor cursorQuery = db.query("PRAGMA table_info(`" + table + "`)");
        try {
            Cursor cursor = cursorQuery;
            int nameIndex = cursor.getColumnIndex(HintConstants.AUTOFILL_HINT_NAME);
            Set $this$tableColumns_u24lambda_u241_u24lambda_u240 = SetsKt.createSetBuilder();
            if (nameIndex >= 0) {
                while (cursor.moveToNext()) {
                    String string = cursor.getString(nameIndex);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    $this$tableColumns_u24lambda_u241_u24lambda_u240.add(string);
                }
            }
            Set<String> setBuild = SetsKt.build($this$tableColumns_u24lambda_u241_u24lambda_u240);
            CloseableKt.closeFinally(cursorQuery, null);
            return setBuild;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorQuery, th);
                throw th2;
            }
        }
    }
}
