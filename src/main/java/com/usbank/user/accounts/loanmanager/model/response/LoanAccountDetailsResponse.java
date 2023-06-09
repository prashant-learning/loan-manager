package com.usbank.user.accounts.loanmanager.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoanAccountDetailsResponse {

    private int id;
    private String loanType;
    private String loanStatus;
    private double loanAmount;
    private double interestRate;
    private double emi;
    private double timeframe;
    private String accountId;
}
