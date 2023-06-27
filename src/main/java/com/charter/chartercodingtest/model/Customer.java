package com.charter.chartercodingtest.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Customers")
@Data
@NoArgsConstructor
public class Customer {
    public Customer(String name, double moneySpent, long rewardsTotal) {
        this.name = name;
        this.moneySpent = moneySpent;
        this.rewardsTotal = rewardsTotal;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "moneyspent")
    private double moneySpent;

    @Column(name = "rewardstotal")
    private long rewardsTotal;
}
