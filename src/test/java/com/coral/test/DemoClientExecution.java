package com.coral.test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.common.collect.Lists;

/**
 * Created by CCC on 2015/10/28.
 */
public class DemoClientExecution {

    private static String[] urls = new String[] { "http://localhost:7070/hytrix-demo/demo/getEstimating", "http://localhost:7070/hytrix-demo/demo/getCustomer",
            "http://localhost:7070/hytrix-demo/demo/getAccount", "http://localhost:7070/hytrix-demo/demo/getPayment", "http://localhost:7070/hytrix-demo/demo/getOrder",
            "http://localhost:7070/hytrix-demo/demo/getCreditCard", "http://localhost:7070/hytrix-demo/demo/getOperator", "http://localhost:7070/hytrix-demo/demo/getItemPrice",
            "http://localhost:7070/hytrix-demo/demo/getTotalPrice" };

    public static void main(String[] args) throws Exception {
        System.out.println("Start to Execution");
        ExecutorService executor = Executors.newScheduledThreadPool(10);
        final int threadNum = 900000;
        List<Callable<BigDecimal>> tasks = Lists.newArrayList();
        for (int i = 0; i < threadNum; ++i) {
            tasks.add(new Callable<BigDecimal>() {
                @Override
                public BigDecimal call() throws Exception {
                    // call some service;
                    Thread.sleep(randomNumber(5, 300));
                    HttpConnectionUtils.httpGet(randomURL());
                    return BigDecimal.ZERO;
                }
            });
        }
        List<Future<BigDecimal>> futures = executor.invokeAll(tasks);
        for (int i = 0; i < threadNum; ++i) {
            futures.get(i).get();
        }
    }

    public static int randomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static String randomURL() {
        int index = randomNumber(0, (urls.length - 1));
        return urls[index];
    }
}
