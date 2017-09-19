echo "Starting the zuul container"
java -jar -Deureka.client.service-url.defaultZone=http://discovery-server:8761/eureka /opt/zuul/api-gateway-1.0.jar