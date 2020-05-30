# Witio-mail-sender

### Tools

- Java 11
- Mysql
- MongoDB
- Redis

### Libraries
- SendGrid
- Gmail smtp

## Steps to run

1. `docker-compose up -d`
2. Build Java application `gradlew bootJar` Be sure to use java 11    
4. For relational profile, Before to run app, run the next command to populate the database: 

    `docker exec -i mail-sender-service_dbsql_1 mysql -uesuarez-mail --password=esPass mailService < "./src/main/resources/data.sql"` 
    
    If you see the next error: `ERROR 2002 (HY000): Can't connect to local MySQL server through socket '/var/run/mysqld/mysqld.sock' (2)` 
    
    Then use: 
    - `docker cp ./src/main/resources/data.sql mail-sender-service_dbsql_1:/`
    - `docker exec -it mail-sender-service_dbsql_1 bash`
    - `mysql -u root -h 127.0.0.1 --password=rootPass mailService < "./data.sql"`

5. For norelational profile: 
    1. Login as root: `docker exec -it mail-sender-service_dbnon-sql_1 mongo -u root -p rootPass`
    1. Create the database for the app: `use mail`
    1. Create the user to connect to the app: `db.createUser({user: "esuarez", pwd: "esPass", roles: [{ role: "readWrite", db: "mail" }] })`
    1. Go to: `http://localhost:8282/init` to init the nonSql database, after running the app
    
3. Run app `gradlew bootRun` 
    
    Use `--args='--spring.profiles.active=relational'` flag for relational environment 
    
    or `--args='--spring.profiles.active=norelational'`
    
6. Go to the root route to see Swagger routes `http://localhost:8181/` or `http://localhost:8282/` 
