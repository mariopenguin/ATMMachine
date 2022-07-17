package com.example.ATM.Database.ATMMachines;
import com.example.ATM.Models.ATMMachine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IATMRepository extends CrudRepository<ATMMachine,Long>{
}
