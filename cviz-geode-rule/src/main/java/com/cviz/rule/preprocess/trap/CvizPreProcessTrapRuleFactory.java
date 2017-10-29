package com.cviz.rule.preprocess.trap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.cviz.geode.cache.domain.PreProcRule;
import com.cviz.geode.common.api.PreProcRuleService;
import com.cviz.rule.preprocess.CVizPreProcessType;
import com.cviz.rule.preprocess.CvizPreProcessConstant;

@Service
public class CvizPreProcessTrapRuleFactory {

	@Value("${preprocess.rule.source}")
	private String ruleSource;

	@Value("${preprocess.rule.files}")
	private Resource[] files;

	@Autowired
	private PreProcRuleService preProcRuleService;

	private final Log logger = LogFactory.getLog(CvizPreProcessTrapRuleFactory.class);

	public List<CVizPreProcessTrapRule> create() throws JAXBException, IOException{
		List<CVizPreProcessTrapRule> list = new ArrayList<CVizPreProcessTrapRule>();
		if(ruleSource.equalsIgnoreCase(CvizPreProcessConstant.CVIZ_PREPROCESS_FILE_RULE_SOURCE)) {
			Assert.notEmpty(files, "No rule files found! Please enter a valid rule file path");
			for(Resource file : files){
				JAXBContext jaxbContext = JAXBContext.newInstance(CVizPreProcessTrapXMLRule.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				CVizPreProcessTrapXMLRule rule = (CVizPreProcessTrapXMLRule) jaxbUnmarshaller.unmarshal(file.getInputStream());
				logger.info("Load rule file " + rule.getRuleName());
				list.add(rule);
			}
		}else if(ruleSource.equalsIgnoreCase(CvizPreProcessConstant.CVIZ_PREPROCESS_DB_RULE_SOURCE)) {
			for(PreProcRule preProcRule : preProcRuleService.getAllByRuleType(CVizPreProcessType.TRAP.toString(), 100)) {
				list.add(new CVizPreProcessTrapDBRule(preProcRule));
			}
		}
		if(list.isEmpty()) {
			logger.error("No trap pre process rule found. Please add rules and try again.");
		}
		return list;
	}
}
