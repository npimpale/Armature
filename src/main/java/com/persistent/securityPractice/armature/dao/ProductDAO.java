package com.persistent.securityPractice.armature.dao;

import java.util.List;
import java.util.Map;

import com.persistent.securityPractice.armature.dto.Product;

public interface ProductDAO {

	Product getProductByProject(long id);

	Product getProductByProject(String projectName);

	Number addProduct(Product product);

	List<Product> getProjectProducts(long projectId);

	Map<Long,List<Product>> getProjectsProducts(List<Long> projectIds);

	boolean checkProductExistsWithName(String productName);

	boolean checkProductExistsWithVersion(String productName, String version);

	Product getProductById(Long productId) throws Exception;

}
