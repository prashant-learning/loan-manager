package com.usbank.user.accounts.loanmanager.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Setter
@Getter
@Table(name = "loan_application")
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 120)
    private String loanType;

    @NotBlank
    @Size(max = 120)
    private String loanStatus;

    @NotNull
    private boolean needsApproval;

    @NotNull
    private double loanAmount;

    @NotNull
    private double interestRate;

    @NotNull
    private double emi;

    @NotNull
    private double timeframe;

    @NotBlank
    @Size(max = 120)
    private String applicantOccupation;

    @NotNull
    private int creditScore;

    @NotBlank
    @Size(max = 120)
    private String accountId;



}
