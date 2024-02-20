package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

// https://github.com/quarkusio/quarkus/issues/38866
@QuarkusTest
public class MyReadinessTest {
    @Test
    void test() {
        for (int i = 0; i < 10_000; i++) {
            when()
                    .get("/q/health/ready")
            .then()
                    .statusCode(200)
                    .body("checks[0].name", is("RequestContextActive: true"));
        }
    }
}
