
package javaapplication1;

import ca.uhn.fhir.model.api.*;
import java.util.HashMap;
import java.util.Map;

public enum myPriorityCodesEnum {

	/**
	 * Display: <b>Immediate</b><br>
	 * Code Value: <b>stat</b>
	 *
	 * Immediately in real time.
	 */
	diseased("diseased", "http://hl7.org/fhir/processpriority"),
	
	/**
	 * Display: <b>Normal</b><br>
	 * Code Value: <b>normal</b>
	 *
	 * With best effort.
	 */
	nondiseased("nondiseased", "http://hl7.org/fhir/processpriority"),
	
	/**
	 * Display: <b>Deferred</b><br>
	 * Code Value: <b>deferred</b>
	 *
	 * Later, when possible.
	 */
	notfortreatment("notfortreatment", "http://hl7.org/fhir/processpriority"),
	
	cancel("cancel", "http://hl7.org/fhir/processpriority"),
	
	unknown("unknown", "http://hl7.org/fhir/processpriority"),
	
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

	private static Map<String, myPriorityCodesEnum> CODE_TO_ENUM = new HashMap<String, myPriorityCodesEnum>();
	private static Map<String, Map<String, myPriorityCodesEnum>> SYSTEM_TO_CODE_TO_ENUM = new HashMap<String, Map<String, myPriorityCodesEnum>>();
	
	private final String myCode;
	private final String mySystem;
	
	static {
		for (myPriorityCodesEnum next : myPriorityCodesEnum.values()) {
			CODE_TO_ENUM.put(next.getCode(), next);
			
			if (!SYSTEM_TO_CODE_TO_ENUM.containsKey(next.getSystem())) {
				SYSTEM_TO_CODE_TO_ENUM.put(next.getSystem(), new HashMap<String, myPriorityCodesEnum>());
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
	public static myPriorityCodesEnum forCode(String theCode) {
		myPriorityCodesEnum retVal = CODE_TO_ENUM.get(theCode);
		return retVal;
	}

	/**
	 * Converts codes to their respective enumerated values
	 */
	public static final IValueSetEnumBinder<myPriorityCodesEnum> VALUESET_BINDER = new IValueSetEnumBinder<myPriorityCodesEnum>() {
		@Override
		public String toCodeString(myPriorityCodesEnum theEnum) {
			return theEnum.getCode();
		}

		@Override
		public String toSystemString(myPriorityCodesEnum theEnum) {
			return theEnum.getSystem();
		}
		
		@Override
		public myPriorityCodesEnum fromCodeString(String theCodeString) {
			return CODE_TO_ENUM.get(theCodeString);
		}
		
		@Override
		public myPriorityCodesEnum fromCodeString(String theCodeString, String theSystemString) {
			Map<String, myPriorityCodesEnum> map = SYSTEM_TO_CODE_TO_ENUM.get(theSystemString);
			if (map == null) {
				return null;
			}
			return map.get(theCodeString);
		}
		
	};
	
	/** 
	 * Constructor
	 */
	myPriorityCodesEnum(String theCode, String theSystem) {
		myCode = theCode;
		mySystem = theSystem;
	}

	
}
