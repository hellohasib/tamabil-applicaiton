package com.landport.tamabil.controller;



import com.landport.tamabil.model.CnfAgent;
import com.landport.tamabil.service.CnfService;
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
@RequestMapping("cnf")
public class CnfController {

    private CnfService cnfService;

    @Autowired
    public void setImporterService(CnfService cnfService) {
        this.cnfService = cnfService;
    }

    @GetMapping
    public String index() {
        return "redirect:/cnf/1";
    }

    @GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<CnfAgent> page = cnfService.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        return "cnf/list";

    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("cnf", new CnfAgent());
        return "cnf/form";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        model.addAttribute("cnf", cnfService.get(id));
        return "cnf/form";

    }

    @PostMapping(value = "/save")
    public String save(CnfAgent cnf, final RedirectAttributes ra,HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Date now=new Date() ;
        cnf.setCreatedAt(now);
        cnf.setCreatedBy(principal.getName());
        CnfAgent save = cnfService.save(cnf);
        ra.addFlashAttribute("successFlash", "Cnf saved successfully");
        return "redirect:/cnf";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        cnfService.delete(id);
        return "redirect:/cnf";

    }

}
