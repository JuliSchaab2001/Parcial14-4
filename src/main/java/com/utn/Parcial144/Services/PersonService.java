package com.utn.Parcial144.Services;

import com.utn.Parcial144.Models.Manager;
import com.utn.Parcial144.Models.Person;
import com.utn.Parcial144.Models.Player;
import com.utn.Parcial144.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private PersonRepository  personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void add(Person person){
        personRepository.save(person);
    }

    public Person getById(Integer id){
        Person person = personRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        if(person instanceof Manager){
            person = (Person)bovedaAndTotal(((Manager)person),((Manager)person).getPlayerList());
        }
        return person;
    }

    public void delete(Integer id){
        personRepository.deleteById(id);
    }

    public List<Person> getAll(){
        List<Person> personList = personRepository.findAll();
        List<Person> personList2 =new ArrayList<>();

        for(Person person : personList){
            if(person instanceof Manager){
                person = (Person)bovedaAndTotal(((Manager)person),((Manager)person).getPlayerList());
            }
            personList2.add(person);
        }

        return personList2;
    }


    private Manager bovedaAndTotal(Manager manager, List<Player> playerList){
        Integer monto = 0;
        Integer peso;

        for (Player player : playerList){
            if(player.getCurrency().equals("EURO")){
                monto = monto +(player.getCurrency().getAmount()*111); //111 pesos -> 1 Euro;
            }
            if(player.getCurrency().equals("DOLAR")){
                monto = monto +(player.getCurrency().getAmount()*92); //111 pesos -> 1 Euro;
            }
        }
        manager.setTotalAmount(monto);
        manager.setPesoDeLaBoveda(Math.round(monto/100));//Son billetes de 100


        return manager;
    }

    public void put(Integer managerId, Integer playerId){
        if(getById(playerId) instanceof Player && getById(managerId) instanceof Manager) {
            Player player =(Player)getById(playerId);
            Manager manager = (Manager) getById(managerId);
            manager.getPlayerList().add(player);
            personRepository.save((Person) manager);
        }
    }
}
