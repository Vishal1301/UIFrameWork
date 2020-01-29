package com.test.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.test.extentreports.ExtentManager;
import com.test.utils.TestBase;
import com.test.utils.TestUtil;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;

/**
 * @author Vishal Limbani
 *
 */
public class TestListener extends TestBase implements ITestListener {

	private static final Logger log = Logger.getLogger(TestListener.class);

	//Extent Report Declarations
    private static ExtentReports extent = ExtentManager.createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public synchronized void onStart(ITestContext context) {
    	try {
    		log.info("Test Suite started!");
		} catch (Exception e) {
			log.info(e);
		}
    }

	@Override
	public synchronized void onFinish(ITestContext context) {
		try {
			
			Iterator<ITestResult> failedTestCases = context.getFailedTests().getAllResults().iterator();
			if (context.getFailedTests().size() > 0) {
				log.info("failedTestCases");
				while (failedTestCases.hasNext()) {
					ITestResult failedTestCase = failedTestCases.next();
					ITestNGMethod method = failedTestCase.getMethod();
					log.info("Failed test case: " + failedTestCase + " Failed test method: " + method);
					if (context.getFailedTests().getResults(method).size() > 1) {
						log.info("failed test case remove as dup:" + failedTestCase.getTestClass().toString());
						failedTestCases.remove();
					} else if (context.getPassedTests().getResults(method).size() > 0) {
						log.info("failed test case remove as pass retry:" + failedTestCase.getTestClass().toString());
						failedTestCases.remove();
					}
				}
			}
			else {
				log.info("Congrats, All test cases are passed");
			}
			log.info(("Test Suite is ending!"));
			
	
			extent.flush();
			
		} catch (Exception e) {
			log.info(e);
		}

		
	}

    @Override
    public synchronized void onTestStart(ITestResult result) {
    	try {
    		 log.info((result.getMethod().getMethodName() + " started!"));
    	        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
    	        test.set(extentTest);
		} catch (Exception e) {
			log.info(e);
		}
       
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
    	try {
    		log.info(("Test case" + result.getMethod().getMethodName() + " passed!"));
            test.get().pass("Test passed");
		} catch (Exception e) {
			log.info(e);
		}
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
    	
    	try {
			TestUtil.captureCurrentScreenshot();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	 try {
    		 if (result.getMethod().getRetryAnalyzer() != null) {
                 Retry retryAnalyzer = (Retry)result.getMethod().getRetryAnalyzer();

                 if(retryAnalyzer.isRetryAvailable()) {
                 } else {
                     result.setStatus(ITestResult.FAILURE);
                 }
                 Reporter.setCurrentTestResult(result);
             }
        	 log.info((result.getMethod().getMethodName() + " failed!"));
             test.get().fail(result.getThrowable());
		} catch (Exception e) {
			 log.info(e);
		}
    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        try {
        	log.info((result.getMethod().getMethodName() + " skipped!"));
            test.get().skip(result.getThrowable());
            extent.flush();
		} catch (Exception e) {
			log.info(e);
		}
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    	try {
    		log.info(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
		} catch (Exception e) {
			log.info(e);
		}
    }
}