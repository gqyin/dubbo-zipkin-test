package com.alibaba.config;

import brave.internal.Platform;
import brave.propagation.TraceContext;
import com.zipkin.dubbo.common.TracingThreadLocal;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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


    @Around("@annotation(com.alibaba.config.ZipKin)&& @annotation(zipkin)")
    public Object proceed(ProceedingJoinPoint pjp, ZipKin zipkin) throws Throwable{

        TraceContext context = TracingThreadLocal.get();
        if (context==null){
            context = intoContext(zipkin.sampled());

        }
        context.toBuilder().sampled(zipkin.sampled());
        TracingThreadLocal.set(context);
        Object result =null;
        try {
            result = pjp.proceed();

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            TracingThreadLocal.close();
        }
        return result;
    }

    private static TraceContext intoContext(boolean sampled) {
        long nextId = Platform.get().randomLong();
        return TraceContext.newBuilder()
                .sampled(sampled)
                .debug(false)
                .traceIdHigh(nextId)
                .traceId(nextId)
                .spanId(nextId).build();
    }
}
