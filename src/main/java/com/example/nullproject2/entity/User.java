package com.example.nullproject2.entity;

import com.bigchaindb.util.KeyPairUtils;
import com.example.nullproject2.BigchainCall;
import com.example.nullproject2.roles.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import javax.persistence.Id;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @Field(name = "address")
    private String address;

    @Field(name = "phone_number")
    private String phone_number;

    @Field(name = "city")
    private String city;

    @Field(name = "country")
    private String country;

    @Field(name = "available_Doses")
    private int availableDoses;

    @Field(name = "username")
    private String username;

    @Field(name = "email")
    private String email;

    @Field(name = "password")
    private String password;

    @Field(name = "private")
    private String privateKey;

    @Field(name = "public")
    private String publicKey;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    @DBRef
    private List<Vaccine> vaccines = new ArrayList<>();


    public User(String name, String address, String phone_number, String city, String country, int availableDoses,String username, String email, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.city = city;
        this.country = country;
        this.availableDoses = availableDoses;
        this.username = username;
        this.email = email;
        this.password = password;
        KeyPair keys = BigchainCall.getKeys();
        EdDSAPublicKey pubkey = (EdDSAPublicKey) keys.getPublic();
        this.publicKey = KeyPairUtils.encodePublicKeyInBase58(pubkey).toString();
        this.privateKey = keyPair();
    }


    private String keyPair() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, SignatureException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        keyGen.initialize(256, random);

        KeyPair pair = keyGen.generateKeyPair();
        PrivateKey priv = pair.getPrivate();
        Signature dsa = Signature.getInstance("SHA1withECDSA");

        dsa.initSign(priv);

        String str = "This is string to sign";
        byte[] strByte = str.getBytes("UTF-8");
        dsa.update(strByte);
        byte[] realSig = dsa.sign();
        return "" + new BigInteger(1, realSig).toString(16);
    }
}
