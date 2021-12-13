package com.zmk.cache1.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtilSOS {
	public static String FOLDER_STRING = "C:\\CBSLD";// "C:\\Users\\kietdm\\Desktop\\temp\\TEST\\";
	public static String pathFileCacheTest = FOLDER_STRING + "\\sos.txt";

	public static void readSOS() {
		try {
			readStringFromFile();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static String readStringFromFile() {
		String returString = "";
		String pathFile = pathFileCacheTest;// getPath(app);
		List<String> listStrings = new ArrayList<String>();

		try {
			File file = new File(pathFile); // creates a new file instance
			FileReader fr = new FileReader(file); // reads the file
			BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream
			StringBuffer sb = new StringBuffer(); // constructs a string buffer with no characters
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line); // appends line to string buffer
				//sb.append("\n"); // line feed
				returString+= line;
				listStrings.add(line);
			}
			fr.close(); // closes the stream and release the resources
			System.out.println("Contents of File: ");
			System.out.println(sb.toString()); // returns a string that textually represents the object
		} catch (IOException e) {
			e.printStackTrace();
		}
	
//		 try {
//		      File myObj = new File(pathFile);
//		      Scanner myReader = new Scanner(myObj);
//		      while (myReader.hasNextLine()) {
//		    	  returString+= myReader.nextLine();
//		    	  listStrings.add(myReader.nextLine());
//		    	  
//		      }
//		      myReader.close();
//		    } catch (FileNotFoundException e) {
//		    }
	System.out.println("\n STRING GOTTEN: "+returString);

	String typeCheck = null;if(listStrings!=null&&listStrings.size()>0)
	{
		int size = listStrings.size();
		for (int i = 0; i < size; i++) {
			System.out.println("index " + i + " -> " + listStrings.get(i));
		}
		typeCheck = listStrings.get(size - 1);
		GlobalVariable.UX = listStrings.get(0);
		GlobalVariable.PX = listStrings.get(1);
	}

	deleteFile(typeCheck);
		 return returString;
	}

	private static void deleteFile(String typecheck) {
		if(typecheck != null) {
			String pathFile = pathFileCacheTest;// getPath(app);
			 try {
			      File myObj = new File(pathFile);
			      if(typecheck.trim().equalsIgnoreCase("LIVE")) {
				      myObj.delete();
			      }
			 }catch (Exception e) {
			}
		}
	}

	public static String getPathX(String app) {
		String pathFile = "";
		 if(app.trim().equalsIgnoreCase(GlobalVariable.APP)) {
			 pathFile = pathFileCacheTest;
		 }
		 return pathFile;
	}
}
