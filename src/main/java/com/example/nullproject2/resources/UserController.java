package com.example.nullproject2.resources;


import com.example.nullproject2.entity.User;
import com.example.nullproject2.entity.Vaccine;
import com.example.nullproject2.enumerations.UserStatus;
import com.example.nullproject2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/{username}/")
public class UserController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("getHospital")
    public User getHospital(@PathVariable String username) {
        return userRepository.getHospital(username);
    }


    @PostMapping ("updateUser")
    public String updateUser (@RequestBody User newUser){
        Update update = new Update();
        User user = mongoTemplate.findOne(Query.query(Criteria.where("username").is(newUser.getUsername())), User.class);

        int c = user.getAvailableDoses();
        UserStatus s = user.getTransactionStatus();
        List<Vaccine> vac = user.getVaccines();
        String keys = user.getKeys();
        String key = user.getPublicKey();
        userRepository.save(newUser);

        update.set("available_Doses", c);
        update.set("TransactionStatus", s);
        update.set("vaccines", vac);
        update.set("keys", keys);
        update.set("publicKey", key);


        Criteria criteria = Criteria.where("username").is(newUser.getUsername());
        mongoTemplate.updateFirst(Query.query(criteria), update, "users");

        return "Updated user with id: " + newUser.getId();
    }
}
