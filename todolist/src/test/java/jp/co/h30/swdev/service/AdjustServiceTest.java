package jp.co.h30.swdev.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdjustServiceTest {
	private AdjustService service;

	@BeforeEach
	public void setUp() {
		service = new AdjustService();
		System.clearProperty(AdjustService.PROPERTY_KEY);
	}


	@Test
	public void setCriteriaDateWhenItHasParameter() {
		String criteriaDate = "2018/1/1";

		service.execute(criteriaDate);

		assertEquals(criteriaDate, System.getProperty(AdjustService.PROPERTY_KEY));
	}

	@Test
	public void clearCriteriaDateWhenItHasNoParameters() {
		System.setProperty(AdjustService.PROPERTY_KEY, "2018/1/1");

		service.execute();

		assertEquals(null, System.getProperty(AdjustService.PROPERTY_KEY));
	}

	@Test
	public void clearCriteriaDateWhenItHasNoParameters2() {
		System.setProperty(AdjustService.PROPERTY_KEY, "2018/1/1");

		service.execute();

		assertEquals(null, System.getProperty(AdjustService.PROPERTY_KEY));
	}
}
