package com.rithsagea.dnd.api.scene;

import java.util.Collection;

public interface Scene {
	public Collection<Token> getTokens();
	public Collection<Prop> getProps(); //backdrop, barrels and stuff, etc
}
