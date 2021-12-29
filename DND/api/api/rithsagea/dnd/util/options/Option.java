package api.rithsagea.dnd.util.options;

import java.util.List;

public interface Option<T> {
	public List<Choice<T>> getChoices();
}
