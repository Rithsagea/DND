package api.rithsagea.dnd.util.choice;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import com.rithsagea.util.choice.Choice;
import com.rithsagea.util.choice.Option;

import api.rithsagea.dnd.types.IndexedItem;

public class OptionItem implements Option {

	private Set<Choice> choices;
	private Set<Choice> chosen;
	private int count;
	
	public OptionItem(int count, Object...objs) {
		this(count, Arrays.asList(objs));
	}
	
	public OptionItem(int count, Collection<?> objs) {
		this.count = count;
		choices = new LinkedHashSet<>();
		chosen = new LinkedHashSet<>();
		
		for(Object obj : objs) {
			if(obj == null) continue;
			
			if(obj instanceof Choice) choices.add((Choice) obj);
			if(obj instanceof IndexedItem) choices.add(new IndexedChoice((IndexedItem) obj));
		}
	}
	
	@Override
	public Set<Choice> getChoices() {
		return choices;
	}

	@Override
	public boolean choose(Choice choice) {
		if(chosen.size() > count || !choices.contains(choice)) return false;
		choices.remove(choice);
		chosen.add(choice);
		
		return true;
	}
	
	@Override
	public boolean remove(Choice choice) {
		if(chosen.size() <= 0 || !chosen.contains(choice)) return false;
		chosen.remove(choice);
		choices.add(choice);
		
		return true;
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public Set<Choice> getChosenChoices() {
		return chosen;
	}
}
