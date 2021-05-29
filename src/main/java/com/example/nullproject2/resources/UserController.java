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


import java.util.HashMap;
import java.util.HashSet;
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
        String keys = user.getKeys();
        String key = user.getPublicKey();
        userRepository.save(newUser);

        update.set("available_Doses", c);
        update.set("vaccines", vac);
        update.set("keys", keys);
        update.set("publicKey", key);


        Criteria criteria = Criteria.where("username").is(newUser.getUsername());
        mongoTemplate.updateFirst(Query.query(criteria), update, "users");

        return "Updated user with id: " + newUser.getId();
    }

    @PostMapping("makeMeAvailable")
    public String makeMeAvailable(@PathVariable String username){
        User user = mongoTemplate.findOne(Query.query(Criteria.where("username").is(username)), User.class);
        if (user.getTransactionStatus() == 0) {
            user.setTransactionStatus(1);
        }else if (user.getTransactionStatus() == 1){
            user.setTransactionStatus(0);
        }
        userRepository.save(user);
        return "You are now available";
    }

    @GetMapping("findAllAvailable")
    public Map findAllByIAmAvailableEquals() {
        Map<String, HashSet<String>> hospitals = new HashMap<>();


        List<User> avaliableUsers = userRepository.findByTransactionStatus(1);
        for (User availbleUser : avaliableUsers) {

            for (Vaccine vaccine : availbleUser.getVaccines()) {

                if (hospitals.containsKey(vaccine.getHospitalName())) {
                    if (hospitals.get(vaccine.getHospitalName()) != null) {
                        HashSet<String> brands = hospitals.get(vaccine.getHospitalName());
                        brands.add(vaccine.getBrand().getBrand());
                    } else {

                        HashSet<String> brands = new HashSet<>();
                        brands.add(vaccine.getBrand().getBrand());
                        hospitals.put(vaccine.getHospitalName(), brands);
                    }
                } else {

                    HashSet<String> brands = new HashSet<>();
                    brands.add(vaccine.getBrand().getBrand());
                    hospitals.put(vaccine.getHospitalName(), brands);
                }
            }
        }

        return hospitals;
    }
}
