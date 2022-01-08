package io.github.veryuniqueusername.betterminecraft.types;

import net.minecraft.util.StringIdentifiable;

public enum RopeType implements StringIdentifiable {
	TOP("top"), BOTTOM("bottom"), MIDDLE("middle"), BOTH("both");

	private final String name;

	RopeType(String name) {
		this.name = name;
	}

	public String toString() {
		return this.asString();
	}

	public String asString() {
		return this.name;
	}
}