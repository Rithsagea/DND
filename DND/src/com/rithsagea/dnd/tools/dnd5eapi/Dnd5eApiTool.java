package com.rithsagea.dnd.tools.dnd5eapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rithsagea.dnd.api.misc.AbilityScore;
import com.rithsagea.dnd.api.misc.Skill;

public class Dnd5eApiTool {
	
	private String url;
	private Gson gson;
	
	public static final String ABILITY_SCORE_HEADER = "/ability-scores";
	public static final String SKILL_HEADER = "/skills";
	
	public Dnd5eApiTool(String url) {
		
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		builder.registerTypeAdapter(AbilityScore.class, new AbilityScoreAdapter());
		builder.registerTypeAdapter(Skill.class, new SkillAdapter());
		gson = builder.create();
		
		this.url = url;
	}
	
	public String get(String header) {
		URL url;
		HttpURLConnection conn;
		StringBuilder builder = new StringBuilder();
		InputStream is = null;
		
		try {
			url = new URL(this.url + header);
			conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			
			is = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			for(String line; (line = reader.readLine()) != null;) {
				builder.append(line);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(is != null) is.close();
			} catch(IOException e) {
				
			}
		}
		
		return builder.toString();
	}
	
	public List<String> getIndex(String header) {
		JsonObject obj = gson.fromJson(get(header), JsonObject.class);
		
		List<String> index = new ArrayList<String>();
		for(JsonElement elem : obj.get("results").getAsJsonArray()) {
			index.add(elem.getAsJsonObject().get("index").getAsString());
		}
		
		return index;
	}
	
	public List<AbilityScore> getAbilityScores() {
		List<String> index = getIndex(ABILITY_SCORE_HEADER);
		List<AbilityScore> res = new ArrayList<>();
		for(String item : index) {
			res.add(gson.fromJson(get(ABILITY_SCORE_HEADER + "/" + item), AbilityScore.class));
		}
		
		return res;
	}
	
	public List<Skill> getSkills() {
		List<String> index = getIndex(SKILL_HEADER);
		List<Skill> res = new ArrayList<>();
		for(String item : index) {
			res.add(gson.fromJson(get(SKILL_HEADER + "/" + item), Skill.class));
		}
		
		return res;
	}
}
