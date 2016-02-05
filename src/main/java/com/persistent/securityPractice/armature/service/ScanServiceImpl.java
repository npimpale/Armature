package com.persistent.securityPractice.armature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistent.securityPractice.armature.dto.Assessment;
import com.persistent.securityPractice.armature.dto.Product;
import com.persistent.securityPractice.armature.dto.REPOSITORIES;

@Service
public class ScanServiceImpl implements ScanService {
	private static String SOURCE_PATH = "\\\\PTD10833\\Jenkins_Workspace\\";
	private static String DEST_PATH = "D:\\SecurityScanner\\";

	private UserService userService;
	private ProjectService projectService;
	private ProductService productService;
	private AssessmentService assessmentService;
	private JenkinsJobService jenkinsJobService;
	private XMLParserService xmlParserService;
	private ZipService zipService;
	private QueueService queueService;

	@Autowired(required = true)
	public void setQueueService(QueueServiceImpl queueServiceImpl) {
		this.queueService = queueServiceImpl;
	}

	@Autowired(required = true)
	public void setZipService(ZipServiceImpl zipServiceImpl) {
		this.zipService = zipServiceImpl;
	}

	@Autowired(required = true)
	public void setXmlParserService(XMLParserService xmlParserService) {
		this.xmlParserService = xmlParserService;
	}

	@Autowired(required = true)
	public void setJenkinsJobService(JenkinsJobServiceImpl jenkinsJobServiceImpl) {
		this.jenkinsJobService = jenkinsJobServiceImpl;
	}

	@Autowired(required = true)
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
	}

	@Autowired(required = true)
	public void setAssessmentService(AssessmentServiceImpl assessmentServiceImpl) {
		this.assessmentService = assessmentServiceImpl;
	}

	public Boolean scan(Assessment assessment) throws Exception {
		REPOSITORIES repo = null;
		int responseCode = 0;
		boolean isSuccess = false;
		try {
			Product product = productService.getProductById(assessment
					.getProductId());
			// Create config file for repoas per the product info
			repo = product.getRepository();
			switch (repo) {
			case GIT:
				// Create config file for jenkins
				String configFilePath = xmlParserService
						.createJenkinsConfigFile(REPOSITORIES.GIT,
								assessment.getUserName(), product);
				String jobName = assessment.getProjectName() + "_"
						+ product.getName() + "_" + assessment.getUserName();
				String sourceCodePath = SOURCE_PATH + jobName;
				String zipFilePath = null;
				// Create job in Jenkins
				responseCode = jenkinsJobService.createJob(configFilePath,
						jobName);
				if (responseCode == 200) {
					// Checkout source code at shared location
					isSuccess = jenkinsJobService.pullCode(jobName);
					if (!isSuccess) {
						throw new Exception(
								"Error while checking out source code!");
					}
					// Create Zip file for specific tools for scanning
					// TODO later check which tool are scanning and accordingly
					// create or skip zipping
					zipFilePath = zipService.createSourceCodeZip(sourceCodePath,
							DEST_PATH, jobName + ".zip");
					System.out.println("Zip created successfully: " + zipFilePath);
					
					isSuccess = queueService.addAssessmentToQueue(zipFilePath, assessment);
				}
				

				break;
			case CVS:
				break;
			case SVN:
				break;
			case TFS:
				break;
			default:
				break;
			}

			return isSuccess;

		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}

}
