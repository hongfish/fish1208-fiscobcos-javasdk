package com.fish1208.controller;

import com.fish1208.common.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 *账户控制器
 */
@Slf4j
@RestController
@RequestMapping("/chain/account")
public class AccountController {

    @Autowired
    private Client client;

    /**
     * 创建新账户
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result<?> create(){
        //创建普通外部账户
        CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().createKeyPair();
        //账户地址
        String accountAddress = cryptoKeyPair.getAddress();
        //账户私钥
        String privateKey = cryptoKeyPair.getHexPrivateKey();
        //账户公钥
        String publicKey = cryptoKeyPair.getHexPublicKey();

        log.info("生成账户地址={}，私钥={}，公钥={}", accountAddress, privateKey, publicKey);

        Map<String, Object> result = new HashMap<>();
        result.put("account",accountAddress);
        result.put("privateKey",privateKey);
        result.put("publicKey",publicKey);
        return Result.data(result);
    }

}
