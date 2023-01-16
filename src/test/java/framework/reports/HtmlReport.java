package framework.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class HtmlReport {
    private static ExtentReports reports;

    private HtmlReport(){
        reports = new ExtentReports();
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("Report.html");
        reports.attachReporter(extentSparkReporter);
    }

    public static ExtentTest createTest(String name){
        if(reports == null){
            new HtmlReport();
        }
        return reports.createTest(name);
    }

    public static void flushReports(){
        if(reports != null){
            reports.flush();
        }
    }
}
