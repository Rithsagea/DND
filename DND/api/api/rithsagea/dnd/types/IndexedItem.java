package api.rithsagea.dnd.types;

public interface IndexedItem {
	public default String getName() {
		return LanguageManager.getInstance().get(this, KeyConstants.NAME);
	}
	
	public String getId();
}
