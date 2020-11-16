
package org.study.project;

public class SnowflakeArithmethic {

    public static void main(String[] args) {
//        long time = System.currentTimeMillis();
//        String timeStr = time+"";
//        System.out.println(timeStr.length()+"  "+timeStr);
        int i = 10;
        System.out.println(i << 10);
    }


}
//public class SnowflakeArithmethic implements IGenerator<Long>{
//
//        public static final int NODE_SHIFT = 10;
//        public static final int SEQ_SHIFT = 12;
//        public static final short MAX_NODE = 1024;
//        public static final short MAX_SEQUENCE = 4096;
//        private short sequence;
//        private long referenceTime;
//        private int node = -1;
//        private NodeGetter nodeGetter;
//
//        public Long generate(Class<?> modelClass) {
//            return this.next();
//        }
//
//        public void setNode(int node) {
//            if (node >= 0 && node <= 1024) {
//                this.node = node;
//            } else {
//                throw new IllegalArgumentException(String.format("node must be between %s and %s", 0, 1024));
//            }
//        }
//
//        public synchronized long next() {
//            long currentTime = System.currentTimeMillis();
//            if (currentTime < this.referenceTime) {
//                throw new RuntimeException(String.format("Last referenceTime %s is after reference time %s", this.referenceTime, currentTime));
//            } else {
//                if (currentTime > this.referenceTime) {
//                    this.sequence = 0;
//                } else {
//                    if (this.sequence >= 4096) {
//                        throw new RuntimeException("Sequence exhausted at " + this.sequence);
//                    }
//
//                    ++this.sequence;
//                }
//
//                long counter = (long) this.sequence;
//                this.referenceTime = currentTime;
//                return currentTime << 10 << 12 | (long) (this.getNode() << 12) | counter;
//            }
//        }
//
//        public void setNodeGetter(NodeGetter nodeGetter) {
//            this.nodeGetter = nodeGetter;
//        }
//
//        public int getNode() {
//            return this.nodeGetter != null ? this.nodeGetter.getNode() : this.node;
//        }
//    }
//}

class SnowId {
    private long workerId; // 这个就是代表了机器id
    private long datacenterId; // 这个就是代表了机房id
    private long sequence; // 这个就是代表了一毫秒内生成的多个id的最新序号
    private long twepoch = 1288834974657L;

    private long workerIdBits = 5L;
    private long datacenterIdBits = 5L;

    // 这个是二进制运算，就是5 bit最多只能有31个数字，也就是说机器id最多只能是32以内
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    // 这个是一个意思，就是5 bit最多只能有31个数字，机房id最多只能是32以内
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    private long sequenceBits = 12L;

    private long workerIdShift = sequenceBits;
    private long datacenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long lastTimestamp = -1L;

    public long getWorkerId() {
        return workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public SnowId(long workerId, long datacenterId, long sequence) {

        // sanity check for workerId
        // 这儿不就检查了一下，要求就是你传递进来的机房id和机器id不能超过32，不能小于0
        if (workerId > maxWorkerId || workerId < 0) {

            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }

        if (datacenterId > maxDatacenterId || datacenterId < 0) {

            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }

        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }

    // 这个是核心方法，通过调用nextId()方法，让当前这台机器上的snowflake算法程序生成一个全局唯一的id
    public synchronized long nextId() {

        // 这儿就是获取当前时间戳，单位是毫秒
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            System.err.printf( "clock is moving backwards. Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException( String.format("Clock moved backwards. Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }
        // 下面是说假设在同一个毫秒内，又发送了一个请求生成一个id
        // 这个时候就得把seqence序号给递增1，最多就是4096
        if (lastTimestamp == timestamp) {

            // 这个意思是说一个毫秒内最多只能有4096个数字，无论你传递多少进来，
            //这个位运算保证始终就是在4096这个范围内，避免你自己传递个sequence超过了4096这个范围
            sequence = (sequence + 1) & sequenceMask;

            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }

        } else {
            sequence = 0;
        }

        // 这儿记录一下最近一次生成id的时间戳，单位是毫秒
        lastTimestamp = timestamp;

        // 这儿就是最核心的二进制位运算操作，生成一个64bit的id
        // 先将当前时间戳左移，放到41 bit那儿；将机房id左移放到5 bit那儿；将机器id左移放到5 bit那儿；将序号放最后12 bit
        // 最后拼接起来成一个64 bit的二进制数字，转换成10进制就是个long型
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) | sequence;
    }

    private long tilNextMillis(long lastTimestamp) {

        long timestamp = timeGen();

        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    //---------------测试---------------
    public static void main(String[] args) {

        SnowId worker = new SnowId(1, 1, 1);

        for (int i = 0; i < 30; i++) {
            System.out.println(worker.nextId());
        }
    }
}
