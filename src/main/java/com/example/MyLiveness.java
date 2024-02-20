package com.example;

import jakarta.inject.Inject;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
public class MyLiveness implements HealthCheck {
    @Inject
    MyRequestScopedBean dbSource;

    @Override
    public HealthCheckResponse call() {
        dbSource.doSomething();
        return HealthCheckResponse.builder().name("whatever").up().build();
    }
}
