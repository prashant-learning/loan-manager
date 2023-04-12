package com.usbank.user.accounts.loanmanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.usbank.user.accounts.loanmanager.exception.ApplicationNotFoundException;
import com.usbank.user.accounts.loanmanager.exception.PaymentNotPossibleException;
import com.usbank.user.accounts.loanmanager.model.request.LoanApplicationRequest;
import com.usbank.user.accounts.loanmanager.model.request.LoanPaymentRequest;
import com.usbank.user.accounts.loanmanager.model.response.LoanAccountDetailsResponse;
import com.usbank.user.accounts.loanmanager.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/loan")
public class InternalController implements LoanServiceApi{

    @Autowired
    private LoanService loanService;

    @Override
    @Tag(name = "loan management")
    @Operation(summary = "Used for creating loan application", description = "")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "201", description = "Successful created the loan application"),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            }
    )
    @PostMapping("/application")
    public ResponseEntity<Integer> createLoanApplication(@RequestBody LoanApplicationRequest loanApplicationRequest) throws JsonProcessingException {

        return ResponseEntity.status(HttpStatus.CREATED).body(loanService.createLoanApplication(loanApplicationRequest));
    }


    @Override
    @Tag(name = "loan management")
    @Operation(summary = "Used for approval of loan application", description = "")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Successful approved the loan application"),
                    @ApiResponse(responseCode = "404", description = "Loan application not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            }
    )
    @PutMapping("/{loanApplicationId}")
    public ResponseEntity<Void> approveLoan(@PathVariable int loanApplicationId) {
        try {
            loanService.approveLoan(loanApplicationId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ApplicationNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    @Tag(name = "loan management")
    @Operation(summary = "Used for payment toward loan application", description = "")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "201", description = "Successful created the loan payment"),
                    @ApiResponse(responseCode = "403", description = "Operation is not allowed"),
                    @ApiResponse(responseCode = "404", description = "Loan application not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            }
    )
    @PostMapping("/payment/{loanApplicationId}")
    public ResponseEntity<String> loanPayment(@PathVariable int loanApplicationId, @RequestBody LoanPaymentRequest loanPaymentRequest) {
       try {
        return   ResponseEntity.status(HttpStatus.OK).body(loanService.loanPayment(loanApplicationId, loanPaymentRequest));
       }  catch (ApplicationNotFoundException exception){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       } catch (PaymentNotPossibleException exception){
           return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
       }
    }


    @Override
    @Tag(name = "loan management")
    @Operation(summary = "Used for fetching loan details", description = "")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Successful fetched the loan details"),
                    @ApiResponse(responseCode = "404", description = "Loan account not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
            }
    )
    @GetMapping("/loan/{accountId}")
    public ResponseEntity<LoanAccountDetailsResponse> getLoanDetailsByAccountId(@PathVariable String accountId) {
      return   ResponseEntity.status(HttpStatus.OK).body(loanService.getLoanAccountDetails(accountId));
    }
}
