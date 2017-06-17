package com.babar.geode.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babar.geode.common.model.Alert;
import com.babar.geode.common.repository.AlertRepository;

@Service
public class BabarGeodeAlertService {

	@Autowired
	private AlertRepository alertRepository;

	public void insertAlert(Alert alert){
		alertRepository.save(alert);
	}
}