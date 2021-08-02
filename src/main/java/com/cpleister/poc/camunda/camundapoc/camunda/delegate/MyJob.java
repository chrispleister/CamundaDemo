package com.cpleister.poc.camunda.camundapoc.camunda.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.persistence.entity.ExecutionEntity;

@Slf4j
public class MyJob implements JavaDelegate {

	@Override
	public void execute(DelegateExecution delegateExecution) throws Exception {
		String processName = ((ExecutionEntity) delegateExecution).getProcessDefinition().getKey();
		delegateExecution.getProcessBusinessKey();
		log.info(String.format("My Job called from %s ! Going to sleep for 3 seconds...", processName));
		Thread.sleep(3000);
	}
}
