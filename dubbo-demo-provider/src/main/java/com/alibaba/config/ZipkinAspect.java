package com.alibaba.config;

import brave.internal.Platform;
import brave.propagation.TraceContext;
import com.zipkin.dubbo.common.TracingThreadLocal;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Description: dubbo-test
 * @author: yinguanqun
 * @date: 2018/12/24 下午4:34
 * @company: 北京悦畅科技有限公司
 * Copyright (c) 2012-2017 All Rights Reserved.
 */
@Aspect
@Component
public class ZipkinAspect {

    @Pointcut(value = "@annotation(com.alibaba.config.ZipKin)")
    public void pointCut(){}


    @Before(value = "pointCut() && @annotation(zipkin)")
    public void before(JoinPoint jp, ZipKin zipkin) {

        TraceContext context = TracingThreadLocal.get();
        if (context==null){
            context = intoContext(zipkin.sampled());

        }
        context.toBuilder().sampled(zipkin.sampled());
        TracingThreadLocal.set(context);
    }

    private static TraceContext intoContext(boolean sampled) {
        long nextId = Platform.get().randomLong();
        return TraceContext.newBuilder()
                .sampled(sampled)
                .debug(true)
                .traceIdHigh(nextId)
                .traceId(nextId)
                .spanId(nextId).build();
    }
}
