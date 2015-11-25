package com.coral.service;

import java.util.Random;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;

/**
 * Created by CCC on 2015/10/28.
 */
public abstract class AbstractCommand extends HystrixCommand<String> {

    protected String name;

    protected static Integer defaultTO = 500;

    public AbstractCommand(String name, Integer timeout) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("DemoGroup")).andCommandKey(HystrixCommandKey.Factory.asKey(name + "Command"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(name + "RemoteService")).andCommandPropertiesDefaults(
                        // we default to a 600ms timeout for primary
                        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(timeout)).andThreadPoolPropertiesDefaults(
                        com.netflix.hystrix.HystrixThreadPoolProperties.Setter().withCoreSize(55).withMaxQueueSize(15).withQueueSizeRejectionThreshold(12).withKeepAliveTimeMinutes(8)));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        Random rand = new Random();
        int nextInt = rand.nextInt(200);
        Thread.sleep(nextInt);
        /*System.out.println("Run the run() in " + name + "Command");*/
        return "Hello " + name + "!";
    }
}
