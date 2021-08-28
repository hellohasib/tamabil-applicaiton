package com.landport.tamabil.controller;


import com.landport.tamabil.model.Importer;
import com.landport.tamabil.model.Weight;
import com.landport.tamabil.service.ImporterService;
import com.landport.tamabil.service.WeightService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
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
import java.util.Optional;

@Controller
@RequestMapping("weight")
public class WeightController {

    private WeightService weightService;
    @Autowired
    private ImporterService importerService;

    @Autowired
    public void setPilotService(WeightService weightService) {
        this.weightService = weightService;
    }

    @GetMapping
    public String index() {
        return "redirect:/weight/1";
    }

    @GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<Weight> page = weightService.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        return "weight/list";

    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("weight", new Weight());
        model.addAttribute("importer", importerService.getAllList());
        return "weight/form";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        model.addAttribute("weight", weightService.get(id));
        model.addAttribute("importer", importerService.getAllList());
        return "weight/form";

    }

    @PostMapping(value = "/save")
    public String save(Weight weight, final RedirectAttributes ra,HttpServletRequest request) throws UnirestException {
        Principal principal = request.getUserPrincipal();
        Date now=new Date() ;
        weight.setCreatedAt(now);
        weight.setCreatedBy(principal.getName());
        Weight save = weightService.save(weight);
        ra.addFlashAttribute("successFlash", "Weight saved successfully");
        Optional<Importer> importerList=importerService.findById(Long.parseLong(weight.getImporterid()+""));
        String sendTo= "880"+String.valueOf(importerList.get().getCellphone());
        String text= "Dear,%20"+importerList.get().getImportername().toString()+"%20your%20cargo%20entered%20our%20Tamabil%20Port%20successfully.%20The%20weight%20of%20goods%20is%20"+weight.getNetweight()+"KG.%20Thank%20you.";
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("http://217.172.190.215/sendtext?apikey=78c04812eccc01f7&secretkey=582ef419&callerID=8809612448805&toUser="+sendTo+"&messageContent="+text)
                .asString();
        return "redirect:/weight";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        weightService.delete(id);
        return "redirect:/weight";

    }

}
