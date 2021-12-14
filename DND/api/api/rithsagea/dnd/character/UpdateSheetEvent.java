package api.rithsagea.dnd.character;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import api.rithsagea.dnd.event.Event;
import api.rithsagea.dnd.types.traits.Trait;

public class UpdateSheetEvent implements Event {
	
	private CharacterSheet sheet;
	
	public UpdateSheetEvent(CharacterSheet sheet) {
		this.sheet = sheet;
	}
	
	public CharacterSheet getSheet() {
		return sheet;
	}
	
	public static class LoadTraitsEvent extends UpdateSheetEvent {

		private Set<Trait> traits;
		
		public LoadTraitsEvent(CharacterSheet sheet) {
			super(sheet);
			traits = new TreeSet<>();
		}
		
		public void addTrait(Trait trait) {
			traits.add(trait);
		}
		
		public void addTraits(Trait... traits) {
			this.traits.addAll(Arrays.asList(traits));
		}
		
		public Set<Trait> getTraits() {
			return Collections.unmodifiableSet(traits);
		}
		
	}
}
