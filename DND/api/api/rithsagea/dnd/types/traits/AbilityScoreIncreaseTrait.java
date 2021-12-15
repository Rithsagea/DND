package api.rithsagea.dnd.types.traits;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import api.rithsagea.dnd.character.UpdateAbilityEvent.UpdateAbilityScoreEvent;
import api.rithsagea.dnd.event.EventHandler;
import api.rithsagea.dnd.types.DndRace;
import api.rithsagea.dnd.types.enums.Ability;

public class AbilityScoreIncreaseTrait extends Trait {

	private DndRace race;
	private Map<Ability, Integer> scores;
	
	public AbilityScoreIncreaseTrait(DndRace race, Map<Ability, Integer> scores) {
		this.race = race;
		this.scores = new TreeMap<>(scores);
	}
	
	public DndRace getRace() {
		return race;
	}
	
	public Map<Ability, Integer> getScores() {
		return Collections.unmodifiableMap(scores);
	}
	
	@EventHandler
	public void onUpdateAbilityScore(UpdateAbilityScoreEvent e) {
		if(scores.containsKey(e.getAbility()))
			e.setValue(e.getValue() + scores.get(e.getAbility()));
	}
	
	@Override
	public String getId() {
		return race.getId() + ".AbilityScoreIncrease";
	}
	
	@Override
	public String getName() {
		return "Ability Score Increase";
	}
}
