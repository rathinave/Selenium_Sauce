
// src/test/java/Test_Base/ExtentReportListener.java
package Test_Base;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;
import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportListener implements ITestListener, ISuiteListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ISuite suite) {
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String reportPath = "reports/ExtentReport_" + ts + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("UI Test Results");
        spark.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Suite", suite.getName());
        extent.setSystemInfo("Environment", "QA");
    }

    @Override
    public void onFinish(ISuite suite) {
        if (extent != null) extent.flush();
        System.out.println("Extent report generated at /reports");
    }

    @Override
    public void onTestStart(ITestResult result) {
        String className = result.getTestClass().getName();
        String methodName = result.getMethod().getMethodName();

        ExtentTest t = extent.createTest(methodName)
                             .assignCategory(className);
        test.set(t);
        test.get().info("Starting: " + className + "." + methodName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());

        WebDriver driver = extractDriver(result.getInstance());
        if (driver != null) {
            String screenshotPath = takeScreenshot(driver, result.getMethod().getMethodName());
            if (screenshotPath != null) {
                test.get().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
            }
        } else {
            test.get().warning("WebDriver 'dr' not found in test instance; screenshot skipped.");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }

    // ---- Helpers ----

    private WebDriver extractDriver(Object instance) {
        try {
            // expects: WebDriver field named 'dr' in your test class
            var field = instance.getClass().getDeclaredField("dr");
            field.setAccessible(true);
            return (WebDriver) field.get(instance);
        } catch (Exception e) {
            return null;
        }
    }

    private String takeScreenshot(WebDriver driver, String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            File dest = new File("reports/screenshots/" + name + "_" + ts + ".png");
            dest.getParentFile().mkdirs();
            FileUtils.copyFile(src, dest);
            return dest.getAbsolutePath();
        } catch (IOException | WebDriverException e) {
            test.get().warning("Screenshot capture failed: " + e.getMessage());
            return null;
        }
    }
}
