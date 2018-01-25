#Base image 
FROM openjdk:8-jre-alpine

MAINTAINER "Mustafa K" 

#Add bash shell for wait-for-it script
RUN apk add --update bash && rm -rf /var/cache/apk/*

#Create a folder and copy the folder contents
RUN mkdir -p /opt/service

COPY run_service.sh hashdehash-1.0.jar wait-for-it.sh /opt/service/
 
#Open the port 5000
EXPOSE 5000
	
# Start App
CMD /opt/service/run_service.sh

#java -jar -Deureka.instance.prefer-ip-address=true -Deureka.client.service-url.defaultZone=http://discovery-server:8761/eureka /opt/service/hashdehash-1.0.jar
