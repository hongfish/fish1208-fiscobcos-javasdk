package com.fish1208.controller;

import com.alibaba.fastjson.JSON;
import com.fish1208.common.response.Result;
import com.fish1208.contract.WorldCup;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 世界杯竟猜
 */
@Slf4j
@RestController
@RequestMapping("/contract/world/cup")
public class WorldCupController {

    @Autowired
    private WorldCup worldCup;

    /**
     *
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/create-game", method = RequestMethod.POST)
    public Result<?> createGame(@RequestBody Map<String, Object> param) throws Exception {

        String gameName = (String)param.get("gameName");
        String homeTeamName = (String)param.get("homeTeamName");
        String guestTeamName = (String)param.get("guestTeamName");
        BigInteger gameStartTime = new BigInteger(String.valueOf(parseDate((String)param.get("gameStartTime"))));
        BigInteger betOffTime = new BigInteger(String.valueOf(parseDate((String)param.get("betOffTime"))));
        BigInteger medalTime = new BigInteger(String.valueOf(parseDate((String)param.get("medalTime"))));
        TransactionReceipt receipt = worldCup.createGame(gameName, homeTeamName, guestTeamName, gameStartTime, betOffTime, medalTime);
        log.info("worldCup is createGame, receipt = {}", JSON.toJSONString(receipt));
        return Result.data(receipt);
    }

    private long parseDate(String date){
        long time = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).
                parse(date, new ParsePosition(0)).getTime() / 1000;
        return time;
    }

}
