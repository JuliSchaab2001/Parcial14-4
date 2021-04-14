package com.utn.Parcial144.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player extends Person{

    private double weight;

    private double height;

    private Integer goals;

    private Integer minutes;

    private String birthDate;

    @JoinColumn(name = "player_id")
    @OneToOne(fetch = FetchType.EAGER)
    private Currency currency;
}
