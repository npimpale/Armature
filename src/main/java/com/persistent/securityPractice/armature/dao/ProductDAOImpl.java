package com.persistent.securityPractice.armature.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.persistent.securityPractice.armature.dao.mapper.ProductMapper;
import com.persistent.securityPractice.armature.dto.Product;
import com.persistent.securityPractice.armature.queries.ProductQueries;

@Component
public class ProductDAOImpl implements ProductDAO {
	private NamedParameterJdbcTemplate namedParaJdbcTemplate;
	private SimpleJdbcInsert productInsert;

	@Autowired(required = true)
	public void setNamedParaJdbcTemplate(DataSource dataSource) {
		this.namedParaJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public void setProductInsert(DataSource dataSource) {
		this.productInsert = new SimpleJdbcInsert(dataSource)
				.withTableName("central_tracker.product_info").usingGeneratedKeyColumns("id");
	}

	public Product getProductByProject(long id) {
		return namedParaJdbcTemplate.queryForObject(
				ProductQueries.GET_PRODUCT_BY_PROJECT_ID,
				new MapSqlParameterSource("project_id", id),
				new ProductMapper());
	}

	public Product getProductByProject(String projectName) {
		return namedParaJdbcTemplate.queryForObject(
				ProductQueries.GET_PRODUCT_BY_PROJECT_NAME,
				new MapSqlParameterSource("project_name", projectName),
				new ProductMapper());
	}

	public Number addProduct(Product product) {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(product);
		return productInsert.executeAndReturnKey(parameterSource);
	}

	public List<Product> getProjectProducts(long projectId) {
		List<Product> productList = new ArrayList<Product>();
		namedParaJdbcTemplate.query(ProductQueries.GET_PROJECT_PRODUCTS,
				new MapSqlParameterSource("project_id", projectId),
				new ProductMapper());
		return productList;
	}

	public Map<Long, List<Product>> getProjectsProducts(List<Long> projectIds) {
		Map<Long, List<Product>> projectsProductsMap = new HashMap<Long, List<Product>>();
		List<Product> productList = new ArrayList<Product>();

		productList.addAll(namedParaJdbcTemplate.query(
				ProductQueries.GET_ALL_PROJECTS_PRODUCTS,
				new MapSqlParameterSource("project_ids", projectIds),
				new ProductMapper()));
		// TODO logic for creating map
		Long id = null;
		for (Product product : productList) {
			id = product.getProjectId();
			if (projectIds.contains(id)) {
				List<Product> newProductList = null;
				if (!projectsProductsMap.containsKey(id)) {
					newProductList = new ArrayList<Product>();
					newProductList.add(product);
					projectsProductsMap.put(id, newProductList);
				} else {
					newProductList = projectsProductsMap.get(id);
					newProductList.add(product);
					projectsProductsMap.put(id, newProductList);
				}
			}
		}
		return projectsProductsMap;
	}

	public boolean checkProductExistsWithName(String productName) {
		boolean isExists = namedParaJdbcTemplate.queryForObject(
				ProductQueries.CHECK_PRODUCT_EXISTS_NAME,
				new MapSqlParameterSource("product_name", productName),
				Boolean.class);
		return isExists;
	}

	public boolean checkProductExistsWithVersion(String productName,
			String version) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("product_name", productName);
		parameters.put("version", version);
		boolean isExists = namedParaJdbcTemplate.queryForObject(
				ProductQueries.CHECK_PRODUCT_EXISTS_VERSION,
				new MapSqlParameterSource("product_name", version),
				Boolean.class);
		return isExists;
	}

	public Product getProductById(Long productId) throws Exception {
		Product product = null;
		try {
			product = namedParaJdbcTemplate.queryForObject(
					ProductQueries.GET_PRODUCT_BY_ID,
					new MapSqlParameterSource("product_id", productId),
					new ProductMapper());
		} catch (Exception e) {
			throw new Exception("Error in Product Dao while getting product", e);
		}
		return product;
	}

}
