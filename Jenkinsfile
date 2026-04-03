pipeline {
    agent any

    tools {
        maven 'maven3'
        jdk 'jdk21'
    }

    environment {
        DOCKER_COMPOSE_FILE = 'docker-compose.yml'
    }

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
                    sh 'mvn package -DskipTests'
                }
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }
        stage('Build Docker Images') {
            when { branch 'master' }
            steps {
                withChecks('Build Docker Images') {
                    sh "docker compose -f ${DOCKER_COMPOSE_FILE} build --pull"
                }
            }

        }
        stage('Stop Old Containers') {
            steps {
                withChecks('Stop Old Containers') {
                    sh "docker compose -f ${DOCKER_COMPOSE_FILE} down || true"
                }
            }
        }
        stage('Deploy') {
            when { branch 'master' }
            steps {
                withChecks('Deploy') {
                    sh "docker compose -f ${DOCKER_COMPOSE_FILE} up -d --remove-orphans"
                }
            }
        }
        stage('Health Check') {
            steps {
                withChecks('Health Check') {
                    sh "docker compose -f ${DOCKER_COMPOSE_FILE} ps"
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}