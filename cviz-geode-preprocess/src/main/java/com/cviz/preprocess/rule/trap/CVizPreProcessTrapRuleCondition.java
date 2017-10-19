package com.cviz.preprocess.rule.trap;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.cviz.preprocess.rule.CVizPreProcessType;

public class CVizPreProcessTrapRuleCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String ruleType = context.getEnvironment().getProperty("preprocess.rule.type");
		return ruleType.equalsIgnoreCase(CVizPreProcessType.TRAP.toString());
	}
}