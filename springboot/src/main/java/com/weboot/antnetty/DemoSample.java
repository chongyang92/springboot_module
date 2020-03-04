package com.weboot.antnetty;

import com.alipay.mychain.sdk.api.MychainClient;
import com.alipay.mychain.sdk.api.env.ClientEnv;
import com.alipay.mychain.sdk.api.env.ISslOption;
import com.alipay.mychain.sdk.api.env.SignerOption;
import com.alipay.mychain.sdk.api.env.SslBytesOption;
import com.alipay.mychain.sdk.api.logging.AbstractLoggerFactory;
import com.alipay.mychain.sdk.api.logging.ILogger;
import com.alipay.mychain.sdk.api.utils.ConfidentialUtil;
import com.alipay.mychain.sdk.api.utils.Utils;
import com.alipay.mychain.sdk.common.VMTypeEnum;
import com.alipay.mychain.sdk.crypto.MyCrypto;
import com.alipay.mychain.sdk.crypto.PublicKey;
import com.alipay.mychain.sdk.crypto.keyoperator.KeyOperator;
import com.alipay.mychain.sdk.crypto.keyoperator.Pkcs8KeyOperator;
import com.alipay.mychain.sdk.crypto.keypair.KeyTypeEnum;
import com.alipay.mychain.sdk.crypto.keypair.Keypair;
import com.alipay.mychain.sdk.crypto.signer.SignerBase;
import com.alipay.mychain.sdk.domain.account.Account;
import com.alipay.mychain.sdk.domain.account.AccountStatus;
import com.alipay.mychain.sdk.domain.account.AuthMap;
import com.alipay.mychain.sdk.domain.account.Identity;
import com.alipay.mychain.sdk.errorcode.ErrorCode;
import com.alipay.mychain.sdk.message.query.QueryAccountResponse;
import com.alipay.mychain.sdk.message.transaction.AbstractTransactionRequest;
import com.alipay.mychain.sdk.message.transaction.TransactionReceiptResponse;
import com.alipay.mychain.sdk.message.transaction.account.CreateAccountRequest;
import com.alipay.mychain.sdk.message.transaction.account.CreateAccountResponse;
import com.alipay.mychain.sdk.message.transaction.confidential.ConfidentialRequest;
import com.alipay.mychain.sdk.message.transaction.contract.CallContractRequest;
import com.alipay.mychain.sdk.message.transaction.contract.DeployContractRequest;
import com.alipay.mychain.sdk.type.BaseFixedSizeUnsignedInteger;
import com.alipay.mychain.sdk.utils.ByteUtils;
import com.alipay.mychain.sdk.utils.IOUtil;
import com.alipay.mychain.sdk.utils.RandomUtil;
import com.alipay.mychain.sdk.vm.EVMOutput;
import com.alipay.mychain.sdk.vm.EVMParameter;
import com.alipay.mychain.zoro.crypto.ecdsa.AbstractKeyPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoSample {
    /**
     * contract code
     */
    private static String contractCodeString
        =
        "6080604052633b9aca00600055600060015534801561001d57600080fd5b5033600281905550610492806100346000396000f3006080604"
            +
            "05260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063"
            +
            "af7c102c1461005c578063b2628df81461009d578063d4486019146100ec575b600080fd5b34801561006857600080fd5b5061008"
            +
            "76004803603810190808035906020019092919050505061013b565b6040518082815260200191505060405180910390f35b348015"
            +
            "6100a957600080fd5b506100d26004803603810190808035906020019092919080359060200190929190505050610158565b60405"
            +
            "1808215151515815260200191505060405180910390f35b3480156100f857600080fd5b5061012160048036038101908080359060"
            +
            "200190929190803590602001909291905050506102d8565b604051808215151515815260200191505060405180910390f35b60006"
            +
            "0036000838152602001908152602001600020549050919050565b6000600254331415156101d3576040517f08c379a00000000000"
            +
            "000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f5065726d697"
            +
            "373696f6e2064656e69656400000000000000000000000000000081525060200191505060405180910390fd5b6000548260015401"
            +
            "131580156101ee57506001548260015401135b80156101fa5750600082135b151561026e576040517f08c379a0000000000000000"
            +
            "00000000000000000000000000000000000000000815260040180806020018281038252600e8152602001807f496e76616c696420"
            +
            "76616c75652100000000000000000000000000000000000081525060200191505060405180910390fd5b816003600085815260200"
            +
            "190815260200160002060008282540192505081905550816001600082825401925050819055508183337f31a52246bf8c995cecfd"
            +
            "d5404cf290ae6c2f4e174e888e4de4fd208137ec274d60405160405180910390a46001905092915050565b6000816003600033815"
            +
            "26020019081526020016000205412151515610365576040517f08c379a00000000000000000000000000000000000000000000000"
            +
            "000000000081526004018080602001828103825260138152602001807f62616c616e6365206e6f7420656e6f75676821000000000"
            +
            "0000000000000000081525060200191505060405180910390fd5b60008213801561037757506000548213155b15156103eb576040"
            +
            "517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600e815"
            +
            "2602001807f496e76616c69642076616c756521000000000000000000000000000000000000815250602001915050604051809103"
            +
            "90fd5b816003600033815260200190815260200160002060008282540392505081905550816003600085815260200190815260200"
            +
            "1600020600082825401925050819055508183337f97c0c2106db19ca3c64afdc86820cd157d60361f777bf0e5323254d6c9689550"
            +
            "60405160405180910390a460009050929150505600a165627a7a72305820371af9e83b0e49ca71634c470c75e504d08db9abbaf39"
            + "92f30434f8d7a7994d40029";
    private static byte[] contractCode = ByteUtils.hexStringToBytes(contractCodeString);
    /**
     * contract id
     */
    private static String testContractId = "CreditManager" + System.currentTimeMillis();

    /**
     * baas上创建的帐户名字
     */
    private static final String account = "13520461988";
    private static Identity userIdentity;
    private static Keypair userKeypair;

    /**
     * create account test
     */
    private static Identity testAccount1 = Utils.getIdentityByName("test_account_"+System.currentTimeMillis());
    //private static Identity testAccount1 = Utils.getIdentityByName("myconcract"+System.currentTimeMillis());

    //创建两个账户
    private static Account mytestAccount1;
    private static Account mytestAccount2;

    /**
     * key pair for testKeyPair1
     */
    private static Keypair testKeyPair1;
    /**
     * key pair for testKeyPair2
     */
    private static Keypair testKeyPair2;

    private static String testAccount1Identity = "account_1";
    private static String testAccount2Identity = "account_2";

    /**
     * sdk client
     */
    private static MychainClient sdk;
    /**
     * client key password
     */
    private static String keyPassword = "CHONGyang@921016";  //根据实际情况更新，申请证书时候指定的SSL密码
    /**
     * user password
     */
    private static String userPassword = "CHONGyang@921016"; //根据实际情况更新。申请证书时，创建账户的密码
    /**
     * host ip
     */
    //47.103.163.178 18130
    private static String host = "47.103.163.178"; //根据实际情况更新，在BaaS平台，通过查看目标合约链"详情"，在"区块浏览器"中查看"节点详情"可获取链节点的 IP地址 和 端口号。

    /**
     * server port
     */
    private static int port = 18130;               //根据实际情况更新
    /**
     * trustCa password.
     */
    private static String trustStorePassword = "mychain";
    /**
     * mychain environment
     */
    private static ClientEnv env;
    /**
     * mychain is tee Chain
     */
    private static boolean isTeeChain = false;
    /**
     * tee chain publicKeys
     */
    private static List<byte[]> publicKeys = new ArrayList<byte[]>();
    /**
     * tee chain secretKey
     */
    private static String secretKey = "123456";

    private static void exit(String tag, String msg) {
        exit(String.format("%s error : %s ", tag, msg));
    }

    private static void exit(String msg) {
        System.out.println(msg);
        System.exit(0);
    }

    private static String getErrorMsg(int errorCode) {
        int minMychainSdkErrorCode = ErrorCode.SDK_INTERNAL_ERROR.getErrorCode();
        if (errorCode < minMychainSdkErrorCode) {
            return ErrorCode.valueOf(errorCode).getErrorDesc();
        } else {
            return ErrorCode.valueOf(errorCode).getErrorDesc();
        }
    }

    private static void initMychainEnv() throws IOException {
        // any user key for sign message
        String userPrivateKeyFile = "user.key";//账户的私钥
        userIdentity = Utils.getIdentityByName(account); //根据实际情况更新'gushui03'为'user.key'对应的账户名(BaaS申请证书时创建的账户名)
        Pkcs8KeyOperator pkcs8KeyOperator = new Pkcs8KeyOperator();
        userKeypair = pkcs8KeyOperator.load(IOUtil.inputStreamToByte(DemoSample.class.getClassLoader().getResourceAsStream(userPrivateKeyFile)), userPassword);

        // use publicKeys by tee
        if(isTeeChain) {
            Keypair keypair = new Pkcs8KeyOperator()
                .loadPubkey(
                    IOUtil.inputStreamToByte(DemoSample.class.getClassLoader().getResourceAsStream("test_seal_pubkey.pem")));
            byte[] publicKeyDer = keypair.getPubkeyEncoded(); //tee_rsa_public_key.pem 从BaaS下载获取
            publicKeys.add(publicKeyDer);
        }

        env = buildMychainEnv();

        ILogger logger = AbstractLoggerFactory.getInstance(DemoSample.class);
        env.setLogger(logger);
    }

    private static ClientEnv buildMychainEnv() throws IOException {
        InetSocketAddress inetSocketAddress = InetSocketAddress.createUnresolved(host, port);
        String keyFilePath = "client.key";
        String certFilePath = "client.crt";
        String trustStoreFilePath = "trustCa";

        // build ssl option
        ISslOption sslOption = new SslBytesOption.Builder()
            .keyBytes(IOUtil.inputStreamToByte(DemoSample.class.getClassLoader().getResourceAsStream(keyFilePath)))
            .certBytes(IOUtil.inputStreamToByte(DemoSample.class.getClassLoader().getResourceAsStream(certFilePath)))
            .keyPassword(keyPassword)
            .trustStorePassword(trustStorePassword)
            .trustStoreBytes(
                IOUtil.inputStreamToByte(DemoSample.class.getClassLoader().getResourceAsStream(trustStoreFilePath)))
            .build();

        List<InetSocketAddress> socketAddressArrayList = new ArrayList<InetSocketAddress>();
        socketAddressArrayList.add(inetSocketAddress);//SDK 支持多节点配置，当主节点不可用时，会自动切换到配置的其他节点进行重连，从而保证服务的稳定性。
        List<SignerBase> signerBaseList = new ArrayList<SignerBase>();
        SignerBase signerBase = MyCrypto.getInstance().createSigner(userKeypair);
        signerBaseList.add(signerBase);
        SignerOption signerOption = new SignerOption();
        signerOption.setSigners(signerBaseList);
        return ClientEnv.build(socketAddressArrayList, sslOption, signerOption);
    }


    private static void signRequest(AbstractTransactionRequest request) {
        // sign request
        long ts = sdk.getNetwork().getSystemTimestamp();
        request.setTxTimeNonce(ts, BaseFixedSizeUnsignedInteger.Fixed64BitUnsignedInteger
            .valueOf(RandomUtil.randomize(ts + request.getTransaction().hashCode())), true);
        request.complete();
        sdk.getConfidentialService().signRequest(env.getSignerOption().getSigners(), request);
    }

    private static void initAccount(){

        // generate key pair
        KeyTypeEnum keyTypeEnum = KeyTypeEnum.KEY_ECCK1_PKCS8;
        KeyOperator keyOperator = new Pkcs8KeyOperator();
        testKeyPair1 = keyOperator.generate(keyTypeEnum);
        System.out.println("generate testKeyPair1 success");
        testKeyPair2 = keyOperator.generate(keyTypeEnum);
        System.out.println("generate testKeyPair2 success");

        // build account
        mytestAccount1 = new Account();
        mytestAccount1.setIdentity(Utils.getIdentityByName(testAccount1Identity));
        mytestAccount1.setBalance(22220000);
        mytestAccount1.setStatus(AccountStatus.NORMAL);
        AuthMap authMap1 = new AuthMap();
        Map<PublicKey,Integer> map1 = new HashMap<>();
        map1.put(new PublicKey(testKeyPair1),1);
        authMap1.setAuthMap(map1);
        mytestAccount1.setAuthMap(authMap1);
        //mytestAccount1.setRecoverKey(new PublicKey(testKeyPair1.get));

        mytestAccount2 = new Account();
        mytestAccount2.setIdentity(Utils.getIdentityByName(testAccount2Identity));
        mytestAccount2.setBalance(33330000);
        mytestAccount2.setStatus(AccountStatus.NORMAL);
        AuthMap authMap2 = new AuthMap();
        Map<PublicKey,Integer> map2 = new HashMap<>();
        map2.put(new PublicKey(testKeyPair2),1);
        authMap2.setAuthMap(map2);
        mytestAccount2.setAuthMap(authMap2);
        //mytestAccount1.setRecoverKey(new PublicKey(testKeyPair1.get));

        System.out.println(mytestAccount1.getBalance());
        System.out.println(mytestAccount2.getBalance());

    }

   /* private static void initPrivateKeyList(){
        PrivateKey test1PrivateKey = null;
        Pkcs8KeyOperator pkcs8KeyOperator = new Pkcs8KeyOperator();
        try {
            test1PrivateKey = testKeyPair1.getPrivkeyId();
        } catch (Exception e) {
            e.printStackTrace();
            exit("initPrivateKeyList", "create test1PrivateKey failed");
        }

        PrivateKey test2PrivateKey = null;
        try {
            test2PrivateKey = testKeyPair2.getEcPrivateKey();
        } catch (Exception e) {
            e.printStackTrace();
            exit("initPrivateKeyList", "create test2PrivateKey failed");
        }

        // add all private keys into list
        userPrivateKeyArrayList.add(userPrivateKey);
        test1PrivateKeyArrayList.add(test1PrivateKey);
        test2PrivateKeyArrayList.add(test2PrivateKey);
    }*/

    private static void createAccount() {
        /*MychainParams params = new MychainParams.Builder()
                .gas(BigInteger.valueOf(4000000))
                .privateKeyList(userPrivateKeyArrayList)
                .build();*/

        // build CreateAccountRequest, user.key account creates testAccount1
        /*CreateAccountRequest request = CreateAccountRequest.build(userAccount,
                testAccount1, params, System.currentTimeMillis(), 0,
                BaseFixedSizeUnsignedInteger.Fixed64BitUnsignedInteger.valueOf(BigInteger.ONE));*/
        CreateAccountRequest request = new CreateAccountRequest(mytestAccount1.getIdentity(),mytestAccount1);
        CreateAccountResponse createAccountResult = sdk.getAccountService().createAccount(request);
        System.out.println("createAccountResult："+createAccountResult.getErrorCode());
        System.out.println("createAccountResult:"+createAccountResult.getExceptionMessage());
        // create testAccount1
        if (!createAccountResult.isSuccess()) {
            exit("createAccount", createAccountResult.getExceptionMessage());
        } else {
            System.out.println("create testAccount1 success.");
        }

        // build testAccount2 request, user.key account creates testAccount2
        /*CreateAccountRequest createAccount2Request = CreateAccountRequest.build(userAccount,
                testAccount2, params, System.currentTimeMillis(), 0,
                BaseFixedSizeUnsignedInteger.Fixed64BitUnsignedInteger.valueOf(BigInteger.ONE));*/

        // create testAccount2
        /*MychainBaseResult<ReplyTransactionReceipt> createAccountResult2 = sdk.getAccountService().createAccount(
                createAccount2Request);
        if (!createAccountResult2.isSuccess()) {
            exit("createAccount", getErrorMsg((int)createAccountResult2.getData().getTransactionReceipt().getResult()));
        } else {
            System.out.println("create testAccount2 success.");
        }*/

    }

    private static void deployContract() {
        EVMParameter contractParameters = new EVMParameter();

        // build DeployContractRequest
        DeployContractRequest request = new DeployContractRequest(userIdentity,
            Utils.getIdentityByName(testContractId), contractCode, VMTypeEnum.EVM,
            contractParameters, BigInteger.ZERO);

        //  set Gas limit
        // Make sure your account balance is greater than 50,000
        request.setTxGas(50000);

        TransactionReceiptResponse deployContractResult;
        if (isTeeChain) {
            signRequest(request);

            // generate transaction key
            byte[] transactionKey = ConfidentialUtil.keyGenerate(secretKey,
                request.getTransaction().getHash().getValue());

            ConfidentialRequest confidentialRequest = new ConfidentialRequest(request, publicKeys, transactionKey);

            deployContractResult = sdk.getConfidentialService().confidentialRequest(confidentialRequest);
        } else {
            deployContractResult = sdk.getContractService().deployContract(request);
        }

        // deploy contract
        if (!deployContractResult.isSuccess()
            || deployContractResult.getTransactionReceipt().getResult() != 0) {
            exit("deployContract",
                getErrorMsg((int)deployContractResult.getTransactionReceipt().getResult()));
        } else {
            System.out.println("deploy contract success.");
        }
    }

    private static void issue() {
        EVMParameter parameters = new EVMParameter("Issue(identity,int256)");
        parameters.addIdentity(userIdentity);
        parameters.addUint(BigInteger.valueOf(100));

        // build CallContractRequest
        CallContractRequest request = new CallContractRequest(userIdentity,
            Utils.getIdentityByName(testContractId), parameters, BigInteger.ZERO, VMTypeEnum.EVM);

        //  set Gas limit
        // Make sure your account balance is greater than 50,000
        request.setTxGas(50000);

        TransactionReceiptResponse callContractResult;
        if (isTeeChain) {
            signRequest(request);

            // generate transaction key
            byte[] transactionKey = ConfidentialUtil.keyGenerate(secretKey,
                request.getTransaction().getHash().getValue());

            ConfidentialRequest confidentialRequest = new ConfidentialRequest(request, publicKeys, transactionKey);

            callContractResult = sdk.getConfidentialService().confidentialRequest(confidentialRequest);
        } else {
            callContractResult = sdk.getContractService().callContract(request);
        }

        if (!callContractResult.isSuccess() || callContractResult.getTransactionReceipt().getResult() != 0) {
            exit("issue", getErrorMsg((int)callContractResult.getTransactionReceipt().getResult()));
        } else {
            System.out.println("issue success.");
        }
    }

    private static void transfer() {
        // contract parameters
        EVMParameter contractParameters = new EVMParameter("Transfer(identity,int256)");
        contractParameters.addIdentity(testAccount1);
        contractParameters.addUint(BigInteger.valueOf(50));

        CallContractRequest request = new CallContractRequest(userIdentity,
            Utils.getIdentityByName(testContractId), contractParameters, BigInteger.ZERO, VMTypeEnum.EVM);

        //  set Gas limit
        // Make sure your account balance is greater than 50,000
        request.setTxGas(50000);

        TransactionReceiptResponse callContractResult;
        if (isTeeChain) {
            signRequest(request);

            // generate transaction key
            byte[] transactionKey = ConfidentialUtil.keyGenerate(secretKey,
                request.getTransaction().getHash().getValue());

            ConfidentialRequest confidentialRequest = new ConfidentialRequest(request, publicKeys, transactionKey);

            callContractResult = sdk.getConfidentialService().confidentialRequest(confidentialRequest);
        } else {
            callContractResult = sdk.getContractService().callContract(request);
        }

        if (!callContractResult.isSuccess() || callContractResult.getTransactionReceipt().getResult() != 0) {
            exit("transfer", getErrorMsg((int)callContractResult.getTransactionReceipt().getResult()));
        } else {
            System.out.println("transfer success.");
        }
    }

    private static BigInteger query(Identity account) {
        // contract parameters
        EVMParameter parameters = new EVMParameter("Query(identity)");
        parameters.addIdentity(account);

        // build call contract request
        CallContractRequest request = new CallContractRequest(userIdentity,
            Utils.getIdentityByName(testContractId), parameters, BigInteger.ZERO, VMTypeEnum.EVM);

        //  set Gas limit
        // Make sure your account balance is greater than 50,000
        request.setTxGas(50000);

        TransactionReceiptResponse callContractResult;
        if (isTeeChain) {
            signRequest(request);

            // generate transaction key
            byte[] transactionKey = ConfidentialUtil.keyGenerate(secretKey,
                request.getTransaction().getHash().getValue());

            ConfidentialRequest confidentialRequest = new ConfidentialRequest(request, publicKeys, transactionKey);

            callContractResult = sdk.getConfidentialService().confidentialRequest(confidentialRequest);
        } else {
            callContractResult = sdk.getContractService().callContract(request);
        }

        if (!callContractResult.isSuccess() || callContractResult.getTransactionReceipt().getResult() != 0) {
            exit("query", getErrorMsg((int)callContractResult.getTransactionReceipt().getResult()));
        }

        byte[] output = null;
        if (isTeeChain) {
            output = ConfidentialUtil.decrypt(secretKey, callContractResult.getTransactionReceipt().getOutput(), request.getTransaction().getHash().hexStrValue());
        } else {
            output = callContractResult.getTransactionReceipt().getOutput();
        }

        if (output == null) {
            exit("decrypt tee", "decrypt tee output failed");
            return BigInteger.ZERO;
        }

        // decode return values
        EVMOutput contractReturnValues = new EVMOutput(ByteUtils.toHexString(output));
        return contractReturnValues.getUint();
    }

    private static void expect(BigInteger balance, BigInteger expectBalance) {
        if (balance.compareTo(expectBalance) != 0) {
            exit("expect", "the account value is not expected.");
        } else {
            System.out.println("check account balance success.");
        }
    }

    private static void initSdk() {
        sdk = new MychainClient();
        boolean initResult = sdk.init(env);
        if (!initResult) {
            exit("initSdk", "sdk init failed.");
        }
    }

    /**
     * 1.拥有企业支付宝账号，购买后可以使用蚂蚁金服提供的各种服务，包括蚂蚁区块链Baas平台、刷脸认证
     *
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        /** 四个文件的理解
         * 客户端要确认链的身份， 需要提前获取对应的CA证书trustCa。
         * 链要能确认客户端的身份，需要客户端提供用户（机构）的×××书 client.crt和client.key。
         * 为了交易能够验证成功，用户需要拥有发起交易账户的私钥user.key。
         */
        //step 1:init mychain env.
        /*
        * 设置账户名(account)、账户私钥保护密码(userPassword)、账户私钥文件(user.key)证书
        * account相当于，一个人拥有的一个银行账号;userPassword银行账号的密码；
        * client相当于一个分行机构；user相当于在分行注册的用户；
        * 上面是转账前准备工作，接下来要和蚂蚁金服Baas平台的合约体验链的其中一个节点(合约体验链共4个节点)建立SSL连接，
        * 还需要三个证书，CA 机构的根证书（trustCa）、客户端的证书文件（client.crt）、客户端的私钥文件（client.key）（上面已经提供了user.key）
        * 以及客户端的私钥文件(client.key)的SSL私钥密码keyPassword;CA 机构的根证书（trustCa）的密码trustStorePassword
        * 密钥对(公钥、私钥)登陆(区别于用户名密码)，拿到这把私钥钥匙和Baas平台已有的公钥校验，即可成功登陆
        * 至此，和Baas平台的合约体验链的某一节点连接的准备工作已完成；
        * */
        //SDK 支持多节点配置，当主节点不可用时，会自动切换到配置的其他节点进行重连，从而保证服务的稳定性。
        initMychainEnv();

        //step 2: init sdk client
        //建立连接
        initSdk();

        /*// 创建账号
        String newAccountString = "1454102071@qq.com";
        Identity newUserIdentity = Utils.getIdentityByName(newAccountString);
        Account newAccount = new Account();
        newAccount.setIdentity(newUserIdentity);
        newAccount.setBalance(100000);
        BigInteger newAmount = new BigInteger("1000000");
        //CreateAccountRequest request = new CreateAccountRequest(newUserIdentity, newAccount, newAmount);
        // accountId创建account账号
        CreateAccountRequest request = new CreateAccountRequest(newUserIdentity,newAccount);
        // 参考错误信息说明文档内容，检查返回的数据
        CreateAccountResponse response = sdk.getAccountService().createAccount(request);
        System.out.println("11111111:"+response.getBlockNumber());
        System.out.println("22222222:"+response.getExceptionMessage());
        System.out.println("33333333:"+response.getErrorCode());
*/
        //查询账户信息

        /*QueryAccountResponse queryAccountResponse = sdk.getQueryService().queryAccount(userIdentity);
        System.out.println("queryAccountResponse:"+queryAccountResponse.getAccount().getBalance());
        System.out.println("queryAccountResponse:"+queryAccountResponse.getBlockNumber());*/
        //QueryAccountResponse queryAccountResponse = sdk.getQueryService().queryAccount(Utils.getIdentityByName("Xy19952"));
        QueryAccountResponse queryAccountResponse = sdk.getQueryService().queryAccount(userIdentity);

        System.out.println("queryAccountResponse:"+queryAccountResponse.getBlockNumber());//浏览器，查询块高
        System.out.println("queryAccountResponse:"+queryAccountResponse.getAccount().getBalance());
        System.out.println("queryAccountResponse:"+queryAccountResponse.getErrorCode());
        System.out.println("queryAccountResponse:"+queryAccountResponse.toString());

        //init testAccount1 、testAccount2
        initAccount();

        //create testAccount1 、testAccount2
        createAccount();

        QueryAccountResponse queryAccountResponse1 = sdk.getQueryService().queryAccount(Utils.getIdentityByName(testAccount1Identity));

        System.out.println("queryAccountResponse1:"+queryAccountResponse1.getBlockNumber());//浏览器，查询块高
        System.out.println("queryAccountResponse1:"+queryAccountResponse1.getAccount().getBalance());
        System.out.println("queryAccountResponse1:"+queryAccountResponse1.getErrorCode());
        System.out.println("queryAccountResponse1:"+queryAccountResponse1.toString());
        //step 3 : deploy a contract using useridentity.
        //deployContract();

        //step 4 issue 100 assets to testAccount1.
        //issue();

        //step 5 : transfer 50 assets from useridentity to testAccount1
        //transfer();

        //step 6 : query testAccount1 whose balance should be 50.
        //BigInteger balance = query(testAccount1);

        //step 7 : compare to expect balance.
        //expect(balance, BigInteger.valueOf(50));


        //step 8 : sdk shut down
        sdk.shutDown();
    }
}
