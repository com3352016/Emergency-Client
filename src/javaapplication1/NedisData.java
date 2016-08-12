/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import ca.uhn.fhir.model.primitive.IdDt;

/**
 *
 * @author Administrator
 */
public class NedisData {
	
	private IdDt id;
	
	private int age;
	
	private String name;
	
	private enum gender {
		
		MALE("Male"),
		
		FEMALE("Female"),
		
		;
		
		private final String valStr;
		
		gender(String str){
			valStr = str;
		}
		
	};
	
	private myPriorityCodesEnum priority;
	
	private TransportationEnum transportation;
	
	private ConsiousnessEnum consiousness;
	
	private SeverityEnum severity;
	
	private double syslitolic;
	
	private double dyslitolic;
	
	private double vital;
	
	private double vital2;
	
	private double oxygen;
	
	private double hb;
	
	private double k;
	
	private double lactate;

	public IdDt getId() {
            return id;
	}

	public void setId(IdDt idDt) {
            this.id = idDt;
	}

	public int getAge() {
            return age;
	}

	public void setAge(int age) {
            this.age = age;
	}

	public String getName() {
            return name;
	}

	public void setName(String name) {
            this.name = name;
	}

	public myPriorityCodesEnum getPriority() {
            return priority;
	}

	public void setPriority(myPriorityCodesEnum priority) {
            this.priority = priority;
	}

	public TransportationEnum getTransportation() {
            return transportation;
	}

	public void setTransportation(TransportationEnum transportation) {
            this.transportation = transportation;
	}

	public ConsiousnessEnum getConsiousness() {
            return consiousness;
	}

	public void setConsiousness(ConsiousnessEnum consiousness) {
            this.consiousness = consiousness;
	}

	public SeverityEnum getSeverity() {
            return severity;
	}

	public void setSeverity(SeverityEnum severity) {
            this.severity = severity;
	}

	public double getSyslitolic() {
            return syslitolic;
	}

	public void setSyslitolic(double syslitolic) {
            this.syslitolic = syslitolic;
	}

	public double getDyslitolic() {
            return dyslitolic;
	}

	public void setDyslitolic(double dyslitolic) {
            this.dyslitolic = dyslitolic;
	}

	public double getVital() {
            return vital;
	}

	public void setVital(double vital) {
            this.vital = vital;
	}

	public double getVital2() {
            return vital2;
	}

	public void setVital2(double vital2) {
            this.vital2 = vital2;
	}

	public double getOxygen() {
            return oxygen;
	}

	public void setOxygen(double oxygen) {
            this.oxygen = oxygen;
	}

	public double getHb() {
            return hb;
	}

	public void setHb(double hb) {
            this.hb = hb;
	}

	public double getK() {
            return k;
	}

	public void setK(double k) {
            this.k = k;
	}

	public double getLactate() {
            return lactate;
	}

	public void setLactate(double lactate) {
            this.lactate = lactate;
	}
	
}