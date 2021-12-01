package com.rithsagea.dnd.api.game;

import java.util.Collection;

public interface Scene {
	public Collection<Token> getTokens();
	public Collection<Prop> getProps(); //backdrop, barrels and stuff, etc
}
