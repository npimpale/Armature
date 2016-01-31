package com.persistent.securityPractice.armature.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.stereotype.Service;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.persistent.securityPractice.armature.dto.Product;
import com.persistent.securityPractice.armature.dto.REPOSITORIES;

@Service
public class XMLParserServiceImpl implements XMLParserService {
    
	private static String templateParentpath = "D:\\projectworkspace\\SecurityScanner\\src\\templates\\";
	
	private static String tempPath = templateParentpath + "temp\\";
	private static final String GIT_TEMPLATE_NAME = "gitTemplate.xml";

	private void gitXmlReplacer(String templateFilePath, Product product)
			throws IOException {
		File file = new File(templateFilePath);
		try {
			if (!file.exists()) {
				throw new FileNotFoundException(templateFilePath
						+ " file does not exists");
			}

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(templateFilePath);

			// get url element by id
			Node url = doc.getElementsByTagName("url").item(0);
			System.out.println("url textContent:- " + url.getTextContent());
			// "git@github.com:watir/watir-webdriver.git"
			url.setTextContent(product.getUrl());

			// For time Trigger
			// Keeping this commented as it is needed later.
			/*
			 * Node timeTrigger = doc.getElementsByTagName(
			 * "hudson.triggers.TimerTrigger").item(0); NodeList timeTriggerlist
			 * = timeTrigger.getChildNodes(); for (int i = 0; i <
			 * timeTriggerlist.getLength(); i++) { Node node =
			 * timeTriggerlist.item(i); if ("spec".equals(node.getNodeName())) {
			 * node.setTextContent(""); //node.setTextContent("H * * * 5"); } }
			 * 
			 * // for SCM Triggers Node scmTrigger = doc.getElementsByTagName(
			 * "hudson.triggers.SCMTrigger").item(0); NodeList scmTriggerList =
			 * scmTrigger.getChildNodes();
			 * 
			 * for (int i = 0; i < scmTriggerList.getLength(); i++) { Node node1
			 * = scmTriggerList.item(i); if ("spec".equals(node1.getNodeName()))
			 * { node1.setTextContent("H/30 * * * *"); }
			 * 
			 * }
			 */

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(templateFilePath));
			transformer.transform(source, result);

			System.out.println("Done");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		} catch (FileNotFoundException fnf) {
			throw new IOException(fnf);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	public String createJenkinsConfigFile(REPOSITORIES repo, String userName,
			Product product) throws Exception {
		String outputfilePath = "d:\\SecurityScanner\\"+ userName + "/config.xml";
		String templateFilePath = null;
		switch (repo) {
		case GIT:
			templateFilePath = templateParentpath + GIT_TEMPLATE_NAME;
			createTempraryFile(templateFilePath, outputfilePath);
			gitXmlReplacer(outputfilePath, product);

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
		return outputfilePath;
	}

	private void createTempraryFile(String templateFilePath,
			String outputfilePath) {

		File inputFile = new File(templateFilePath);
		File outputFile = new File(outputfilePath);
		String checkPath = "";
		if (!outputFile.exists()) {
			checkPath = StringUtils.substringBeforeLast(outputfilePath, "/");
			System.out.println("outputpath: " + checkPath);

			if (!new File(checkPath).isDirectory()) {
				try {
					new File(checkPath).mkdirs();
					outputFile.createNewFile();
				} catch (IOException e) {
					// TODO Add proper exception
					e.printStackTrace();
				}

			} else {
				try {
					outputFile.createNewFile();
				} catch (IOException e) {
					// TODO Add proper exception
					e.printStackTrace();
				}
			}

			try {
				writeToTargetPath(new FileInputStream(inputFile),
						new FileOutputStream(outputFile));
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}

		}

	}

	private void writeToTargetPath(FileInputStream fileInputStream,
			FileOutputStream fileOutputStream) {
		try {
			final byte[] buffer = new byte[1024];
			int n;
			while ((n = fileInputStream.read(buffer)) != -1) {
				fileOutputStream.write(buffer, 0, n);
			}
		} catch (IOException e) {

			e.printStackTrace();
		} finally {

			try {
				fileOutputStream.close();
				fileInputStream.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}
}
