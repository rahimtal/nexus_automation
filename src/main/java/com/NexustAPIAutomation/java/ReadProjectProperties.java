package com.NexustAPIAutomation.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;

/*
 * This class will handle reading data from project properties file
 * @author: Talha Rahim
 * @since:04/08/2019
 */
public class ReadProjectProperties {

	public ReadProjectProperties() {
		System.out.println("Reading Project Properties");

	}

	public static String ReadFile(String str) {
		String Result = null;
		Properties prop = new Properties();
		File filePath = new File("./Configuration\\Project.properties");
		FileInputStream objfile;
		try {
			objfile = new FileInputStream(filePath);
			prop.load(objfile);
			Result = prop.getProperty(str);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail("Project.properties file not found in Configuration folder.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail("Error reading Project.properties file.");
		}

		return Result;
	}

}
