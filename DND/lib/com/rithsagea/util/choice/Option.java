package com.rithsagea.util.choice;

import java.util.Set;

public interface Option {
	public Set<Choice> getChoices();
	
	public int getCount();
	public boolean choose(Choice choice);
	public boolean remove(Choice choice);

	public Set<Choice> getChosen();
}
