package ai.nous.aurora.data.local.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes12.dex */
public final class AuroraDatabase_Impl extends AuroraDatabase {
    private volatile MessageDao _messageDao;
    private volatile SessionDao _sessionDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(final DatabaseConfiguration config) {
        SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(5) { // from class: ai.nous.aurora.data.local.db.AuroraDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(final SupportSQLiteDatabase db) {
                db.execSQL("CREATE TABLE IF NOT EXISTS `sessions` (`id` TEXT NOT NULL, `ownerKey` TEXT NOT NULL, `title` TEXT NOT NULL, `source` TEXT, `model` TEXT, `messageCount` INTEGER NOT NULL, `lastActiveMs` INTEGER NOT NULL, `preview` TEXT, PRIMARY KEY(`id`, `ownerKey`))");
                db.execSQL("CREATE TABLE IF NOT EXISTS `messages` (`id` TEXT NOT NULL, `sessionId` TEXT NOT NULL, `ownerKey` TEXT NOT NULL, `role` TEXT NOT NULL, `content` TEXT NOT NULL, `timestamp` INTEGER NOT NULL, `deliveryStatus` TEXT NOT NULL, `clientMsgId` TEXT, PRIMARY KEY(`id`, `ownerKey`))");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_messages_sessionId_ownerKey` ON `messages` (`sessionId`, `ownerKey`)");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_messages_ownerKey` ON `messages` (`ownerKey`)");
                db.execSQL("CREATE INDEX IF NOT EXISTS `index_messages_deliveryStatus` ON `messages` (`deliveryStatus`)");
                db.execSQL(RoomMasterTable.CREATE_QUERY);
                db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '9456717d449d0242a8a43dff818b21ff')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(final SupportSQLiteDatabase db) {
                db.execSQL("DROP TABLE IF EXISTS `sessions`");
                db.execSQL("DROP TABLE IF EXISTS `messages`");
                List<? extends RoomDatabase.Callback> _callbacks = AuroraDatabase_Impl.this.mCallbacks;
                if (_callbacks != null) {
                    for (RoomDatabase.Callback _callback : _callbacks) {
                        _callback.onDestructiveMigration(db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onCreate(final SupportSQLiteDatabase db) {
                List<? extends RoomDatabase.Callback> _callbacks = AuroraDatabase_Impl.this.mCallbacks;
                if (_callbacks != null) {
                    for (RoomDatabase.Callback _callback : _callbacks) {
                        _callback.onCreate(db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(final SupportSQLiteDatabase db) {
                AuroraDatabase_Impl.this.mDatabase = db;
                AuroraDatabase_Impl.this.internalInitInvalidationTracker(db);
                List<? extends RoomDatabase.Callback> _callbacks = AuroraDatabase_Impl.this.mCallbacks;
                if (_callbacks != null) {
                    for (RoomDatabase.Callback _callback : _callbacks) {
                        _callback.onOpen(db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(final SupportSQLiteDatabase db) {
                DBUtil.dropFtsSyncTriggers(db);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(final SupportSQLiteDatabase db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public RoomOpenHelper.ValidationResult onValidateSchema(final SupportSQLiteDatabase db) {
                HashMap<String, TableInfo.Column> _columnsSessions = new HashMap<>(8);
                _columnsSessions.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, 1));
                _columnsSessions.put("ownerKey", new TableInfo.Column("ownerKey", "TEXT", true, 2, null, 1));
                _columnsSessions.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, 1));
                _columnsSessions.put("source", new TableInfo.Column("source", "TEXT", false, 0, null, 1));
                _columnsSessions.put("model", new TableInfo.Column("model", "TEXT", false, 0, null, 1));
                _columnsSessions.put("messageCount", new TableInfo.Column("messageCount", "INTEGER", true, 0, null, 1));
                _columnsSessions.put("lastActiveMs", new TableInfo.Column("lastActiveMs", "INTEGER", true, 0, null, 1));
                _columnsSessions.put("preview", new TableInfo.Column("preview", "TEXT", false, 0, null, 1));
                HashSet<TableInfo.ForeignKey> _foreignKeysSessions = new HashSet<>(0);
                HashSet<TableInfo.Index> _indicesSessions = new HashSet<>(0);
                TableInfo _infoSessions = new TableInfo("sessions", _columnsSessions, _foreignKeysSessions, _indicesSessions);
                TableInfo _existingSessions = TableInfo.read(db, "sessions");
                if (!_infoSessions.equals(_existingSessions)) {
                    return new RoomOpenHelper.ValidationResult(false, "sessions(ai.nous.aurora.data.local.db.SessionEntity).\n Expected:\n" + _infoSessions + "\n Found:\n" + _existingSessions);
                }
                HashMap<String, TableInfo.Column> _columnsMessages = new HashMap<>(8);
                _columnsMessages.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, 1));
                _columnsMessages.put("sessionId", new TableInfo.Column("sessionId", "TEXT", true, 0, null, 1));
                _columnsMessages.put("ownerKey", new TableInfo.Column("ownerKey", "TEXT", true, 2, null, 1));
                _columnsMessages.put("role", new TableInfo.Column("role", "TEXT", true, 0, null, 1));
                _columnsMessages.put("content", new TableInfo.Column("content", "TEXT", true, 0, null, 1));
                _columnsMessages.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, 1));
                _columnsMessages.put("deliveryStatus", new TableInfo.Column("deliveryStatus", "TEXT", true, 0, null, 1));
                _columnsMessages.put("clientMsgId", new TableInfo.Column("clientMsgId", "TEXT", false, 0, null, 1));
                HashSet<TableInfo.ForeignKey> _foreignKeysMessages = new HashSet<>(0);
                HashSet<TableInfo.Index> _indicesMessages = new HashSet<>(3);
                _indicesMessages.add(new TableInfo.Index("index_messages_sessionId_ownerKey", false, Arrays.asList("sessionId", "ownerKey"), Arrays.asList("ASC", "ASC")));
                _indicesMessages.add(new TableInfo.Index("index_messages_ownerKey", false, Arrays.asList("ownerKey"), Arrays.asList("ASC")));
                _indicesMessages.add(new TableInfo.Index("index_messages_deliveryStatus", false, Arrays.asList("deliveryStatus"), Arrays.asList("ASC")));
                TableInfo _infoMessages = new TableInfo("messages", _columnsMessages, _foreignKeysMessages, _indicesMessages);
                TableInfo _existingMessages = TableInfo.read(db, "messages");
                if (!_infoMessages.equals(_existingMessages)) {
                    return new RoomOpenHelper.ValidationResult(false, "messages(ai.nous.aurora.data.local.db.MessageEntity).\n Expected:\n" + _infoMessages + "\n Found:\n" + _existingMessages);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "9456717d449d0242a8a43dff818b21ff", "fdf395823c52406e5d4db505ad1f24f6");
        SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
        SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
        return _helper;
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        HashMap<String, String> _shadowTablesMap = new HashMap<>(0);
        HashMap<String, Set<String>> _viewTables = new HashMap<>(0);
        return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "sessions", "messages");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            _db.execSQL("DELETE FROM `sessions`");
            _db.execSQL("DELETE FROM `messages`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            _db.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!_db.inTransaction()) {
                _db.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<>();
        _typeConvertersMap.put(SessionDao.class, SessionDao_Impl.getRequiredConverters());
        _typeConvertersMap.put(MessageDao.class, MessageDao_Impl.getRequiredConverters());
        return _typeConvertersMap;
    }

    @Override // androidx.room.RoomDatabase
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<>();
        return _autoMigrationSpecsSet;
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> getAutoMigrations(final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
        List<Migration> _autoMigrations = new ArrayList<>();
        return _autoMigrations;
    }

    @Override // ai.nous.aurora.data.local.db.AuroraDatabase
    public SessionDao sessionDao() {
        SessionDao sessionDao;
        if (this._sessionDao != null) {
            return this._sessionDao;
        }
        synchronized (this) {
            if (this._sessionDao == null) {
                this._sessionDao = new SessionDao_Impl(this);
            }
            sessionDao = this._sessionDao;
        }
        return sessionDao;
    }

    @Override // ai.nous.aurora.data.local.db.AuroraDatabase
    public MessageDao messageDao() {
        MessageDao messageDao;
        if (this._messageDao != null) {
            return this._messageDao;
        }
        synchronized (this) {
            if (this._messageDao == null) {
                this._messageDao = new MessageDao_Impl(this);
            }
            messageDao = this._messageDao;
        }
        return messageDao;
    }
}
