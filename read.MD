

Populate database just for test purpose 
`docker exec -i mail-sender-service_dbsql_1 mysql -uesuarez-mail --password=esPass mailService < "./src/main/resources/data.sql"`
