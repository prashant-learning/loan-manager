package com.usbank.user.accounts.loanmanager.repository;

import com.usbank.user.accounts.loanmanager.model.entity.LoanPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanPaymentRepository extends JpaRepository<LoanPayment, Integer> {
}
