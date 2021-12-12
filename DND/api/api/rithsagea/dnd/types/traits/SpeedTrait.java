package api.rithsagea.dnd.types.traits;

import api.rithsagea.dnd.character.UpdateSpeedEvent;
import api.rithsagea.dnd.event.EventHandler;
import api.rithsagea.dnd.types.DndRace;

public class SpeedTrait extends Trait {

	private DndRace race;
	private int speed;
	
	public SpeedTrait(DndRace race, int speed) {
		this.race = race;
		this.speed = speed;
	}
	
	public DndRace getRace() {
		return race;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	@Override
	public String getId() {
		return race.getId() + ".Speed";
	}
	
	@Override
	public String getName() {
		return "Speed";
	}

	@EventHandler
	public void onUpdateSpeed(UpdateSpeedEvent e) {
		e.setValue(getSpeed());
	}
}
