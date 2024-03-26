package linh.to.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.text.MessageFormat;

public class LoggerUtil {
    public static void printJob(String jobName, int logLevel) {
        System.out.println(String.join(" ",StringUtils.repeat("-", logLevel), jobName ));
    }

    public static void printTimeComsuming(String msg, Long timeStart, int logLevel) {
        String logMsg = MessageFormat.format("%s %s: %s", StringUtils.repeat("-", logLevel), msg, DurationFormatUtils.formatDuration(System.currentTimeMillis() - timeStart, "mm:ss,SSS"));
        System.out.println(logMsg);

    }
}
