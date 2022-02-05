package api.rithsagea.dnd.types;

import java.util.Set;

import com.rithsagea.util.choice.Option;
import com.rithsagea.util.event.Listener;

public interface Aspect extends IndexedItem, Listener {
	public Set<Option> getOptions();
}
