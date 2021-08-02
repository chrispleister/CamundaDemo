package com.cpleister.poc.camunda.camundapoc.web.rest;

import camundajar.impl.scala.util.Try;
import lombok.val;
import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.exception.NullValueException;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;

@RestController
@RequestMapping(path = "/camundapoc/api/process")
public class JobController {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private ManagementService managementService;

	@Autowired
	private RepositoryService repositoryService;

	@ResponseBody
	@GetMapping(path = "/{processName}")
	public ResponseEntity startJob(@PathVariable String processName) {
		boolean processIsDefined = repositoryService
				.createProcessDefinitionQuery()
				.list()
				.stream()
				.anyMatch(processDefinition -> processName.equals(processDefinition.getKey()));

		if (!processIsDefined) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Process Name is not found in deployments.");
		}

		Instant start = Instant.now();
		ProcessInstance instance = runtimeService.createProcessInstanceByKey(processName).execute();
		Instant end = Instant.now();
		Duration duration = Duration.between(start, end);

		String output = String.format("Process Name: %s. Started %s \n End %s \n <b> Duration %s Seconds. </b>", processName, start
				.toString(), end.toString(), duration.getSeconds());
		return ResponseEntity.status(200).body(output);
	}
}
