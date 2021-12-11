package test.rithsagea.dnd;

import api.rithsagea.dnd.types.values.ModifierAdd;
import api.rithsagea.dnd.types.values.ModifierBase;
import api.rithsagea.dnd.types.values.ModifierMax;
import api.rithsagea.dnd.types.values.ModifierMin;
import api.rithsagea.dnd.types.values.ModifierMultiply;
import api.rithsagea.dnd.types.values.Value;

public class ValuesTest {
	public static void main(String[] args) {
		Value val = new Value();
		val.addModifier(new ModifierBase(10, 100), null);
		System.out.println(val);
		
		val.addModifier(new ModifierAdd(10, 99), null);
		System.out.println(val);
		
		val.addModifier(new ModifierAdd(-5, 98), null);
		System.out.println(val);
		
		val.addModifier(new ModifierMin(20, 97), null);
		System.out.println(val);
		
		val.addModifier(new ModifierMultiply(2, 96), null);
		System.out.println(val);
		
		val.addModifier(new ModifierMax(25, 95), null);
		System.out.println(val);
	}
}
