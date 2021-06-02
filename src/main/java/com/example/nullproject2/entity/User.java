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

    @Field(name = "iAmAvailable")
    private boolean iAmAvailable;

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
        this.iAmAvailable = true;
        this.availableDoses = 0;
        KeyPair keys = BigchainCall.getKeys();
        EdDSAPublicKey pubkey = (EdDSAPublicKey) keys.getPublic();
        this.publicKey = KeyPairUtils.encodePublicKeyInBase58(pubkey).toString();  //sosto
        this.keys = KeyPairUtils.encodePrivateKeyBase64(keys);


    }

    public boolean getAvailability()
    {
        return this.iAmAvailable;
    }


    @JsonIgnore
    public KeyPair getKeyPairs()
    {
        return KeyPairUtils.decodeKeyPair(this.keys);
    }

    public HashMap<String, Date> sortByValue(HashMap<String, Date> dates)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Date> > list =
                new LinkedList<Map.Entry<String, Date> >(dates.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Date> >() {
            public int compare(Map.Entry<String, Date> o1,
                               Map.Entry<String, Date> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Date> temp = new LinkedHashMap<String, Date>();
        for (Map.Entry<String, Date> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
