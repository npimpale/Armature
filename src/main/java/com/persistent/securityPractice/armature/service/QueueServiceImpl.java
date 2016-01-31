package com.persistent.securityPractice.armature.service;

import org.springframework.stereotype.Service;

import com.persistent.securityPractice.armature.dto.Assessment;
import com.persistent.securitybu.scanner.manager.QueueSender;

@Service
public class QueueServiceImpl implements QueueService {
	
	private QueueSender sender;

	@Override
	public boolean addAssessmentToQueue(String zipFilePath,
			Assessment assessment) {
		// TODO Auto-generated method stub
		
		/*populateQueueProject(assessment);
		
		sender.sendProjectToQueue(zipFilePath, queueProject, "10.77.164.39", "61616", queueProject.getRemQueue());*/
		
		
		return false;
	}

}
