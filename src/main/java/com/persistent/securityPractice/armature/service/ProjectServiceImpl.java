package com.persistent.securityPractice.armature.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistent.securityPractice.armature.dao.ProjectDAO;
import com.persistent.securityPractice.armature.dao.ProjectDAOImpl;
import com.persistent.securityPractice.armature.dto.Product;
import com.persistent.securityPractice.armature.dto.Project;

@Service
public class ProjectServiceImpl implements ProjectService {
	private ProjectDAO projectDAO;
	private ProductService productService;

	@Autowired(required = true)
	public void setProjectDAO(ProjectDAOImpl projectDAOImpl) {
		this.projectDAO = projectDAOImpl;
	}

	@Autowired(required = true)
	public void setProductService(ProductServiceImpl productServiceImpl) {
		this.productService = productServiceImpl;
	}

	public List<Project> getProjectListByUser(long id) {
		List<Project> projectList = projectDAO.getProjectsByUser(id);
		populateProjectsProducts(projectList,
				getProjectsProducts(getProjectIdList(projectList)));
		return projectList;
	}

	public List<Project> getProjectListByUser(String userName) {
		List<Project> projectList = projectDAO.getProjectsByUser(userName);
		return projectList;
	}

	public long addProject(Project project) throws Exception {
		// check project exists
		long key = 0;
		try {
			if (!project.getName().isEmpty()) {
				if (!checkProjectExists(project.getName())) {
					key = projectDAO.addProject(project).longValue();
				} else {
					throw new Exception("Error: Project already Exists");
				}
			} else {
				throw new Exception("Error in Project name");
			}

		} catch (Exception e) {
			throw new Exception(e);
		}

		return key;
	}

	// This is not yet in use
	private List<Product> getProjectProducts(long projectId) {
		List<Product> productList = new ArrayList<Product>();
		productList.addAll(productService.getProjectProducts(projectId));
		return productList;
	}

	private Map<Long, List<Product>> getProjectsProducts(List<Long> projectIds) {
		Map<Long, List<Product>> projectsProductsMap = new HashMap<Long, List<Product>>();
		projectsProductsMap.putAll(productService
				.getProjectsProducts(projectIds));
		return projectsProductsMap;
	}

	private List<Long> getProjectIdList(List<Project> projectList) {
		List<Long> projectIdlist = new ArrayList<Long>();
		for (Project project : projectList) {
			projectIdlist.add(project.getId());
		}
		return projectIdlist;
	}

	private void populateProjectsProducts(List<Project> projectList,
			Map<Long, List<Product>> projectsProducts) {
		// TODO Auto-generated method stub
		for (Project project : projectList) {
			project.setProductList(projectsProducts.get(project.getId()));
		}
	}

	private boolean checkProjectExists(String projectName) {
		boolean exists = projectDAO.checkProjectExists(projectName);
		return exists;
	}

	@Override
	public Project getProjectByProject(Long projectId) throws Exception {
		Project project = null;
		try {
			project = projectDAO.getProjectByProject(projectId);
		} catch (Exception e) {
			throw new Exception(
					"Error in Project Service while getting project.", e);
		}
		return project;
	}
}
