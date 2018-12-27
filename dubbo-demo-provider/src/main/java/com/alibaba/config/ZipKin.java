package com.alibaba.config;

import java.lang.annotation.*;

/**
 * @Description: dubbo-test
 * @author: yinguanqun
 * @date: 2018/12/24 下午4:28
 * @company: 北京悦畅科技有限公司
 * Copyright (c) 2012-2017 All Rights Reserved.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ZipKin {
    boolean sampled() default true;
}
