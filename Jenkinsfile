node  {
    try {
        stage ('Checkout') {
            //git 'https://github.com/mustafa3009/projectX'
            checkout scm
        }
        
          
          
               
        stage ('Build') {
        	stash name: 'hashdehash', 
        	includes: 'source/hashdehash2/**/*'
        	
        	stash name: 'api-gateway', 
        	includes: 'source/api-gateway/**/*'
        	
        	stash name: 'discovery-server', 
        	includes: 'source/discovery-server/**/*'
        
			parallel service: {
				buildPackage ('source/hashdehash2', 'hashdehash')
			}, api-gateway: {
				buildPackage ('source/api-gateway', 'api-gateway')
			}, discovery-server: {
				buildPackage ('source/discovery-server', 'discovery-server')
			}	
        }
        
        stage ('Copy Jars to docker folder') {
            sh 'cp /repo/hashdehash-1.0.jar docker/service/'
            sh 'cp /repo/api-gateway-1.0.jar docker/zuul/'
            sh 'cp /repo/discovery-server-1.0.jar docker/eureka/'
        }
        
      
        stage ('Deploying services') {
            dir ('docker') {
              //  sh '/usr/local/bin/docker-compose down'
                sh '/usr/local/bin/docker-compose up -d --build'
                sleep 30
                sh '/usr/local/bin/docker-compose scale hashdehash=3'
                sleep 60
            }
        }
        
        stage ('Run Tests') {
           // step 'Run tests'
            sh 'echo pwd && pwd'
            // sh 'pip install requests && python test/test.py --port 5000 --cert-path localcert/localhost.crt'
            sh 'python test/test.py --domain localhost --port 8765 --cert-path localcert/localhost.crt'
            
        }
       
        stage ('Archive') {
        	archiveArtifacts 'source/**/target/*.jar'
        }      	
       notify ('Success') 

    } catch (err) {
       notify ('Failure:' + err)
       currentBuild.result = 'FAILURE'
    }
}
    
    def buildPackage (sourcePath, stashName) {
    	node {
			sh 'rm -rf *'
        	unstash stashName
        	dir (sourcePath) {
				sh 'mvn clean package'
			}
			sh 'cp source/**/target/*.jar /repo'
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