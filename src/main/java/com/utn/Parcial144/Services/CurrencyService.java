package com.utn.Parcial144.Services;

import com.utn.Parcial144.Models.Currency;
import com.utn.Parcial144.Models.Person;
import com.utn.Parcial144.Models.Player;
import com.utn.Parcial144.Repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
@Service
public class CurrencyService {

    private CurrencyRepository currencyRepository;
    private PersonService personService;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository, PersonService personService) {
        this.currencyRepository = currencyRepository;
        this.personService = personService;
    }



    public List<Currency> getAll() {
        return currencyRepository.findAll();
    }

    public Currency getById(Integer id){
        return currencyRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public void add(Currency currency){
        currencyRepository.save(currency);
    }

    public void delete(Integer id){
        currencyRepository.deleteById(id);
    }


    public void addCurrecyToPlayer(Integer currencyId, Integer playerId) {
        Player person =(Player) personService.getById(playerId);
        Currency currency = getById(currencyId);
        person.setCurrency(currency);
    }
}
