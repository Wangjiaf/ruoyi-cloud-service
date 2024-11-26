package com.ruoyi.common.core.utils.uuid;

public class SnowflakeUtils {

    /**
     * 开始时间戳 (2021-01-01)
     */
    private static final long START_TIMESTAMP = 1609430400000L;

    /**
     * 机器ID所占的位数
     */
    private static final long WORKER_ID_BITS = 5L;

    /**
     * 数据标识ID所占的位数
     */
    private static final long DATA_CENTER_ID_BITS = 5L;

    /**
     * 支持的最大机器ID，结果是31 (0B11111)
     */
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    /**
     * 支持的最大数据标识ID，结果是31 (0B11111)
     */
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);

    /**
     * 序列在ID中占的位数
     */
    private static final long SEQUENCE_BITS = 12L;

    /**
     * 机器ID向左移12位
     */
    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;

    /**
     * 数据标识ID向左移17位(12+5)
     */
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    /**
     * 时间戳向左移22位(5+5+12)
     */
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;

    /**
     * 支持的最大序列号，结果是4095 (0B111111111111)
     */
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);

    /**
     * 工作机器ID
     */
    private static final long workerId = 1L;

    /**
     * 数据中心ID
     */
    private static final long dataCenterId = 1L;

    /**
     * 毫秒内序列号
     */
    private static long sequence = 0L;

    /**
     * 上次生成ID的时间戳
     */
    private static long lastTimestamp = -1L;

    /**
     * 生成ID
     *
     * @return long类型的ID
     */
    public static synchronized long nextId() {
        long timestamp = System.currentTimeMillis();

        // 如果当前时间小于上次生成ID的时间戳，说明系统时钟回退过，抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("系统时钟回退，拒绝生成ID，上次生成ID的时间戳：%d，当前时间戳：%d",
                    lastTimestamp, timestamp));
        }

        // 如果当前时间等于上次生成ID的时间戳（同一毫秒内），则序列号加1
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                // 如果序列号已经超过最大值，需要等待到下一毫秒再继续生成ID
                timestamp = waitNextMillis(timestamp);
            }
        } else {
            sequence = 0L;
        }

        // 更新上次生成ID的时间戳
        lastTimestamp = timestamp;

        // 生成ID
        return ((timestamp - START_TIMESTAMP) << TIMESTAMP_LEFT_SHIFT) |
                (dataCenterId << DATA_CENTER_ID_SHIFT) |
                (workerId << WORKER_ID_SHIFT) |
                sequence;
    }

    /**
     * 等待下一毫秒
     *
     * @param timestamp 上次生成ID的时间戳
     * @return 下一毫秒的时间戳
     */
    private static long waitNextMillis(long timestamp) {
        long nextTimestamp = System.currentTimeMillis();
        while (nextTimestamp <= timestamp) {
            nextTimestamp = System.currentTimeMillis();
        }
        return nextTimestamp;
    }

    // 测试并发
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(SnowflakeUtils.nextId());
                }
            });
            thread.start();
        }
    }

}
