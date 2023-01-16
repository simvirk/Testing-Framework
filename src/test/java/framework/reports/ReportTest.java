package framework.reports;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ReportTest {
    ExtentTest test;

    @BeforeSuite
    public void setUp(){
        test = HtmlReport.createTest("Pragra Test");
    }

    @Test
    public void testName() {
        System.out.println("Running Test cases");
        test.log(Status.PASS, "This test case passed");
    }

    @Test
    public void test2() {
        System.out.println("Running Test cases");
        test.log(Status.FAIL, "This test case failed");
    }

    @AfterSuite
    public void tearDown() {
        HtmlReport.flushReports();
    }
}

