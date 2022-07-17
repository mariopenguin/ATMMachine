package com.example.ATM.Database.Operations;


import org.springframework.stereotype.Service;

@Service
public class OperationsService {
    private final IOperationsService iOperationsRepository;

    public OperationsService(IOperationsService iOperationsRepository) {
        this.iOperationsRepository = iOperationsRepository;
    }
}
