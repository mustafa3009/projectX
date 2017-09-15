#Base image 
FROM openjdk:8-jre-alpine

MAINTAINER "Mustafa K" 

#Create a folder and copy the paxos folder contents
RUN mkdir -p /opt/paxos
COPY . /opt/paxos
 
#Open the port 5000
EXPOSE 5000
	
# Start App
CMD java -jar -Dserver.ssl.key-store=/opt/paxos/keystore.p12 /opt/paxos/hashdehash2-1.0.jar
