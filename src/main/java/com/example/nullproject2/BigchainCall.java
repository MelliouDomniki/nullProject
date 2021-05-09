package com.example.nullproject2;

import java.io.IOException;
import java.security.KeyPair;
import java.util.Map;
import java.util.TreeMap;
import com.bigchaindb.builders.BigchainDbConfigBuilder;
import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.FulFill;
import com.bigchaindb.model.GenericCallback;
import com.bigchaindb.model.MetaData;
import com.bigchaindb.model.Transaction;
import com.bigchaindb.util.Base58;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;

import net.i2p.crypto.eddsa.EdDSAPublicKey;
import okhttp3.Response;


public class BigchainCall {

    //public   BigchainDBJavaDriverUsageExample(String args[]) throws Exception {
    public   BigchainCall() throws Exception {

      // BigchainDBJavaDriverUsageExample examples = new BigchainDBJavaDriverUsageExample();

        //configuration
        BigchainDbConfigBuilder
                .baseUrl("http://localhost:9984/")
                .addToken("header1", "")
	            .addToken("header2", "").setup();


        //generate Keys gia kathe nosokomeio
        KeyPair keys = BigchainCall.getKeys();

        System.out.println(Base58.encode(keys.getPublic().getEncoded()));
        System.out.println(Base58.encode(keys.getPrivate().getEncoded()));

        // create New asset
        Map<String, String> assetData = new TreeMap<String, String>() {{
            //stoixeia vaccination
            put("patient-id", "324543");
        }};
        System.out.println("(*) Assets Prepared..");

        // create metadata
        MetaData metaData = new MetaData();
        metaData.setMetaData("date", "12/01/21");
        metaData.setMetaData("vaccine-id", "12");
        metaData.setMetaData("vaccination-status", "PENDING");
        System.out.println("(*) Metadata Prepared.." );

        //execute CREATE transaction
        //txId = to id tou asset
        String txId = this.doCreate(assetData, metaData, keys);

        KeyPair targetKeys = BigchainCall.getKeys();

        //create transfer metadata
        MetaData transferMetadata = new MetaData();
        //prepei na vazo kai ayta pou den allazoun an metatrepo kapoia mono
        transferMetadata.setMetaData("vaccination-status", "COMPLETED");
        System.out.println("(*) Transfer Metadata Prepared..");

        //let the transaction commit in block
        Thread.sleep(5000);

        //execute TRANSFER transaction on the CREATED asset
        //keys= new hospital
        this.doTransfer(txId, transferMetadata, keys, targetKeys);

    }

    private void onSuccess(Response response) {
        //TODO : Add your logic here with response from server
        System.out.println("Transaction posted successfully");
    }

    private void onFailure() {
        //TODO : Add your logic here
        System.out.println("Transaction failed");
    }

    private GenericCallback handleServerResponse() {
        //define callback methods to verify response from BigchainDBServer
        GenericCallback callback = new GenericCallback() {

            @Override
            public void transactionMalformed(Response response) {
                System.out.println("malformed " + response.message());
                onFailure();
            }

            @Override
            public void pushedSuccessfully(Response response) {
                System.out.println("pushedSuccessfully");
                onSuccess(response);
            }

            @Override
            public void otherError(Response response) {
                System.out.println("otherError" + response.message());
                onFailure();
            }
        };

        return callback;
    }


    /**
     * generates EdDSA keypair to sign and verify transactions
     * @return KeyPair
     */
    public static KeyPair getKeys() {
        //  prepare your keys
        net.i2p.crypto.eddsa.KeyPairGenerator edDsaKpg = new net.i2p.crypto.eddsa.KeyPairGenerator();
        KeyPair keyPair = edDsaKpg.generateKeyPair();
        //keyPair = axepa
        System.out.println("(*) Keys Generated..");
        return keyPair;

    }

    /**
     * performs CREATE transactions on BigchainDB network
     * @param assetData data to store as asset
     * @param metaData data to store as metadata
     * @param keys keys to sign and verify transaction
     * @return id of CREATED asset
     */
    public String doCreate(Map<String, String> assetData, MetaData metaData, KeyPair keys) throws Exception {

        try {
            //build and send CREATE transaction
            Transaction transaction = null;

            transaction = BigchainDbTransactionBuilder
                    .init()
                    .addAssets(assetData, TreeMap.class)
                    .addMetaData(metaData)
                    .operation(Operations.CREATE)
                    .buildAndSign((EdDSAPublicKey) keys.getPublic(), (EdDSAPrivateKey) keys.getPrivate())
                    .sendTransaction(handleServerResponse());

            System.out.println("(*) CREATE Transaction sent.. - " + transaction.getId());
            System.out.println(transaction.getAsset().getData());
            return transaction.getId();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    /**
     * performs TRANSFER operations on CREATED assets
     * @param txId id of transaction/asset
     * @param metaData data to append for this transaction
     * @param keys keys to sign and verify transactions
     */
    public void doTransfer(String txId, MetaData metaData, KeyPair keys, KeyPair targetKeys) throws Exception {

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
                    //keys apo idi owner
                    .addInput(null, fulfill, (EdDSAPublicKey) keys.getPublic())
                    //keys apo neo owner
                    .addOutput("1", (EdDSAPublicKey) targetKeys.getPublic())
                    .addAssets(txId, String.class)
                    .addMetaData(metaData)
                    .operation(Operations.TRANSFER)
                    //keys apo palio owner
                    .buildAndSign((EdDSAPublicKey) keys.getPublic(), (EdDSAPrivateKey) keys.getPrivate())
                    .sendTransaction(handleServerResponse());

            System.out.println("(*) TRANSFER Transaction sent.. - " + transaction.getId());


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
