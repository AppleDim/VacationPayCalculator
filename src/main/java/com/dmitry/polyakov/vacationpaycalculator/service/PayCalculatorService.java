package com.dmitry.polyakov.vacationpaycalculator.service;

import com.dmitry.polyakov.vacationpaycalculator.model.VacationPayData;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service
public class PayCalculatorService {
    public final static int CURRENT_YEAR = LocalDate.now().getYear();
    public final static double VACATION_COEFFICIENT = 29.4; //applicable coefficient for Russian Federation

    public double calculateVacationPay(VacationPayData vacationPayData) {
        double vacationPay = (vacationPayData.getAverageAnnualSalary() / (12 * VACATION_COEFFICIENT)) * vacationPayData.getVacationDays();
        DecimalFormat decimalFormat = new DecimalFormat("#.##"); //round to 2 digits
        return Double.parseDouble(decimalFormat.format(vacationPay).replace(",", "."));
    }

    public double calculateVacationPayWithDates(VacationPayData vacationPayData) {
        int paidDays = calculatePaidDays(vacationPayData.getVacationDays(), vacationPayData.getStartVacationDate());
        double vacationPay = (vacationPayData.getAverageAnnualSalary() / (12 * VACATION_COEFFICIENT)) * paidDays;
        DecimalFormat decimalFormat = new DecimalFormat("#.##"); //round to 2 digits
        return Double.parseDouble(decimalFormat.format(vacationPay).replace(",", "."));
    }

    private int calculatePaidDays(int vacationDays, LocalDate startVacationDate) {
        Predicate<LocalDate> holidays = getHolidays()::contains;
        int paidDays = 0;
        LocalDate currentDate = startVacationDate;
        for (int i = 0; i < vacationDays; i++) {
            if (!holidays.test(currentDate) && !isWeekend(currentDate)) {
                paidDays++;
            }
            currentDate = currentDate.plusDays(1);
        }
        return paidDays;
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private List<LocalDate> getHolidays() {
        //Valid dates for 2023 (weekends included)
        return Stream.of(
                LocalDate.of(CURRENT_YEAR, 1, 1),
                LocalDate.of(CURRENT_YEAR, 1, 2),
                LocalDate.of(CURRENT_YEAR, 1, 3),
                LocalDate.of(CURRENT_YEAR, 1, 4),
                LocalDate.of(CURRENT_YEAR, 1, 5),
                LocalDate.of(CURRENT_YEAR, 1, 6),
                LocalDate.of(CURRENT_YEAR, 1, 7),
                LocalDate.of(CURRENT_YEAR, 1, 8),
                LocalDate.of(CURRENT_YEAR, 2, 23),
                LocalDate.of(CURRENT_YEAR, 2, 24),
                LocalDate.of(CURRENT_YEAR, 2, 25),
                LocalDate.of(CURRENT_YEAR, 2, 26),
                LocalDate.of(CURRENT_YEAR, 4, 29),
                LocalDate.of(CURRENT_YEAR, 4, 30),
                LocalDate.of(CURRENT_YEAR, 5, 1),
                LocalDate.of(CURRENT_YEAR, 5, 6),
                LocalDate.of(CURRENT_YEAR, 5, 7),
                LocalDate.of(CURRENT_YEAR, 5, 8),
                LocalDate.of(CURRENT_YEAR, 6, 10),
                LocalDate.of(CURRENT_YEAR, 6, 11),
                LocalDate.of(CURRENT_YEAR, 6, 12),
                LocalDate.of(CURRENT_YEAR, 11, 4),
                LocalDate.of(CURRENT_YEAR, 11, 5),
                LocalDate.of(CURRENT_YEAR, 11, 6)
        ).toList();
    }
}
