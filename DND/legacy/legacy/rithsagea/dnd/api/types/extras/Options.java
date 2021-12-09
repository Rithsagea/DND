package legacy.rithsagea.dnd.api.types.extras;

import java.util.List;

public class Options implements OptionType {
	
	public int count;
	public List<OptionType> options;
	
	public String toString() { return "option|" + count + ": " + options; }
}
