package com.huytd.ansinhso.repository;

import com.huytd.ansinhso.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findByPhoneNumber(String phoneNumber);
}