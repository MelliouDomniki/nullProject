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
import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.entity.User;
import com.example.nullproject2.entity.Vaccine;
import com.example.nullproject2.enumerations.VaccinationStatus;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;

import net.i2p.crypto.eddsa.EdDSAPublicKey;
import okhttp3.Response;


public class BigchainCall {

    //public   BigchainDBJavaDriverUsageExample(String args[]) throws Exception {
    public   BigchainCall() throws Exception {

    }

    public static void doCreate(User h, Patient p, Date d, Vaccine v) throws Exception {

        KeyPair keys = h.getKeyPairs();
        BigchainDbConfigBuilder
                .baseUrl("http://localhost:9984/") //or use http://testnet.bigchaindb.com
                .addToken("app_id", "")
                .addToken("app_key", "").setup();

        Map<String, String> assetData = new TreeMap<String, String>() {{
            put("AMKA", p.getAmka());
            //put("age", String.valueOf(p.getAge()));
        }};
        System.out.println("(*) Assets Prepared..");

        MetaData metaData = new MetaData();
        metaData.setMetaData("date", d.toString());
        metaData.setMetaData("age",  String.valueOf(p.getAge()) );
        metaData.setMetaData("hospital-name", h.getName());
        metaData.setMetaData("hospital-city", h.getCity());
        metaData.setMetaData("hospital-country", h.getCountry());
        metaData.setMetaData("vaccine-id", v.getId());
        metaData.setMetaData("vaccination-status", VaccinationStatus.PENDING.toString());
        System.out.println("(*) Metadata Prepared..");

        try {
            //build and send CREATE transaction
            Transaction transaction;

            transaction = BigchainDbTransactionBuilder
                    .init()
                    .addAssets(assetData, TreeMap.class)
                    .addMetaData(metaData)
                    .operation(Operations.CREATE)
                    .buildAndSign((EdDSAPublicKey) keys.getPublic(), (EdDSAPrivateKey) keys.getPrivate())
                    .sendTransaction(handleServerResponse("c"));

            System.out.println("(*) CREATE Transaction sent.. - " + transaction.getId());


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void doUpdate(String transId, String assetId, Date d, Vaccine v, User next) throws Exception {


        BigchainDbConfigBuilder
                .baseUrl("http://localhost:9984/") //or use http://testnet.bigchaindb.com
                .addToken("app_id", "")
                .addToken("app_key", "").setup();

        MetaData transferMetadata = new MetaData();
        transferMetadata.setMetaData("where is he now?", "Japan");
        System.out.println("(*) Transfer Metadata Prepared..");
        Thread.sleep(5000);

        Map<String, String> assetData = new TreeMap<String, String>();
        assetData.put("id", assetId);

        try {
            //which transaction you want to fulfill?
            FulFill fulfill = new FulFill();
            fulfill.setOutputIndex(0);
            fulfill.setTransactionId(transId);

            //build and send TRANSFER transaction
            Transaction transaction = BigchainDbTransactionBuilder
                    .init()
                    .addInput(null, fulfill, (EdDSAPublicKey) next.getKeyPairs().getPublic())
                    .addOutput("1", (EdDSAPublicKey) next.getKeyPairs().getPublic())
                    .addAssets(assetId, String.class)
                    .addMetaData(transferMetadata)
                    .operation(Operations.TRANSFER)
                    .buildAndSign((EdDSAPublicKey) next.getKeyPairs().getPublic(), (EdDSAPrivateKey) next.getKeyPairs().getPrivate())
                    .sendTransaction(handleServerResponse("t"));

            System.out.println("(*) TRANSFER Transaction sent.. - " + transaction.getId());



        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void doTransfer(String transId, KeyPair previous, String assetId, Date d, Vaccine v, User next) throws Exception {


        BigchainDbConfigBuilder
                .baseUrl("http://localhost:9984/") //or use http://testnet.bigchaindb.com
                .addToken("app_id", "")
                .addToken("app_key", "").setup();

        MetaData transferMetadata = new MetaData();
        transferMetadata.setMetaData("where is he now?", "Japan");
        System.out.println("(*) Transfer Metadata Prepared..");
        Thread.sleep(5000);

        Map<String, String> assetData = new TreeMap<String, String>();
        assetData.put("id", assetId);

        try {
            //which transaction you want to fulfill?
            FulFill fulfill = new FulFill();
            fulfill.setOutputIndex(0);
            fulfill.setTransactionId(transId);

            //build and send TRANSFER transaction
            Transaction transaction = BigchainDbTransactionBuilder
                    .init()
                    .addInput(null, fulfill, (EdDSAPublicKey) previous.getPublic())
                    .addOutput("1", (EdDSAPublicKey) next.getKeyPairs().getPublic())
                    .addAssets(assetId, String.class)
                    .addMetaData(transferMetadata)
                    .operation(Operations.TRANSFER)
                    .buildAndSign((EdDSAPublicKey) previous.getPublic(), (EdDSAPrivateKey) previous.getPrivate())
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


//    public static  doTransfer(String txId, Date date,User h, String vid, PatientStatus patStatus) throws Exception {
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

}
