package com.fish1208.bcos.config;

import com.fish1208.bcos.ContractAddress;
import com.fish1208.contract.*;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.client.Client;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConfigurationProperties(prefix = "contract-address")
public class ContractConfig {

    private String kVPerson;
    private String questionAnswer;
    private String worldCup;
    private String billController;
    private String erc1155;
    private String erc1155Tradable;

    @Bean
    public ERC1155Tradable loadERC1155Tradable(Client client){
        return ERC1155Tradable.load(erc1155Tradable, client, client.getCryptoSuite().getCryptoKeyPair());
    }

    @Bean
    public ERC1155 loadERC1155(Client client){
        return ERC1155.load(erc1155, client, client.getCryptoSuite().getCryptoKeyPair());
    }

    @Bean
    public KVPerson loadKVPerson(Client client){
        return KVPerson.load(kVPerson, client, client.getCryptoSuite().getCryptoKeyPair());
    }

    @Bean
    public QuestionAnswer loadQuestionAnswer(Client client){
        return QuestionAnswer.load(questionAnswer, client, client.getCryptoSuite().getCryptoKeyPair());
    }

    @Bean
    public WorldCup loadWorldCup(Client client){
        return WorldCup.load(worldCup, client, client.getCryptoSuite().getCryptoKeyPair());
    }

    @Bean
    public BillController loadBillController(Client client){
        return BillController.load(billController, client, client.getCryptoSuite().getCryptoKeyPair());
    }

    @Bean
    public ContractAddress setAddress(){
        log.info("kVPerson={}, questionAnswer={}", kVPerson, questionAnswer);
        ContractAddress contractAddress = new ContractAddress();
        contractAddress.setKVPerson(kVPerson);
        contractAddress.setQuestionAnswer(questionAnswer);
        contractAddress.setErc1155Tradable(erc1155Tradable);
        return contractAddress;
    }

    public String getErc1155Tradable() {
        return erc1155Tradable;
    }

    public void setErc1155Tradable(String erc1155Tradable) {
        this.erc1155Tradable = erc1155Tradable;
    }

    public String getErc1155() {
        return erc1155;
    }

    public void setErc1155(String erc1155) {
        this.erc1155 = erc1155;
    }

    public String getkVPerson() {
        return kVPerson;
    }

    public void setkVPerson(String kVPerson) {
        this.kVPerson = kVPerson;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public String getWorldCup() {
        return worldCup;
    }

    public void setWorldCup(String worldCup) {
        this.worldCup = worldCup;
    }

    public String getBillController() {
        return billController;
    }

    public void setBillController(String billController) {
        this.billController = billController;
    }
}
