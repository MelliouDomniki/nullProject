package com.example.nullproject2.resources;

import com.example.nullproject2.entity.User;
import com.example.nullproject2.entity.Vaccine;
import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.VaccineStatus;
import com.example.nullproject2.repositories.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static com.example.nullproject2.utils.RandomnessProvider.getDateWithoutTimeUsingFormat;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/{username}/vaccine/")
public class VaccineController {

    @Autowired
    private VaccineRepository vacrepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("add")
    public String saveVaccine (@PathVariable String username,@RequestBody Vaccine vaccine){
        Update update = new Update();
        update.addToSet("vaccines", vaccine);
        Criteria criteria = Criteria.where("username").is(username);
        mongoTemplate.updateFirst(Query.query(criteria), update, "users");
        vacrepo.save(vaccine);
        return "Added vaccine with id: " + vaccine.getId();
    }

    @GetMapping("findAll")
    public List<Vaccine> getVaccines(@PathVariable String username) {
        return vacrepo.findByHospitalName(username);
    }

    @GetMapping("findId/{id}")
    public Optional<Vaccine> getVaccine(@PathVariable String id) {
        return vacrepo.findById(id);
    }

    @DeleteMapping("delete/{id}")
    public String deleteVaccine(@PathVariable String id) {
        vacrepo.deleteById(id);
        return "vaccine deleted with id : " + id;
    }

    @PostMapping ("update")
    public String updateVaccine (@RequestBody Vaccine newVaccine){
        vacrepo.save(newVaccine);
        return "Added vaccine with id: " + newVaccine.getId();
    }

    @GetMapping("countAvailable")
    public int countAvailableVaccines(@PathVariable String username) {
        return vacrepo.countByStatusAndHospitalName(VaccineStatus.AVAILABLE, username);
    }


    @GetMapping("findAllByBrand/{brand}")
    public List<Vaccine> getVaccinesByBrand(@PathVariable String username, @PathVariable Brand brand) {
        return vacrepo.findByHospitalNameAndBrand(username, brand);
    }

    //to allaxa
    @GetMapping("findAllByStatus/{vaccineStatus}")
    public List<Vaccine> getVaccinesByStatus(@PathVariable String username, @PathVariable VaccineStatus vaccineStatus) {
        return vacrepo.findByHospitalNameAndStatus(username, vaccineStatus);
    }

    @GetMapping("findAllByBrandAndStatus/{brand}/{status}")
    public List<Vaccine> getVaccinesByBrandAndStatus(@PathVariable String username, @PathVariable Brand brand, @PathVariable VaccineStatus status){
        return vacrepo.findByHospitalNameAndBrandAndStatus(username,brand,status);
    }

    @GetMapping("findOneByBrandAndStatus/{brand}/{status}")
    public Vaccine getVaccineByBrandAndStatus(@PathVariable String username, @PathVariable Brand brand, @PathVariable VaccineStatus status){
        return vacrepo.findFirstByHospitalNameAndBrandAndStatus(username,brand,status);
    }

    @GetMapping("countBy/{brand}/")
    public int getTheTotalOfABrand(@PathVariable String username, @PathVariable Brand brand){
        return vacrepo.countByHospitalNameAndAndBrandAndStatus(username,brand,VaccineStatus.AVAILABLE);
    }

    @GetMapping("countAllVaccines")
    public String getAllVaccinesOfEachBrand(@PathVariable String username){
        List<String> brands = Arrays.asList("PFIZER","ASTRAZENECA","NOVAVAX","MODERNA","JOHNSON");
        List<String> vaccines = new ArrayList<>();
        String theBrandAndNumber = "";

        for (String brand : brands) {
            theBrandAndNumber = "" + brand + " : " + "" + vacrepo.countByHospitalNameAndAndBrandAndStatus(username, Brand.valueOf(brand), VaccineStatus.AVAILABLE);
            vaccines.add(theBrandAndNumber);
        }
         return vaccines+"";
    }

    @PostMapping("add/{brand}/{number}")
    public String addVaccinesByBrand (@PathVariable String username,@PathVariable String brand,@PathVariable int number) throws ParseException {

        Update update = new Update();

        VaccineStatus s = VaccineStatus.AVAILABLE;
        Brand b;
        User user = mongoTemplate.findOne(Query.query(Criteria.where("username").is(username)), User.class);
        int c = user.getAvailableDoses()+1;

        for (int i = 0 ; i < number ; i++){

            b = Brand.valueOf(brand);

            Vaccine v = new Vaccine(b,s,getDateWithoutTimeUsingFormat(),username);
            vacrepo.save(v);
            update.addToSet("vaccines", v);
            update.set("available_Doses", c++);
            Criteria criteria = Criteria.where("username").is(username);
            mongoTemplate.updateFirst(Query.query(criteria), update, "users");

        }
        return "all good";
    }

    public void decreaseAvailable(Vaccine v, User h)
    {
        Update update = new Update();
        User user = mongoTemplate.findOne(Query.query(Criteria.where("username").is(h.getUsername())), User.class);
        int c = user.getAvailableDoses()-1;
        v.setStatus(VaccineStatus.UNAVAILABLE);
        vacrepo.save(v);
        update.set("available_Doses", c--);
        update.addToSet("vaccines,v");
        //mesa sto user sto vaccines den to kanei unavailable
        //den mas peirazei koitame vaccine repository
        Criteria criteria = Criteria.where("username").is(h.getUsername());
        mongoTemplate.updateFirst(Query.query(criteria), update, "users");
    }

}
