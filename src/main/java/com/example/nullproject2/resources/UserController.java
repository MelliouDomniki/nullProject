package com.example.nullproject2.resources;


import com.example.nullproject2.entity.User;
import com.example.nullproject2.entity.Vaccine;
import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.repositories.UserRepository;
import com.example.nullproject2.repositories.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/{username}/")
public class UserController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private VaccineController vac;

    @GetMapping("getHospital")
    public User getHospital(@PathVariable String username) {
        return userRepository.getHospital(username);
    }


    @PostMapping ("updateUser")
    public String updateUser (@RequestBody User newUser){
        Update update = new Update();
        User user = mongoTemplate.findOne(Query.query(Criteria.where("username").is(newUser.getUsername())), User.class);

        int c = user.getAvailableDoses();
        List<Vaccine> vac = user.getVaccines();
        boolean s = user.getAvailability();
        String keys = user.getKeys();
        String key = user.getPublicKey();
        userRepository.save(newUser);

        update.set("available_Doses", c);
        update.set("vaccines", vac);
        update.set("TransactionStatus", s);
        update.set("keys", keys);
        update.set("publicKey", key);

        Criteria criteria = Criteria.where("username").is(newUser.getUsername());
        mongoTemplate.updateFirst(Query.query(criteria), update, "users");

        return "Updated user with id: " + newUser.getId();
    }

    //gia koympi poy allazo to an dexomai transfers
    @PostMapping("makeMeAvailable")
    public String makeMeAvailable(@PathVariable String username){
        String response = "";
        User user = mongoTemplate.findOne(Query.query(Criteria.where("username").is(username)), User.class);
        if (user.getAvailability())
            response= "you are now unavailable";
        else
            response = "you are now available";
        user.setIAmAvailable(!user.getAvailability());
        userRepository.save(user);
        return response;
    }

    //gia drop down diathesima gia transfer
    @GetMapping("getTransferableHospitals/{brand}")
    public List<String> getTransferableHospitals(@PathVariable String username, @PathVariable String brand) {

        List<String> lista = new ArrayList<>();
        for (User u :userRepository.findAll())
            if (u.getAvailability() && (vac.getTheTotalOfABrand(u.getUsername(),Brand.valueOf(brand))>0))
                lista.add(u.getUsername());

        return lista;
    }


}
