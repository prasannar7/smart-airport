# smart-airport

This project simulates 'smart airport' which has only one runway. No two or more flights can share the runway, only one can use the runway either for landing or take off.

## Getting Started

Please follow the below instructions to get started with smart airport.

### Prerequisites

Please install the below softwares, if not

* [JDK1.7 or above](http://www.oracle.com/technetwork/java/javase/downloads/index.html) - Runtime environment.
* [Maven](https://maven.apache.org/) - Dependency Management.
* [Eclipse](https://www.eclipse.org/downloads/?) - IDE, you can use any other IDE like IntelliJ etc.
* [Git client](https://git-scm.com/download/) - Pull source code.

### Project setup

If you have completed steps under prerequisites, please follow below steps to set up project.

* Clone the project using git clone.

       git clone https://github.com/prasannar7/smart-airport.git

* Open the command prompt under the directory where you find 'pom.xml'.
* Try building the project using 'mvn clean install' command.
* If build is successful then you are set almost.
* Import the project into your preffered IDE either Eclipse or IntelliJ.

## Running the tests

Three ways you can run the tests,

* While building the project automatically test cases will get executed if you execute any of the below mvn commands,

       mvn clean pakage or mvn clean test or mvn clean install.
       
* In IDE you can execute JUnit test cases.
* You can directly execute main method of the class [SmartAirportSimulator](https://github.com/prasannar7/smart-airport/blob/master/src/main/java/com/prasanna/sim/SmartAirportSimulator.java) to simulate smart airport behaviour.

Since the configruations are externilized you can change the configurations in the respective properties files and re run the tests.

## Deployment

Packaging type for this project is 'Jar' so you can run this project from the command prompt also. When you build project using mvn command, it creates jar file under target folder. Go to target folder open the command prompt and execute below command to simulate smart airport behaviour.

    java -jar smart-airport-1.0.0-SNAPSHOT.jar

Sample simulation result is as below,

    ---------------------------------------------------------------------------------------------------------------------------


    Simulation Id: ea99c7c1-e821-4830-a417-bfdbbad7e98d and Simulation starts at: Sun Mar 19 20:55:26 IST 2017


    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Start @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


    ############################################################################################################################
    ################################################# Smart Airport Simulator ##################################################
    ############################################################################################################################


    Flight Number: 1d7f51f6-dfc8-4e26-88f8-a017f1203a22, Flight action: LANDING Using runway: TRUE, Current LANDING time: 2
    Flight Number: 1d7f51f6-dfc8-4e26-88f8-a017f1203a22, Flight action: LANDING Using runway: TRUE, Current LANDING time: 1
    Flight Number: 1d7f51f6-dfc8-4e26-88f8-a017f1203a22 successfully landed.
    Flight Number: 8785ee71-f78d-4fd2-9ff5-83cc524e6c90, Flight action: TAKING_OFF Using runway: TRUE, Current TAKING_OFF time: 3
    Flight Number: 8785ee71-f78d-4fd2-9ff5-83cc524e6c90, Flight action: TAKING_OFF Using runway: TRUE, Current TAKING_OFF time: 2
    Flight Number: c12308b6-e638-4c56-9a86-9ac1c7e19239, Flight action: TAKING_OFF Using runway: FALSE, Wait time: 1
    Flight Number: 8785ee71-f78d-4fd2-9ff5-83cc524e6c90, Flight action: TAKING_OFF Using runway: TRUE, Current TAKING_OFF time: 1
    Flight Number: c12308b6-e638-4c56-9a86-9ac1c7e19239, Flight action: TAKING_OFF Using runway: FALSE, Wait time: 2
    Flight Number: d9c1900d-94af-43d7-a442-1a60b31afff8, Flight action: TAKING_OFF Using runway: FALSE, Wait time: 1
    Flight Number: 8785ee71-f78d-4fd2-9ff5-83cc524e6c90 successfully taken off.
    Flight Number: c12308b6-e638-4c56-9a86-9ac1c7e19239, Flight action: TAKING_OFF Using runway: FALSE, Wait time: 3
    Flight Number: d9c1900d-94af-43d7-a442-1a60b31afff8, Flight action: TAKING_OFF Using runway: FALSE, Wait time: 2
    Flight Number: c12308b6-e638-4c56-9a86-9ac1c7e19239, Flight action: TAKING_OFF Using runway: TRUE, Current TAKING_OFF time: 3
    Flight Number: d9c1900d-94af-43d7-a442-1a60b31afff8, Flight action: TAKING_OFF Using runway: FALSE, Wait time: 3
    Flight Number: c12308b6-e638-4c56-9a86-9ac1c7e19239, Flight action: TAKING_OFF Using runway: TRUE, Current TAKING_OFF time: 2
    Flight Number: d9c1900d-94af-43d7-a442-1a60b31afff8, Flight action: TAKING_OFF Using runway: FALSE, Wait time: 4
    Flight Number: 53d85d78-6c62-4b05-9204-338991747381, Flight action: LANDING Using runway: FALSE, Wait time: 1
    Flight Number: c12308b6-e638-4c56-9a86-9ac1c7e19239, Flight action: TAKING_OFF Using runway: TRUE, Current TAKING_OFF time: 1
    Flight Number: d9c1900d-94af-43d7-a442-1a60b31afff8, Flight action: TAKING_OFF Using runway: FALSE, Wait time: 5
    Flight Number: 53d85d78-6c62-4b05-9204-338991747381, Flight action: LANDING Using runway: FALSE, Wait time: 2
    Flight Number: 907eadf4-4761-4e62-a4ad-c902ecde45ec, Flight action: TAKING_OFF Using runway: FALSE, Wait time: 1
    Flight Number: c12308b6-e638-4c56-9a86-9ac1c7e19239 successfully taken off.
    Flight Number: d9c1900d-94af-43d7-a442-1a60b31afff8, Flight action: TAKING_OFF Using runway: FALSE, Wait time: 6
    Flight Number: 53d85d78-6c62-4b05-9204-338991747381, Flight action: LANDING Using runway: FALSE, Wait time: 3
    Flight Number: 907eadf4-4761-4e62-a4ad-c902ecde45ec, Flight action: TAKING_OFF Using runway: FALSE, Wait time: 2


    $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Simulation Results $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$


    1 Flights have landed.
    2 Flights have taken off.
    1 Flights waiting to land.
    2 Flights waiting to takeoff.
    3 Flights available in the airport.

## Simulation results and configurations

Currently simulation results are pushed to logs, so that you can compare the results later.

* When you execute main method of the class [SmartAirportSimulator](https://github.com/prasannar7/smart-airport/blob/master/src/main/java/com/prasanna/sim/SmartAirportSimulator.java) to simulate smart airport behaviour. Simulation results will be pushed to the log file 'smart-aiport.log' under ${PROJECT_BASEDIR}/logs/smart-airport/smart-airport.log. It loads logging configurations from [log4.properties](https://github.com/prasannar7/smart-airport/blob/master/src/main/resources/log4j.properties) file.
* When you execute JUnit test cases using [SmartAirportSimulatorTest](https://github.com/prasannar7/smart-airport/blob/master/src/test/java/com/prasanna/test/SmartAirportSimulatorTest.java). Simulation results will be pushed to the log file 'smart-aiport-test.log' under ${PROJECT_BASEDIR}/logs/smart-airport-test/smart-airport-test.log. It loads logging configurations from [log4-test.properties](https://github.com/prasannar7/smart-airport/blob/master/src/test/resources/log4j-test.properties) file.

Simulator configuration details have been externalized to properties file.

* When you execute main method of the class [SmartAirportSimulator](https://github.com/prasannar7/smart-airport/blob/master/src/main/java/com/prasanna/sim/SmartAirportSimulator.java) to simulate smart airport behaviour. It loads simulator configuration details from the [smart-airport.properties](https://github.com/prasannar7/smart-airport/blob/master/src/main/resources/smart-airport.properties) file.
* When you execute JUnit test cases using [SmartAirportSimulatorTest](https://github.com/prasannar7/smart-airport/blob/master/src/test/java/com/prasanna/test/SmartAirportSimulatorTest.java). It loads simulator configuration details from the [smart-airport-test.properties](https://github.com/prasannar7/smart-airport/blob/master/src/test/resources/smart-airport-test.properties) file.


## Report

Two types of document you can generate one is Java doc and other one is Site(HTML doc) by executing the below command,

       mvn clean site
       
 Once this is successful, go to target folder, then go to site folder and click on index.html

## Versioning

[Git](https://git-scm.com/) for versioning.

## Authors

* **Prasanna R** - [Prasanna R](https://github.com/prasannar7)
