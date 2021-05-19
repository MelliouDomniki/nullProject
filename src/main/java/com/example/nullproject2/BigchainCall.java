package com.example.nullproject2;

import java.io.IOException;
import java.security.KeyPair;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.bigchaindb.builders.BigchainDbConfigBuilder;
import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.*;
import com.bigchaindb.util.Base58;
import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.entity.User;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;

import net.i2p.crypto.eddsa.EdDSAPublicKey;
import okhttp3.Response;


public class BigchainCall {

    //public   BigchainDBJavaDriverUsageExample(String args[]) throws Exception {
    public   BigchainCall() throws Exception {

//        System.out.println(Base58.encode(keys.getPublic().getEncoded()));
//        System.out.println(Base58.encode(keys.getPrivate().getEncoded()));

        // create metadata


//        //execute CREATE transaction
//        String txId = doCreate(assetData, metaData, keys);
//
//        //create transfer metadata
//        MetaData transferMetadata = new MetaData();
//        transferMetadata.setMetaData("where is he now?", "Japan");
//        System.out.println("(*) Transfer Metadata Prepared..");
//
//        //let the transaction commit in block
//        Thread.sleep(5000);
//
//        //execute TRANSFER transaction on the CREATED asset
//        doTransfer(txId, transferMetadata, keys);

    }

