package com.usbank.user.accounts.loanmanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.usbank.user.accounts.loanmanager.model.request.LoanApplicationRequest;
import com.usbank.user.accounts.loanmanager.model.request.LoanPaymentRequest;
import com.usbank.user.accounts.loanmanager.model.response.LoanAccountDetailsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public interface LoanServiceApi {

    public ResponseEntity<Integer> createLoanApplication(LoanApplicationRequest loanApplicationRequest) throws JsonProcessingException;

    public ResponseEntity<Void> approveLoan(int loanApplicationId);

    public ResponseEntity<String> loanPayment(int loanApplicationId, LoanPaymentRequest loanPaymentRequest);

    public ResponseEntity<LoanAccountDetailsResponse> getLoanDetailsByAccountId(String accountId);

}
