pipeline {
    agent any
    stages {
        stage('Docker Pull'){
            steps {
                sh 'docker pull kyesung8282/boogle1:latest'
            }
        }
        
        stage('Docker deploy'){
            steps {
               
                sh 'docker run -itd -p  80:8083 kyesung8282/boogle1:latest'
            }
        }
    }
}
