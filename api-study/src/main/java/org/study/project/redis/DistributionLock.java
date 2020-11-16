package org.study.project.redis;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Arrays;

/**
 * @author chenyao
 * @date 2019/8/2 11:24
 * @description
 */
public class DistributionLock {
    private static final Jedis jedis = JedisUtil.jedis;
    public static final String lock_key = "distribution_lock";

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                String uuid = UUID.randomUUID().toString();
                if (getLock(lock_key, uuid, 3)) {
                    System.out.println(Thread.currentThread().getName() + "线程获取了分布式锁");
                } else {
                    System.out.println(Thread.currentThread().getName() + " 未获取到锁");
                }
            }).start();
        }
        while (true) {
        }
    }

    @Test
    public void test() throws InterruptedException {
        String uuid = UUID.randomUUID().toString();
        if (getLock(lock_key, uuid, 3)) {
            System.out.println(Thread.currentThread().getName() + "线程获取了分布式锁");
            Thread.sleep(4000);
            if (releaseLock(lock_key, uuid)) {
                System.out.println(Thread.currentThread().getName() + "线程释放了锁");
            } else {
                System.out.println(Thread.currentThread().getName() + "线程释放锁失败");
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " 未获取到锁");
        }
    }

    @Test
    public void lockTest() throws InterruptedException {


    }

    public static boolean getLock(String key, String uniqueValue, int time) {
        //NX not exist
        //EX 秒 PX 毫秒
        String nx = JedisUtil.getJedis().set(key, uniqueValue, "NX", "EX", time);
        return "OK".equals(nx) ? true : false;
    }

    public boolean releaseLock(String key, String uniqueValue) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] " +
                "then return redis.call('del', KEYS[1]) "+
                "elseif redis.call('get', KEYS[1]) == 'null' "+
                "then return 2 "+
                "else return redis.call('get', KEYS[1]) end";
        Object obj = jedis.eval(script, Arrays.asList(key), Arrays.asList(uniqueValue));
        System.out.println(obj);
        if ("1".equals(String.valueOf(obj))) {
            return true;
        }else if("2".equals(String.valueOf(obj))){
            System.out.println("key="+key+" value="+uniqueValue+"已经过期");
            return true;
        }
        return false;
    }
//    public void lock(){
//        log.info("出入库操作参数打印============【" + JSON.toJSONString(dto) + "】");
//        String key = PREFIX + dto.getBranchCompanyId();
//        boolean locked = false;
//
//        try {
//            //尝试获取锁
//            locked = distributedLocker.tryLock(key, TimeUnit.SECONDS, 6, 5);
//
//            if (!locked){
//                throw new ErpBusinessException("仓库正在变动，请稍后再试");
//            }
//            //操作库存
//            warehouseService.outInWarehouseStock(dto);
//        }catch (Exception e){
//            if (e instanceof ErpBusinessException){
//                throw new ErpBusinessException(e.getMessage());
//            }else{
//                e.printStackTrace();
//                throw new ErpBusinessException("操作失败");
//            }
//        }finally {
//            //释放锁
//            if (locked) {
//                distributedLocker.unlock(key);
//            }
//        }
//    }
}
