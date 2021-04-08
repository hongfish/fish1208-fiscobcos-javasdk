package com.fish1208.bcos.config;

import com.fish1208.bcos.ContractAddress;
import com.fish1208.contract.KVPerson;
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

    @Bean
    public KVPerson loadImage(Client client){
        return KVPerson.load(kVPerson, client, client.getCryptoSuite().getCryptoKeyPair());
    }

    @Bean
    public ContractAddress setAddress(){
        log.info("kVPerson={}", kVPerson);
        ContractAddress contractAddress = new ContractAddress();
        contractAddress.setKVPerson(kVPerson);
        return contractAddress;
    }

    public String getkVPerson() {
        return kVPerson;
    }

    public void setkVPerson(String kVPerson) {
        this.kVPerson = kVPerson;
    }

}
