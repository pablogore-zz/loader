###Compile and buildt he  Jar:
The easy way to build the project it´s just run the 
command gradle depends of the your system.
> For Linux/MacOs
```
./gradle
```

> For Windows
```
gradle.bat
```

This command execute the default task 
```
defaultTasks 'clean', 'compileJava', 'test', 
'checkstyleMain' , 'pmd', 'fatjar'
```

###Databse Migration with Flyway:
##### Assumptions
1. The user/password should properly configure and exist previously
2. Before execute the migration , ensure that database schema was created
3. Modify the file config.properties

#### Create tables 
```
    ./gradlew flywayMigrate -i
```
#### Clean 
```
    ./gradlew flywayClean
```
Once we execute the migration and create the fatJar we are able to run the Parser , to do that  
we should execute a gradle task copyJarToBin.
This task will copy the jar file and the config.properties into the working directory  **"work"**

```
$ java -jar logger_http-all-1.0-SNAPSHOT.jar

        -a --accesslog           accesslog file
        -s --startDate           start date under analysis
        -d --duration            duration daily | hourly
        -t --threshold           threshold value
        -i --ip                          ip for filter
        -c --clean                       clean tables for MySQL
```

We also provider a Docker file with docker-compose to install a MySQL database    