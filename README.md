[![Pet-Clinic build : ](https://circleci.com/gh/vcheruk2/pet-clinic.svg?style=shield)](https://circleci.com/gh/vcheruk2/pet-clinic)
[![Active Development](https://img.shields.io/badge/Maintenance%20Level-Actively%20Developed-brightgreen.svg)](https://github.com/vcheruk2/pet-clinic/issues)

# Pet-Clinic Web Application

A multi-module end to end **Pet Clinic Web Application using Spring MVC**

### Functionalities:

1. Displays Owners/Vet information, Pets and Pet Type and their association to owners and their visit to a veterinarian. Currently, data stored using bootstrap & H2 database.

2. **CRUD Operations**

* Displays Owners/Veterinarians information
* Create/Find an Owner
* Pets & Vets visits
* Update pet association with owner/veterinarians

### Tools and Technologies:

* **Technology** : Bootstrap, Java, Spring MVC, Hibernate, JPA, JUnit5, Mockito, Maven & Gradle
* **Application Servicer**: Apache Tomcat Server
* **Database** : H2 Database

#### Supported URLs:
  * Home Page - http://localhost:8080/
  * Find Owner - http://localhost:8080/owners/find
  * Show All Owners - http://localhost:8080/owners?lastName=
  * Owner Information & Pet Association - http://localhost:8080/owners/1
  * Vet List Page  - http://localhost:8080/vets.html
  
## Utilized front-end html/css pages from Spring Project examples provided by Pivotal.

### Home Page
![alt text](https://github.com/vcheruk2/pet-clinic/blob/master/pc_home_page.JPG?raw=true "Title")

### Owner All
![alt text](https://github.com/vcheruk2/pet-clinic/blob/master/pc_owners_all.JPG?raw=true "Title")

### Owner Find
![alt text](https://github.com/vcheruk2/pet-clinic/blob/master/pc_owners_find.JPG?raw=true "Title")

### Owner Info
![alt text](https://github.com/vcheruk2/pet-clinic/blob/master/pc_owners_info.JPG?raw=true "Title")

### Vet Info
![alt text](https://github.com/vcheruk2/pet-clinic/blob/master/pc_vet_page.JPG?raw=true "Title")

### To build the project (Windows):

1. Development Platform - IntelliJ Idea
   * [Download IntelliJ Idea](https://www.jetbrains.com/idea/download/#section=windows)
   
2. Java SDK
   * [Download Java](https://www.java.com/en/download/)
   
3. Build Tool - Maven & Gradle
   * [Download Maven](https://maven.apache.org/download.cgi)
   * [Install Maven](https://maven.apache.org/install.html)
   * [Download Gradle](https://gradle.org/releases/)
   * [Install Gradle](https://gradle.org/install/)

4. After downloading & Installing Java, Maven and Gradle.
  * Ensure that the path variables are set correctly. (JAVA_HOME, GRADLE_HOME, M2_HOME & MAVEN_HOME).
  * Add these to the Path variable in System variables "%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%GRADLE_HOME%\bin". This lets your system know where the required binaries are located.
  
5. Verify installation
  * Java installation can be verified by running "java -verson" in command prompt.
  * Maven installation can be verified by running "mvn -v" in command prompt.
  * Gradle installation can be verified by running "gradle -v" in command prompt.
  We should be seeing actual versions rather than '* is not recognized as as internal or external command' error.

6. Clone the repository and import it to IntelliJ

7. Run the Recipe Spring Application through IntelliJ.
