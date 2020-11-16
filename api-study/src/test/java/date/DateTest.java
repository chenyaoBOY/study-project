//package date;
//
//import io.netty.org.study.project.util.internal.ConcurrentSet;
//import org.junit.Test;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.org.study.project.util.Calendar;
//import java.org.study.project.util.Date;
//import java.org.study.project.util.Set;
//import java.org.study.project.util.TimeZone;
//import java.org.study.project.util.concurrent.CountDownLatch;
//import java.org.study.project.util.concurrent.Executor;
//import java.org.study.project.util.concurrent.Executors;
//
//public class DateTest {
//
//    public static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    public static CountDownLatch count = new CountDownLatch(100);
//    public static CountDownLatch count2 = new CountDownLatch(1);
//    public static Executor executor = Executors.newFixedThreadPool(100);
//
//    @Test
//    public void timeZoneTest(){
//        System.out.println(new Date());
//        TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));
//        Date date = new Date();
//        System.out.println(date);
//    }
//    @Test
//    public void validateSimpleDateFormatIsNotThreadSafe() throws InterruptedException {
//        Set set =  new ConcurrentSet();
//        Calendar calendar = Calendar.getInstance();
//        for (int i = 0; i < 100; i++) {
//            final int  ii = i;
//            executor.execute(() -> {
//                try {
//                    count2.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                calendar.add(Calendar.DATE,ii);
//                set.add(format.format(calendar.getTime()));
//                count.countDown();
//            });
//        }
//        count2.countDown();
//        count.await();
//        System.out.println(set.size());
//
//    }
//
//    @Test
//    public void stringTest(){
//        char[] arr = new char[] {'h', 'e', 'l', 'l', 'o', ' ', 'w', 'o', 'r', 'l', 'd'};
//        String s = new String(arr,0,arr.length); // "hello world"
//        arr[0] = 'a'; // replace the first character with 'a'
//        System.out.println(s); // aello world
//    }
//}
