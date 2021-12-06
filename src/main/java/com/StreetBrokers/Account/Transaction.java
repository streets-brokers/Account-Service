package com.StreetBrokers.Account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;
    private TransactionType transactionType;
    private ModeOfPayment modeOfPayment;
    private Double amount;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
