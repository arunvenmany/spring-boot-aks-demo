def label = "worker-${UUID.randomUUID().toString()}"

podTemplate(label: label, containers: [
        containerTemplate(name: 'gradle', image: 'gradle:4.8.1-jdk8', command: 'cat', ttyEnabled: true),
        containerTemplate(name: 'docker', image: 'docker', command: 'cat', ttyEnabled: true),
        containerTemplate(name: 'kubectl', image: 'lachlanevenson/k8s-kubectl:v1.14.8', command: 'cat', ttyEnabled: true),
        containerTemplate(name: 'helm', image: 'lachlanevenson/k8s-helm:v2.8.2', command: 'cat', ttyEnabled: true)
],
        volumes: [
                hostPathVolume(mountPath: '/home/gradle/.gradle', hostPath: '/tmp/jenkins/.gradle'),
                hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock')
        ]) {
    node(label) {
        def myRepo = checkout scm
        def gitCommit = myRepo.GIT_COMMIT
        def gitBranch = myRepo.GIT_BRANCH
        stage('Test') {
            try {
                container('gradle') {
                    sh """
            pwd
            echo "GIT_BRANCH=${gitBranch}" 
            echo "GIT_COMMIT=${gitCommit}" 
            gradle test -g /home/gradle
            """
                }
            }
            catch (exc) {
                println "Failed to test - ${currentBuild.fullDisplayName}"
                throw (exc)
            }
        }
        stage('Build') {
            container('gradle') {
                sh "gradle build -g /home/gradle"
            }
        }
        stage('Create Docker images') {
            container('docker') {
                withCredentials([[$class          : 'UsernamePasswordMultiBinding',
                                  credentialsId   : 'dockerhub',
                                  usernameVariable: 'DOCKER_HUB_USER',
                                  passwordVariable: 'DOCKER_HUB_PASSWORD']]) {
                    sh """
            docker login http://aksspringreacthandson.azurecr.io -u ${DOCKER_HUB_USER} -p ${DOCKER_HUB_PASSWORD}
            docker build -t aksspringreacthandson.azurecr.io/spring-aks-app:${gitCommit} .
            docker tag aksspringreacthandson.azurecr.io/spring-aks-app:${gitCommit}  aksspringreacthandson.azurecr.io/spring-aks-app:latest
            docker push aksspringreacthandson.azurecr.io/spring-aks-app:${gitCommit}
            docker push aksspringreacthandson.azurecr.io/spring-aks-app:latest
            """
                }
            }
        }

        stage('Deploy to Kubernetes') {
            container('helm') {
                sh """
        helm init --client-only --skip-refresh
        helm upgrade  spring-boot-aks-app ./infra/helm/ --install --namespace handson --values infra/helm/values.yaml --wait  --force --recreate-pods
        """
            }
        }
    }
}
