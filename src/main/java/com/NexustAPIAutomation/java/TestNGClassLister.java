package com.NexustAPIAutomation.java;


	import java.io.File;
	import java.util.*;

	public class TestNGClassLister {

	    public static void main(String[] args) {
	        File root = new File("src/test/java");
	        List<String> classNames = new ArrayList<>();
	        listClasses(root, "", classNames);

	        for (String className : classNames) {
	            System.out.println("    <class name=\"" + className + "\" />");
	        }
	    }

	    private static void listClasses(File dir, String pkg, List<String> classNames) {
	        for (File file : dir.listFiles()) {
	            if (file.isDirectory()) {
	                listClasses(file, pkg + file.getName() + ".", classNames);
	            } else if (file.getName().endsWith(".java")) {
	                String className = pkg + file.getName().replace(".java", "");
	                classNames.add(className);
	            }
	        }
	    }
	}



