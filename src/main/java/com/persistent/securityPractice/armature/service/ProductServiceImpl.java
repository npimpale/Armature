package com.persistent.securityPractice.armature.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistent.securityPractice.armature.dao.ProductDAO;
import com.persistent.securityPractice.armature.dao.ProductDAOImpl;
import com.persistent.securityPractice.armature.dto.Product;

@Service
public class ProductServiceImpl implements ProductService {
	private ProductDAO productDao;
	private ContactService contactService;

	@Autowired(required = true)
	public void setContactService(ContactServiceImpl contactServiceImpl) {
		this.contactService = contactServiceImpl;
	}

	@Autowired(required = true)
	public void setProductDao(ProductDAOImpl productDaoImpl) {
		this.productDao = productDaoImpl;
	}

	public Product getProductByProject(long id) {
		Product product = productDao.getProductByProject(id);
		return product;
	}

	public Product getProductByProject(String projectName) {
		Product product = productDao.getProductByProject(projectName);
		return product;
	}

	public long addProduct(Product product) throws Exception {
		// TODO check if the product already exists or not with version as
		// composite check
		// 2 cases if id is not present in product i need to check if the
		// product with same name should not exist.
		// second case if product id exists then check that there are no 2 same
		// versions.
		long newProductId = 0;
		try {
			if (product.getId() == 0 && !product.getName().isEmpty()) {
				if (!checkProductExistsWithName(product.getName())) {
					newProductId = productDao.addProduct(product).longValue();
				} else {
					throw new Exception("Error: Product already exists!");
				}
			} else if (product.getId() != 0 && !product.getVersion().isEmpty()) {
				if (!checkProductExistsWithVersion(product.getName(),
						product.getVersion())) {
					newProductId = productDao.addProduct(product).longValue();
				} else {
					throw new Exception(
							"Error: Product version already exists!");
				}
			}
			return newProductId;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public List<Product> getProjectProducts(long projectId) {
		// TODO Auto-generated method stub
		List<Product> productList = new ArrayList<Product>();
		productList.addAll(productDao.getProjectProducts(projectId));
		return productList;
	}

	public Map<Long, List<Product>> getProjectsProducts(List<Long> projectIds) {
		// TODO Auto-generated method stub
		return productDao.getProjectsProducts(projectIds);
	}

	private boolean checkProductExistsWithName(String productName) {
		return productDao.checkProductExistsWithName(productName);
	}

	private boolean checkProductExistsWithVersion(String productName,
			String version) {
		// TODO Auto-generated method stub
		return productDao.checkProductExistsWithVersion(productName, version);
	}

	public Product getProductById(Long productId) throws Exception {
		Product product = null;
		try {
			product = productDao.getProductById(productId);
		} catch (Exception e) {
			throw new Exception(
					"Error in Product Service while getting product", e);
		}

		return product;
	}
}
