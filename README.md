# Don't hesitate to give me a ⭐ to make the project more popular.
# APIRestAssuredJunit
This is a project using Rest Assured to test API. I used the public API https://reqres.in/, so you can be easy to touch, apply and learn others' APIs. [You can reach me](https://github.com/gunzpro21/gunzpro21). 
## Tools / libraries used:
1. Java 1.8
2. Rest Assured 4.5.1
3. JUnit 4.12
4. Maven 3.8
5. Extent reports 5.0.9
6. Lombok 1.18.20
7. Java faker 1.0.2
## Required software:
* [Java 1.8+ installed](https://phoenixnap.com/kb/install-java-windows)
* [Maven Installed](https://mkyong.com/maven/how-to-install-maven-in-windows/)
* [Enable LOMBOK plugin on your IDE](https://www.baeldung.com/lombok-ide)
## How to execute the tests:
You can open each test class on src\test\java\mainStream and execute a specific test you want to run( **You to need configure build path to Junit4 on Eclipse**).
## Running the test suites
- The test suites can be run directly by your IDE or by command line. If you run mvn test all the tests will execute because it's the regular Maven lifecycle to run all the tests: mvn test.
- To run different suites based on the groups defined for each test you must inform the property. The example below shows how to run the test for each pipeline stage:
mvn -Dtest=LoginTest test
## Cucumber Report
This is a feature overview page:
![image](https://user-images.githubusercontent.com/27693044/228485644-aa06c22a-e770-4e32-9e27-b9865d7877a9.png)
And detail Page
![image](https://user-images.githubusercontent.com/27693044/228486123-4ed9641f-7d8d-4e36-99d1-410edd50239a.png)
