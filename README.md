Spring Boot application:

To build:
    -clone repo to your local machine: git clone <repo url from git>
    -cd to the new directory and build using: mvn clean install

To run:
    -In intellij you can use the run configurations
    -From the command line: mvn spring-boot:run or java -jar target/charter-coding-test-0.0.1-SNAPSHOT.jar

The database is empty to start out. I have included the data for postman in main/java/resources/postman.txt. This file includes the data and urls to run in postman.

1. Add customers to the database using the data in the postman file as the request body. http://localhost:8282/addCustomers
2. Calculate the new reward for the customer by posting to: http://localhost:8282/updateCustomerRewardBalance/{id} and using the test data as the request body.
In postman, navigate to the "Body" tab. Select the radio button labeled "raw" and change the dropdown to JSON.