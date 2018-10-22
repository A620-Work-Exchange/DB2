package nju.py.db2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 基础套餐服务
     */
    private BasicFee basicFeeList;

    /**
     * 套餐服务，考虑叠加
     */
    private List<MultiFee> multiFeeList;


}
