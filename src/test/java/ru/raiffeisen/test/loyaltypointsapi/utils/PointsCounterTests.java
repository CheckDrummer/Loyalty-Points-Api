package ru.raiffeisen.test.loyaltypointsapi.utils;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointsCounterTests {

	@Test
	void less5000Test() {
		Integer points = PointsCounter.count(BigDecimal.valueOf(3457));
		assertEquals(3457, points);
	}

	@Test
	void less7500Test() {
		Integer points = PointsCounter.count(BigDecimal.valueOf(6790));
		assertEquals(8580, points);
	}

	@Test
	void more7500Test() {
		Integer points = PointsCounter.count(BigDecimal.valueOf(7800));
		assertEquals(10900, points);
	}
}
