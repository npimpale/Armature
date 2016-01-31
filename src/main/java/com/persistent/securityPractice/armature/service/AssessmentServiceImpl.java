package com.persistent.securityPractice.armature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistent.securityPractice.armature.dao.AssessmentDAO;
import com.persistent.securityPractice.armature.dao.AssessmentDAOImpl;
import com.persistent.securityPractice.armature.dto.Assessment;

@Service
public class AssessmentServiceImpl implements AssessmentService {
	private AssessmentDAO assessmentDao;
	/*private UserService userService;
	private ProjectService projectService;
	private ProductService productService;*/

	/*@Autowired(required = true)
	public void setUserService(UserServiceImpl userServiceImpl) {
		this.userService = userServiceImpl;
	}

	@Autowired(required = true)
	public void setProjectService(ProjectServiceImpl projectServiceImpl) {
		this.projectService = projectServiceImpl;
	}

	@Autowired(required = true)
	public void setProductService(ProductServiceImpl productServiceImpl) {
		this.productService = productServiceImpl;
	}*/

	@Autowired(required = true)
	public void setAssessmentDao(AssessmentDAOImpl assessmentDaoImpl) {
		this.assessmentDao = assessmentDaoImpl;
	}

	public Number createAssessment(Assessment assessment) throws Exception {
		
		Number id = null;
		try {
			id = assessmentDao.createAssessment(assessment);
		} catch (Exception e) {
			throw new Exception("Exception in create assessment service.", e);
		}

		return id;
	}

	public long updateAssessment(Assessment assessment) throws Exception {
		long id = 0;
		try{
			id = assessmentDao.updateAssessment(assessment);
		}catch(Exception e){
			throw new Exception("Error while updating Assessment in Service", e);
		}
		
		return id;
	}
}
