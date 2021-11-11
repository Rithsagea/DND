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
import com.rithsagea.dnd.api.misc.Alignment;
import com.rithsagea.dnd.api.misc.Language;
import com.rithsagea.dnd.api.misc.Proficiency;
import com.rithsagea.dnd.api.misc.Skill;

public class Dnd5eApiTool {
	
	private String url;
	private Gson gson;
	
	public Dnd5eApiTool(String url) {
		
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		builder.registerTypeAdapter(AbilityScore.class, new AbilityScoreAdapter());
		builder.registerTypeAdapter(Skill.class, new SkillAdapter());
		builder.registerTypeAdapter(Proficiency.class, new ProficiencyAdapter());
		builder.registerTypeAdapter(Language.class, new LanguageAdapter());
		builder.registerTypeAdapter(Alignment.class, new AlignmentAdapter());
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
	
	public <T> List<T> getItems(String header, Class<T> clazz) {
		List<String> index = getIndex(header);
		List<T> res = new ArrayList<>();
		
		for(String item : index) {
			res.add(gson.fromJson(get(header + "/" + item), clazz));
		}
		
		return res;
	}
	
	public List<AbilityScore> getAbilityScores() {
		return getItems("/ability-scores", AbilityScore.class);
	}
	
	public List<Skill> getSkills() {
		return getItems("/skills", Skill.class);
	}
	
	public List<Proficiency> getProficiencies() {
		return getItems("/proficiencies", Proficiency.class);
	}
	
	public List<Language> getLanguages() {
		return getItems("/languages", Language.class);
	}
	
	public List<Alignment> getAlignments() {
		return getItems("/alignments", Alignment.class);
	}
}
