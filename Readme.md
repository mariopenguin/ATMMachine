#ATM Machine Application

Instructions to execute the project: 

First of all, compile the proyect and build the .jar using the following command: `./mvnw package && java -jar target/atm-0.0.1.jar`

After that, build the Docker image with: `docker build -t atm .`
Finally, use `docker-compose up` in order to build the two services.

Our application has 2 services:
1. The first service contains a **mysql** server image, the configuration is ubicated in the **docker-compose.yml** file. If you have mysql in your computer, you can comment the network line and uncomment the port line in order to test it with your own mysql server.
2. The second service is our springboot project and it's listening in localhost:8080.

##Application architecture
The application has been divided in the following modules:
- **Controllers**: The controllers manage the api routes that we will see in the next section, each class of the model has It's own controller in order to perform the required operations.
- **Database**: These classes manages the access to the mysql database. Currently, I am using an ORM (Object Relational Mapping) to perform the basic operations with the objects.
- **Exceptions**:This module contains all the types of exceptions that can be thrown in our app.
- **Models**:This file contains the classes required that models the objects of our application.

##API routes
In order to make easier the petitions, I uploaded a file called "ATMRequests.json", if you want to test the API I recommend to use Postman and then import the requests that are in this file and feel free to experiment changing values. 
###InitDatabase
This controller performs the initial set-up of the database. It creates the users and one atm machine with the predefined values of the problem.
It is recommended to make a GET petition to `http://localhost:8080/api/db` in order to create some predefined examples of customers and ATM machines in the database.

###ATMController
This controller manages all the ATM objects stored in the database. This controller allows to list all the ATM machines, to find one by ID and to create a new one (but with the predefined amount of funds).
- GET `api/atm/listAll`
- GET `api/atm/`
- POST `api/atm/createAtm`
###CustomerController
This controller allows the creation of new Customers and see the list of all of them.
- GET `api/user/listUsers`
- GET `api/user/showUserInfo` (needs Id param)
- POST `api/user/createUser` (needs name,pin,actualFunds and maxWiithDrawal)
###OperationsController
This controller manages the funds dispension. In order to perform this operation, it requires the customerId, the atmId and the amount of funds to dispense.
After that, we check that the atm and the client have enough funds and notes.
If all the conditions are met, we withdraw the funds to the client and the ATM and then we store a new object that records the data of the operation (FundsDispension object).
-GET `api/operations/dispense` (customerId,pin,atmId,fundsToDispense)

##JUnit Tests
In this case, I had little time so I only could make JUnit tests for the ATMMachine class because it's the only class that has an algorithm with "complex" business logic. This part of the app is critical so I wanted to guarantee
that there aren't any kind of bugs in it. 
The rest of the code of the application is really easy and it's composed of constructors and getters/setters methods so there is no urgent need to test it. 


##
