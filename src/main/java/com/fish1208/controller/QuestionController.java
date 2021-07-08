package com.fish1208.controller;

import com.alibaba.fastjson.JSON;
import com.fish1208.common.response.Result;
import com.fish1208.contract.KVPerson;
import com.fish1208.contract.QuestionAnswer;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple4;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.utils.Numeric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/contract/question")
public class QuestionController {

    @Autowired
    private QuestionAnswer questionAnswer;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<?> add(@RequestBody Map<String, Object> param) throws Exception {
        String question = (String)param.get("question");
        String answer = (String) param.get("answer");
        byte[] questionByte = Numeric.hexStringToByteArray(asciiToHex(question));
        byte[] answerByte = Numeric.hexStringToByteArray(asciiToHex(answer));
        TransactionReceipt receipt = questionAnswer.question(questionByte, answerByte);
        log.info("questionAnswer is question receipt = {}", JSON.toJSONString(receipt));
        return Result.data(receipt);
    }

    @RequestMapping(value = "/list-unsolved", method = RequestMethod.GET)
    public Result<?> listUnsolved() throws Exception {
        List list = questionAnswer.unsolvedList();
        return Result.data(list);
    }

    // String to 64 length HexString (equivalent to 32 Hex lenght)
    private String asciiToHex(String asciiValue)
    {
        char[] chars = asciiValue.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++)
        {
            hex.append(Integer.toHexString((int) chars[i]));
        }

        return hex.toString() + "".join("", Collections.nCopies(32 - (hex.length()/2), "00"));
    }
}
