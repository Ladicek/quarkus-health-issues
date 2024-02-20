package com.example;

import com.sun.management.HotSpotDiagnosticMXBean;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.management.MBeanServer;
import java.io.IOException;
import java.lang.management.ManagementFactory;

import static io.restassured.RestAssured.get;
import static org.junit.jupiter.api.Assertions.assertEquals;

// https://github.com/quarkusio/quarkus/issues/38878
@QuarkusTest
public class MyLivenessTest {
    private static final int COUNT = 5;

    @Test
    void test() throws IOException {
        for (int i = 0; i < COUNT; i++) {
            System.out.println("======================================== iteration " + (i + 1));
            get("/q/health/live");
        }

        assertEquals(COUNT, MyRequestScopedBean.CALLED.get());
        assertEquals(COUNT, MyRequestScopedBean.DESTROYED.get());
    }

    public static void dumpHeap(String filePath) throws IOException {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        HotSpotDiagnosticMXBean mxBean = ManagementFactory.newPlatformMXBeanProxy(server,
                "com.sun.management:type=HotSpotDiagnostic", HotSpotDiagnosticMXBean.class);
        mxBean.dumpHeap(filePath, false);
    }
}
