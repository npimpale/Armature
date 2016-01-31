package com.persistent.securityPractice.armature.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.persistent.securityPractice.armature.dto.Assessment;
import com.persistent.securityPractice.armature.dto.Hello;
import com.persistent.securityPractice.armature.dto.STATUS;
import com.persistent.securityPractice.armature.service.AssessmentService;
import com.persistent.securityPractice.armature.service.AssessmentServiceImpl;
import com.persistent.securityPractice.armature.service.ScanService;
import com.persistent.securityPractice.armature.service.ScanServiceImpl;

@Controller
@RequestMapping(value = "/scanner")
public class ScanController {
	private ScanService scanService;
	private AssessmentService assessmentService;

	@Autowired(required = true)
	public void setAssessmentService(AssessmentServiceImpl assessmentServiceImpl) {
		this.assessmentService = assessmentServiceImpl;
	}

	@Autowired(required = true)
	public void setScanService(ScanServiceImpl scanServiceImpl) {
		this.scanService = scanServiceImpl;
	}

	@RequestMapping(value = "/startScan", method = RequestMethod.POST, headers = "Accept=*/*", produces = "application/json")
	public ModelAndView startScan(@RequestBody Assessment assessment,
			HttpServletRequest request, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		long id = 0;
		boolean isStartSuccess = false;
		try {
			isStartSuccess = scanService.scan(assessment);
			if (isStartSuccess) {
				// update the Assessment record.
				Assessment updateAssessment = new Assessment();
				updateAssessment.setId(assessment.getId());
				updateAssessment.setStatus(STATUS.IN_PROGRESS);
				updateAssessment.setUpdateDate(new Date());
				id = assessmentService.updateAssessment(updateAssessment);
				if (id == 0) {
					throw new Exception(
							"Could not update Assessment after startscan");
				}
				mav.addObject("success", true);
				mav.addObject("assessment", assessment);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mav;
	}

	@RequestMapping(value = "/helloworld", method = RequestMethod.POST, headers = "Accept=*/*", produces = "application/json")
	public Hello helloWorld(@RequestBody Hello hello,
			HttpServletRequest request, HttpSession session) {

		try {
			System.out.println(hello.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hello;
	}

}
