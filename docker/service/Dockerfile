#Base image 
FROM openjdk:8-jre-alpine

MAINTAINER "Mustafa K" 

#Create a folder and copy the paxos folder contents
RUN mkdir -p /opt/service

COPY hashdehash-1.0.jar /opt/service/
 
#Open the port 5000
EXPOSE 5000
	
# Start App
CMD java -jar -Deureka.instance.prefer-ip-address=true -Deureka.client.service-url.defaultZone=http://discovery-server:8761/eureka /opt/service/hashdehash-1.0.jar
