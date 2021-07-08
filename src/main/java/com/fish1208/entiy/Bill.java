package com.fish1208.entiy;

import lombok.Data;

@Data
public class Bill {

    private String infoID;	// 票据号码
    private String infoAmt;		// 票据金额
    private String infoType;	// 票据类型

    private String infoIsseDate;	// 出票日期
    private String infoDueDate;	// 到期日期

    private String drwrAcct;	// 出票人名称
    private String drwrCmID;	// 出票人证件号码

    private String accptrAcct;	// 承兑人名称
    private String accptrCmID;	// 承兑人证件号码

    private String pyeeAcct;	// 收款人名称
    private String pyeeCmID;	// 收款人证件号码

    private String holdrAcct;	// 当前持票人名称
    private String holdrCmID;	// 当前持票人证件号码

    private String waitEndorseAcct;	// 待背书人名称
    private String waitEndorseCmID;	// 待背书人证件号码

    private String rejectEndorseAcct;	// 拒绝背书人名称
    private String rejectEndorseCmID;	// 拒绝背书人证件号码

    private String state;	// 票据状态
}
