package com.rithsagea.util.tree;

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
	
	public static class ByteLeaf extends Leaf<Byte> {
		public ByteLeaf(byte value) {
			super(value);
		}
		
		@Override
		public String toString() {
			return get() + "b";
		}
	}
	
	public static class ShortLeaf extends Leaf<Short> {
		public ShortLeaf(short value) {
			super(value);
		}
	
		@Override
		public String toString() {
			return get() + "s";
		}
	}
	
	public static class IntegerLeaf extends Leaf<Integer> {
		public IntegerLeaf(Integer value) {
			super(value);
		}
	}
	
	public static class LongLeaf extends Leaf<Long> {
		public LongLeaf(Long value) {
			super(value);
		}
	
		@Override
		public String toString() {
			return get() + "l";
		}
	}
	
	public static class FloatLeaf extends Leaf<Float> {
		public FloatLeaf(float value) {
			super(value);
		}
		
		@Override
		public String toString() {
			return get() + "f";
		}
	}
	
	public static class DoubleLeaf extends Leaf<Double> {
		public DoubleLeaf(Double value) {
			super(value);
		}
	
		@Override
		public String toString() {
			return get() + "d";
		}
	}

	public static class CharacterLeaf extends Leaf<Character> {
		public CharacterLeaf(char value) {
			super(value);
		}
		
		@Override
		public String toString() {
			return ((int) get()) + "c";
		}
	}
	
	public static class StringLeaf extends Leaf<String> {
		public StringLeaf(String value) {
			super(value);
		}
	
		@Override
		public String toString() {
			return '"' + get().replace("\"", "\\\"").replace("\n", "\\n") + '"';
		}
	}

	public static class BooleanLeaf extends Leaf<Boolean> {
		public BooleanLeaf(Boolean value) {
			super(value);
		}
	}
	
	public static class ObjectLeaf extends Leaf<Object> {
		public ObjectLeaf(Object value) {
			super(value);
		}
	}

	public static ObjectLeaf of(Object obj) {
		return new ObjectLeaf(obj);
	}
	
	public static ByteLeaf of(byte b) {
		return new ByteLeaf(b);
	}
	
	public static ShortLeaf of(short s) {
		return new ShortLeaf(s);
	}
	
	public static IntegerLeaf of(int num) {
		return new IntegerLeaf(num);
	}
	
	public static LongLeaf of(long num) {
		return new LongLeaf(num);
	}
	
	public static FloatLeaf of(float num) {
		return new FloatLeaf(num);
	}
	
	public static DoubleLeaf of(double num) {
		return new DoubleLeaf(num);
	}

	public static CharacterLeaf of(char c) {
		return new CharacterLeaf(c);
	}
	
	public static StringLeaf of(String str) {
		return new StringLeaf(str);
	}
	
	public static BooleanLeaf of(boolean bool) {
		return new BooleanLeaf(bool);
	}
}
