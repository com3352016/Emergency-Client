
package javaapplication1;

import ca.uhn.fhir.model.api.*;
import java.util.HashMap;
import java.util.Map;

public enum TransportationEnum {

	/**
	 * Display: <b>Immediate</b><br>
	 * Code Value: <b>stat</b>
	 *
	 * Immediately in real time.
	 */
	Ambulance("119Ambulance", "http://hl7.org/fhir/transportation"),
	
	/**
	 * Display: <b>Normal</b><br>
	 * Code Value: <b>normal</b>
	 *
	 * With best effort.
	 */
	HospitalAmbulance("HospitalAmbulance", "http://hl7.org/fhir/transportation"),
	
	/**
	 * Display: <b>Deferred</b><br>
	 * Code Value: <b>deferred</b>
	 *
	 * Later, when possible.
	 */
	OtherAmbulance("OtherAmbulance", "http://hl7.org/fhir/transportation"),
	
	PublicCar("PublicCar", "http://hl7.org/fhir/transportation"),
	
	Airlift("Airlift", "http://hl7.org/fhir/transportation"),
	
	OtherCar("OtherCar", "http://hl7.org/fhir/transportation"),
	
	Walk("Walk", "http://hl7.org/fhir/transportation"),
	
	Other("Other", "http://hl7.org/fhir/transportation"),
	
	Unkwown("Unkwown", "http://hl7.org/fhir/transportation"),
	
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

	private static Map<String, TransportationEnum> CODE_TO_ENUM = new HashMap<String, TransportationEnum>();
	private static Map<String, Map<String, TransportationEnum>> SYSTEM_TO_CODE_TO_ENUM = new HashMap<String, Map<String, TransportationEnum>>();
	
	private final String myCode;
	private final String mySystem;
	
	static {
		for (TransportationEnum next : TransportationEnum.values()) {
			CODE_TO_ENUM.put(next.getCode(), next);
			
			if (!SYSTEM_TO_CODE_TO_ENUM.containsKey(next.getSystem())) {
				SYSTEM_TO_CODE_TO_ENUM.put(next.getSystem(), new HashMap<String, TransportationEnum>());
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
	public static TransportationEnum forCode(String theCode) {
		TransportationEnum retVal = CODE_TO_ENUM.get(theCode);
		return retVal;
	}

	/**
	 * Converts codes to their respective enumerated values
	 */
	public static final IValueSetEnumBinder<TransportationEnum> VALUESET_BINDER = new IValueSetEnumBinder<TransportationEnum>() {
		@Override
		public String toCodeString(TransportationEnum theEnum) {
			return theEnum.getCode();
		}

		@Override
		public String toSystemString(TransportationEnum theEnum) {
			return theEnum.getSystem();
		}
		
		@Override
		public TransportationEnum fromCodeString(String theCodeString) {
			return CODE_TO_ENUM.get(theCodeString);
		}
		
		@Override
		public TransportationEnum fromCodeString(String theCodeString, String theSystemString) {
			Map<String, TransportationEnum> map = SYSTEM_TO_CODE_TO_ENUM.get(theSystemString);
			if (map == null) {
				return null;
			}
			return map.get(theCodeString);
		}
		
	};
	
	/** 
	 * Constructor
	 */
	TransportationEnum(String theCode, String theSystem) {
		myCode = theCode;
		mySystem = theSystem;
	}

	
}
