pipeline {
    agent any
    tools {
    	git 'Default'
	}
    stages {
        stage('Checkout') {
            steps {
				bat 'git --version'
                git branch: 'master', url: 'https://github.com/Harshe2025/SM_RestAPI'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
    }
}