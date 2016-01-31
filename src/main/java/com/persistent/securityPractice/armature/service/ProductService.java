package com.persistent.securityPractice.armature.service;

import java.util.List;
import java.util.Map;

import com.persistent.securityPractice.armature.dto.Product;

public interface ProductService {
	public Map<Long, List<Product>> getProjectsProducts(List<Long> projectIds);

	public List<Product> getProjectProducts(long projectId);

	public long addProduct(Product product) throws Exception;

	public Product getProductByProject(String projectName);

	public Product getProductByProject(long id);

	public Product getProductById(Long productId) throws Exception;

}
