package api.rithsagea.dnd.types.classes;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class DndClass implements AbstractClass {

	private int level;
	private boolean multiclass;
	private Set<Feature> features;
	
	public DndClass(boolean multiclass) {
		this.multiclass = multiclass;
		features = new HashSet<>();
		
		level = 1;
		onLevelUp(level);
	}

	protected void addFeature(Feature f) {
		features.add(f);
	}
	
	@Override
	public Set<Feature> getFeatures() {
		return Collections.unmodifiableSet(features);
	}
	
	public abstract void onLevelUp(int level);

	@Override
	public void levelUp() {
		level++;
		
		onLevelUp(level);
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public boolean isMulticlass() {
		return multiclass;
	}

	@Override
	public String toString() {
		return getName();
	}
}
