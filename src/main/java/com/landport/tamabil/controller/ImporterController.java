package com.landport.tamabil.controller;


import com.landport.tamabil.model.Importer;
import com.landport.tamabil.service.ImporterService;
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
@RequestMapping("importer")
public class ImporterController {

    private ImporterService importerService;

    @Autowired
    public void setImporterService(ImporterService importerService) {
        this.importerService = importerService;
    }

    @GetMapping
    public String index() {
        return "redirect:/importer/1";
    }

    @GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<Importer> page = importerService.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        return "importer/list";

    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("importer", new Importer());
        return "importer/form";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        model.addAttribute("importer", importerService.get(id));
        return "importer/form";

    }

    @PostMapping(value = "/save")
    public String save(Importer importer, final RedirectAttributes ra,HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        Date now=new Date() ;
        importer.setCreatedAt(now);
        importer.setCreatedBy(principal.getName());
        Importer save = importerService.save(importer);
        ra.addFlashAttribute("successFlash", "Importer saved successfully");
        return "redirect:/importer";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        importerService.delete(id);
        return "redirect:/importer";

    }

}
