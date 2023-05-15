package com.dmitry.polyakov.vacationpaycalculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacationPayData {
    private double averageAnnualSalary;
    private int vacationDays;
    private LocalDate startVacationDate; //month > 12 will be set to 12

    public VacationPayData(double averageAnnualSalary, int vacationDays) {
        this.averageAnnualSalary = averageAnnualSalary;
        this.vacationDays = vacationDays;
    }
}
