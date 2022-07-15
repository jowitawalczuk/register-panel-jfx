# Registration and Logging Panel in JavaFX

>Application with GUI that enables reading and saving entered by user data from excel spreadsheet.

## Table of Contents
* [Funcionality](#funcionality)
* [Screenshots](#screenshots)
* [JavaFX installation](#javafx-installation)
* [Setup](#setup)
* [Project Status](#project-status)
* [Room for Improvement](#room-for-improvement)

## Funcionality
The parent functionality comes down to:
- registration of new users (checking if entered data meet the registration conditions ex. password containing at least 6 characters etc., saving user data into excel spreadsheet)
- logging registered users (iterating trough excel spreadsheet in order to find a user with the entered username && checking correctness of the entered password)

## Screenshots

![image](https://user-images.githubusercontent.com/104503502/179259597-fb1f6ad6-94b8-4203-94af-8db1217004e4.png)

![image](https://user-images.githubusercontent.com/104503502/179256527-5412a670-880f-40e2-8dfa-35894a852ebb.png)

## JavaFX installation
This program was written using Java 11.
In Java 11, JavaFX was removed from the SDK.
It is now in its own separate module, and if you want to use it in your application you will need to specifically include it.
You can find more information about this in [_here_](https://blog.idrsolutions.com/using-javafx-with-java-11/). 

## Setup
In order to make this program to work I used listed below .jar files. 
* common-collections4-4.4
* common-compress-1.21
* poi-5.2.2
* poi-excelant-5.5.2
* poi-ooxml-5.2.2
* xmlbeans-5.1.0
* commons-io-2.11.0
* poi-ooxml-full-5.2.2
* log4j
* log4j-api-2.18.0 

...and set of javafx's.

You can download it from [_here_](https://mvnrepository.com/).

If you are using IntelliJ IDEA IDE adding those into your project takes place as follows:

File -> Project Structure... -> Modules -> Dependencies -> + -> JARs an Directiories

Note: There is a posibility that not all of them are necessary (if so please, let me know :))
 
## Project Status
Project is no longer being worked on due successful implementation of the intended functionality and widespreading lack of interest in JavaFX.

## Room for Improvement
* The logic of nesting conditional statements in "RegistrationController.java" file.
