
package javaapplication1;

import ca.uhn.fhir.model.api.*;
import java.util.HashMap;
import java.util.Map;

public enum ConsiousnessEnum {

	A("Alert", "http://hl7.org/fhir/transportation"),
	
	V("Verbal Response", "http://hl7.org/fhir/transportation"),
	
	P("Painful Response", "http://hl7.org/fhir/transportation"),
	
	U("Unresponsive", "http://hl7.org/fhir/transportation"),
	
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

	private static Map<String, ConsiousnessEnum> CODE_TO_ENUM = new HashMap<String, ConsiousnessEnum>();
	private static Map<String, Map<String, ConsiousnessEnum>> SYSTEM_TO_CODE_TO_ENUM = new HashMap<String, Map<String, ConsiousnessEnum>>();
	
	private final String myCode;
	private final String mySystem;
	
	static {
		for (ConsiousnessEnum next : ConsiousnessEnum.values()) {
			CODE_TO_ENUM.put(next.getCode(), next);
			
			if (!SYSTEM_TO_CODE_TO_ENUM.containsKey(next.getSystem())) {
				SYSTEM_TO_CODE_TO_ENUM.put(next.getSystem(), new HashMap<String, ConsiousnessEnum>());
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
	public static ConsiousnessEnum forCode(String theCode) {
		ConsiousnessEnum retVal = CODE_TO_ENUM.get(theCode);
		return retVal;
	}

	/**
	 * Converts codes to their respective enumerated values
	 */
	public static final IValueSetEnumBinder<ConsiousnessEnum> VALUESET_BINDER = new IValueSetEnumBinder<ConsiousnessEnum>() {
		@Override
		public String toCodeString(ConsiousnessEnum theEnum) {
			return theEnum.getCode();
		}

		@Override
		public String toSystemString(ConsiousnessEnum theEnum) {
			return theEnum.getSystem();
		}
		
		@Override
		public ConsiousnessEnum fromCodeString(String theCodeString) {
			return CODE_TO_ENUM.get(theCodeString);
		}
		
		@Override
		public ConsiousnessEnum fromCodeString(String theCodeString, String theSystemString) {
			Map<String, ConsiousnessEnum> map = SYSTEM_TO_CODE_TO_ENUM.get(theSystemString);
			if (map == null) {
				return null;
			}
			return map.get(theCodeString);
		}
		
	};
	
	/** 
	 * Constructor
	 */
	ConsiousnessEnum(String theCode, String theSystem) {
		myCode = theCode;
		mySystem = theSystem;
	}

	
}
