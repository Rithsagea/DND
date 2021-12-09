package legacy.rithsagea.dnd.api.types;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class DndSubclass extends IndexedItem {
	
	public String name;
	public String flavor;
	public String description;
	
	@SerializedName(value = "classId", alternate = "parentClass")
	public String classId;
	public List<DndSubclassLevel> levels;
}
