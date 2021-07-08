package com.fish1208.controller;

import com.alibaba.fastjson.JSON;
import com.fish1208.bcos.ContractAddress;
import com.fish1208.bcos.util.Convert;
import com.fish1208.common.response.Result;
import com.fish1208.contract.ERC1155Tradable;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

/**
 * ERC1155
 */
@Slf4j
@RestController
@RequestMapping("/erc1155/tradable")
public class ERC1155TradableController {

    @Autowired
    private ERC1155Tradable erc1155Tradable;

    @Autowired
    private ContractAddress contractAddress;

    @Autowired
    private Client client;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result<?> create(@RequestBody Map<String, Object> param) throws Exception {
        String initialOwner = (String)param.get("initialOwner");
        BigInteger initialSupply = Convert.toWei((String)param.get("initialSupply"), Convert.Unit.ETHER).toBigInteger();
        String uri = (String)param.get("uri");
        String data = (String)param.get("data");
        TransactionReceipt receipt = erc1155Tradable.create(initialOwner, initialSupply, uri, data.getBytes());
        if(receipt.isStatusOK()){
            Tuple1<BigInteger> tuple1 = erc1155Tradable.getCreateOutput(receipt);
            return Result.data(tuple1.getValue1());
        }
        return Result.fail("create is fail");
    }

    @RequestMapping(value = "/mint", method = RequestMethod.POST)
    public Result<?> mint(@RequestBody Map<String, Object> param) throws Exception {
        String to = (String)param.get("to");
        BigInteger id = BigInteger.valueOf((Integer)param.get("id"));
        BigInteger quantity = Convert.toWei((String)param.get("quantity"), Convert.Unit.ETHER).toBigInteger();
        String data = (String)param.get("data");
        TransactionReceipt receipt = erc1155Tradable.mint(to, id, quantity, data.getBytes());
        return Result.data(receipt);
    }

    @RequestMapping(value = "/totalSupply", method = RequestMethod.GET)
    public Result<?> totalSupply(@RequestParam Integer id) throws Exception {
        BigInteger amount = erc1155Tradable.totalSupply(BigInteger.valueOf(id));
        BigDecimal value = Convert.fromWei(amount.toString(), Convert.Unit.ETHER);
        return Result.data(value.toString());
    }

    @RequestMapping(value = "/uri", method = RequestMethod.GET)
    public Result<?> uri(@RequestParam Integer id) throws Exception {
        String uri = erc1155Tradable.uri(BigInteger.valueOf(id));
        return Result.data(uri);
    }

    @RequestMapping(value = "/balanceOf", method = RequestMethod.POST)
    public Result<?> balanceOf(@RequestBody Map<String, Object> param) throws Exception {
        String owner = (String)param.get("owner");
        BigInteger id = BigInteger.valueOf((Integer)param.get("id"));
        BigInteger amount = erc1155Tradable.balanceOf(owner, id);
        BigDecimal value = Convert.fromWei(amount.toString(), Convert.Unit.ETHER);
        return Result.data(value.toString());
    }

    @RequestMapping(value = "/safeTransferFrom", method = RequestMethod.POST)
    public Result<?> safeTransferFrom(@RequestBody Map<String, Object> param) throws Exception {
        String from = (String)param.get("from");
        String fromPrivateKey = (String)param.get("fromPrivateKey");
        String to = (String)param.get("to");
        BigInteger id = BigInteger.valueOf((Integer)param.get("id"));
        BigInteger amount = Convert.toWei((String)param.get("amount"), Convert.Unit.ETHER).toBigInteger();
        String data = (String)param.get("data");
        TransactionReceipt receipt = erc1155Tradable.setApprovalForAll(from, true);
        log.info("setApprovalForAll={}", JSON.toJSONString(receipt));

        CryptoKeyPair credentials = client.getCryptoSuite().createKeyPair(fromPrivateKey);
        ERC1155Tradable fromERC1155Tradable = ERC1155Tradable.load(contractAddress.getErc1155Tradable(), client, credentials);
        TransactionReceipt receipt1 = fromERC1155Tradable.safeTransferFrom(from, to, id, amount, data.getBytes());
        log.info("safeTransferFrom={}", JSON.toJSONString(receipt1));
        return Result.data(receipt1);
    }

}
