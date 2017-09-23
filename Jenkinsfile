node {
    try {
        stage ('Checkout') {
            //git 'https://github.com/mustafa3009/projectX'
            checkout scm
        }
        
        stage ('Build') {
            def steps = [:]
			steps["Service"] = {
				dir ('source/hashdehash2') {
                	sh 'mvn clean package'
                } 
			}
			steps["Api Gateway"] = {
				dir ('source/api-gateway') {
                	sh 'mvn clean package'
                }   
			}
			steps["Service Discovery"] = {
				dir ('source/discovery-server') {
                	sh 'mvn clean package'
                }   
			}
			parallel steps
        }
        
        stage ('Copy Jars....') {
            sh 'cp source/hashdehash2/target/hashdehash-1.0.jar docker/service/'
            sh 'cp source/api-gateway/target/api-gateway-1.0.jar docker/zuul/'
            sh 'cp source/discovery-server/target/discovery-server-1.0.jar docker/eureka/'
        }
        
        stage ('Deploying services') {
            dir ('docker') {
                sh '/usr/local/bin/docker-compose down'
                sh '/usr/local/bin/docker-compose up -d'
                sleep 60
                sh '/usr/local/bin/docker-compose scale hashdehash=3'
                sleep 120
            }
        }
        
        stage ('Run Tests') {
           // step 'Run tests'
            sh 'echo pwd && pwd'
            // sh 'pip install requests && python test/test.py --port 5000 --cert-path localcert/localhost.crt'
            sh 'python test/test.py --domain localhost --port 8765 --cert-path localcert/localhost.crt'
            
        }
       
        stage ('Archive') {
            archiveArtifacts 'source/hashdehash2/target/*.jar', 
                            'source/api-gateway/target/*.jar',
                            'source/discovery-server/target/*.jar'
        }
       notify ('Success') 

    } catch (err) {
       notify ('Failure:' + err)
       currentBuild.result = 'FAILURE'
    }
}
    
    def notify(status){
        emailext (
          to: "mk@gmail.com",
          subject: "${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
          body: """<p>${status}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
            <p>Check console output at <a href='${env.BUILD_URL}'>${env.JOB_NAME} [${env.BUILD_NUMBER}]</a></p>""",
    )
}