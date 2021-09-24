package com.landport.tamabil.controller;

import com.landport.tamabil.model.DashboardData;
import com.landport.tamabil.repository.CargoRepository;
import com.landport.tamabil.repository.ImporterRepository;
import com.landport.tamabil.repository.PilotRepository;
import com.landport.tamabil.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @Autowired
    private CargoRepository cargoRepository;
    @Autowired
    private PilotRepository pilotRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private ImporterRepository importerRepository;
    @GetMapping("/")
    public String index(Model model) {
        DashboardData dashboardData=new DashboardData();
        dashboardData.setTotalCargo(cargoRepository.count());
        dashboardData.setTotalPilot(pilotRepository.count());
        dashboardData.setTotalVehicle(vehicleRepository.count());
        dashboardData.setTotalImporter(importerRepository.count());
        model.addAttribute(dashboardData);
        return "dashboard/index";
    }

}
