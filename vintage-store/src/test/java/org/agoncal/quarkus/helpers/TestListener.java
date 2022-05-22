package org.agoncal.quarkus.helpers;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.reporting.ReportEntry;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;

public class TestListener implements TestExecutionListener {
    @Override public void testPlanExecutionStarted(TestPlan testPlan) {
        // Перед всеми тестами
        System.out.printf("testPlanExecutionStarted %s%n", testPlan);
    }

    @Override public void testPlanExecutionFinished(TestPlan testPlan) {
        // После всех тестов
        System.out.printf("testPlanExecutionFinished %s%n", testPlan);
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
    }

    @Override public void dynamicTestRegistered(TestIdentifier testIdentifier) {
        System.out.printf("dynamicTestRegistered %s%n", testIdentifier);
    }

    @Override public void executionSkipped(TestIdentifier testIdentifier, String reason) {
        System.out.printf("executionSkipped %s && %s%n", testIdentifier, reason);
    }

    @Override public void executionStarted(TestIdentifier testIdentifier) {
        // перед каждым тестом
        System.out.printf("executionStarted %s%n", testIdentifier);
    }

    @Override public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        // после каждого теста
        System.out.printf("executionFinished %s && %s%n", testIdentifier, testExecutionResult);
    }

    @Override public void reportingEntryPublished(TestIdentifier testIdentifier, ReportEntry entry) {
        System.out.printf("reportingEntryPublished %s && %s%n", testIdentifier, entry);
    }
}
