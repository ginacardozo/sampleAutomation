package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;



public class Retry implements IRetryAnalyzer {
    private static final Logger log = LogManager.getLogger(Retry.class);
    private static final int MAX_RETRY_COUNT = 3;
    private int retryCount = 0;

    @Override
    public boolean retry(final ITestResult result) {
        if (!result.isSuccess()){
            if (this.retryCount < MAX_RETRY_COUNT) {
                log.info("Retrying test {} with status {} for the {} time(s).", result.getName(),
                        getResultStatusName(result.getStatus()), this.retryCount + 1);
                this.retryCount++;
                return true;
            }
        }
        return false;
    }

    public String getResultStatusName(final int status) {
        String resultName = null;
        if (status == 1)
            resultName = "SUCCESS";
        if (status == 2)
            resultName = "FAILURE";
        if (status == 3)
            resultName = "SKIP";
        return resultName;
    }
}
