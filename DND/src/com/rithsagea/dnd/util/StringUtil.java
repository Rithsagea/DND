package com.rithsagea.dnd.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StringUtil {
	public static String convertDesc(String source) {
		StringBuilder builder = new StringBuilder();
		for(String line : source.replaceAll("\r", "").split("\n")) {
			builder.append(line);
			
			if(line.isEmpty()) {
				builder.setLength(builder.length() - 1);
				builder.append('\n');
			} else {
				builder.append(' ');
			}
		}
		
		builder.setLength(builder.length() - 1);
		
		return builder.toString();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
		
		StringBuilder builder = new StringBuilder();
		String line;
		while((line = reader.readLine()) != null) {
			builder.append(line);
			if(line.isEmpty()) {
				builder.setLength(builder.length() - 1);
				builder.append('\n');
			} else {
				builder.append(' ');
			}
		}
		builder.setLength(builder.length() - 1);
		
		System.out.print(builder.toString());
		
		reader.close();
	}
}
