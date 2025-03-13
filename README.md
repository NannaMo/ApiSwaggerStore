# API Test Automation - Store (Swagger Petstore)

## Project Description 
This project contains automated API tests for the endpoints of the store group of the Swagger Petstore.  
The tests check basic functions such as creating, receiving, and deleting orders.  

Implemented using:  
- [Java](https://www.java.com/) - core programming language
- [TestNG](https://testng.org/) - test framework
- [Rest Assured](https://rest-assured.io/) - for API testing
- [Maven](https://maven.apache.org/) - for dependency management
- [Allure](https://qameta.io/allure/) - for report generation
- [SLF4J](http://www.slf4j.org/) - for logging
- [Lombok](https://projectlombok.org/) - for code simplification

## Requirements  
Before you start, make sure you have:  
- Java 11+  
- Apache Maven 3+  
- Allure Commandline (to view reports)  
- Git (to clone the repository)  

You can check the versions with the commands:  
java -version
mvn -version
allure --version
git --version

## Settings

Cloning the repository
```sh
git clone <repo-url>
cd ApiSwaggerStore
```

Installing dependencies
```sh
mvn clean install
```

## Known Issues

⚠️ **Note:** The tests interact with the public [Swagger Petstore](https://petstore.swagger.io/) API, which is a shared testing environment.  
- Test data may be modified or overwritten by other users, potentially affecting test stability.  
- Some tests may fail due to data inconsistencies or concurrent modifications by other testers.  
- The API itself may be unavailable at times, causing temporary failures.  

🔹 If you encounter unexpected failures, consider re-running the tests or verifying the test data before execution.  

## Run the tests

Run all tests
```sh
mvn clean test
```

Generate and view test results in Allure
```sh
mvn allure:serve
```

## Approach to testing

Organization of tests
- Tests are divided into endpoints of the store group.
- Both positive and negative scenarios are provided for each endpoint.
- TestNG is used to manage test scenarios and parameterize tests.
  
Verification of API responses
- Status code is checked (200, 400, 404, etc.).
- JSON schemas and key values in responses are validated.
- Requests and responses are logged using SLF4J.
  
Reporting and logging
- Allure is used to generate detailed reports.
- All tests contain Allure annotations to display test steps.
  
Extensibility and support
- Lombok is used to reduce boilerplate code.
- The API client is implemented on the basis of Rest Assured.
- Test data can be passed via JSON or parameters.
