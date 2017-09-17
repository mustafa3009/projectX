#Base image 
FROM openjdk:8-jre-alpine

MAINTAINER "Mustafa K" 

#Create a folder and copy the paxos folder contents
RUN mkdir -p /opt/eureka
COPY discovery-server-1.0.jar /opt/eureka
 
#Open the port 5000
#EXPOSE 5000
	
# Start App
CMD java -jar /opt/eureka/discovery-server-1.0.jar
