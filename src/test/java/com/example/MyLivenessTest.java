package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static org.junit.jupiter.api.Assertions.assertEquals;

// https://github.com/quarkusio/quarkus/issues/38878
@QuarkusTest
public class MyLivenessTest {
    private static final int COUNT = 1000;

    @Test
    void test() {
        for (int i = 0; i < COUNT; i++) {
            get("/q/health/live");
        }

        assertEquals(COUNT, MyRequestScopedBean.CALLED.get());
        assertEquals(COUNT, MyRequestScopedBean.DESTROYED.get());
    }
}
