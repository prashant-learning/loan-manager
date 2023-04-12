package com.usbank.user.accounts.loanmanager.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usbank.user.accounts.loanmanager.exception.ApplicationNotFoundException;
import com.usbank.user.accounts.loanmanager.exception.PaymentNotPossibleException;
import com.usbank.user.accounts.loanmanager.model.entity.LoanApplication;
import com.usbank.user.accounts.loanmanager.model.entity.LoanPayment;
import com.usbank.user.accounts.loanmanager.model.request.LoanApplicationRequest;
import com.usbank.user.accounts.loanmanager.model.request.LoanPaymentRequest;
import com.usbank.user.accounts.loanmanager.model.response.LoanAccountDetailsResponse;
import com.usbank.user.accounts.loanmanager.repository.LoanApplicationRepository;
import com.usbank.user.accounts.loanmanager.repository.LoanPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private LoanPaymentRepository loanPaymentRepository;

    public int createLoanApplication(LoanApplicationRequest loanApplicationRequest) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(loanApplicationRequest);
        LoanApplication loanApplicationEntity = mapper.readValue(jsonString, LoanApplication.class);

        LoanApplication loanApplicationResponse = loanApplicationRepository.save(loanApplicationEntity);
        return loanApplicationResponse.getId();
    }

    public void approveLoan(int loanApplicationId) {

        if (loanApplicationRepository.findById(loanApplicationId).isPresent()) {
            loanApplicationRepository.updateLoanStatus("approved", loanApplicationId);
        } else {
            throw new ApplicationNotFoundException("Application not found");
        }
    }

    public String loanPayment(int loanApplicationId, LoanPaymentRequest loanPaymentRequest) {
        Optional<LoanApplication> mayBeLoanApplication = loanApplicationRepository.findById(loanApplicationId);
        if (mayBeLoanApplication.isEmpty()) {
            throw new ApplicationNotFoundException("Application not found");
        } else {
            if (loanPaymentRequest.getPaymentAmount() < mayBeLoanApplication.get().getEmi()) {
                throw new PaymentNotPossibleException("Payment not possible due to payment amount is less than emi");
            }

            LoanPayment loanPayment = new LoanPayment();
            loanPayment.setLoanApplicationId(loanApplicationId);
            loanPayment.setPaymentDate(loanPaymentRequest.getPaymentDate());
            loanPayment.setPaymentMode(loanPaymentRequest.getPaymentMode());
            loanPayment.setPaymentAmount(loanPaymentRequest.getPaymentAmount());

            loanPaymentRepository.save(loanPayment);
            return "Successful payment toward your loan emi";
        }
    }

    public LoanAccountDetailsResponse getLoanAccountDetails(String accountId) {
        Optional<LoanApplication> mayBeLoanApplication = loanApplicationRepository.findByAccountId(accountId).stream().findFirst();

        if (mayBeLoanApplication.isEmpty()) {
            throw new ApplicationNotFoundException("Loan application is not found");
        } else {
            LoanApplication loanApplication = mayBeLoanApplication.get();

            return LoanAccountDetailsResponse.builder()
                    .id(loanApplication.getId())
                    .accountId(loanApplication.getAccountId())
                    .loanType(loanApplication.getLoanType())
                    .loanAmount(loanApplication.getLoanAmount())
                    .loanStatus(loanApplication.getLoanStatus())
                    .emi(loanApplication.getEmi())
                    .interestRate(loanApplication.getInterestRate())
                    .timeframe(loanApplication.getTimeframe())
                    .build();

        }
    }
}
