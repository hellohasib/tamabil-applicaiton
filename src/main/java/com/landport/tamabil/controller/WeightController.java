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
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
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
        String netweight="";
        SimpleDateFormat format=new SimpleDateFormat();
        Date now=new Date() ;
        weight.setCreatedAt(now);
        weight.setCreatedBy(principal.getName());
        if (weight.getUnloadweight()>0){
            weight.setNetweight(weight.getLoadweight()-weight.getUnloadweight());
            netweight=String.valueOf(weight.getLoadweight()-weight.getUnloadweight());
        }
        Weight save = weightService.save(weight);
        ra.addFlashAttribute("successFlash", "Weight saved successfully");
        Optional<Importer> importerList=importerService.findById(Long.parseLong(weight.getImporterid()+""));
        String sendTo= "880"+String.valueOf(importerList.get().getCellphone());
        String importername=importerList.get().getImportername();
        String importerfullname=importername.replaceAll(" ","%20");

        if (weight.getLoadweight()>0 && weight.getUnloadweight()>0){
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
            String timeColonPattern = "hh:mm:ss a";
            DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);
            LocalTime colonTime = LocalTime.of(17, 35, 50);

            String text= "Date:%20"+date+"%0a"+
                    "Time:%20"+colonTime+"%0a"+
                    "Importer:%20"+importerfullname+"%0a"+
                    "Cargo%20entered%20into%20Tamabil%20Landport"+"%0a"+
                    "Weight%20of%20goods:%20"+netweight+"%0a"+
                    "Thanks,%20BSBK";

            Unirest.setTimeouts(0, 0);
            HttpResponse<String> response = Unirest.post("http://217.172.190.215/sendtext?apikey=78c04812eccc01f7&secretkey=582ef419&callerID=8809612448805&toUser="+sendTo+"&messageContent="+text)
                    .asString();
        }

        return "redirect:/weight";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        weightService.delete(id);
        return "redirect:/weight";

    }
    @GetMapping("allweight")
    public List<Weight> getAllWeight(){
        return weightService.getAll();
    }

}
