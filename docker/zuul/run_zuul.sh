echo "Starting the zuul container"
java -jar -Deureka.client.service-url.defaultZone=http://discovery-server:8761/eureka -Dserver.ssl.key-store=/opt/zuul/keystore.p12 /opt/zuul/api-gateway-1.0.jar