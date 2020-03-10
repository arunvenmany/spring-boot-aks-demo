def label = "worker-${UUID.randomUUID().toString()}"

podTemplate(label: label, containers: [
  containerTemplate(name: 'gradle', image: 'gradle:4.8.1-jdk8', command: 'cat', ttyEnabled: true),
  containerTemplate(name: 'docker', image: 'docker', command: 'cat', ttyEnabled: true),
  containerTemplate(name: 'kubectl', image: 'lachlanevenson/k8s-kubectl:v1.14.8', command: 'cat', ttyEnabled: true),
  containerTemplate(name: 'helm', image: 'lachlanevenson/k8s-helm:v2.13.1', command: 'cat', ttyEnabled: true)
],
volumes: [
  hostPathVolume(mountPath: '/home/gradle/.gradle', hostPath: '/tmp/jenkins/.gradle'),
  hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock')
]) {
  node(label) {
    def myRepo = checkout scm
    def gitCommit = myRepo.GIT_COMMIT
    def gitBranch = myRepo.GIT_BRANCH
    def shortGitCommit = "${gitCommit[0..10]}"
    def previousGitCommit = sh(script: "git rev-parse ${gitCommit}~", returnStdout: true)
 
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
        throw(exc)
      }
    }
    stage('Build') {
      container('gradle') {
        sh "gradle build -g /home/gradle"
      }
    }
    stage('Create Docker images') {
      container('docker') {
        withCredentials([[$class: 'UsernamePasswordMultiBinding',
          credentialsId: 'dockerhub',
          usernameVariable: 'DOCKER_HUB_USER',
          passwordVariable: 'DOCKER_HUB_PASSWORD']]) {
          sh """
            docker login http://aksspringreacthandson.azurecr.io -u ${DOCKER_HUB_USER} -p ${DOCKER_HUB_PASSWORD}
            docker build -t aksspringreacthandson.azurecr.io/spring-aks-app:${gitCommit} .
            docker push aksspringreacthandson.azurecr.io/spring-aks-app:${gitCommit}
            """
        }
      }
    }

    stage('Deploy to Kubernetes') {
      container('helm') {
         withCredentials([[$class: 'UsernamePasswordMultiBinding',
          credentialsId: 'mongo-password',
          usernameVariable: 'MONGO_USER',
          passwordVariable: 'MONGO_PASSWORD']]) {
        sh """
        helm init --client-only --skip-refresh
        touch ./infra/helm/tmp-values.yaml
        echo "dockerTag=${gitCommit} \n mongoPassword=${MONGO_PASSWORD}" >> ./infra/helm/tmp-values.yaml
        cat ./infra/helm/tmp-values.yaml
        helm upgrade  spring-boot-aks-app ./infra/helm/ --set dockerTag=${gitCommit} --values infra/helm/values.yaml --set  --namespace handson
        """
      }
     }
    }
  }
}
