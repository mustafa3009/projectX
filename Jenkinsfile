node {
    try {
        stage ('Checkout') {
            //git 'https://github.com/mustafa3009/projectX'
            checkout scm
        }
        
        stage ('Build') {
            parallel buildService: {
                def srcPath = 'source/hashdehash2'
                dir (srcPath) {
                sh 'mvn clean package'
                }    
            }, buildApiGatewway: {
                def srcPath = 'source/api-gateway'
                dir (srcPath) {
                sh 'mvn clean package'
                }
            }, buildServiceDiscovery: {
                def srcPath = 'source/discovery-server'
                dir (srcPath) {
                sh 'mvn clean package'
                }
            }, failFast: true
        }
        
        stage ('Copy Jars....') {
            sh 'cp source/hashdehash2/target/hashdehash-1.0.jar docker/service/'
            sh 'cp source/api-gateway/target/api-gateway-1.0.jar docker/zuul/'
            sh 'cp source/discovery-server/target/discovery-server-1.0.jar docker/eureka/'
        }
        
        stage ('Start services') {
            dir ('docker') {
                sh '/usr/local/bin/docker-compose up -d'
            }
            sleep 20
            
        }
        
        stage ('Test') {
           // step 'Copy the jars to docker folder'
            
            // sh 'pip install requests && python test/test.py --port 5000 --cert-path localcert/localhost.crt'
            sh 'python test/test.py --port 8765 --cert-path localcert/localhost.crt'
            
        }
       
        stage ('Archive') {
            archiveArtifacts 'source/hashdehash2/target/*.jar', 
                            'source/api-gateway/target/*.jar',
                            'source/eureka/target/*.jar'
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