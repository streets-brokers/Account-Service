package com.StreetBrokers.Account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "account")
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;
    private Double totalBalance;
    /**
    * This Client id will be changed once integrated with the Client service
     */
    private Long clientId;

    @JsonIgnore
    @OneToMany(
            mappedBy = "account",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    Set<Transaction> transaction = new HashSet<>();
}
