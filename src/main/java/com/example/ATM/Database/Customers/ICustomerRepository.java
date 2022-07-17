package com.example.ATM.Database.Customers;

import com.example.ATM.Models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICustomerRepository extends CrudRepository<Customer,Long> {
}
