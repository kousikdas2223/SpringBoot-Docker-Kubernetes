package com.springboottutorial.accounts.repository;

import com.springboottutorial.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findByCustomerId(int customerId);

    @Transactional
    @Modifying
    void deleteByCustomerId(int customerId);
}
