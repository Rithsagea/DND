package test.rithsagea.dnd;

import com.rithsagea.dnd.race.elf.ElfRace;
import com.rithsagea.dnd.race.human.HumanRace;

import api.rithsagea.dnd.types.Registry;
import api.rithsagea.dnd.types.traits.Trait;

public class RaceTest {
	public static void registerRaces(Registry registry) {
		registry.registerRace(new ElfRace());
		registry.registerRace(new HumanRace());
	}
	
	public static void main(String[] args) {
		Registry registry = Registry.getInstance();
		TraitTest.registerTraits(registry);
		registerRaces(registry);
		
		for(Trait trait : registry.getRace("Human").getTraits()) {
			System.out.println(trait);
		}
	}
}
