package com.rithsagea.dnd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DescriptionTool {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
		
		StringBuilder builder = new StringBuilder();
		boolean flag = false;
		String line;
		while((line = reader.readLine()) != null) {
			builder.append(line);
			if(line.isBlank()) {
				builder.setLength(builder.length() - 1);
				builder.append('\n');
			} else {
				builder.append(' ');
			}
		}
		builder.setLength(builder.length() - 1);
		
		System.out.println(builder.toString());
		
		reader.close();
	}
}
