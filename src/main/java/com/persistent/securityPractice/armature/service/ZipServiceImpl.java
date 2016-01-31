package com.persistent.securityPractice.armature.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Service;

@Service
public class ZipServiceImpl implements ZipService {
	private List<String> fileList;
	private static String SOURCE;
	private static String DESTINATION;

	@Override
	public String createSourceCodeZip(String source, String destination,
			String zipFileName) {
		// TODO Auto-generated method stub
		SOURCE = source;
		DESTINATION = destination;
		byte[] buffer = new byte[1024];
		File node = new File(SOURCE);
		String zipFilePath = DESTINATION + zipFileName;
		fileList = new ArrayList<String>();
		generateFileList(node);

		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(zipFilePath);
			zos = new ZipOutputStream(fos);
			System.out.println("Output to Zip : " + zipFilePath);

			for (String file : fileList) {
				System.out.println("File Added : " + file);
				ZipEntry ze = new ZipEntry(file);
				zos.putNextEntry(ze);
				FileInputStream in = new FileInputStream(SOURCE
						+ File.separator + file);
				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				in.close();
			}
			zos.closeEntry();
			// remember close it
			System.out.println("Done");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				zos.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

		return zipFilePath;
	}

	/**
	 * Traverse a directory and get all files, and add the file into fileList
	 * 
	 * @param node
	 *            file or directory
	 */
	private void generateFileList(File node) {

		// add file only
		if (node.isFile()) {
			fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
		}

		if (node.isDirectory()) {
			String[] subNote = node.list();
			for (String filename : subNote) {
				generateFileList(new File(node, filename));
			}
		}

	}

	/**
	 * Format the file path for zip
	 * 
	 * @param file
	 *            file path
	 * @return Formatted file path
	 */
	private String generateZipEntry(String file) {
		return file.substring(SOURCE.length(), file.length());
	}

}
