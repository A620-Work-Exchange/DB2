package nju.py.db2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;
import java.util.Date;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 账单日期
     */
    private Date date;

    /**
     * 通话时长
     */
    private int callTime;

    /**
     * 短信数量
     */
    private int smsAmount;

    /**
     * 本地流量使用量
     */
    private int localDataUsage;

    /**
     * 国内流量使用量
     */
    private int domesticDataUsage;

    /**
     * 国外流量使用量
     */
    private int foreignDataUsage;


}
