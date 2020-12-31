package org.crazy.thread.base;

import org.crazy.thread.threadpool.ext.ThreadPoolCantSeeException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author chenyao
 * @date 2020/12/10 19:30
 * @description
 */
public class TryCatchThreadException {
    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(10);
        List<Future<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Integer> submit = service.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return 1 / 0;
                }
            });
            list.add(submit);
        }
        for (Future<Integer> submit : list) {
            try {
                submit.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        service.shutdownNow();
    }
}
