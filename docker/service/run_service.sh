echo "Starting the hash service container"
java -jar -Dcassandra.host=cassandra0 -Deureka.instance.prefer-ip-address=true -Deureka.client.service-url.defaultZone=http://discovery-server:8761/eureka /opt/service/hashdehash-1.0.jar
