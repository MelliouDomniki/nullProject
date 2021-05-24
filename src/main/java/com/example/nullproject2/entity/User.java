package com.example.nullproject2.entity;

import com.bigchaindb.util.KeyPairUtils;
import com.example.nullproject2.BigchainCall;
import com.example.nullproject2.roles.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.persistence.Id;
import java.security.*;
import java.util.*;

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

    @Field(name = "keys")
    private String keys;

    @Field(name = "publicKey")
    private String publicKey;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    @DBRef
    private List<Vaccine> vaccines = new ArrayList<>();

    public User(String name, String address, String phone_number, String city, String country,String username, String email, String password) {


        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.city = city;
        this.country = country;
        this.username = username;
        this.email = email;
        this.password = password;
        KeyPair keys = BigchainCall.getKeys();
        EdDSAPublicKey pubkey = (EdDSAPublicKey) keys.getPublic();
        this.publicKey = KeyPairUtils.encodePublicKeyInBase58(pubkey).toString();  //sosto
        this.keys = KeyPairUtils.encodePrivateKeyBase64(keys);

        //vac.addVaccines(this.username, this.availableDoses);

    }

    @JsonIgnore
    public KeyPair getKeyPairs()
    {
        return KeyPairUtils.decodeKeyPair(this.keys);
    }
}
