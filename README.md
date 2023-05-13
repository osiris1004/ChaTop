
![Telesport](/src/main//resources//assets/chaTop.png)


# About The Project

A backend system to handle the connection between the future tenants and owners for seasonal rentals.

# Built With

This section should list any major frameworks/libraries/Dependency used in the project. Here are a few examples.
 - Spring Boots
 - spring-boot-starter-data-jpa
 - mysql-connector-j
 - lombok
 - spring-boot-starter-security
 - io.jsonwebtoken and it sub dependencies 
 - spring-boot-starter-mail
 - springdoc-openapi-starter-webmvc-ui


# Getting Started
This is an example of how you may give instructions on setting up your project locally. To get a local copy up and running it by Following these simple example steps.

## Prerequisites
This is an example of how to list things you need to use the software and how to install them.

### Back-end

1. Requirements
    - JDK 17 and above
    - Maven

2. Clone the repo (backend system)
    >`https://github.com/osiris1004/OC-Project-2.git`
   
3. configure your env.properties
    - create your env.properties at root and th following
    ``` 
        #data base info
        DB_USER= xxxx
        DB_PASSWORD= xxxx

        #mail info
        SENDER_USER=xxxxxx.gmail.com
        SENDER_PASSORD=xxxxx
    ```
4. Running the application locally
    - There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the **ChatopApplication class** from your IDE.

    - Alternatively you can use the Spring Boot Maven plugin like so:
        >`mvn spring-boot:run`
 

### Front-end

1. Clone the repo
    >`git clone https://github.com/osiris1004/OC-Project-2-Front.git`

2. Install NPM packages
    >`npm install`

3. npm
    >`npm start`

# Core concepts 

## Authentication 

- A customer send a request to our backend system.
- First thing that get get executed withing the spring application is the filter.
- Filter has the role to validate and check everything regarding the token that have.
    - in the Filter process will:
        - check if we have the jwt or not
        - if there is no token a 403 is rend back as response 
        - if we have our token we start our validation process as follow;
            1.  check the jxt token
            2. extract the user email(subject) based on the jwt token
            3.  use the email to fetch the user info  from the data base
            4.  if the we are unable to fetch the user it mean the user does not exist and send a 403
            5. if we are able to get  the user, we will then start the validation process
                the amin of the validation process is to validate the generate token based on the user, that is
                 - if the token is not valid or expired, we send a 403 repose
                 - if it is valid we update the securityContextHolder and set connected user. one the securityContextHolder
                 is updated it will automatically dispatch the request to the dispatcher servlet, the send to the controller
                 and then send back the response

The graph below illustrate  the spring boot security architecture 
- ![spring security](/src/main//resources//assets/springSecurity.png)

## Git Flow
Git Flow is a branching model for Git, for managing the development and release of software. The Git Flow model consists of two main branches, that is the main and develop, as well as several supporting branches for feature development, bug fixes, and releases.

Here is a brief overview of the main branches and supporting branches in the Git Flow model:

1. **main branch(master branch)**: This is the main branch that contains the stable and production-ready code. The main(master) branch is always kept in a deployable state and represents the latest release version of the software.

2. **develop branch**: This branch is used for ongoing development and is the main integration branch for feature branches. All new development is merged into the develop branch, which is periodically merged into the master branch when a new release is ready.

3. **Feature branches**: These are branches created off the develop branch for developing new features or enhancements. Feature branches are short-lived and are typically deleted once the feature is completed and merged back into the develop branch.

4. **Release branches**: These are branches created off the develop branch for preparing a new release. Release branches are used to stabilize the code and perform final testing and bug fixing. Once the release is ready, the changes are merged into the master branch and tagged with a release version.



## Contact
Mailto : <a href="mailto:email@example.com, secondemail@example.com">Send Email</a>



