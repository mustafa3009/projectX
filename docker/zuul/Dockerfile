#Base image 
FROM openjdk:8-jre-alpine

MAINTAINER "Mustafa K" 

#Create a folder and copy the paxos folder contents
RUN mkdir -p /opt/zuul
COPY run_zuul.sh api-gateway-1.0.jar keystore.p12 /opt/zuul/
 
#Open the port 8765
EXPOSE 8765
	
# Start App
CMD /opt/zuul/run_zuul.sh
