package com.example.ATM.Database.Operations;

import com.example.ATM.Models.FundsDispension;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOperationsService extends CrudRepository<FundsDispension,Long> {
}
