package com.landport.tamabil.controller;


import com.landport.tamabil.model.Pilot;
import com.landport.tamabil.service.PilotService;
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
@RequestMapping("pilot")
public class PilotController {

    private PilotService pilotService;

    @Autowired
    public void setPilotService(PilotService pilotService) {
        this.pilotService = pilotService;
    }

    @GetMapping
    public String index() {
        return "redirect:/pilot/1";
    }

    @GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<Pilot> page = pilotService.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        return "pilot/list";

    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("pilot", new Pilot());
        return "pilot/form";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        model.addAttribute("pilot", pilotService.get(id));
        return "pilot/form";

    }

    @PostMapping(value = "/save")
    public String save(Pilot pilot, final RedirectAttributes ra,HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Date now=new Date() ;
        pilot.setCreatedAt(now);
        pilot.setCreatedBy(principal.getName());
        Pilot save = pilotService.save(pilot);
        ra.addFlashAttribute("successFlash", "Pilot saved successfully");
        return "redirect:/pilot";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        pilotService.delete(id);
        return "redirect:/pilot";

    }

}
