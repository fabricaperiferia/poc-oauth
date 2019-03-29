package com.aval.oauth.util;

public enum Codes {
	SUCCESS(1000, "Success"), ERROR_TOKEN(2033, "Error Token");
	private final int value;
	private final String label;

	private Codes(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return this.value;
	}

	public String getLabel() {
		return this.label;
	}
}
