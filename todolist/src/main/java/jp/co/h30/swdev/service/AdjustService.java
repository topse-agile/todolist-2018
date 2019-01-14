package jp.co.h30.swdev.service;

public class AdjustService {
	public static final String PROPERTY_KEY = "CRITERIA_DATE";
	
	public void execute(String criteriaDate) {
		System.setProperty(PROPERTY_KEY, criteriaDate);
	}
	
	public void execute() {
		System.clearProperty(PROPERTY_KEY);
	}
}
