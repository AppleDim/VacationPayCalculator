package com.dmitry.polyakov.vacationpaycalculator.tests.service;

import com.dmitry.polyakov.vacationpaycalculator.model.VacationPayData;
import com.dmitry.polyakov.vacationpaycalculator.service.PayCalculatorService;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VacationPayWithDatesServiceTest {
    @Test
    public void testCalculateVacationPayWithDatesInMay() {
        VacationPayData data = new VacationPayData(300_000, 21, LocalDate.of(2023, 5, 1));
        PayCalculatorService service = new PayCalculatorService();
        //actualVacationDays = 12 days in May (2, 3, 4, 5, 10, 11, 12, 15, 16, 17, 18, 19)
        double expected = 300_000*12/(29.4*12);
        double result = service.calculateVacationPayWithDates(data);
        assertEquals(expected, result, 0.01);
    }

    @Test
    public void testCalculateVacationPayWithDatesInJanuary() {
        VacationPayData data = new VacationPayData(300_000, 10, LocalDate.of(2023, 1, 1));
        PayCalculatorService service = new PayCalculatorService();
        //actualVacationDays = 2 (weekend lasts from 1 to 8th January, only 9th and 10th January are working days)
        double expected = 300_000*2/(29.4*12);
        double result = service.calculateVacationPayWithDates(data);
        assertEquals(expected, result, 0.01);
    }
}
