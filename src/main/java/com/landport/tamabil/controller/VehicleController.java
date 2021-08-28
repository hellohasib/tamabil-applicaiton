package com.landport.tamabil.controller;


import com.landport.tamabil.model.Vehicle;
import com.landport.tamabil.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("vehicle")
public class VehicleController {

    private VehicleService vehicleService;

    @Autowired
    public void setVehicleService(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public String index() {
        return "redirect:/vehicle/1";
    }

    @GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<Vehicle> page = vehicleService.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        return "vehicle/list";

    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("vehicle", new Vehicle());
        return "vehicle/form";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        model.addAttribute("vehicle", vehicleService.get(id));
        return "vehicle/form";

    }

    @PostMapping(value = "/save")
    public String save(Vehicle vehicle, final RedirectAttributes ra,HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Date now=new Date() ;
        vehicle.setCreatedAt(now);
        vehicle.setCreatedBy(principal.getName());
        Vehicle save = vehicleService.save(vehicle);
        ra.addFlashAttribute("successFlash", "Vehicle saved successfully");
        return "redirect:/vehicle";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        vehicleService.delete(id);
        return "redirect:/vehicle";

    }

}
