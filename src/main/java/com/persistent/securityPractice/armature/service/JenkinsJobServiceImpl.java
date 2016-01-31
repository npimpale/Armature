package com.persistent.securityPractice.armature.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@Service
public class JenkinsJobServiceImpl implements JenkinsJobService {
	// @Value("${jenkins.url}")
	// TODO make this configurable. Implement Message Source.
	private static String URL = "http://ptd10833:8081//";

	/**
	 * Method to build job
	 * 
	 * @param jobname
	 * @throws Exception
	 */
	public Boolean pullCode(String jobName) throws Exception {
		System.out.println("In invoking the JOb");
		HttpURLConnection conn = null;
		boolean isSuccess = false;
		try {
			int count = 0;
			URL url = new URL(URL + "job/" + jobName + "/build");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			System.out.println(conn.getResponseCode());
			if (conn.getResponseCode() == 201) {
				while (!isSuccess) {
					// This is for the create job to get in to the queue. other
					// wise it throws error.
					Thread.sleep(10 * 1000);

					if (getJobStatus(jobName) != null && getJobStatus(jobName).equals("SUCCESS")) {
						isSuccess = true;
						break;
					}

					if (count == 4 && getJobStatus(jobName).equals("FAILURE")) {
						isSuccess = false;
						throw new Exception(
								"Error while pulling source code in Jenkins Service");
					}
					count += 1;
					// Thread.sleep(5 * 1000);
				}

			} else {
				throw new Exception("Unable to submit Job");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}
		return isSuccess;
	}

	/**
	 * Method to get the status of the job
	 * 
	 * @param jobname
	 */
	private String getJobStatus(String jobName) {

		String line = null;
		String result = null;
		HttpURLConnection connection = null;
		try {
			URL url = new URL(URL + "job/" + jobName
					+ "/lastBuild/api/xml?tree=result");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.connect();
			InputStreamReader inputStreamReader = new InputStreamReader(
					(InputStream) connection.getContent());
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			System.out.println(connection.getResponseCode());
			do {
				line = bufferedReader.readLine();
				System.out.println(line + " \n");
			} while (line == null);
			// <freeStyleBuild><result>SUCCESS</result></freeStyleBuild>
			// fetching output
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder db = null;
			db = factory.newDocumentBuilder();
			InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(line));

			org.w3c.dom.Document document = db.parse(inputSource);
			result = document.getDocumentElement().getTextContent();
			System.out.println("Result is: " + result);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}
		return result;
	}

	/**
	 * Post method to create JOB
	 * 
	 * @param input
	 * @param jobname
	 * @return
	 */
	public Integer createJob(String configFilePath, String jobName)
			throws TransformerException, IOException, Exception {

		int responsecode = 0;
		try {
			// Setting up the URL related info for Connection
			URL url = new URL(URL + "createItem?name=" + jobName);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/xml");
			OutputStream os = conn.getOutputStream();
			TransformerFactory factory = TransformerFactory.newInstance();

			Transformer transformer = factory.newTransformer();
			FileReader fileReader = new FileReader(configFilePath);
			StreamSource source = new StreamSource(fileReader);
			StreamResult result = new StreamResult(os);
			transformer.transform(source, result);
			os.flush();
			System.out.println("responseCode:-" + conn.getResponseCode()
					+ " Message: " + conn.getResponseMessage());
			responsecode = conn.getResponseCode();

			conn.disconnect();

			System.out.println("Output from Server .... \n");

		} catch (TransformerException e) {
			throw new TransformerException(
					"Error while transforming config file in JenkinsJobService",
					e);
		} catch (MalformedURLException e) {
			throw new Exception("Malformed create job url!", e);
		} catch (IOException e) {
			throw new IOException(
					"Config file not found in jenkins create job", e);
		}
		return responsecode;

	}

}
