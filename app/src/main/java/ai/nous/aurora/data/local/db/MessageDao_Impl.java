package ai.nous.aurora.data.local.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
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
public final class MessageDao_Impl implements MessageDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<MessageEntity> __insertionAdapterOfMessageEntity;
    private final SharedSQLiteStatement __preparedStmtOfAdoptOwnerless;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final SharedSQLiteStatement __preparedStmtOfDeleteById;
    private final SharedSQLiteStatement __preparedStmtOfDeleteForOwner;
    private final SharedSQLiteStatement __preparedStmtOfDeleteForSession;
    private final SharedSQLiteStatement __preparedStmtOfFailAllPending;
    private final SharedSQLiteStatement __preparedStmtOfFailAllPendingForOwner;
    private final SharedSQLiteStatement __preparedStmtOfFailInterruptedForOwner;
    private final SharedSQLiteStatement __preparedStmtOfSetDeliveryStatus;
    private final SharedSQLiteStatement __preparedStmtOfUpdateStatusIf;

    public MessageDao_Impl(final RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfMessageEntity = new EntityInsertionAdapter<MessageEntity>(__db) { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `messages` (`id`,`sessionId`,`ownerKey`,`role`,`content`,`timestamp`,`deliveryStatus`,`clientMsgId`) VALUES (?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(final SupportSQLiteStatement statement, final MessageEntity entity) {
                statement.bindString(1, entity.getId());
                statement.bindString(2, entity.getSessionId());
                statement.bindString(3, entity.getOwnerKey());
                statement.bindString(4, entity.getRole());
                statement.bindString(5, entity.getContent());
                statement.bindLong(6, entity.getTimestamp());
                statement.bindString(7, entity.getDeliveryStatus());
                if (entity.getClientMsgId() == null) {
                    statement.bindNull(8);
                } else {
                    statement.bindString(8, entity.getClientMsgId());
                }
            }
        };
        this.__preparedStmtOfDeleteForSession = new SharedSQLiteStatement(__db) { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM messages WHERE sessionId = ? AND ownerKey = ?";
            }
        };
        this.__preparedStmtOfSetDeliveryStatus = new SharedSQLiteStatement(__db) { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE messages SET deliveryStatus = ? WHERE id = ? AND ownerKey = ?";
            }
        };
        this.__preparedStmtOfUpdateStatusIf = new SharedSQLiteStatement(__db) { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE messages SET deliveryStatus = ? WHERE id = ? AND sessionId = ? AND ownerKey = ? AND deliveryStatus = ?";
            }
        };
        this.__preparedStmtOfFailAllPending = new SharedSQLiteStatement(__db) { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE messages SET deliveryStatus = 'failed' WHERE sessionId = ? AND ownerKey = ? AND deliveryStatus = 'pending'";
            }
        };
        this.__preparedStmtOfFailInterruptedForOwner = new SharedSQLiteStatement(__db) { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE messages SET deliveryStatus = 'failed' WHERE ownerKey = ? AND deliveryStatus IN ('pending', 'sending')";
            }
        };
        this.__preparedStmtOfFailAllPendingForOwner = new SharedSQLiteStatement(__db) { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.7
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE messages SET deliveryStatus = 'failed' WHERE ownerKey = ? AND deliveryStatus = 'pending'";
            }
        };
        this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.8
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM messages WHERE id = ? AND ownerKey = ?";
            }
        };
        this.__preparedStmtOfDeleteForOwner = new SharedSQLiteStatement(__db) { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.9
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM messages WHERE ownerKey = ?";
            }
        };
        this.__preparedStmtOfAdoptOwnerless = new SharedSQLiteStatement(__db) { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.10
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE messages SET ownerKey = ? WHERE ownerKey = '' OR ownerKey IS NULL";
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.11
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM messages";
            }
        };
    }

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Object insert(final MessageEntity message, final Continuation $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.12
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                MessageDao_Impl.this.__db.beginTransaction();
                try {
                    MessageDao_Impl.this.__insertionAdapterOfMessageEntity.insert(message);
                    MessageDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    MessageDao_Impl.this.__db.endTransaction();
                }
            }
        }, $completion);
    }

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Object insertAll(final List<MessageEntity> messages, final Continuation $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.13
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                MessageDao_Impl.this.__db.beginTransaction();
                try {
                    MessageDao_Impl.this.__insertionAdapterOfMessageEntity.insert((Iterable) messages);
                    MessageDao_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    MessageDao_Impl.this.__db.endTransaction();
                }
            }
        }, $completion);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$claimForRetry$0(String id, String sessionId, String ownerKey, Continuation __cont) {
        return MessageDao.DefaultImpls.claimForRetry(this, id, sessionId, ownerKey, __cont);
    }

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Object claimForRetry(final String id, final String sessionId, final String ownerKey, final Continuation $completion) {
        return RoomDatabaseKt.withTransaction(this.__db, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Object f$0 = null;
                return MessageDao_Impl.this.lambda$claimForRetry$0(id, sessionId, ownerKey, (Continuation) obj);
            }
        }, $completion);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$markSending$1(String id, String sessionId, String ownerKey, Continuation __cont) {
        return MessageDao.DefaultImpls.markSending(this, id, sessionId, ownerKey, __cont);
    }

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Object markSending(final String id, final String sessionId, final String ownerKey, final Continuation $completion) {
        return RoomDatabaseKt.withTransaction(this.__db, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MessageDao_Impl.this.lambda$markSending$1(id, sessionId, ownerKey, (Continuation) obj);
            }
        }, $completion);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object lambda$replaceForSession$2(String sessionId, String ownerKey, List rows, Continuation __cont) {
        return MessageDao.DefaultImpls.replaceForSession(this, sessionId, ownerKey, rows, __cont);
    }

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Object replaceForSession(final String sessionId, final String ownerKey, final List<MessageEntity> rows, final Continuation $completion) {
        return RoomDatabaseKt.withTransaction(this.__db, new Function1() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MessageDao_Impl.this.lambda$replaceForSession$2(sessionId, ownerKey, rows, (Continuation) obj);
            }
        }, $completion);
    }

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Object deleteForSession(final String sessionId, final String ownerKey, final Continuation $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.14
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement _stmt = MessageDao_Impl.this.__preparedStmtOfDeleteForSession.acquire();
                _stmt.bindString(1, sessionId);
                _stmt.bindString(2, ownerKey);
                try {
                    MessageDao_Impl.this.__db.beginTransaction();
                    try {
                        _stmt.executeUpdateDelete();
                        MessageDao_Impl.this.__db.setTransactionSuccessful();
                        Unit unit = Unit.INSTANCE;
                        MessageDao_Impl.this.__db.endTransaction();
                        MessageDao_Impl.this.__preparedStmtOfDeleteForSession.release(_stmt);
                        return unit;
                    } catch (Throwable th) {
                        MessageDao_Impl.this.__db.endTransaction();
                        throw th;
                    }
                } catch (Throwable th2) {
                    MessageDao_Impl.this.__preparedStmtOfDeleteForSession.release(_stmt);
                    throw th2;
                }
            }
        }, $completion);
    }

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Object setDeliveryStatus(final String id, final String ownerKey, final String status, final Continuation $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Integer>() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.15
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Integer call() throws Exception {
                SupportSQLiteStatement _stmt = MessageDao_Impl.this.__preparedStmtOfSetDeliveryStatus.acquire();
                _stmt.bindString(1, status);
                _stmt.bindString(2, id);
                _stmt.bindString(3, ownerKey);
                try {
                    MessageDao_Impl.this.__db.beginTransaction();
                    try {
                        Integer _result = Integer.valueOf(_stmt.executeUpdateDelete());
                        MessageDao_Impl.this.__db.setTransactionSuccessful();
                        MessageDao_Impl.this.__db.endTransaction();
                        MessageDao_Impl.this.__preparedStmtOfSetDeliveryStatus.release(_stmt);
                        return _result;
                    } catch (Throwable th) {
                        MessageDao_Impl.this.__db.endTransaction();
                        throw th;
                    }
                } catch (Throwable th2) {
                    MessageDao_Impl.this.__preparedStmtOfSetDeliveryStatus.release(_stmt);
                    throw th2;
                }
            }
        }, $completion);
    }

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Object updateStatusIf(final String id, final String sessionId, final String ownerKey, final String expectedStatus, final String newStatus, final Continuation $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Integer>() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.16
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Integer call() throws Exception {
                SupportSQLiteStatement _stmt = MessageDao_Impl.this.__preparedStmtOfUpdateStatusIf.acquire();
                _stmt.bindString(1, newStatus);
                _stmt.bindString(2, id);
                _stmt.bindString(3, sessionId);
                _stmt.bindString(4, ownerKey);
                _stmt.bindString(5, expectedStatus);
                try {
                    MessageDao_Impl.this.__db.beginTransaction();
                    try {
                        Integer _result = Integer.valueOf(_stmt.executeUpdateDelete());
                        MessageDao_Impl.this.__db.setTransactionSuccessful();
                        MessageDao_Impl.this.__db.endTransaction();
                        MessageDao_Impl.this.__preparedStmtOfUpdateStatusIf.release(_stmt);
                        return _result;
                    } catch (Throwable th) {
                        MessageDao_Impl.this.__db.endTransaction();
                        throw th;
                    }
                } catch (Throwable th2) {
                    MessageDao_Impl.this.__preparedStmtOfUpdateStatusIf.release(_stmt);
                    throw th2;
                }
            }
        }, $completion);
    }

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Object failAllPending(final String sessionId, final String ownerKey, final Continuation $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Integer>() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.17
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Integer call() throws Exception {
                SupportSQLiteStatement _stmt = MessageDao_Impl.this.__preparedStmtOfFailAllPending.acquire();
                _stmt.bindString(1, sessionId);
                _stmt.bindString(2, ownerKey);
                try {
                    MessageDao_Impl.this.__db.beginTransaction();
                    try {
                        Integer _result = Integer.valueOf(_stmt.executeUpdateDelete());
                        MessageDao_Impl.this.__db.setTransactionSuccessful();
                        MessageDao_Impl.this.__db.endTransaction();
                        MessageDao_Impl.this.__preparedStmtOfFailAllPending.release(_stmt);
                        return _result;
                    } catch (Throwable th) {
                        MessageDao_Impl.this.__db.endTransaction();
                        throw th;
                    }
                } catch (Throwable th2) {
                    MessageDao_Impl.this.__preparedStmtOfFailAllPending.release(_stmt);
                    throw th2;
                }
            }
        }, $completion);
    }

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Object failInterruptedForOwner(final String ownerKey, final Continuation $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Integer>() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.18
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Integer call() throws Exception {
                SupportSQLiteStatement _stmt = MessageDao_Impl.this.__preparedStmtOfFailInterruptedForOwner.acquire();
                _stmt.bindString(1, ownerKey);
                try {
                    MessageDao_Impl.this.__db.beginTransaction();
                    try {
                        Integer _result = Integer.valueOf(_stmt.executeUpdateDelete());
                        MessageDao_Impl.this.__db.setTransactionSuccessful();
                        MessageDao_Impl.this.__db.endTransaction();
                        MessageDao_Impl.this.__preparedStmtOfFailInterruptedForOwner.release(_stmt);
                        return _result;
                    } catch (Throwable th) {
                        MessageDao_Impl.this.__db.endTransaction();
                        throw th;
                    }
                } catch (Throwable th2) {
                    MessageDao_Impl.this.__preparedStmtOfFailInterruptedForOwner.release(_stmt);
                    throw th2;
                }
            }
        }, $completion);
    }

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Object failAllPendingForOwner(final String ownerKey, final Continuation $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Integer>() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.19
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Integer call() throws Exception {
                SupportSQLiteStatement _stmt = MessageDao_Impl.this.__preparedStmtOfFailAllPendingForOwner.acquire();
                _stmt.bindString(1, ownerKey);
                try {
                    MessageDao_Impl.this.__db.beginTransaction();
                    try {
                        Integer _result = Integer.valueOf(_stmt.executeUpdateDelete());
                        MessageDao_Impl.this.__db.setTransactionSuccessful();
                        MessageDao_Impl.this.__db.endTransaction();
                        MessageDao_Impl.this.__preparedStmtOfFailAllPendingForOwner.release(_stmt);
                        return _result;
                    } catch (Throwable th) {
                        MessageDao_Impl.this.__db.endTransaction();
                        throw th;
                    }
                } catch (Throwable th2) {
                    MessageDao_Impl.this.__preparedStmtOfFailAllPendingForOwner.release(_stmt);
                    throw th2;
                }
            }
        }, $completion);
    }

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Object deleteById(final String id, final String ownerKey, final Continuation $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.20
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement _stmt = MessageDao_Impl.this.__preparedStmtOfDeleteById.acquire();
                _stmt.bindString(1, id);
                _stmt.bindString(2, ownerKey);
                try {
                    MessageDao_Impl.this.__db.beginTransaction();
                    try {
                        _stmt.executeUpdateDelete();
                        MessageDao_Impl.this.__db.setTransactionSuccessful();
                        Unit unit = Unit.INSTANCE;
                        MessageDao_Impl.this.__db.endTransaction();
                        MessageDao_Impl.this.__preparedStmtOfDeleteById.release(_stmt);
                        return unit;
                    } catch (Throwable th) {
                        MessageDao_Impl.this.__db.endTransaction();
                        throw th;
                    }
                } catch (Throwable th2) {
                    MessageDao_Impl.this.__preparedStmtOfDeleteById.release(_stmt);
                    throw th2;
                }
            }
        }, $completion);
    }

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Object deleteForOwner(final String ownerKey, final Continuation $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.21
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement _stmt = MessageDao_Impl.this.__preparedStmtOfDeleteForOwner.acquire();
                _stmt.bindString(1, ownerKey);
                try {
                    MessageDao_Impl.this.__db.beginTransaction();
                    try {
                        _stmt.executeUpdateDelete();
                        MessageDao_Impl.this.__db.setTransactionSuccessful();
                        Unit unit = Unit.INSTANCE;
                        MessageDao_Impl.this.__db.endTransaction();
                        MessageDao_Impl.this.__preparedStmtOfDeleteForOwner.release(_stmt);
                        return unit;
                    } catch (Throwable th) {
                        MessageDao_Impl.this.__db.endTransaction();
                        throw th;
                    }
                } catch (Throwable th2) {
                    MessageDao_Impl.this.__preparedStmtOfDeleteForOwner.release(_stmt);
                    throw th2;
                }
            }
        }, $completion);
    }

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Object adoptOwnerless(final String canonicalOwner, final Continuation $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Integer>() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.22
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Integer call() throws Exception {
                SupportSQLiteStatement _stmt = MessageDao_Impl.this.__preparedStmtOfAdoptOwnerless.acquire();
                _stmt.bindString(1, canonicalOwner);
                try {
                    MessageDao_Impl.this.__db.beginTransaction();
                    try {
                        Integer _result = Integer.valueOf(_stmt.executeUpdateDelete());
                        MessageDao_Impl.this.__db.setTransactionSuccessful();
                        MessageDao_Impl.this.__db.endTransaction();
                        MessageDao_Impl.this.__preparedStmtOfAdoptOwnerless.release(_stmt);
                        return _result;
                    } catch (Throwable th) {
                        MessageDao_Impl.this.__db.endTransaction();
                        throw th;
                    }
                } catch (Throwable th2) {
                    MessageDao_Impl.this.__preparedStmtOfAdoptOwnerless.release(_stmt);
                    throw th2;
                }
            }
        }, $completion);
    }

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Object deleteAll(final Continuation $completion) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.23
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement _stmt = MessageDao_Impl.this.__preparedStmtOfDeleteAll.acquire();
                try {
                    MessageDao_Impl.this.__db.beginTransaction();
                    try {
                        _stmt.executeUpdateDelete();
                        MessageDao_Impl.this.__db.setTransactionSuccessful();
                        Unit unit = Unit.INSTANCE;
                        MessageDao_Impl.this.__db.endTransaction();
                        MessageDao_Impl.this.__preparedStmtOfDeleteAll.release(_stmt);
                        return unit;
                    } catch (Throwable th) {
                        MessageDao_Impl.this.__db.endTransaction();
                        throw th;
                    }
                } catch (Throwable th2) {
                    MessageDao_Impl.this.__preparedStmtOfDeleteAll.release(_stmt);
                    throw th2;
                }
            }
        }, $completion);
    }

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Flow<List<MessageEntity>> observeMessages(final String sessionId, final String ownerKey) {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM messages WHERE sessionId = ? AND ownerKey = ? ORDER BY timestamp ASC", 2);
        _statement.bindString(1, sessionId);
        _statement.bindString(2, ownerKey);
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"messages"}, new Callable<List<MessageEntity>>() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.24
            @Override // java.util.concurrent.Callable
            public List<MessageEntity> call() throws Exception {
                String _tmpClientMsgId;
                Cursor _cursor = DBUtil.query(MessageDao_Impl.this.__db, _statement, false, null);
                try {
                    int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                    int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
                    int _cursorIndexOfOwnerKey = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerKey");
                    int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
                    int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
                    int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
                    int _cursorIndexOfDeliveryStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryStatus");
                    int _cursorIndexOfClientMsgId = CursorUtil.getColumnIndexOrThrow(_cursor, "clientMsgId");
                    List<MessageEntity> _result = new ArrayList<>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
                        String _tmpOwnerKey = _cursor.getString(_cursorIndexOfOwnerKey);
                        String _tmpRole = _cursor.getString(_cursorIndexOfRole);
                        String _tmpContent = _cursor.getString(_cursorIndexOfContent);
                        long _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
                        String _tmpDeliveryStatus = _cursor.getString(_cursorIndexOfDeliveryStatus);
                        if (_cursor.isNull(_cursorIndexOfClientMsgId)) {
                            _tmpClientMsgId = null;
                        } else {
                            _tmpClientMsgId = _cursor.getString(_cursorIndexOfClientMsgId);
                        }
                        MessageEntity _item = new MessageEntity(_tmpId, _tmpSessionId, _tmpOwnerKey, _tmpRole, _tmpContent, _tmpTimestamp, _tmpDeliveryStatus, _tmpClientMsgId);
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

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Object listForSession(final String sessionId, final String ownerKey, final Continuation $completion) {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM messages WHERE sessionId = ? AND ownerKey = ? ORDER BY timestamp ASC", 2);
        _statement.bindString(1, sessionId);
        _statement.bindString(2, ownerKey);
        CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
        return CoroutinesRoom.execute(this.__db, false, _cancellationSignal, new Callable<List<MessageEntity>>() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.25
            @Override // java.util.concurrent.Callable
            public List<MessageEntity> call() throws Exception {
                String _tmpClientMsgId;
                Cursor _cursor = DBUtil.query(MessageDao_Impl.this.__db, _statement, false, null);
                try {
                    int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                    int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
                    int _cursorIndexOfOwnerKey = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerKey");
                    int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
                    int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
                    int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
                    int _cursorIndexOfDeliveryStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryStatus");
                    int _cursorIndexOfClientMsgId = CursorUtil.getColumnIndexOrThrow(_cursor, "clientMsgId");
                    List<MessageEntity> _result = new ArrayList<>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
                        String _tmpOwnerKey = _cursor.getString(_cursorIndexOfOwnerKey);
                        String _tmpRole = _cursor.getString(_cursorIndexOfRole);
                        String _tmpContent = _cursor.getString(_cursorIndexOfContent);
                        long _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
                        String _tmpDeliveryStatus = _cursor.getString(_cursorIndexOfDeliveryStatus);
                        if (_cursor.isNull(_cursorIndexOfClientMsgId)) {
                            _tmpClientMsgId = null;
                        } else {
                            _tmpClientMsgId = _cursor.getString(_cursorIndexOfClientMsgId);
                        }
                        MessageEntity _item = new MessageEntity(_tmpId, _tmpSessionId, _tmpOwnerKey, _tmpRole, _tmpContent, _tmpTimestamp, _tmpDeliveryStatus, _tmpClientMsgId);
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

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Object getById(final String id, final String ownerKey, final Continuation $completion) {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM messages WHERE id = ? AND ownerKey = ? LIMIT 1", 2);
        _statement.bindString(1, id);
        _statement.bindString(2, ownerKey);
        CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
        return CoroutinesRoom.execute(this.__db, false, _cancellationSignal, new Callable<MessageEntity>() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.26
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public MessageEntity call() throws Exception {
                MessageEntity _result;
                String _tmpClientMsgId;
                Cursor _cursor = DBUtil.query(MessageDao_Impl.this.__db, _statement, false, null);
                try {
                    int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                    int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
                    int _cursorIndexOfOwnerKey = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerKey");
                    int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
                    int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
                    int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
                    int _cursorIndexOfDeliveryStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryStatus");
                    int _cursorIndexOfClientMsgId = CursorUtil.getColumnIndexOrThrow(_cursor, "clientMsgId");
                    if (_cursor.moveToFirst()) {
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
                        String _tmpOwnerKey = _cursor.getString(_cursorIndexOfOwnerKey);
                        String _tmpRole = _cursor.getString(_cursorIndexOfRole);
                        String _tmpContent = _cursor.getString(_cursorIndexOfContent);
                        long _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
                        String _tmpDeliveryStatus = _cursor.getString(_cursorIndexOfDeliveryStatus);
                        if (_cursor.isNull(_cursorIndexOfClientMsgId)) {
                            _tmpClientMsgId = null;
                        } else {
                            _tmpClientMsgId = _cursor.getString(_cursorIndexOfClientMsgId);
                        }
                        _result = new MessageEntity(_tmpId, _tmpSessionId, _tmpOwnerKey, _tmpRole, _tmpContent, _tmpTimestamp, _tmpDeliveryStatus, _tmpClientMsgId);
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

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Object getByIdInSession(final String id, final String sessionId, final String ownerKey, final Continuation $completion) {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM messages WHERE id = ? AND sessionId = ? AND ownerKey = ? LIMIT 1", 3);
        _statement.bindString(1, id);
        _statement.bindString(2, sessionId);
        _statement.bindString(3, ownerKey);
        CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
        return CoroutinesRoom.execute(this.__db, false, _cancellationSignal, new Callable<MessageEntity>() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.27
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public MessageEntity call() throws Exception {
                MessageEntity _result;
                String _tmpClientMsgId;
                Cursor _cursor = DBUtil.query(MessageDao_Impl.this.__db, _statement, false, null);
                try {
                    int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                    int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
                    int _cursorIndexOfOwnerKey = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerKey");
                    int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
                    int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
                    int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
                    int _cursorIndexOfDeliveryStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryStatus");
                    int _cursorIndexOfClientMsgId = CursorUtil.getColumnIndexOrThrow(_cursor, "clientMsgId");
                    if (_cursor.moveToFirst()) {
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
                        String _tmpOwnerKey = _cursor.getString(_cursorIndexOfOwnerKey);
                        String _tmpRole = _cursor.getString(_cursorIndexOfRole);
                        String _tmpContent = _cursor.getString(_cursorIndexOfContent);
                        long _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
                        String _tmpDeliveryStatus = _cursor.getString(_cursorIndexOfDeliveryStatus);
                        if (_cursor.isNull(_cursorIndexOfClientMsgId)) {
                            _tmpClientMsgId = null;
                        } else {
                            _tmpClientMsgId = _cursor.getString(_cursorIndexOfClientMsgId);
                        }
                        _result = new MessageEntity(_tmpId, _tmpSessionId, _tmpOwnerKey, _tmpRole, _tmpContent, _tmpTimestamp, _tmpDeliveryStatus, _tmpClientMsgId);
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

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Object failedForSession(final String sessionId, final String ownerKey, final Continuation $completion) {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM messages WHERE sessionId = ? AND ownerKey = ? AND deliveryStatus = 'failed' ORDER BY timestamp ASC", 2);
        _statement.bindString(1, sessionId);
        _statement.bindString(2, ownerKey);
        CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
        return CoroutinesRoom.execute(this.__db, false, _cancellationSignal, new Callable<List<MessageEntity>>() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.28
            @Override // java.util.concurrent.Callable
            public List<MessageEntity> call() throws Exception {
                String _tmpClientMsgId;
                Cursor _cursor = DBUtil.query(MessageDao_Impl.this.__db, _statement, false, null);
                try {
                    int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                    int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
                    int _cursorIndexOfOwnerKey = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerKey");
                    int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
                    int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
                    int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
                    int _cursorIndexOfDeliveryStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryStatus");
                    int _cursorIndexOfClientMsgId = CursorUtil.getColumnIndexOrThrow(_cursor, "clientMsgId");
                    List<MessageEntity> _result = new ArrayList<>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
                        String _tmpOwnerKey = _cursor.getString(_cursorIndexOfOwnerKey);
                        String _tmpRole = _cursor.getString(_cursorIndexOfRole);
                        String _tmpContent = _cursor.getString(_cursorIndexOfContent);
                        long _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
                        String _tmpDeliveryStatus = _cursor.getString(_cursorIndexOfDeliveryStatus);
                        if (_cursor.isNull(_cursorIndexOfClientMsgId)) {
                            _tmpClientMsgId = null;
                        } else {
                            _tmpClientMsgId = _cursor.getString(_cursorIndexOfClientMsgId);
                        }
                        MessageEntity _item = new MessageEntity(_tmpId, _tmpSessionId, _tmpOwnerKey, _tmpRole, _tmpContent, _tmpTimestamp, _tmpDeliveryStatus, _tmpClientMsgId);
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

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Object outboxForSession(final String sessionId, final String ownerKey, final Continuation $completion) {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM messages WHERE sessionId = ? AND ownerKey = ? AND deliveryStatus IN ('pending', 'failed', 'sending', 'ambiguous') ORDER BY timestamp ASC", 2);
        _statement.bindString(1, sessionId);
        _statement.bindString(2, ownerKey);
        CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
        return CoroutinesRoom.execute(this.__db, false, _cancellationSignal, new Callable<List<MessageEntity>>() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.29
            @Override // java.util.concurrent.Callable
            public List<MessageEntity> call() throws Exception {
                String _tmpClientMsgId;
                Cursor _cursor = DBUtil.query(MessageDao_Impl.this.__db, _statement, false, null);
                try {
                    int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                    int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
                    int _cursorIndexOfOwnerKey = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerKey");
                    int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
                    int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
                    int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
                    int _cursorIndexOfDeliveryStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryStatus");
                    int _cursorIndexOfClientMsgId = CursorUtil.getColumnIndexOrThrow(_cursor, "clientMsgId");
                    List<MessageEntity> _result = new ArrayList<>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
                        String _tmpOwnerKey = _cursor.getString(_cursorIndexOfOwnerKey);
                        String _tmpRole = _cursor.getString(_cursorIndexOfRole);
                        String _tmpContent = _cursor.getString(_cursorIndexOfContent);
                        long _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
                        String _tmpDeliveryStatus = _cursor.getString(_cursorIndexOfDeliveryStatus);
                        if (_cursor.isNull(_cursorIndexOfClientMsgId)) {
                            _tmpClientMsgId = null;
                        } else {
                            _tmpClientMsgId = _cursor.getString(_cursorIndexOfClientMsgId);
                        }
                        MessageEntity _item = new MessageEntity(_tmpId, _tmpSessionId, _tmpOwnerKey, _tmpRole, _tmpContent, _tmpTimestamp, _tmpDeliveryStatus, _tmpClientMsgId);
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

    @Override // ai.nous.aurora.data.local.db.MessageDao
    public Object listOwnerless(final Continuation $completion) {
        final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire("SELECT * FROM messages WHERE ownerKey = '' OR ownerKey IS NULL", 0);
        CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
        return CoroutinesRoom.execute(this.__db, false, _cancellationSignal, new Callable<List<MessageEntity>>() { // from class: ai.nous.aurora.data.local.db.MessageDao_Impl.30
            @Override // java.util.concurrent.Callable
            public List<MessageEntity> call() throws Exception {
                String _tmpClientMsgId;
                Cursor _cursor = DBUtil.query(MessageDao_Impl.this.__db, _statement, false, null);
                try {
                    int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
                    int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
                    int _cursorIndexOfOwnerKey = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerKey");
                    int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
                    int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
                    int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
                    int _cursorIndexOfDeliveryStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryStatus");
                    int _cursorIndexOfClientMsgId = CursorUtil.getColumnIndexOrThrow(_cursor, "clientMsgId");
                    List<MessageEntity> _result = new ArrayList<>(_cursor.getCount());
                    while (_cursor.moveToNext()) {
                        String _tmpId = _cursor.getString(_cursorIndexOfId);
                        String _tmpSessionId = _cursor.getString(_cursorIndexOfSessionId);
                        String _tmpOwnerKey = _cursor.getString(_cursorIndexOfOwnerKey);
                        String _tmpRole = _cursor.getString(_cursorIndexOfRole);
                        String _tmpContent = _cursor.getString(_cursorIndexOfContent);
                        long _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
                        String _tmpDeliveryStatus = _cursor.getString(_cursorIndexOfDeliveryStatus);
                        if (_cursor.isNull(_cursorIndexOfClientMsgId)) {
                            _tmpClientMsgId = null;
                        } else {
                            _tmpClientMsgId = _cursor.getString(_cursorIndexOfClientMsgId);
                        }
                        MessageEntity _item = new MessageEntity(_tmpId, _tmpSessionId, _tmpOwnerKey, _tmpRole, _tmpContent, _tmpTimestamp, _tmpDeliveryStatus, _tmpClientMsgId);
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
