package com.utn.Parcial144.Controllers;

import com.utn.Parcial144.Models.Currency;
import com.utn.Parcial144.Repositories.CurrencyRepository;
import com.utn.Parcial144.Services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/")
    public List<Currency> getAll(){
        return currencyService.getAll();
    }

    @GetMapping("/{id}")
    public Currency getById(@PathVariable Integer id){
        return currencyService.getById(id);
    }

    @PostMapping("/")
    public void add(@RequestBody Currency currency){
        currencyService.add(currency);
    }

    @DeleteMapping("/{id}") //Se puede eliminar de ambas formas
    public void delete(@PathVariable Integer id){
        currencyService.delete(id);
    }

    @DeleteMapping("/delete/")
    public void delete2(@RequestParam Integer id){
        currencyService.delete(id);
    }

    @PutMapping("/{currencyId}/player/{playerId}")
    public void addCurrecyToPlayer(@PathVariable Integer currencyId,@PathVariable Integer  playerId){
        currencyService.addCurrecyToPlayer(currencyId, playerId);
    }




}
