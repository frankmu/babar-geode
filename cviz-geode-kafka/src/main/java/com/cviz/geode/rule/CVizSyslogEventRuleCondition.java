package com.cviz.geode.rule;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class CVizSyslogEventRuleCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String dbname = context.getEnvironment().getProperty("rule.type");
		return dbname.equalsIgnoreCase(CVizEventType.SYSLOG.toString());
	}
}