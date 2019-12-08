# EDITABLE_PROFILE


[![Build Status](https://travis-ci.org/nandakumar82/editable_profile_backend.svg?branch=master)](https://travis-ci.org/nandakumar82/editable_profile_backend)

The repository proves as a java backend layer for the users to Create/Edit/View their profile as well as other's profiles

## Getting Started

The code can be cloned on to the machine and run like a simple SpringBoot application 

### Prerequisites

The code has been built with [Intellij Idea Ultimate 2019.3](https://www.jetbrains.com/idea/download/download-thanks.html?platform=windows) version

Lombok plugin installed on the IDE with annotation processors turned on

Java 1.8

Maven wrapper provided by the IDE for dependency management

SpringBoot 2.2.2.RELEASE version

Git
 
SonarLint plugins for code check on the dev environment

### Installing

Once the code has been clone on the local desktop, open it in an IDE of choice,(preferably Intellij) 
navigate to the EditableProfileApplication.java file and right click and run the file as an application.
Since the application makes use of an embedded MongoDb database, there is no more of configuration changes required.
Please remember that this is not a complete functionality and the service will evolve over time.

In case any issues, please run

``` 
mvn clean install
```

to generate the jar file and execute the jar as an application


```
java -jar  editable_profile-0.0.1-SNAPSHOT.jar
```

Once the system is up and running, 

Please follow the 



[SwaggerUi](http://localhost:8080/swagger-ui.html#/profile-controller)


to list out the api documentations available.


[Create an UserProfile](http://localhost:8080/api/profile)

``````
input

{
    "displayName": "test4",
    "passPhrase": "test4",
    "realName": "Nanda Kumar",
    "profilePicture": "giuhojflajfasfk;lasdkfa;sdfk;asdfk;asdkfasd;lk",
    "birthday": "1982-12-14T00:00:00.000+0000",
    "gender": "Male",
    "ethnicity": "Others",
    "religion": "Hindu",
    "height": "173",
    "figure": "Atheletic",
    "maritalStatus": "Married",
    "occupation": "Engineer",
    "aboutMe": "lksdflsmfs;dlf;asdlfka",
    "location": "sdfasdfsadfasdfasdfadsfads"
}
``````

[Other's view](http://localhost:8080/api/profile/{displayName})


[User's View](http://localhost:8080/api/profile/{displayName})

``````
{
  "displayName": "test4",
  "passPhrase": "test4"
}
``````



## Running the tests

Automated Unit test as well as Integration tests have been added to test the classes written which are configured to run
on the Travis CI



### Break down into end to end tests

The tests are self explanatory from their names as detailed below

```
ProfileControllerTest.java

ProfileMapperTest.java

EditableProfileRepositoryTest.java

ProfileServiceTest.java

EditableProfileApplicationTests.java


```

### And coding style tests

#### Work In Progress, as CodeCoverage reports written with Mockito framework is not presently getting reflected in the dashboard

SonarCloud is integrated and refreshed dashboard is available for viewing at

[SonarCloud](https://sonarcloud.io/dashboard?id=nandakumar82_editable_profile_backend)

  

## Deployment

In Progress


## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.


## Authors

* **Nanda Kumar** - *Initial work* - [GitHub](https://github.com/nandakumar82/editable_profile_backend)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Thanks to online materials for helping me come out of the roadblocks with ease

