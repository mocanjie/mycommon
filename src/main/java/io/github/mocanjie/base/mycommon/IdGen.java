package io.github.mocanjie.base.mycommon;

import java.util.concurrent.locks.ReentrantLock;

public class IdGen {

    // 起始时间戳: Thu, 04 Nov 2010 01:42:54 GMT
    private static final long TWEPOCH = 1288834974657L;

    private static final long WORKER_ID_BITS = 5L;
    private static final long DATACENTER_ID_BITS = 5L;
    private static final long MAX_WORKER_ID = -1L ^ (-1L << WORKER_ID_BITS);         // 最大 31
    private static final long MAX_DATACENTER_ID = -1L ^ (-1L << DATACENTER_ID_BITS); // 最大 31
    private static final long SEQUENCE_BITS = 12L;
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;
    private static final long SEQUENCE_MASK = -1L ^ (-1L << SEQUENCE_BITS);          // 最大 4095

    // 允许的最大时钟回拨毫秒数，超出则抛异常
    private static final long MAX_BACKWARD_MS = 5L;

    private final long workerId;
    private final long datacenterId;
    private final ReentrantLock lock = new ReentrantLock();

    private long sequence = 0L;
    private long lastTimestamp = -1L;

    private static class IdGenHolder {
        private static final IdGen instance = new IdGen();
    }

    public static IdGen get() {
        return IdGenHolder.instance;
    }

    public IdGen() {
        this(0L, 0L);
    }

    public IdGen(long workerId, long datacenterId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("workerId must be between 0 and %d", MAX_WORKER_ID));
        }
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("datacenterId must be between 0 and %d", MAX_DATACENTER_ID));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public long nextId() {
        lock.lock();
        try {
            long timestamp = timeGen();

            if (timestamp < lastTimestamp) {
                long backward = lastTimestamp - timestamp;
                if (backward <= MAX_BACKWARD_MS) {
                    // 小幅回拨，等待时钟追上
                    timestamp = tilNextMillis(lastTimestamp);
                } else {
                    throw new RuntimeException(String.format(
                            "Clock moved backwards. Refusing to generate id for %d milliseconds", backward));
                }
            }

            if (lastTimestamp == timestamp) {
                sequence = (sequence + 1) & SEQUENCE_MASK;
                if (sequence == 0) {
                    // 当前毫秒序列号耗尽，等待下一毫秒
                    timestamp = tilNextMillis(lastTimestamp);
                }
            } else {
                sequence = 0L;
            }

            lastTimestamp = timestamp;

            return ((timestamp - TWEPOCH) << TIMESTAMP_LEFT_SHIFT)
                    | (datacenterId << DATACENTER_ID_SHIFT)
                    | (workerId << WORKER_ID_SHIFT)
                    | sequence;
        } finally {
            lock.unlock();
        }
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }
}
