package com.dh.homework.bid.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "credit_bid")
public class CreditBid {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_credit_bid")
    @SequenceGenerator(name = "sq_credit_bid", allocationSize = 1)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
//    @Column(name = "amount")
    private Integer amount;

    @NotNull
    @Size(min = 1, max = 100)
//    @Column(name = "receiving_city")
    private String receivingCity;

    @NotNull
    @Enumerated
//    @Column(name = "goal")
    private Goal goal;

    public enum Goal {
        JUST_MONEY,
        REFINANCING_LOAN,
        BUYING_NEW_CAR,
        BUYING_USED_CAR,
        MORTGAGE,
        MORTGAGE_REFINANCING
    }
}
