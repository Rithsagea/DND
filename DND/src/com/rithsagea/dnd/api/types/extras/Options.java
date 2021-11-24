package com.rithsagea.dnd.api.types.extras;

import java.util.List;

public class Options implements OptionType {
	public int count;
	public List<OptionType> option;
	
	public String typeName() { return "option"; }
	public String toString() { return count + ": " + option; }
}
