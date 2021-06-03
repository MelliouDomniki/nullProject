package com.example.nullproject2;

import java.io.IOException;
import java.security.KeyPair;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import com.bigchaindb.builders.BigchainDbConfigBuilder;
import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.*;
import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.entity.User;
import com.example.nullproject2.entity.Vaccine;
import com.example.nullproject2.enumerations.VaccinationStatus;
import com.example.nullproject2.enumerations.VaccineStatus;
import com.example.nullproject2.resources.VaccineController;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;

import net.i2p.crypto.eddsa.EdDSAPublicKey;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;


public class BigchainCall {


    public   BigchainCall()  {

    }


    public  static String doCreate(User h, Patient p, Date d, String brand ) throws Exception {

        KeyPair keys = h.getKeyPairs();
        BigchainDbConfigBuilder
                .baseUrl("http://localhost:9984/") //or use http://testnet.bigchaindb.com
                .addToken("app_id", "")
                .addToken("app_key", "").setup();

        Map<String, String> assetData = new TreeMap<String, String>() {{
            put("AMKA", p.getAmka());
            put("age", String.valueOf(p.getAge()));
            put("name",p.getName());
        }};
        System.out.println("(*) Assets Prepared..");

        MetaData metaData = new MetaData();
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        metaData.setMetaData("date", form.format(d));
        metaData.setMetaData("hospital-name", h.getName());
        metaData.setMetaData("hospital-city", h.getCity());
        metaData.setMetaData("hospital-country", h.getCountry());
        metaData.setMetaData("brand", brand);
        metaData.setMetaData("status", VaccinationStatus.PENDING.toString());
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
            return handleServerResponse("c").toString();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "ok";
    }

    public static void doUpdate(String transId, String assetId,String status, User cur, Patient p,Date d,String brand) throws Exception {

        BigchainDbConfigBuilder
                .baseUrl("http://localhost:9984/") //or use http://testnet.bigchaindb.com
                .addToken("app_id", "")
                .addToken("app_key", "").setup();


        MetaData transferMetadata = new MetaData();
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        transferMetadata.setMetaData("date", form.format(d));
        transferMetadata.setMetaData("hospital-name", cur.getName());
        transferMetadata.setMetaData("hospital-city", cur.getCity());
        transferMetadata.setMetaData("hospital-country", cur.getCountry());
        transferMetadata.setMetaData("brand", brand);
        transferMetadata.setMetaData("status", status);

        System.out.println("(*) Transfer Metadata Prepared..");
        //Thread.sleep(5000);

        Map<String, String> assetData = new TreeMap<String, String>();
        assetData.put("id", assetId);
        assetData.put("AMKA", p.getAmka());
        assetData.put("age", String.valueOf(p.getAge()));
        assetData.put("name",p.getName());

        try {
            //which transaction you want to fulfill?
            FulFill fulfill = new FulFill();
            fulfill.setOutputIndex(0);
            fulfill.setTransactionId(transId);

            //build and send TRANSFER transaction
            Transaction transaction = BigchainDbTransactionBuilder
                    .init()
                    .addInput(null, fulfill, (EdDSAPublicKey) cur.getKeyPairs().getPublic())
                    .addOutput("1", (EdDSAPublicKey) cur.getKeyPairs().getPublic())
                    .addAssets(assetId, String.class)
                    .addMetaData(transferMetadata)
                    .operation(Operations.TRANSFER)
                    .buildAndSign((EdDSAPublicKey) cur.getKeyPairs().getPublic(), (EdDSAPrivateKey) cur.getKeyPairs().getPrivate())
                    .sendTransaction(handleServerResponse("t"));

            System.out.println("(*) TRANSFER Transaction sent.. - " + transaction.getId());



        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void doTransfer(String transId, String assetId,String status, User cur, User next, Patient p,Date d, String brand) throws Exception {

        BigchainDbConfigBuilder
                .baseUrl("http://localhost:9984/") //or use http://testnet.bigchaindb.com
                .addToken("app_id", "")
                .addToken("app_key", "").setup();


        MetaData transferMetadata = new MetaData();
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        transferMetadata.setMetaData("date", form.format(d));
        transferMetadata.setMetaData("hospital-name", cur.getName());
        transferMetadata.setMetaData("hospital-city", cur.getCity());
        transferMetadata.setMetaData("hospital-country", cur.getCountry());
        transferMetadata.setMetaData("brand",brand);
        transferMetadata.setMetaData("status", status);

        System.out.println("(*) Transfer Metadata Prepared..");
        //Thread.sleep(5000);

        Map<String, String> assetData = new TreeMap<String, String>();
        assetData.put("id", assetId);
        assetData.put("AMKA", p.getAmka());
        assetData.put("age", String.valueOf(p.getAge()));
        assetData.put("name",p.getName());

        try {
            //which transaction you want to fulfill?
            FulFill fulfill = new FulFill();
            fulfill.setOutputIndex(0);
            fulfill.setTransactionId(transId);

            //build and send TRANSFER transaction
            Transaction transaction = BigchainDbTransactionBuilder
                    .init()
                    .addInput(null, fulfill, (EdDSAPublicKey) cur.getKeyPairs().getPublic())
                    .addOutput("1", (EdDSAPublicKey) next.getKeyPairs().getPublic())
                    .addAssets(assetId, String.class)
                    .addMetaData(transferMetadata)
                    .operation(Operations.TRANSFER)
                    .buildAndSign((EdDSAPublicKey) cur.getKeyPairs().getPublic(), (EdDSAPrivateKey) cur.getKeyPairs().getPrivate())
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


    public static KeyPair getKeys() {
        //  prepare your keys
        net.i2p.crypto.eddsa.KeyPairGenerator edDsaKpg = new net.i2p.crypto.eddsa.KeyPairGenerator();
        KeyPair keyPair = edDsaKpg.generateKeyPair();
        //keyPair = axepa
        System.out.println("(*) Keys Generated..");
        return keyPair;

    }




}
