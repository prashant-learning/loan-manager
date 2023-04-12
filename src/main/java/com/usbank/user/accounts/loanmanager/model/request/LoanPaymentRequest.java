package com.usbank.user.accounts.loanmanager.model.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LoanPaymentRequest {

    private LocalDate paymentDate;
    private double paymentAmount;
    private String paymentMode;
}
