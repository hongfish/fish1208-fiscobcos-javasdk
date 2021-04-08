package com.fish1208.controller;

import com.alibaba.fastjson.JSON;
import com.fish1208.common.response.Result;
import com.fish1208.contract.KVPerson;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple4;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/contract/person")
public class PersonController {

    @Autowired
    private KVPerson person;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result<?> get(@RequestParam String id) throws Exception {
        if (person != null) {
            log.info("KVPerson address is: {}", person.getContractAddress());
            Tuple4<Boolean, String, BigInteger, String> tuple = person.get(id);
            return Result.data(tuple);
        }
        return Result.fail("执行KVPerson合约失败");
    }

    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public Result<?> set(@RequestBody Map<String,Object> param) throws Exception {
        if (person != null) {
            log.info("KVPerson address is: {}", person.getContractAddress());
            String id = (String)param.get("id");
            String name = (String) param.get("name");
            BigInteger age = BigInteger.valueOf((Integer) param.get("age"));
            String sex = (String) param.get("sex");
            TransactionReceipt receipt = person.set(id, name, age, sex);
            log.info("KVPerson receipt = {}", JSON.toJSONString(receipt));
            return Result.data(receipt);
        }
        return Result.fail("执行KVPerson合约失败");
    }
}
