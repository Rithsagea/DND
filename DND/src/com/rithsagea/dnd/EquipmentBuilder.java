package com.rithsagea.dnd;

import java.io.File;

import com.rithsagea.dnd.api.SourceBook;
import com.rithsagea.dnd.api.SourceRegistry;
import com.rithsagea.dnd.api.types.Equipment;
import com.rithsagea.dnd.api5e.Datapack;
import com.rithsagea.dnd.api5e.data.equipment.Dnd5eEquipment;
import com.rithsagea.dnd.api5e.data.extra.CoinQuantity;

public class EquipmentBuilder {
	private static final File SOURCE_DIRECTORY = new File("source/");
	
	private static Datapack data5e;
	
	private static String toString(CoinQuantity coin) {
		return String.format("%d%s", coin.quantity, coin.unit);
	}
	
	public static void main(String[] args) {
		SourceRegistry.init(SOURCE_DIRECTORY);
		SourceRegistry.load();
		
		data5e = Datapack.loadDatapack(new File("5e.json"));
		SourceBook book = SourceRegistry.getBooks().get("5e");
		
		for(String key : data5e.Equipment.keySet()) {
			Dnd5eEquipment model = data5e.Equipment.get(key);
			Equipment equip = new Equipment();
			
			equip.id = model.id;
			equip.name = model.name;
			equip.description = model.description;
			equip.category = model.category;
			equip.weight = model.weight;
			equip.category = model.cost == null ? null : toString(model.cost);
			
			book.register(equip);
			
			book.register(key, equip);
		}
		
		SourceRegistry.saveBooks();
	}
}
