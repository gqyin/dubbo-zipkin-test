//package com.alibaba.config;
//
//import com.github.kristofa.brave.Brave;
//import com.github.kristofa.brave.httpclient.BraveHttpRequestInterceptor;
//import com.github.kristofa.brave.httpclient.BraveHttpResponseInterceptor;
//import com.github.kristofa.brave.servlet.BraveServletFilter;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import zipkin.Span;
//import zipkin.reporter.AsyncReporter;
//import zipkin.reporter.Reporter;
//import zipkin.reporter.Sender;
//import zipkin.reporter.okhttp3.OkHttpSender;
//
//@Configuration
//public class ZipConfig {
//
//    @Value("${brave.name}")
//    private String applicationName;
//    @Value("${http.sender.address}")
//    private String sendAddress;
//
//    /**
//     * Brave各工具类的封装
//     *
//     * @return Brave
//     */
//    @Bean
//    public Brave brave() {
//        Sender sender = OkHttpSender.create(sendAddress);
//        Reporter<Span> reporter = AsyncReporter.builder(sender).build();
//        Brave brave = new Brave.Builder(applicationName).reporter(reporter).build();
//        return brave;
//    }
//
//    /**
//     * 拦截器，需要serverRequestInterceptor,serverResponseInterceptor 分别完成sr和ss操作
//     *
//     * @param brave
//     * @return
//     */
//    @Bean
//    public BraveServletFilter braveServletFilter(Brave brave) {
//        return BraveServletFilter.create(brave);
//    }
//
//    /**
//     * httpClient客户端，需要clientRequestInterceptor,clientResponseInterceptor分别完成cs和cr操作
//     *
//     * @param brave
//     * @return
//     */
//    @Bean
//    public CloseableHttpClient closeableHttpClient(Brave brave) {
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .addInterceptorFirst(BraveHttpRequestInterceptor.create(brave))
//                .addInterceptorFirst(BraveHttpResponseInterceptor.create(brave))
//                .build();
//        return httpClient;
//    }
//
//}
