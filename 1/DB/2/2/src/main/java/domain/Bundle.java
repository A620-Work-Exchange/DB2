package domain;

import domain.enumeration.BundleType;
import util.DateUtil;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "Bundle")
public class Bundle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 套餐类型
     */
    private BundleType bundleType;

    /**
     * 套餐订购日期
     */
    private LocalDate orderDate;

    /**
     * 生效日期
     */
    private LocalDate beginDate;

    /**
     * 如有必要设定套餐时间/月
     */
    private int period;

    /**
     * 截止日期
     */
    private LocalDate endDate;

    public Bundle() {
    }



    public Bundle(BundleType bundleType, LocalDate orderDate, LocalDate beginDate, LocalDate endDate) {
        this.bundleType = bundleType;
        this.orderDate = orderDate;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public Bundle(BundleType bundleType, LocalDate orderDate, LocalDate endDate) {
        this.bundleType = bundleType;
        this.orderDate = orderDate;
        this.beginDate = DateUtil.getCurrentDate();
        this.period = 1;
        this.endDate = endDate;
    }


    public Bundle(BundleType bundleType, LocalDate beginDate,
                  int period, LocalDate endDate) {
        this.bundleType = bundleType;
        this.orderDate = DateUtil.getCurrentDate();
        this.beginDate = beginDate;
        this.period = period;
        this.endDate = endDate;
    }

    public Bundle(BundleType bundleType, LocalDate orderDate,
                  LocalDate beginDate, int period, LocalDate endDate) {
        this.bundleType = bundleType;
        this.orderDate = orderDate;
        this.beginDate = beginDate;
        this.period = period;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public BundleType getBundleType() {
        return bundleType;
    }

    public void setBundleType(BundleType bundleType) {
        this.bundleType = bundleType;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
