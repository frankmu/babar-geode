package com.cviz.geode.cache.listener;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.TransactionEvent;
import org.apache.geode.cache.TransactionListener;

public class CvizGeodeCompactTransactionListener implements TransactionListener, Declarable{

	private final Log logger = LogFactory.getLog(CvizGeodeCompactTransactionListener.class);
	
	public CvizGeodeCompactTransactionListener() {
	}

	@Override
	public void init(Properties props) {
	}

	@Override
	public void close() {
	}

	@Override
	public void afterCommit(TransactionEvent event) {
		logger.info("test CvizGeodeCompactTransactionListener");
		System.out.println("test CvizGeodeCompactTransactionListener");
	}

	@Override
	public void afterFailedCommit(TransactionEvent event) {
	}

	@Override
	public void afterRollback(TransactionEvent event) {
	}
}