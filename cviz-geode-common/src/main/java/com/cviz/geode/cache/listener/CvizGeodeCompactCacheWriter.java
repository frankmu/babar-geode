package com.cviz.geode.cache.listener;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.geode.cache.CacheWriter;
import org.apache.geode.cache.CacheWriterException;
import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.EntryEvent;
import org.apache.geode.cache.RegionEvent;

import com.cviz.geode.cache.domain.Alert;

public class CvizGeodeCompactCacheWriter implements CacheWriter<String, Alert>, Declarable{
	
	private final Log logger = LogFactory.getLog(CvizGeodeCompactCacheWriter.class);

	public CvizGeodeCompactCacheWriter() {
	}

	@Override
	public void init(Properties props) {
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeUpdate(EntryEvent<String, Alert> event) throws CacheWriterException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeCreate(EntryEvent<String, Alert> event) throws CacheWriterException {
		// TODO Auto-generated method stub
		logger.info("test CvizGeodeCompactCacheWriter");
		System.out.println("test CvizGeodeCompactCacheWriter");
	}

	@Override
	public void beforeDestroy(EntryEvent<String, Alert> event) throws CacheWriterException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeRegionDestroy(RegionEvent<String, Alert> event) throws CacheWriterException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeRegionClear(RegionEvent<String, Alert> event) throws CacheWriterException {
		// TODO Auto-generated method stub
		
	}

}
