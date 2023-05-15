package com.dmitry.polyakov.vacationpaycalculator.tests.service;

import com.dmitry.polyakov.vacationpaycalculator.model.VacationPayData;
import com.dmitry.polyakov.vacationpaycalculator.service.PayCalculatorService;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VacationPayServiceTest {

    @Test
    public void testCalculateVacationPay() {
        VacationPayData data = new VacationPayData(300_000, 12);
        PayCalculatorService service = new PayCalculatorService();
        double expected = 300_000*12/(29.4*12);
        double result = service.calculateVacationPay(data);
        assertEquals(expected, result, 0.01);
    }
}
