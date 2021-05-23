package com.example.nullproject2.resources;

import com.example.nullproject2.entity.User;
import com.example.nullproject2.entity.Vaccine;
import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.VaccineStatus;
import com.example.nullproject2.fakedata.RandomnessProvider;
import com.example.nullproject2.repositories.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import static com.example.nullproject2.fakedata.RandomnessProvider.getDateWithoutTimeUsingFormat;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class VaccineController {

    @Autowired
    private VaccineRepository vacrepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("{username}/addVaccine")
    public String saveVaccine (@PathVariable String username,@RequestBody Vaccine vaccine){
        Update update = new Update();
        update.addToSet("vaccines", vaccine);
        Criteria criteria = Criteria.where("username").is(username);
        mongoTemplate.updateFirst(Query.query(criteria), update, "users");
        vacrepo.save(vaccine);
        return "Added vaccine with id: " + vaccine.getId();
    }

    @GetMapping("{username}/findAllVaccines")
    public List<Vaccine> getVaccines(@PathVariable String username) {
        return vacrepo.findByHospitalName(username);
    }

    @GetMapping("/findVaccineById/{id}")
    public Optional<Vaccine> getVaccine(@PathVariable String id) {
        return vacrepo.findById(id);
    }

    @DeleteMapping("/deleteVaccine/{id}")
    public String deleteVaccine(@PathVariable String id) {
        vacrepo.deleteById(id);
        return "vaccine deleted with id : " + id;
    }

    @PostMapping ("/updateVaccine")
    public String updateVaccine (@RequestBody Vaccine newVaccine){
        vacrepo.save(newVaccine);
        return "Added vaccine with id: " + newVaccine.getId();
    }

    @GetMapping("{username}/findAllVaccinesByBrand/{brand}")
    public List<Vaccine> getVaccinesByBrand(@PathVariable String username, @PathVariable Brand brand) {
        return vacrepo.findByHospitalNameAndBrand(username, brand);
    }

    //to allaxa
    @GetMapping("{username}/findAllVaccinesByStatus/{vaccineStatus}")
    public List<Vaccine> getVaccinesByStatus(@PathVariable String username, @PathVariable VaccineStatus vaccineStatus) {
        return vacrepo.findByHospitalNameAndStatus(username, vaccineStatus);
    }

    @GetMapping("{username}/findAllVaccinesByBrandAndStatus/{brand}+{status}")
    public List<Vaccine> getVaccinesByBrandAndStatus(@PathVariable String username, @PathVariable Brand brand, @PathVariable VaccineStatus status){
        return vacrepo.findByHospitalNameAndBrandAndStatus(username,brand,status);
    }

    @GetMapping("{username}/findOneVaccineByBrandAndStatus/{brand}+{status}")
    public Optional<Vaccine> getVaccineByBrandAndStatus(@PathVariable String username, @PathVariable Brand brand, @PathVariable VaccineStatus status){
        return vacrepo.findFirstByHospitalNameAndBrandAndStatus(username,brand,status);
    }

    @PostMapping("{username}/addVaccines/{number}")
    public String addVaccines (@PathVariable String username,@PathVariable int number) throws ParseException {

        Update update = new Update();

        VaccineStatus s = VaccineStatus.AVAILABLE;
        Brand b;

        for (int i = 0 ; i < number ; i++){


            b = RandomnessProvider.getBrand();

            Vaccine v = new Vaccine(b,s,getDateWithoutTimeUsingFormat(),username);
            vacrepo.save(v);
            update.addToSet("vaccines", v);
            Criteria criteria = Criteria.where("username").is(username);
            mongoTemplate.updateFirst(Query.query(criteria), update, "users");

        }
        return "all good";
    }
}
