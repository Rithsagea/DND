package api.rithsagea.dnd.event;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {
	public int priority() default EventPriority.DEFAULT;
}
