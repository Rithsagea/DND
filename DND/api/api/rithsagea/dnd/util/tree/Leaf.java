package api.rithsagea.dnd.util.tree;

public class Leaf<T> extends Node {
	private T value;
	
	protected Leaf(T value) {
		this.value = value;
	}
	
	public void set(T value) {
		this.value = value;
	}
	
	public T get() {
		return value;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
	
	public static class ObjectLeaf extends Leaf<Object> {
		protected ObjectLeaf(Object value) {
			super(value);
		}
	}
	
	public static class StringLeaf extends Leaf<String> {
		protected StringLeaf(String value) {
			super(value);
		}
	
		@Override
		public String toString() {
			return '"' + get() + '"';
		}
	}

	public static class IntegerLeaf extends Leaf<Integer> {
		protected IntegerLeaf(Integer value) {
			super(value);
		}
	}

	public static class LongLeaf extends Leaf<Long> {
		protected LongLeaf(Long value) {
			super(value);
		}
	
		@Override
		public String toString() {
			return get() + "l";
		}
	}
	
	public static class DoubleLeaf extends Leaf<Double> {
		protected DoubleLeaf(Double value) {
			super(value);
		}
	
		@Override
		public String toString() {
			return get() + "d";
		}
	}

	public static class BooleanLeaf extends Leaf<Boolean> {
		protected BooleanLeaf(Boolean value) {
			super(value);
		}
	}
	
	public static Node auto(Object obj) {
		if(obj instanceof Node) return (Node) obj; // do nothing
		if(obj instanceof String) return of((String) obj);
		if(obj instanceof Integer) return of((int) obj);
		if(obj instanceof Long) return of((long) obj);
		if(obj instanceof Double) return of((double) obj);
		if(obj instanceof Boolean) return of((boolean) obj);
		return of(obj);
	}
	
	public static ObjectLeaf of(Object obj) {
		return new ObjectLeaf(obj);
	}
	
	public static StringLeaf of(String str) {
		return new StringLeaf(str);
	}
	
	public static IntegerLeaf of(int num) {
		return new IntegerLeaf(num);
	}
	
	public static LongLeaf of(long num) {
		return new LongLeaf(num);
	}
	
	public static DoubleLeaf of(double num) {
		return new DoubleLeaf(num);
	}

	public static BooleanLeaf of(boolean bool) {
		return new BooleanLeaf(bool);
	}
}
