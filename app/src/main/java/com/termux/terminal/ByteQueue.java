package com.termux.terminal;

/* JADX INFO: loaded from: classes3.dex */
final class ByteQueue {
    private final byte[] mBuffer;
    private int mHead;
    private boolean mOpen = true;
    private int mStoredBytes;

    public ByteQueue(int size) {
        this.mBuffer = new byte[size];
    }

    public synchronized void close() {
        this.mOpen = false;
        notify();
    }

    public synchronized int read(byte[] buffer, boolean block) {
        while (this.mStoredBytes == 0 && this.mOpen) {
            if (!block) {
                return 0;
            }
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        if (!this.mOpen) {
            return -1;
        }
        int totalRead = 0;
        int bufferLength = this.mBuffer.length;
        boolean wasFull = bufferLength == this.mStoredBytes;
        int length = buffer.length;
        int offset = 0;
        while (length > 0 && this.mStoredBytes > 0) {
            int oneRun = Math.min(bufferLength - this.mHead, this.mStoredBytes);
            int bytesToCopy = Math.min(length, oneRun);
            System.arraycopy(this.mBuffer, this.mHead, buffer, offset, bytesToCopy);
            this.mHead += bytesToCopy;
            if (this.mHead >= bufferLength) {
                this.mHead = 0;
            }
            this.mStoredBytes -= bytesToCopy;
            length -= bytesToCopy;
            offset += bytesToCopy;
            totalRead += bytesToCopy;
        }
        if (wasFull) {
            notify();
        }
        return totalRead;
    }

    public boolean write(byte[] buffer, int offset, int lengthToWrite) {
        int oneRun;
        if (lengthToWrite + offset > buffer.length) {
            throw new IllegalArgumentException("length + offset > buffer.length");
        }
        if (lengthToWrite <= 0) {
            throw new IllegalArgumentException("length <= 0");
        }
        int bufferLength = this.mBuffer.length;
        synchronized (this) {
            while (true) {
                boolean wasEmpty = true;
                if (lengthToWrite <= 0) {
                    return true;
                }
                while (bufferLength == this.mStoredBytes && this.mOpen) {
                    try {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                if (!this.mOpen) {
                    return false;
                }
                if (this.mStoredBytes != 0) {
                    wasEmpty = false;
                }
                int bytesToWriteBeforeWaiting = Math.min(lengthToWrite, bufferLength - this.mStoredBytes);
                lengthToWrite -= bytesToWriteBeforeWaiting;
                while (bytesToWriteBeforeWaiting > 0) {
                    int tail = this.mHead + this.mStoredBytes;
                    if (tail >= bufferLength) {
                        tail -= bufferLength;
                        oneRun = this.mHead - tail;
                    } else {
                        oneRun = bufferLength - tail;
                    }
                    int bytesToCopy = Math.min(oneRun, bytesToWriteBeforeWaiting);
                    System.arraycopy(buffer, offset, this.mBuffer, tail, bytesToCopy);
                    offset += bytesToCopy;
                    bytesToWriteBeforeWaiting -= bytesToCopy;
                    this.mStoredBytes += bytesToCopy;
                }
                if (wasEmpty) {
                    notify();
                }
            }
        }
    }
}
