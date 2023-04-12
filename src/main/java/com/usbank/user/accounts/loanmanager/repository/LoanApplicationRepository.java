package com.usbank.user.accounts.loanmanager.repository;

import com.usbank.user.accounts.loanmanager.model.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Integer> {

    @Modifying
    @Transactional
    @Query(value = "Update loan_application set loan_status = :loanStatus where id = :id", nativeQuery = true)
    public void updateLoanStatus(String loanStatus, int id);

    public List<LoanApplication> findByAccountId(String accountId);
}
