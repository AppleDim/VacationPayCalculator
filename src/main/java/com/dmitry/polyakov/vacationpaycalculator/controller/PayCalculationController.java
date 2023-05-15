package com.dmitry.polyakov.vacationpaycalculator.controller;

import com.dmitry.polyakov.vacationpaycalculator.model.VacationPayData;
import com.dmitry.polyakov.vacationpaycalculator.service.PayCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.*;

@Controller
public class PayCalculationController {

    private final PayCalculatorService payCalculatorService;

    @Autowired
    public PayCalculationController(PayCalculatorService payCalculatorService) {
        this.payCalculatorService = payCalculatorService;
    }

    @GetMapping("/calculate")
    public String calculateVacation(Model model) {
        model.addAttribute("vacationData", new VacationPayData());
        return "calculate";
    }

    @PostMapping("/calculated-result")
    public String calculateVacationWithDates(@ModelAttribute VacationPayData vacationData, Model model) {
        double vacationPay;
        if (vacationData.getStartVacationDate() == null) {
            vacationPay = payCalculatorService.calculateVacationPay(vacationData);
        } else {
            vacationPay = payCalculatorService.calculateVacationPayWithDates(vacationData);
        }
        model.addAttribute("vacationPay", vacationPay);
        return "result";
    }
}
