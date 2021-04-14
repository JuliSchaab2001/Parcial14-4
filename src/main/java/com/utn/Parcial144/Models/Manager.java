package com.utn.Parcial144.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manager extends Person {



    private Integer pesoDeLaBoveda;

    private Integer totalAmount;

    @JoinColumn(name = "manager_id")
    @OneToMany(fetch = FetchType.EAGER)
    List<Player> playerList;
}
