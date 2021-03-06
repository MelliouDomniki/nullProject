package com.example.nullproject2.resources;

import com.example.nullproject2.entity.Patient;
import com.example.nullproject2.entity.User;
import com.example.nullproject2.entity.Vaccine;
import com.example.nullproject2.enumerations.Brand;
import com.example.nullproject2.enumerations.VaccineStatus;
import com.example.nullproject2.repositories.PatientRepository;
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
    private PatientRepository patrepo;

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

    @GetMapping("findBrands/{patId}")
    public List<String> getBrands(@PathVariable String username, @PathVariable String patId) {

        List<String> brands = new ArrayList<>();
        List<String> lista = new ArrayList<>();
        Patient patient = patrepo.findFirstById(patId);
        if (patient.getAppoint()==0 && patient.getStatus().equals("0/2"))
        {
            brands.add("PFIZER");
            brands.add("MODERNA");
            brands.add("NOVAVAX");
            brands.add("JOHNSON");
            brands.add("ASTRAZENECA");
        }

        else
            brands.add(patient.getBrand().toString());

        for (String s: brands)
        {
            if (countGlobalByBrand(s)>0)
                lista.add(s);
        }
        return lista;
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

    //metraei ta available anexartita to brand , gia karta available
    @GetMapping("countAvailable")
    public int countAvailableVaccines(@PathVariable String username) {
        return vacrepo.countByStatusAndHospitalName(VaccineStatus.AVAILABLE, username) +vacrepo.countByStatusAndHospitalName(VaccineStatus.RESERVED, username);
    }

    //den koitaei status
    @GetMapping("findAllByBrand/{brand}")
    public List<Vaccine> getVaccinesByBrand( @PathVariable String username, @PathVariable Brand brand) {
        return vacrepo.findByHospitalNameAndBrand(username, brand);
    }

    @GetMapping("findAllByStatus/{vaccineStatus}")
    public List<Vaccine> getVaccinesByStatus(@PathVariable String username, @PathVariable VaccineStatus vaccineStatus) {
        return vacrepo.findByHospitalNameAndStatus(username, vaccineStatus);
    }

    @GetMapping("findAllByDoubleStatus")
    public List<Vaccine> getVaccinesByDoubleStatus(@PathVariable String username) {

        List<Vaccine> vac= new ArrayList<>();
        vac.addAll(vacrepo.findByHospitalNameAndStatus(username, VaccineStatus.RESERVED));
        vac.addAll(vacrepo.findByHospitalNameAndStatus(username, VaccineStatus.AVAILABLE));

        return vac;
    }


    @GetMapping("findAllByBrandAndStatus/{brand}/{status}")
    public List<Vaccine> getVaccinesByBrandAndStatus(@PathVariable String username, @PathVariable Brand brand, @PathVariable VaccineStatus status){
        return vacrepo.findByHospitalNameAndBrandAndStatus(username,brand,status);
    }


    @GetMapping("findOneByBrandAndStatus/{brand}/{status}")
    public Vaccine getVaccineByBrandAndStatus(@PathVariable String username, @PathVariable Brand brand, @PathVariable VaccineStatus status){
        return vacrepo.findFirstByHospitalNameAndBrandAndStatus(username,brand,status);
    }

    @GetMapping("countGlobalBy/{brand}/")
    public int countGlobalByBrand( @PathVariable String brand){
        return vacrepo.countByBrandAndStatus(Brand.valueOf(brand),VaccineStatus.AVAILABLE)-vacrepo.countByBrandAndStatus(Brand.valueOf(brand),VaccineStatus.RESERVED);
    }

    //metraei ta available toy nosokomeioy gia to brand poy dino
    @GetMapping("countBy/{brand}/")
    public int getTheTotalOfABrand(@PathVariable String username, @PathVariable Brand brand){
        return vacrepo.countByHospitalNameAndAndBrandAndStatus(username,brand,VaccineStatus.AVAILABLE);
    }

    @GetMapping("countReservedBy/{brand}/")
    public int getTheTotalOfABrandReserv(@PathVariable String username, @PathVariable Brand brand){
        return vacrepo.countByHospitalNameAndAndBrandAndStatus(username,brand,VaccineStatus.RESERVED);
    }

    //gia vaccine page poy thelo posa available exo apo kathe brand me mia klisi
    @GetMapping("countAllVaccines")
    public List<String> getAllVaccinesOfEachBrand(@PathVariable String username){
        List<String> brands = Arrays.asList("PFIZER","ASTRAZENECA","NOVAVAX","MODERNA","JOHNSON");
        List<String> vaccines = new ArrayList<>();
        String theBrandAndNumber = "";

        for (String brand : brands) {
            theBrandAndNumber = "" + brand + " : " + "" + vacrepo.countByHospitalNameAndAndBrandAndStatus(username, Brand.valueOf(brand), VaccineStatus.AVAILABLE);
            vaccines.add(theBrandAndNumber);
        }
         return vaccines;
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
        Criteria criteria = Criteria.where("username").is(h.getUsername());
        mongoTemplate.updateFirst(Query.query(criteria), update, "users");
    }

}
