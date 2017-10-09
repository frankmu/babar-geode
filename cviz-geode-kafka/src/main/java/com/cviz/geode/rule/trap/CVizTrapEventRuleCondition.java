package com.cviz.geode.rule.trap;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.cviz.geode.rule.CVizEventType;

public class CVizTrapEventRuleCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String dbname = context.getEnvironment().getProperty("rule.type");
		return dbname.equalsIgnoreCase(CVizEventType.TRAP.toString());
	}
}