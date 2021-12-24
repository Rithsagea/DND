package api.rithsagea.dnd.meta;

import java.util.Map;
import java.util.TreeMap;

import com.rithsagea.util.event.EventHandler;

import api.rithsagea.dnd.character.events.UpdateAbilityEvent.UpdateAbilityScoreEvent;
import api.rithsagea.dnd.types.enums.Ability;

public class StatMeta implements Meta {
	private Map<Ability, Integer> abilities;
	
	public StatMeta() {
		abilities = new TreeMap<>();
	}
	
	public void set(Ability ability, int value) {
		abilities.put(ability, value);
	}
	
	public int get(Ability ability) {
		return abilities.get(ability);
	}
	
	@EventHandler
	public void onUpdateAbilityScore(UpdateAbilityScoreEvent e) {
		Ability a = e.getAbility();
		if(abilities.containsKey(a)) {
			e.add(abilities.get(a));
		}
	}

	@Override
	public String getLabel() {
		return Meta.LABEL_STAT;
	}
}
