package com.cviz.preprocess.rule.syslog;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.cviz.preprocess.rule.CVizPreProcessType;

public class CVizPreProcessSyslogRuleCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String dbname = context.getEnvironment().getProperty("rule.type");
		return dbname.equalsIgnoreCase(CVizPreProcessType.SYSLOG.toString());
	}
}