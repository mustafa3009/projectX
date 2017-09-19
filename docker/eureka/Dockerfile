#Base image 
FROM openjdk:8-jre-alpine

MAINTAINER "Mustafa K" 

#Create a folder and copy the paxos folder contents
RUN mkdir -p /opt/eureka
COPY run_eureka.sh discovery-server-1.0.jar /opt/eureka/
 
#Open the port 5000
#EXPOSE 8761
	
# Start App
CMD /opt/eureka/run_eureka.sh
