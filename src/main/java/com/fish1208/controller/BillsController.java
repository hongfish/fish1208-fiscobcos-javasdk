package com.fish1208.controller;

import com.alibaba.fastjson.JSON;
import com.fish1208.common.response.Result;
import com.fish1208.contract.BillController;
import com.fish1208.entiy.Bill;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * 票据审计
 */
@Slf4j
@RestController
@RequestMapping("/contract/bill")
public class BillsController {

    @Autowired
    private BillController billController;

    @RequestMapping(value = "/issue", method = RequestMethod.POST)
    public Result<?> issue(@RequestBody Map<String, Object> param) throws Exception {
        String s = (String)param.get("s");
        TransactionReceipt receipt = billController.issue(s);
        return Result.data(receipt);
    }

    @RequestMapping(value = "/queryBills", method = RequestMethod.GET)
    public Result<?> queryBills(@RequestParam String holdrCmID) throws Exception {
        TransactionReceipt receipt = billController.queryBills(holdrCmID);
        Tuple1<List<String>> tuple1 = billController.getQueryBillsOutput(receipt);
        List<String> list = tuple1.getValue1();
//        log.info("billController is queryBills receipt = {}", JSON.toJSONString(receipt));
        return Result.data(list);
    }

    @RequestMapping(value = "/queryBillByNo", method = RequestMethod.GET)
    public Result<?> queryBillByNo(@RequestParam String infoID) throws Exception {
        TransactionReceipt receipt = billController.queryBillByNo(infoID);
        Tuple1<String> tuple1 = billController.getQueryBillByNoOutput(receipt);
//        log.info("billController is queryBills receipt = {}", JSON.toJSONString(receipt));
        String value = tuple1.getValue1();
        Bill bill = JSON.parseObject(value, Bill.class);
        return Result.data(bill);
    }

}
