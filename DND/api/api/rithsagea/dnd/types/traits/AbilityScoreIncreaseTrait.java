package api.rithsagea.dnd.types.traits;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import com.rithsagea.util.event.EventHandler;

import api.rithsagea.dnd.character.events.values.UpdateAbilityScoreEvent;
import api.rithsagea.dnd.types.DndRace;
import api.rithsagea.dnd.types.enums.Ability;

public class AbilityScoreIncreaseTrait extends UniqueTrait {

	private Map<Ability, Integer> scores;
	
	public AbilityScoreIncreaseTrait(DndRace race, Map<Ability, Integer> scores) {
		super(race);
		this.scores = new TreeMap<>(scores);
	}
	
	public Map<Ability, Integer> getScores() {
		return Collections.unmodifiableMap(scores);
	}
	
	@EventHandler
	public void onUpdateAbilityScore(UpdateAbilityScoreEvent e) {
		if(scores.containsKey(e.getAbility()))
			e.add(scores.get(e.getAbility()));
	}

	@Override
	public String getSubId() {
		return "AbilityScoreIncrease";
	}
}
