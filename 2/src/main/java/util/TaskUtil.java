package util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskUtil {
    public static void initUserInfo() {
        InitializationTask task = new InitializationTask(DateUtil.getFirstDayNextMonth());
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);

        executor.schedule(task, DateUtil.getDaysBetweenMonths(), TimeUnit.DAYS);
        executor.shutdown();
    }
}
