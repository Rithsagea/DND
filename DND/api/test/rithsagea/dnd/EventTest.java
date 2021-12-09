package test.rithsagea.dnd;

import api.rithsagea.dnd.event.Event;
import api.rithsagea.dnd.event.EventBus;
import api.rithsagea.dnd.event.EventHandler;
import api.rithsagea.dnd.event.EventListener;

public class EventTest {
	private static class TestListener1 implements EventListener {
		@EventHandler
		public void onTestEvent(TestEvent e) {
			System.out.println("1: " + e.getMessage());
		}
	}
	
	private static class TestListener2 implements EventListener {
		@EventHandler(priority = 3)
		public void onTestEvent(TestEvent e) {
			System.out.println("2: " + e.getMessage());
		}
	}
	
	private static class TestEvent implements Event {
		
		private String message;
		
		public TestEvent(String message) {
			this.message = message;
		}
		
		public String getMessage() {
			return message;
		}
		
		@Override
		public void finish() {
			System.out.println("Message: " + message);
		}
	}
	
	public static void main(String[] args) {
		EventBus bus = new EventBus();
		TestListener1 list1 = new TestListener1();
		TestListener2 list2 = new TestListener2();
		
		bus.registerListener(list1);
		bus.registerListener(list2);
		bus.submitEvent(new TestEvent("Hello World"));
	}
}
