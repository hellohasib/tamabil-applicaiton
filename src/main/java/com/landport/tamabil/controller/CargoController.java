package com.landport.tamabil.controller;



import com.landport.tamabil.model.Cargo;
import com.landport.tamabil.service.CargoService;
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
@RequestMapping("cargo")
public class CargoController {

    private CargoService cargoService;

    @Autowired
    public void setImporterService(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping
    public String index() {
        return "redirect:/cargo/1";
    }

    @GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<Cargo> page = cargoService.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        return "cargo/list";

    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("cargo", new Cargo());
        return "cargo/form";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        model.addAttribute("cargo", cargoService.get(id));
        return "cargo/form";

    }

    @PostMapping(value = "/save")
    public String save(Cargo cargo, final RedirectAttributes ra,HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Date now=new Date() ;
        cargo.setCreatedAt(now);
        cargo.setCreatedBy(principal.getName());
        Cargo save = cargoService.save(cargo);
        ra.addFlashAttribute("successFlash", "Cargo saved successfully");
        return "redirect:/cargo";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        cargoService.delete(id);
        return "redirect:/cargo";

    }

}
