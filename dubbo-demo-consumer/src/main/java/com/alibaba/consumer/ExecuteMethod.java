package com.alibaba.consumer;

import com.alibaba.api.DemoService;
import com.alibaba.config.ZipKin;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Description: dubbo-test
 * @author: yinguanqun
 * @date: 2018/12/26 下午3:28
 * @company: 北京悦畅科技有限公司
 * Copyright (c) 2012-2017 All Rights Reserved.
 */
@Component
public class ExecuteMethod {

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
