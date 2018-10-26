package util;

import dao.BundleDAO;
import dao.UserDAO;
import domain.User;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.Callable;

/**
 * 每月定时更新
 */
public class InitializationTask implements Callable<String>{
    private LocalDate localDate = DateUtil.getCurrentDate();
    private BundleDAO bundleDAO = new BundleDAO();

    public InitializationTask(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public String call() throws Exception {
        bundleDAO.initUserInfoAtFirstDayOfMonth(localDate);
        return "Init the user info...";
    }

}
