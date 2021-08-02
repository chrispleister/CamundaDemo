# Camunda Spring Boot Demo
Demo camunda project showing async and sync processes
for potential rest clients.

### Start Server
`./mvnw spring-boot:run`

### Start Processes

`curl http://localhost:8080/camundapoc/api/process/SyncProcess`

`curl http://localhost:8080/camundapoc/api/process/AsyncProcess`

### Process Definitions
./src/main/resources/bpmn/AsyncProcess.bpmn
./src/main/resources/bpmn/SyncProcess.bpmn

### Camunda Webinterface
`http://localhost:8080/camunda/app/`