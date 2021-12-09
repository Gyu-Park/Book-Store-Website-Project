pipeline {
    agent any
    stages {
        stage('Clean and Install') {
            steps {

                sh "mvn clean install"
            }
        }


        stage('Build Docker image'){
            steps {
              
                sh 'docker build -t kyesung8282/boogle1:${BUILD_NUMBER} .'
            }
        }

        stage('Docker Login'){
            
            steps {
                 withCredentials([string(credentialsId: 'DockerId', variable: 'Dockerpwd')]) {
                    sh "docker login -u kyesung8282 -p ${Dockerpwd}"
                }
            }                
        }

        stage('Docker Push'){
            steps {
                sh 'docker push kyesung8282/boogle1:${BUILD_NUMBER}'
            }
        }
        
        stage('Docker deploy'){
            steps {
               
                sh 'docker run -itd -p  80:8083 kyesung8282/boogle1:${BUILD_NUMBER}'
            }
        }

        
        stage('Archving') { 
            steps {
                 archiveArtifacts '**/target/*.jar'
            }
        }
    }
}
