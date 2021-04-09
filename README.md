# 基于FiscoBcos的java-sdk实现区块链服务
## 项目结构
基于spring-boot的mvn项目

![001.png](https://github.com/hongfish/fish1208-fiscobcos-javasdk/blob/main/src/main/resources/image/001.png)
* conf   链证书、机构证书、机构私钥、账户

![002.png](https://github.com/hongfish/fish1208-fiscobcos-javasdk/blob/main/src/main/resources/image/002.png)
* contract 应用合约，sol文件

![003.png](https://github.com/hongfish/fish1208-fiscobcos-javasdk/blob/main/src/main/resources/image/003.png)
* application-dev.yml
```
channel-service:
  group-id: 1    # sdk实际连接的群组
config-toml:
  config-path: classpath:config.toml   #区块链集群的配置文件
contract-address:
  kVPerson: "0x3cc40ecd5000f58c3458fef29b91114bd5e18da3"   #合约地址
```

* config.toml 
```
[cryptoMaterial]

certPath = "conf" 
caCert = "conf/ca.crt"   #链证书
sslCert = "conf/sdk.crt"  #机构证书
sslKey = "conf/sdk.key"  #机构私钥
#enSslCert = "conf/gm/gmensdk.crt"    #集群为国密版，则需要配置
#enSslKey = "conf/gm/gmensdk.key"

[network]
peers=["192.168.160.135:20200", "192.168.160.135:20201"]  #节点ip:port

[account]
keyStoreDir = "account"
accountFilePath = "conf/0x9ff96dcf17f27ddd643c23bc1236733aa92a1f20.pem"

accountFileFormat = "pem" 
accountAddress = "0x9ff96dcf17f27ddd643c23bc1236733aa92a1f20" #账户
```

* pom.xml
引用fisco-bcos-java-sdk的2.7.1版本

![004.png](https://github.com/hongfish/fish1208-fiscobcos-javasdk/blob/main/src/main/resources/image/004.png)

## 拷贝证书
### 集群的链证书、机构证书、机构私钥复制项目conf文件下
![005.png](https://github.com/hongfish/fish1208-fiscobcos-javasdk/blob/main/src/main/resources/image/005.png)
### 控制台下账户复制项目conf文件下
![006.png](https://github.com/hongfish/fish1208-fiscobcos-javasdk/blob/main/src/main/resources/image/006.png)
## 控制台部署合约
![007.png](https://github.com/hongfish/fish1208-fiscobcos-javasdk/blob/main/src/main/resources/image/007.png)
```
deploy KVPerson 
transaction hash: 0x72908963644b7e897bf03d0a9ddb9f76428f5b1684aee89eb251d0adf15bdb75
contract address: 0x3cc40ecd5000f58c3458fef29b91114bd5e18da3
```

### 拷贝合约地址
把合约地址复制到项目的application-dev.yml配置文件里，通过合约地址来加载合约，获取合约对象。
![008.png](https://github.com/hongfish/fish1208-fiscobcos-javasdk/blob/main/src/main/resources/image/008.png)

### 生成java文件
1. 合约转换成java文件
```
./sol2java.sh com.fish1208.contract ~/console-all/console-A/contracts/solidity/KVPerson.sol ~/console-all/console-A/contracts/console/
```
![009.png](https://github.com/hongfish/fish1208-fiscobcos-javasdk/blob/main/src/main/resources/image/009.png)

2. 将转换后的java文件复制到项目com.fish1208.contract包里

![010.png](https://github.com/hongfish/fish1208-fiscobcos-javasdk/blob/main/src/main/resources/image/010.png)

## 代码开发
### BcosSDKConfig.java
通过config-toml.toml配置文件获取BcosSDK对象

### ClientConfig.java
通过application-dev.yml配置文件的channel-service得到群组，获取Client连接

### ContractConfig.java
通过application-dev.yml配置文件的contract-address得到合约地址，用来加载合约，获取合约对象

### PersonController.java
调用合约的set、get方法，进行数据上链、链上数据查询。

## 项目启动
![011.png](https://github.com/hongfish/fish1208-fiscobcos-javasdk/blob/main/src/main/resources/image/011.png)

## 调用接口
### 执行KVPerson合约set方法
http://127.0.0.1:7022/contract/person/set

**请求**
```$xslt
POST /contract/person/set HTTP/1.1 
Content-Type: application/json 
{
	"id":"1",
	"name":"袁洪相",
	"age":100,
	"sex":"男"
}
```
![012.png](https://github.com/hongfish/fish1208-fiscobcos-javasdk/blob/main/src/main/resources/image/012.png)
执行KVPerson合约get方法
http://127.0.0.1:7022/contract/person/get?id=1

**请求**
```$xslt
GET /contract/person/get HTTP/1.1  
id=1
```
![013.png](https://github.com/hongfish/fish1208-fiscobcos-javasdk/blob/main/src/main/resources/image/013.png)


## Github地址
https://github.com/hongfish/fish1208-fiscobcos-javasdk
