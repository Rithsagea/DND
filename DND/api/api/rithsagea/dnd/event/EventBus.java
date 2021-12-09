package api.rithsagea.dnd.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class EventBus {
	private static class Handler implements Comparable<Handler> {
		public int priority;
		public Method method;
		public EventListener listener;
		
		public Handler(int priority, Method method, EventListener listener) {
			this.priority = priority;
			this.method = method;
			this.listener = listener;
		}
		
		@Override
		public boolean equals(Object obj) {
			return obj instanceof Handler && 
					method == ((Handler) obj).method &&
					listener == ((Handler) obj).listener;	
		}

		@Override
		public int compareTo(Handler h) {
			return h.priority - priority;
		}
	}
	
	private Map<Class<?>, Set<Handler>> listenerMap;
	
	public EventBus() {
		listenerMap = new HashMap<>();
	}
	
	public void registerListener(EventListener listener) {
		for(Method method : listener.getClass().getMethods()) {
			if(method.getParameterCount() == 1) {
				Parameter p = method.getParameters()[0];
				EventHandler ann = method.getAnnotation(EventHandler.class);
				Class<?> type = p.getType();
				if(Event.class.isAssignableFrom(type) && ann != null) {
					method.setAccessible(true);
					Handler handler = new Handler(ann.priority(), method, listener);
					
					while(Event.class.isAssignableFrom(type)) {
						if(!listenerMap.containsKey(type)) listenerMap.put(type, new TreeSet<>());
						listenerMap.get(type).add(handler);
						
						type = type.getSuperclass();
					}
				}
			}
		}
	}
	
	public void unregisterListener(EventListener listener) {
		for(Method method : listener.getClass().getMethods()) {
			Parameter p = method.getParameters()[0];
			Class<?> type = p.getType();
			if(Event.class.isAssignableFrom(type) && method.isAnnotationPresent(EventHandler.class)) {
				while(Event.class.isAssignableFrom(type)) {
					if(listenerMap.containsKey(type)) {
						listenerMap.get(type).removeIf((h) -> h.listener == listener);
						type = type.getSuperclass();
					}
				}
			}
		}
	}

	public void submitEvent(Event event) {
		if(listenerMap.containsKey(event.getClass())) {
			for(Handler handler : listenerMap.get(event.getClass())) {
				try {
					handler.method.invoke(handler.listener, event);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
