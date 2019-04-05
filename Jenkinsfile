pipeline {
    stage('Preparation') { 
      git 'https://github.com/fabricaperiferia/poc-oauth.git'
   }
    agent { dockerfile true }
    stages {
        stage('Test') {
            steps {
                sh 'node --version'
                sh 'svn --version'
            }
        }
    }
}
