package com.alibaba.consumer;

import com.alibaba.api.DemoService;
import com.alibaba.config.ZipKin;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;

public class Consumer {


    public static void main(String[] args) {

        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext.xml"});
        context.start();
        DemoService demoService = (DemoService) context.getBean("demoService"); // get remote service proxy
        Method method = null;
        try {
            method = Consumer.class.getMethod("excute", DemoService.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (method.isAnnotationPresent(ZipKin.class)){
            System.out.println("------------>true");
        }
        ExecuteMethod executeMethod = (ExecuteMethod)context.getBean("executeMethod");
        executeMethod.excute(demoService);
    }


    @ZipKin
    public void excute(DemoService demoService){

        while (true) {
            try {
                Thread.sleep(1000);
                String hello = demoService.sayHello("world"); // call remote method
                System.out.println(hello); // get result

            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

        }
    }
}