package com.babar.geode.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.babar.geode.rest.model.Alert;
import com.babar.geode.rest.repository.AlertRepository;

@Service
public class BabarAlertService {

	@Autowired
	private AlertRepository alertRepository;

	public void insertAlert(Alert alert){
		alertRepository.save(alert);
	}
}
