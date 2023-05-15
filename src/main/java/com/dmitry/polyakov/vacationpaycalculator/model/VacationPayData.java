package com.dmitry.polyakov.vacationpaycalculator.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class VacationPayData {
    private double averageAnnualSalary;
    private int vacationDays;
    private LocalDate startVacationDate; //month > 12 will be set to 12
}
