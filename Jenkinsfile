pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                withChecks('Build') {
                    sh 'mvn clean compile'
                }
            }
        }
        stage('Test') {
            steps {
                withChecks('Test') {
                    sh 'mvn test'
                }
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Package') {
            when { branch 'master' }
            steps {
                withChecks('Package') {
                    sh 'mvn package'
                }
            }
        }
        stage('Deploy') {
            when { branch 'master' }
            steps {
                withChecks('Deploy') {
                    echo 'Deployment step'
                }
            }
        }
    }
}