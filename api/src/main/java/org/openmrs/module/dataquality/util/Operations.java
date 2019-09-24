package org.openmrs.module.dataquality.util;

public abstract class Operations {
	
	public static boolean isNullOrEmpty(String input) {
		if (input != null) {
			if (input.trim().length() != 0) {
				//true means value
				return true;
			}
			return false;
		}
		return false;
	}
}
