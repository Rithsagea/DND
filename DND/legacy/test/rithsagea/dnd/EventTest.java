package test.rithsagea.dnd;

import com.rithsagea.util.event.Event;
import com.rithsagea.util.event.EventBus;
import com.rithsagea.util.event.EventHandler;
import com.rithsagea.util.event.Listener;

public class EventTest {
	private static class TestListener1 implements Listener {
		@EventHandler
		public void onTestEvent(TestEvent e) {
			System.out.println("1: " + e.getMessage());
		}
	}
	
	private static class TestListener2 implements Listener {
		@EventHandler(priority = 3)
		public void onTesstEvent(TestEvent e) {
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
