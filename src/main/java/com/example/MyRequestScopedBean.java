package com.example;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.RequestScoped;

import java.util.concurrent.atomic.AtomicInteger;

@RequestScoped
public class MyRequestScopedBean {
    public static final AtomicInteger CALLED = new AtomicInteger();
    public static final AtomicInteger DESTROYED = new AtomicInteger();

    public void doSomething() {
        CALLED.incrementAndGet();
    }

    @PreDestroy
    void destroy() {
        DESTROYED.incrementAndGet();
    }
}