    public static String doCreate( KeyPair keys) throws Exception {

        BigchainDbConfigBuilder
                .baseUrl("http://localhost:9984/") //or use http://testnet.bigchaindb.com
                .addToken("app_id", "")
                .addToken("app_key", "").setup();

        Map<String, String> assetData = new TreeMap<String, String>() {{
            put("name", "James Bond");
            put("age", "doesn't matter");
            put("purpose", "saving the world");
        }};
        System.out.println("(*) Assets Prepared..");

        MetaData metaData = new MetaData();
        metaData.setMetaData("where is he now?", "Thailand");
        System.out.println("(*) Metadata Prepared..");

        try {
            //build and send CREATE transaction
            Transaction transaction = null;

            transaction = BigchainDbTransactionBuilder
                    .init()
                    .addAssets(assetData, TreeMap.class)
                    .addMetaData(metaData)
                    .operation(Operations.CREATE)
                    .buildAndSign((EdDSAPublicKey) keys.getPublic(), (EdDSAPrivateKey) keys.getPrivate())
                    .sendTransaction(handleServerResponse("c"));

            System.out.println("(*) CREATE Transaction sent.. - " + transaction.getId());
            return transaction.getId();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public static void doTransfer(String txId, KeyPair keys,KeyPair keys2) throws Exception {


        BigchainDbConfigBuilder
                .baseUrl("http://localhost:9984/") //or use http://testnet.bigchaindb.com
                .addToken("app_id", "")
                .addToken("app_key", "").setup();

        MetaData transferMetadata = new MetaData();
        transferMetadata.setMetaData("where is he now?", "Japan");
        System.out.println("(*) Transfer Metadata Prepared..");
        Thread.sleep(5000);

        Map<String, String> assetData = new TreeMap<String, String>();
        assetData.put("id", txId);

        try {
            //which transaction you want to fulfill?
            FulFill fulfill = new FulFill();
            fulfill.setOutputIndex(0);
            fulfill.setTransactionId(txId);


            //build and send TRANSFER transaction
            Transaction transaction = BigchainDbTransactionBuilder
                    .init()
                    .addInput(null, fulfill, (EdDSAPublicKey) keys.getPublic())
                    .addOutput("1", (EdDSAPublicKey) keys2.getPublic())
                    .addAssets(txId, String.class)
                    .addMetaData(transferMetadata)
                    .operation(Operations.TRANSFER)
                    .buildAndSign((EdDSAPublicKey) keys.getPublic(), (EdDSAPrivateKey) keys.getPrivate())
                    .sendTransaction(handleServerResponse("t"));

            System.out.println("(*) TRANSFER Transaction sent.. - " + transaction.getId());


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static GenericCallback handleServerResponse(String type) {
        //define callback methods to verify response from BigchainDBServer
        GenericCallback callback = new GenericCallback() {

            @Override
            public void transactionMalformed(Response response) {
                System.out.println("malformed " + response.message());
                System.out.println("Transaction failed" + type);
            }

            @Override
            public void pushedSuccessfully(Response response) {
                System.out.println("pushedSuccessfully");
                System.out.println("Transaction posted successfully" + type);
            }

            @Override
            public void otherError(Response response) {
                System.out.println("otherError" + response.message());
                System.out.println("Transaction failed" + type);
            }
        };

        return callback;
    }
//
//
//    /**
//     * generates EdDSA keypair to sign and verify transactions
//     * @return KeyPair
//     */
    public static KeyPair getKeys() {
        //  prepare your keys
        net.i2p.crypto.eddsa.KeyPairGenerator edDsaKpg = new net.i2p.crypto.eddsa.KeyPairGenerator();
        KeyPair keyPair = edDsaKpg.generateKeyPair();
        //keyPair = axepa
        System.out.println("(*) Keys Generated..");
        return keyPair;

    }
//
//    /*
//     * performs CREATE transactions on BigchainDB network
//     * @param assetData data to store as asset
//     * @param metaData data to store as metadata
//     * @param keys keys to sign and verify transaction
//     * @return id of CREATED asset
//     */
//    public static String doCreate(User h, Patient pat, Date date, String vid, KeyPair keys) throws Exception {
//
//        BigchainDbConfigBuilder
//                .baseUrl("http://localhost:9984/")
//                .addToken("header1", "")
//                .addToken("header2", "").setup();
//
//          //KeyPair keys = getKeys();
////        PublicKey pubkey = h.getPublickey();
////        PrivateKey prikey = h.getPrivatekey();
//
//
//        Map<String, String> assetData = new TreeMap<String, String>() {{
//            //stoixeia vaccination
//            put("patient-id",pat.getId());
//            put("patient-age", String.valueOf(pat.getAge()));
//        }};
//        System.out.println("(*) Assets Prepared..");
//
//        // create metadata
//        MetaData metaData = new MetaData();
//        metaData.setMetaData("date", date.toString());
//        metaData.setMetaData("hospital-name", h.getName());
//        metaData.setMetaData("hospital-city", h.getCity());
//        metaData.setMetaData("hospital-country", h.getCountry());
//        metaData.setMetaData("vaccine-id", vid);
//        metaData.setMetaData("vaccination-status", pat.getStatus().toString());
//        System.out.println("(*) Metadata Prepared.." );
//
//        try {
//            //build and send CREATE transaction
//            Transaction transaction = null;
//
//            transaction = BigchainDbTransactionBuilder
//                    .init()
//                    .addAssets(assetData, TreeMap.class)
//                    .addMetaData(metaData)
//                    .operation(Operations.CREATE)
//                    .buildAndSign((EdDSAPublicKey) keys.getPublic(), (EdDSAPrivateKey) keys.getPrivate())
//                    .sendTransaction(handleServerResponse("c"));
//
//            System.out.println("(*) CREATE Transaction sent.. - " + transaction.getId());
//            System.out.println(transaction.getAsset().getData());
//            return transaction.getId();
//
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    public static void doTransfer(String txId, MetaData metaData, KeyPair keys) throws Exception {
//
//        BigchainDbConfigBuilder
//                .baseUrl("http://localhost:9984/")
//                .addToken("header1", "")
//                .addToken("header2", "").setup();
//        Map<String, String> assetData = new TreeMap<String, String>();
//        assetData.put("id", txId);
//
//        try {
//
//
//            //which transaction you want to fulfill?
//            FulFill fulfill = new FulFill();
//            fulfill.setOutputIndex(0);
//            fulfill.setTransactionId(txId);
//
//
//            //build and send TRANSFER transaction
//            Transaction transaction = BigchainDbTransactionBuilder
//                    .init()
//                    .addInput(null, fulfill, (EdDSAPublicKey) keys.getPublic())
//                    .addOutput("1", (EdDSAPublicKey) keys.getPublic())
//                    .addAssets(txId, String.class)
//                    .addMetaData(metaData)
//                    .operation(Operations.TRANSFER)
//                    .buildAndSign((EdDSAPublicKey) keys.getPublic(), (EdDSAPrivateKey) keys.getPrivate())
//                    .sendTransaction(handleServerResponse("t"));
//
//            System.out.println("(*) TRANSFER Transaction sent.. - " + transaction.getId());
//
//
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//    }

//    public static void doTransfer(String txId, Date date,User h, String vid, PatientStatus patStatus) throws Exception {
//
//        Map<String, String> assetData = new TreeMap<String, String>();
//        assetData.put("id", txId);
//        MetaData trmetadata = new MetaData();
//        trmetadata.setMetaData("date", date.toString());
//        trmetadata.setMetaData("hospital-name", h.getName());
//        trmetadata.setMetaData("hospital-city", h.getCity());
//        trmetadata.setMetaData("hospital-country", h.getCountry());
//        trmetadata.setMetaData("vaccine-id", vid);
//        trmetadata.setMetaData("vaccination-status", patStatus.toString());
//        KeyPair previous = getKeys();
//        KeyPair targetKeys = getKeys();
//
//        try {
//
//
//            //which transaction you want to fulfill?
//            FulFill fulfill = new FulFill();
//            fulfill.setOutputIndex(0);
//            fulfill.setTransactionId(txId);
//
//
//            //build and send TRANSFER transaction
//            Transaction transaction = BigchainDbTransactionBuilder
//                    .init()
//                    //keys apo idi owner
//                    .addInput(null, fulfill, (EdDSAPublicKey) previous.getPublic())
//                    //keys apo neo owner
//                    .addOutput("1", (EdDSAPublicKey) targetKeys.getPublic())
//                    .addAssets(txId, String.class)
//                    .addMetaData(trmetadata)
//                    .operation(Operations.TRANSFER)
//                    //keys apo palio owner
//                    .buildAndSign((EdDSAPublicKey) previous.getPublic(), (EdDSAPrivateKey) previous.getPrivate())
//                    .sendTransaction(handleServerResponse("t"));
//
//            System.out.println("(*) TRANSFER Transaction sent.. - " + transaction.getId());
//
//
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//    }
    // BigchainDBJavaDriverUsageExample examples = new BigchainDBJavaDriverUsageExample();


    //configuration
//        BigchainDbConfigBuilder
//                .baseUrl("http://localhost:9984/")
//                .addToken("header1", "")
//	            .addToken("header2", "").setup();


    //generate Keys gia kathe nosokomeio
    //KeyPair keys = BigchainCall.getKeys();

//        System.out.println(Base58.encode(keys.getPublic().getEncoded()));
//        System.out.println(Base58.encode(keys.getPrivate().getEncoded()));

    // create New asset
//        Map<String, String> assetData = new TreeMap<String, String>() {{
//            //stoixeia vaccination
//            put("patient-id", "324543");
//        }};
//        System.out.println("(*) Assets Prepared..");
//
//        // create metadata
//        MetaData metaData = new MetaData();
//        metaData.setMetaData("date", "12/01/21");
//        metaData.setMetaData("vaccine-id", "12");
//        metaData.setMetaData("vaccination-status", "PENDING");
//        System.out.println("(*) Metadata Prepared.." );

    //execute CREATE transaction
    //txId = to id tou asset
    //String txId = this.doCreate(assetData, metaData, keys);

    //KeyPair targetKeys = BigchainCall.getKeys();

    //create transfer metadata
    //MetaData transferMetadata = new MetaData();
    //prepei na vazo kai ayta pou den allazoun an metatrepo kapoia mono
    //transferMetadata.setMetaData("vaccination-status", "COMPLETED");
    //System.out.println("(*) Transfer Metadata Prepared..");

    //let the transaction commit in block
    //Thread.sleep(5000);

    //execute TRANSFER transaction on the CREATED asset
    //keys= new hospital
    //this.doTransfer(txId, transferMetadata, keys, targetKeys);

}
