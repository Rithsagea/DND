package api.rithsagea.dnd.types.traits;

import api.rithsagea.dnd.character.events.UpdateFieldEvent.UpdateSpeedEvent;
import api.rithsagea.dnd.event.EventHandler;
import api.rithsagea.dnd.types.DndRace;

public class SpeedTrait extends UniqueTrait {

	private int speed;
	
	public SpeedTrait(DndRace race, int speed) {
		super(race);
		this.speed = speed;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	@Override
	public String getSubId() {
		return "Speed";
	}

	@EventHandler
	public void onUpdateSpeed(UpdateSpeedEvent e) {
		e.setValue(getSpeed());
	}
}
