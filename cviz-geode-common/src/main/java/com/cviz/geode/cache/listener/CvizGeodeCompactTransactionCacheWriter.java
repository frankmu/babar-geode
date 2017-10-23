package com.cviz.geode.cache.listener;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.TransactionEvent;
import org.apache.geode.cache.TransactionWriter;
import org.apache.geode.cache.TransactionWriterException;

public class CvizGeodeCompactTransactionCacheWriter implements TransactionWriter, Declarable{
	
	private final Log logger = LogFactory.getLog(CvizGeodeCompactTransactionCacheWriter.class);

	public CvizGeodeCompactTransactionCacheWriter() {
	}

	@Override
	public void init(Properties props) {
	}

	@Override
	public void beforeCommit(TransactionEvent event) throws TransactionWriterException {
		// TODO Auto-generated method stub
		logger.info("test CvizGeodeCompactTransactionCacheWriter");
		System.out.println("test CvizGeodeCompactTransactionCacheWriter");
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
}