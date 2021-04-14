package com.utn.Parcial144.Controllers;

import com.utn.Parcial144.Models.Manager;
import com.utn.Parcial144.Models.Person;
import com.utn.Parcial144.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/")
    public List<Person> getAll(){
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable Integer id){
        return personService.getById(id);
    }

    @PostMapping("/")
    public void add(@RequestBody Person person){
        personService.add(person);
    }

    @PutMapping("/{managerId}/player/{playerId}/")
    public void addPlayerToManager(@PathVariable Integer managerId, @PathVariable Integer playerId){
        personService.put(managerId, playerId);
    }

}
