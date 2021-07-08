package com.fish1208.controller;

import com.fish1208.common.response.Result;
import com.fish1208.contract.ERC1155;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Map;

/**
 * ERC1155
 */
@Slf4j
@RestController
@RequestMapping("/contract/erc1155")
public class ERC1155Controller {

    @Autowired
    private ERC1155 erc1155;

    @RequestMapping(value = "/safeTransferFrom", method = RequestMethod.POST)
    public Result<?> safeTransferFrom(@RequestBody Map<String, Object> param) throws Exception {

        String from = (String)param.get("from");
        String to = (String)param.get("to");
        BigInteger id = (BigInteger)param.get("id");
        BigInteger amount = (BigInteger)param.get("amount");
        byte[] data = null;

        TransactionReceipt receipt = erc1155.safeTransferFrom(from, to, id, amount, data);
        return Result.data(receipt);
    }


}
