package legacy.rithsagea.dnd.api.types.extras;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.annotations.JsonAdapter;

@JsonAdapter(OptionListAdapter.class)
public class OptionList implements OptionType {
	public OptionList(Collection<OptionType> values) { this.values = new ArrayList<>(values); }
	
	public List<OptionType> values;
	
	public String toString() { return values.toString(); }
}
