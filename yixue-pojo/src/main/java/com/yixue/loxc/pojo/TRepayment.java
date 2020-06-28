package com.yixue.loxc.pojo;

import lombok.Data;

@Data
public class TRepayment {
    private String id;
    private String borrowId;
    private String borrowUserId;
    private String borrowTitle;
    private java.sql.Timestamp deadline;
    private java.sql.Timestamp repaymentTime;
    private long totalAmount;
    private long principal;
    private long interest;
    private long period;
    private long state;
    private long borrowType;
    private long repaymentType;
    private java.sql.Timestamp createTime;

}
