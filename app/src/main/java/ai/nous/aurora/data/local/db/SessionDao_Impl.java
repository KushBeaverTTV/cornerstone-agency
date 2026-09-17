package ai.nous.aurora.data.local.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomDatabaseKt;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: loaded from: classes12.dex */
public final class SessionDao_Impl implements SessionDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<SessionEntity> __insertionAdapterOfSessionEntity;
    private final SharedSQLiteStatement __preparedStmtOfAdoptOwnerless;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final SharedSQLiteStatement __preparedStmtOfDeleteById;
    private final SharedSQLiteStatement __preparedStmtOfDeleteForOwner;
    private final EntityDeletionOrUpdateAdapter<SessionEntity> __updateAdapterOfSessionEntity;

    public SessionDao_Impl(final RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfSessionEntity = new EntityInsertionAdapter<SessionEntity>(__db) { // from class: ai.nous.aurora.data.local.db.SessionDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `sessions` (`id`,`ownerKey`,`title`,`source`,`model`,`messageCount`,`lastActiveMs`,`preview`) VALUES (?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(final SupportSQLiteStatement statement, final SessionEntity entity) {
                statement.bindString(1, entity.getId());
                statement.bindString(2, entity.getOwnerKey());
                statement.bindString(3, entity.getTitle());
                if (entity.getSource() == null) {
                    statement.bindNull(4);
                } else {
                    statement.bindString(4, entity.getSource());
                }
                if (entity.getModel() == null) {
                    statement.bindNull(5);
                } else {
                    statement.bindString(5, entity.getModel());
                }
                statement.bindLong(6, entity.getMessageCount());
                statement.bindLong(7, entity.getLastActiveMs());
                if (entity.getPreview() == null) {
                    statement.bindNull(8);
                } else {
                    statement.bindString(8, entity.getPreview());
                }
            }
        };
        this.__updateAdapterOfSessionEntity = new EntityDeletionOrUpdateAdapter<SessionEntity>(__db) { // from class: ai.nous.aurora.data.local.db.SessionDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "UPDATE OR ABORT `sessions` SET `id` = ?,`ownerKey` = ?,`title` = ?,`source` = ?,`model` = ?,`messageCount` = ?,`lastActiveMs` = ?,`preview` = ? WHERE `id` = ? AND `ownerKey` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(final SupportSQLiteStatement statement, final SessionEntity entity) {
                statement.bindString(1, entity.getId());
                statement.bindString(2, entity.getOwnerKey());
                statement.bindString(3, entity.getTitle());
                if (entity.getSource() == null) {
                    statement.bindNull(4);
                } else {
                    statement.bindString(4, entity.getSource());
                }
                if (entity.getModel() == null) {
                    statement.bindNull(5);
                } else {
                    statement.bindString(5, entity.getModel());
                }
                statement.bindLong(6, entity.getMessageCount());
                statement.bindLong(7, entity.getLastActiveMs());
                if (entity.getPreview() == null) {
                    statement.bindNull(8);
                } else {
                    statement.bindString(8, entity.getPreview());
                }
                statement.bindString(9, entity.getId());
                statement.bindString(10, entity.getOwnerKey());
            }
        };
        this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) { // from class: ai.nous.aurora.data.local.db.SessionDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM sessions WHERE id = ? AND ownerKey = ?";
            }
        };
        this.__preparedStmtOfDeleteForOwner = new SharedSQLiteStatement(__db) { // from class: ai.nous.aurora.data.local.db.SessionDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM sessions WHERE ownerKey = ?";
            }
        };
        this.__preparedStmtOfAdoptOwnerless = new SharedSQLiteStatement(__db) { // from class: ai.nous.aurora.data.local.db.SessionDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE sessions SET ownerKey = ? WHERE ownerKey = '' OR ownerKey IS NULL";
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: ai.nous.aurora.data.local.db.SessionDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM sessions";
            }
        };
    }

    @Override // ai.nous.aurora.data.local.db.SessionDao
    public Object insert(final SessionEntity session, final Continuation $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: ai.nous.aurora.data.local.db.SessionDao_Impl.7
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SessionDao_Impl.this.__db.beginTransaction();
                try {
                    SessionDao_Impl.this.__insertionAdapterOfSessionEntity.insert(session);
                    SessionDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    SessionDao_Impl.this.__db.endTransaction();
                }
            }
        }, $completion);
    }

    @Override // ai.nous.aurora.data.local.db.SessionDao
    public Object insertAll(final List<SessionEntity> sessions, final Continuation $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: ai.nous.aurora.data.local.db.SessionDao_Impl.8
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SessionDao_Impl.this.__db.beginTransaction();
                try {
                    SessionDao_Impl.this.__insertionAdapterOfSessionEntity.insert((Iterable) sessions);
                    SessionDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    SessionDao_Impl.this.__db.endTransaction();
                }
            }
        }, $completion);
    }

    @Override // ai.nous.aurora.data.local.db.SessionDao
    public Object update(final SessionEntity session, final Continuation $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: ai.nous.aurora.data.local.db.SessionDao_Impl.9
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SessionDao_Impl.this.__db.beginTransaction();
                try {
                    SessionDao_Impl.this.__updateAdapterOfSessionEntity.handle(session);
                    SessionDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    SessionDao_Impl.this.__db.endTransaction();
                }
            }
        }, $completion);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$replaceForOwner$0(String ownerKey, List rows, Continuation __cont) {
        return SessionDao.DefaultImpls.replaceForOwner(this, ownerKey, rows, __cont);
    }

    @Override // ai.nous.aurora.data.local.db.SessionDao
    public Object replaceForOwner(final String ownerKey, final List<SessionEntity> rows, final Continuation $completion) {
        return RoomDatabaseKt.withTransaction(this.__db, new Function1() { // from class: ai.nous.aurora.data.local.db.SessionDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Object f$0 = null;
                Object f$0 = null;
                return SessionDao_Impl.this.lambda$replaceForOwner$0(ownerKey, rows, (Continuation) obj);
            }
        }, $completion);
    }

    @Override // ai.nous.aurora.data.local.db.SessionDao
    public Object deleteById(final String id, final String ownerKey, final Continuation $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: ai.nous.aurora.data.local.db.SessionDao_Impl.10
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement _stmt = SessionDao_Impl.this.__preparedStmtOfDeleteById.acquire();
                _stmt.bindString(1, id);
                _stmt.bindString(2, ownerKey);
                try {
                    SessionDao_Impl.this.__db.beginTransaction();
                    try {
                        _stmt.executeUpdateDelete();
                        SessionDao_Impl.this.__db.setTransactionSuccessful();
                        Unit unit = Unit.INSTANCE;
                        SessionDao_Impl.this.__db.endTransaction();
                        SessionDao_Impl.this.__preparedStmtOfDeleteById.release(_stmt);
                        return unit;
                    } catch (Throwable th) {
                        SessionDao_Impl.this.__db.endTransaction();
                        throw th;
                    }
                } catch (Throwable th2) {
                    SessionDao_Impl.this.__preparedStmtOfDeleteById.release(_stmt);
                    throw th2;
                }
            }
        }, $completion);
    }

    @Override // ai.nous.aurora.data.local.db.SessionDao
    public Object deleteForOwner(final String ownerKey, final Continuation $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: ai.nous.aurora.data.local.db.SessionDao_Impl.11
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement _stmt = SessionDao_Impl.this.__preparedStmtOfDeleteForOwner.acquire();
                _stmt.bindString(1, ownerKey);
                try {
                    SessionDao_Impl.this.__db.beginTransaction();
                    try {
                        _stmt.executeUpdateDelete();
                        SessionDao_Impl.this.__db.setTransactionSuccessful();
                        Unit unit = Unit.INSTANCE;
                        SessionDao_Impl.this.__db.endTransaction();
                        SessionDao_Impl.this.__preparedStmtOfDeleteForOwner.release(_stmt);
                        return unit;
                    } catch (Throwable th) {
                        SessionDao_Impl.this.__db.endTransaction();
                        throw th;
                    }
                } catch (Throwable th2) {
                    SessionDao_Impl.this.__preparedStmtOfDeleteForOwner.release(_stmt);
                    throw th2;
                }
            }
        }, $completion);
    }

    @Override // ai.nous.aurora.data.local.db.SessionDao
    public Object adoptOwnerless(final String canonicalOwner, final Continuation $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Integer>() { // from class: ai.nous.aurora.data.local.db.SessionDao_Impl.12
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Integer call() throws Exception {
                SupportSQLiteStatement _stmt = SessionDao_Impl.this.__preparedStmtOfAdoptOwnerless.acquire();
                _stmt.bindString(1, canonicalOwner);
                try {
                    SessionDao_Impl.this.__db.beginTransaction();
                    try {
                        Integer _result = Integer.valueOf(_stmt.executeUpdateDelete());
                        SessionDao_Impl.this.__db.setTransactionSuccessful();
                        SessionDao_Impl.this.__db.endTransaction();
                        SessionDao_Impl.this.__preparedStmtOfAdoptOwnerless.release(_stmt);
                        return _result;
                    } catch (Throwable th) {
                        SessionDao_Impl.this.__db.endTransaction();
                        throw th;
                    }
                } catch (Throwable th2) {
                    SessionDao_Impl.this.__preparedStmtOfAdoptOwnerless.release(_stmt);
                    throw th2;
                }
            }
        }, $completion);
    }

    @Override // ai.nous.aurora.data.local.db.SessionDao
    public Object deleteAll(final Continuation $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: ai.nous.aurora.data.local.db.SessionDao_Impl.13
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement _stmt = SessionDao_Impl.this.__preparedStmtOfDeleteAll.acquire();
                try {
                    SessionDao_Impl.this.__db.beginTransaction();
                    try {
                        _stmt.executeUpdateDelete();
                        SessionDao_Impl.this.__db.setTransactionSuccessful();
                        Unit unit = Unit.INSTANCE;
                        SessionDao_Impl.this.__db.endTransaction();
                        SessionDao_Impl.this.__preparedStmtOfDeleteAll.release(_stmt);
                        return unit;
                    } catch (Throwable th) {
                        SessionDao_Impl.this.__db.endTransaction();
                        throw th;
                    }
                } catch (Throwable th2) {
                    SessionDao_Impl.this.__preparedStmtOfDeleteAll.release(_stmt);
                    throw th2;
                }
            }
        }, $completion);
    }

    @Override // ai.nous.aurora.data.local.db.SessionDao
    public Flow<List<SessionEntity>> observeSessions(final String ownerKey) {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM sessions WHERE ownerKey = ? ORDER BY lastActiveMs DESC", 1);
        _statement.bindString(1, ownerKey);
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"sessions"}, new Callable<List<SessionEntity>>() { // from class: ai.nous.aurora.data.local.db.SessionDao_Impl.14
            @Override // java.util.concurrent.Callable
            public List<SessionEntity> call() throws Exception {
                String _tmpSource;
                String _tmpModel;
                String _tmpPreview;
                Cursor _cursor = DBUtil.query(SessionDao_Impl.this.__db, _statement, false, null);
                try {
                    int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                    int _cursorIndexOfOwnerKey = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerKey");
                    int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
                    int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
                    int _cursorIndexOfModel = CursorUtil.getColumnIndexOrThrow(_cursor, "model");
                    int _cursorIndexOfMessageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "messageCount");
                    int _cursorIndexOfLastActiveMs = CursorUtil.getColumnIndexOrThrow(_cursor, "lastActiveMs");
                    int _cursorIndexOfPreview = CursorUtil.getColumnIndexOrThrow(_cursor, "preview");
                    List<SessionEntity> _result = new ArrayList<>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpOwnerKey = _cursor.getString(_cursorIndexOfOwnerKey);
                        String _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
                        if (_cursor.isNull(_cursorIndexOfSource)) {
                            _tmpSource = null;
                        } else {
                            _tmpSource = _cursor.getString(_cursorIndexOfSource);
                        }
                        if (_cursor.isNull(_cursorIndexOfModel)) {
                            _tmpModel = null;
                        } else {
                            String _tmpModel2 = _cursor.getString(_cursorIndexOfModel);
                            _tmpModel = _tmpModel2;
                        }
                        int _tmpMessageCount = _cursor.getInt(_cursorIndexOfMessageCount);
                        long _tmpLastActiveMs = _cursor.getLong(_cursorIndexOfLastActiveMs);
                        if (_cursor.isNull(_cursorIndexOfPreview)) {
                            _tmpPreview = null;
                        } else {
                            String _tmpPreview2 = _cursor.getString(_cursorIndexOfPreview);
                            _tmpPreview = _tmpPreview2;
                        }
                        SessionEntity _item = new SessionEntity(_tmpId, _tmpOwnerKey, _tmpTitle, _tmpSource, _tmpModel, _tmpMessageCount, _tmpLastActiveMs, _tmpPreview);
                        _result.add(_item);
                    }
                    return _result;
                } finally {
                    _cursor.close();
                }
            }

            protected void finalize() {
                _statement.release();
            }
        });
    }

    @Override // ai.nous.aurora.data.local.db.SessionDao
    public Object getAll(final String ownerKey, final Continuation $completion) {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM sessions WHERE ownerKey = ? ORDER BY lastActiveMs DESC", 1);
        _statement.bindString(1, ownerKey);
        CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
        return CoroutinesRoom.execute(this.__db, false, _cancellationSignal, new Callable<List<SessionEntity>>() { // from class: ai.nous.aurora.data.local.db.SessionDao_Impl.15
            @Override // java.util.concurrent.Callable
            public List<SessionEntity> call() throws Exception {
                String _tmpSource;
                String _tmpModel;
                String _tmpPreview;
                Cursor _cursor = DBUtil.query(SessionDao_Impl.this.__db, _statement, false, null);
                try {
                    int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                    int _cursorIndexOfOwnerKey = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerKey");
                    int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
                    int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
                    int _cursorIndexOfModel = CursorUtil.getColumnIndexOrThrow(_cursor, "model");
                    int _cursorIndexOfMessageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "messageCount");
                    int _cursorIndexOfLastActiveMs = CursorUtil.getColumnIndexOrThrow(_cursor, "lastActiveMs");
                    int _cursorIndexOfPreview = CursorUtil.getColumnIndexOrThrow(_cursor, "preview");
                    List<SessionEntity> _result = new ArrayList<>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpOwnerKey = _cursor.getString(_cursorIndexOfOwnerKey);
                        String _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
                        if (_cursor.isNull(_cursorIndexOfSource)) {
                            _tmpSource = null;
                        } else {
                            _tmpSource = _cursor.getString(_cursorIndexOfSource);
                        }
                        if (_cursor.isNull(_cursorIndexOfModel)) {
                            _tmpModel = null;
                        } else {
                            String _tmpModel2 = _cursor.getString(_cursorIndexOfModel);
                            _tmpModel = _tmpModel2;
                        }
                        int _tmpMessageCount = _cursor.getInt(_cursorIndexOfMessageCount);
                        long _tmpLastActiveMs = _cursor.getLong(_cursorIndexOfLastActiveMs);
                        if (_cursor.isNull(_cursorIndexOfPreview)) {
                            _tmpPreview = null;
                        } else {
                            String _tmpPreview2 = _cursor.getString(_cursorIndexOfPreview);
                            _tmpPreview = _tmpPreview2;
                        }
                        SessionEntity _item = new SessionEntity(_tmpId, _tmpOwnerKey, _tmpTitle, _tmpSource, _tmpModel, _tmpMessageCount, _tmpLastActiveMs, _tmpPreview);
                        _result.add(_item);
                    }
                    return _result;
                } finally {
                    _cursor.close();
                    _statement.release();
                }
            }
        }, $completion);
    }

    @Override // ai.nous.aurora.data.local.db.SessionDao
    public Object getById(final String id, final String ownerKey, final Continuation $completion) {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM sessions WHERE id = ? AND ownerKey = ? LIMIT 1", 2);
        _statement.bindString(1, id);
        _statement.bindString(2, ownerKey);
        CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
        return CoroutinesRoom.execute(this.__db, false, _cancellationSignal, new Callable<SessionEntity>() { // from class: ai.nous.aurora.data.local.db.SessionDao_Impl.16
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public SessionEntity call() throws Exception {
                SessionEntity _result;
                String _tmpSource;
                String _tmpModel;
                String _tmpPreview;
                Cursor _cursor = DBUtil.query(SessionDao_Impl.this.__db, _statement, false, null);
                try {
                    int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                    int _cursorIndexOfOwnerKey = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerKey");
                    int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
                    int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
                    int _cursorIndexOfModel = CursorUtil.getColumnIndexOrThrow(_cursor, "model");
                    int _cursorIndexOfMessageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "messageCount");
                    int _cursorIndexOfLastActiveMs = CursorUtil.getColumnIndexOrThrow(_cursor, "lastActiveMs");
                    int _cursorIndexOfPreview = CursorUtil.getColumnIndexOrThrow(_cursor, "preview");
                    if (_cursor.moveToFirst()) {
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpOwnerKey = _cursor.getString(_cursorIndexOfOwnerKey);
                        String _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
                        if (_cursor.isNull(_cursorIndexOfSource)) {
                            _tmpSource = null;
                        } else {
                            _tmpSource = _cursor.getString(_cursorIndexOfSource);
                        }
                        if (_cursor.isNull(_cursorIndexOfModel)) {
                            _tmpModel = null;
                        } else {
                            String _tmpModel2 = _cursor.getString(_cursorIndexOfModel);
                            _tmpModel = _tmpModel2;
                        }
                        int _tmpMessageCount = _cursor.getInt(_cursorIndexOfMessageCount);
                        long _tmpLastActiveMs = _cursor.getLong(_cursorIndexOfLastActiveMs);
                        if (_cursor.isNull(_cursorIndexOfPreview)) {
                            _tmpPreview = null;
                        } else {
                            String _tmpPreview2 = _cursor.getString(_cursorIndexOfPreview);
                            _tmpPreview = _tmpPreview2;
                        }
                        _result = new SessionEntity(_tmpId, _tmpOwnerKey, _tmpTitle, _tmpSource, _tmpModel, _tmpMessageCount, _tmpLastActiveMs, _tmpPreview);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _cursor.close();
                    _statement.release();
                }
            }
        }, $completion);
    }

    @Override // ai.nous.aurora.data.local.db.SessionDao
    public Object listOwnerless(final Continuation $completion) {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM sessions WHERE ownerKey = '' OR ownerKey IS NULL", 0);
        CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
        return CoroutinesRoom.execute(this.__db, false, _cancellationSignal, new Callable<List<SessionEntity>>() { // from class: ai.nous.aurora.data.local.db.SessionDao_Impl.17
            @Override // java.util.concurrent.Callable
            public List<SessionEntity> call() throws Exception {
                String _tmpSource;
                String _tmpModel;
                String _tmpPreview;
                Cursor _cursor = DBUtil.query(SessionDao_Impl.this.__db, _statement, false, null);
                try {
                    int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                    int _cursorIndexOfOwnerKey = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerKey");
                    int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
                    int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
                    int _cursorIndexOfModel = CursorUtil.getColumnIndexOrThrow(_cursor, "model");
                    int _cursorIndexOfMessageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "messageCount");
                    int _cursorIndexOfLastActiveMs = CursorUtil.getColumnIndexOrThrow(_cursor, "lastActiveMs");
                    int _cursorIndexOfPreview = CursorUtil.getColumnIndexOrThrow(_cursor, "preview");
                    List<SessionEntity> _result = new ArrayList<>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpOwnerKey = _cursor.getString(_cursorIndexOfOwnerKey);
                        String _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
                        if (_cursor.isNull(_cursorIndexOfSource)) {
                            _tmpSource = null;
                        } else {
                            _tmpSource = _cursor.getString(_cursorIndexOfSource);
                        }
                        if (_cursor.isNull(_cursorIndexOfModel)) {
                            _tmpModel = null;
                        } else {
                            String _tmpModel2 = _cursor.getString(_cursorIndexOfModel);
                            _tmpModel = _tmpModel2;
                        }
                        int _tmpMessageCount = _cursor.getInt(_cursorIndexOfMessageCount);
                        long _tmpLastActiveMs = _cursor.getLong(_cursorIndexOfLastActiveMs);
                        if (_cursor.isNull(_cursorIndexOfPreview)) {
                            _tmpPreview = null;
                        } else {
                            String _tmpPreview2 = _cursor.getString(_cursorIndexOfPreview);
                            _tmpPreview = _tmpPreview2;
                        }
                        SessionEntity _item = new SessionEntity(_tmpId, _tmpOwnerKey, _tmpTitle, _tmpSource, _tmpModel, _tmpMessageCount, _tmpLastActiveMs, _tmpPreview);
                        _result.add(_item);
                    }
                    return _result;
                } finally {
                    _cursor.close();
                    _statement.release();
                }
            }
        }, $completion);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
