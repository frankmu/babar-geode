package com.cviz.rule.preprocess.syslog;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.cviz.rule.preprocess.CVizPreProcessType;

public class CVizPreProcessSyslogRuleCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String ruleType = context.getEnvironment().getProperty("preprocess.rule.type");
		return ruleType.equalsIgnoreCase(CVizPreProcessType.SYSLOG.toString());
	}
}