
package javaapplication1;

import ca.uhn.fhir.model.api.*;
import java.util.HashMap;
import java.util.Map;

public enum SeverityEnum {

	L1("L1", "http://hl7.org/fhir/transportation"),
	
	L2("L2", "http://hl7.org/fhir/transportation"),
	
	L3("L3", "http://hl7.org/fhir/transportation"),
	
	L4("L4", "http://hl7.org/fhir/transportation"),
	
	L5("L5", "http://hl7.org/fhir/transportation"),
	
	;
	
	/**
	 * Identifier for this Value Set:
	 * 
	 */
	public static final String VALUESET_IDENTIFIER = "";

	/**
	 * Name for this Value Set:
	 * Priority Codes
	 */
	public static final String VALUESET_NAME = "Priority Codes";

	private static Map<String, SeverityEnum> CODE_TO_ENUM = new HashMap<String, SeverityEnum>();
	private static Map<String, Map<String, SeverityEnum>> SYSTEM_TO_CODE_TO_ENUM = new HashMap<String, Map<String, SeverityEnum>>();
	
	private final String myCode;
	private final String mySystem;
	
	static {
		for (SeverityEnum next : SeverityEnum.values()) {
			CODE_TO_ENUM.put(next.getCode(), next);
			
			if (!SYSTEM_TO_CODE_TO_ENUM.containsKey(next.getSystem())) {
				SYSTEM_TO_CODE_TO_ENUM.put(next.getSystem(), new HashMap<String, SeverityEnum>());
			}
			SYSTEM_TO_CODE_TO_ENUM.get(next.getSystem()).put(next.getCode(), next);			
		}
	}
	
	/**
	 * Returns the code associated with this enumerated value
	 */
	public String getCode() {
		return myCode;
	}
	
	/**
	 * Returns the code system associated with this enumerated value
	 */
	public String getSystem() {
		return mySystem;
	}
	
	/**
	 * Returns the enumerated value associated with this code
	 */
	public static SeverityEnum forCode(String theCode) {
		SeverityEnum retVal = CODE_TO_ENUM.get(theCode);
		return retVal;
	}

	/**
	 * Converts codes to their respective enumerated values
	 */
	public static final IValueSetEnumBinder<SeverityEnum> VALUESET_BINDER = new IValueSetEnumBinder<SeverityEnum>() {
		@Override
		public String toCodeString(SeverityEnum theEnum) {
			return theEnum.getCode();
		}

		@Override
		public String toSystemString(SeverityEnum theEnum) {
			return theEnum.getSystem();
		}
		
		@Override
		public SeverityEnum fromCodeString(String theCodeString) {
			return CODE_TO_ENUM.get(theCodeString);
		}
		
		@Override
		public SeverityEnum fromCodeString(String theCodeString, String theSystemString) {
			Map<String, SeverityEnum> map = SYSTEM_TO_CODE_TO_ENUM.get(theSystemString);
			if (map == null) {
				return null;
			}
			return map.get(theCodeString);
		}
		
	};
	
	/** 
	 * Constructor
	 */
	SeverityEnum(String theCode, String theSystem) {
		myCode = theCode;
		mySystem = theSystem;
	}

	
}
