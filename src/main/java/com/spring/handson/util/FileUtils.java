package com.spring.handson.util;


import java.io.*;
import java.net.URL;

public class FileUtils {


	public static File getFile(final String fileName) {
		final URL resource = FileUtils.class.getClassLoader().getResource(fileName);
		if (resource == null) {
			throw new RuntimeException("File \"" + fileName + "\" not found");
		}
		return new File(resource.getFile());
	}

	public static String readFile(final String fileName) {
		final StringBuilder builder = new StringBuilder();
		try (BufferedReader br = new BufferedReader((new FileReader(getFile(fileName))))) {
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				builder.append(currentLine);
			}
			return builder.toString();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
