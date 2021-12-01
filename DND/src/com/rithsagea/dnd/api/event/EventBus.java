package com.rithsagea.dnd.api.event;

public class EventBus {
	private static final EventBus INSTANCE = new EventBus();
	
	public static EventBus getInstance() {
		return INSTANCE;
	}
	
	private EventBus() {
		
	}
}
