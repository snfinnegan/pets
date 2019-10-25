# pets
pet-rest

Rest calls to https://petstore.swagger.io/
Task 
1. Create and return a new Pet with:
a. Id
b. Category_name
c. Pet_name
d. Status
e. tagName
f. photoUrl
2. Verify The Pet was created with correct data.
3. Update this Pet_name, Verify update and return record.
4. Delete the Pet and demonstrate pet now deleted.

Requirements: Maven and Java 11 installed

Uses JUnit for Tests and Assertions, JsonPath for Json parsing

To run this repository, clone the repository using the `git clone https://github.com/snfinnegan/pets.git` command
Navigate to the ./pets directory and run `mvn clean`, `mvn generate-sources`, `mvn package`
To re-run the test, run `mvn test`
