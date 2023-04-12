package com.usbank.user.accounts.loanmanager.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@ToString
@Setter
@Getter
@Table(name = "loan_payment")
public class LoanPayment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private int loanApplicationId;

    @NotNull
    private LocalDate paymentDate;

    @NotNull
    private double paymentAmount;

    @NotBlank
    @Size(max = 120)
    private String paymentMode;
}
