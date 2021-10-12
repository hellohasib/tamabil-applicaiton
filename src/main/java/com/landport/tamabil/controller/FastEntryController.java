package com.landport.tamabil.controller;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.landport.tamabil.model.FastEntry;
import com.landport.tamabil.model.Importer;
import com.landport.tamabil.model.Weight;
import com.landport.tamabil.service.FastEntryService;
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
import java.util.*;

@Controller
@RequestMapping("fastentry")
public class FastEntryController {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private FastEntryService fastEntryService;

    @Autowired
    public void setImporterService(FastEntryService fastEntryService) {
        this.fastEntryService = fastEntryService;
    }

    @GetMapping
    public String index() {
        return "redirect:/fastentry/1";
    }

    @GetMapping(value = "/{pageNumber}")
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<FastEntry> page = fastEntryService.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        return "fastentry/list";

    }

    @GetMapping("/add")
    public String add(Model model) {
        Random rnd = new Random();
        int number = rnd.nextInt(99999);
        Calendar cal = Calendar.getInstance();
        FastEntry fastEntry=new FastEntry();
        String finalSerialNumber=String.valueOf(Calendar.getInstance().get(Calendar.YEAR))+String.valueOf(Calendar.getInstance().get(Calendar.MONTH))+String.valueOf(Calendar.getInstance().get(Calendar.DATE))+Long.valueOf(number);
        fastEntry.setTruckserialno(Long.valueOf(finalSerialNumber));

        model.addAttribute("fastentry", fastEntry);
        return "fastentry/form";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        model.addAttribute("fastentry", fastEntryService.get(id));
        return "fastentry/form";

    }

//    @PostMapping(value = "/save")
//    public String save(FastEntry fastentry, final RedirectAttributes ra,HttpServletRequest request) {
//        System.out.println("fastentinput:"+GSON.toJson(fastentry));
//        Principal principal = request.getUserPrincipal();
//        Date now=new Date() ;
//        fastentry.setCreatedAt(now);
//        fastentry.setCreatedBy(principal.getName());
//        FastEntry save = fastEntryService.save(fastentry);
//        ra.addFlashAttribute("successFlash", "FastEntry saved successfully");
//        return "redirect:/fastentry";
//
//    }
    @PostMapping(value = "/save")
    public String save(FastEntry fastentry, final RedirectAttributes ra,HttpServletRequest request) throws UnirestException {
        Principal principal = request.getUserPrincipal();
        String netweight="";
        SimpleDateFormat format=new SimpleDateFormat();
        Date now=new Date() ;
        fastentry.setCreatedAt(now);
        fastentry.setCreatedBy(principal.getName());
        if (fastentry.getUnloadweight()>0){
            fastentry.setNetweight(fastentry.getGrossweight()-fastentry.getUnloadweight());
            netweight=String.valueOf(fastentry.getGrossweight()-fastentry.getUnloadweight());
        }
        FastEntry save = fastEntryService.save(fastentry);
        ra.addFlashAttribute("successFlash", "Weight saved successfully");
//        Optional<Importer> importerList=importerService.findById(Long.parseLong(weight.getImporterid()+""));
        String sendTo= "88"+String.valueOf(fastentry.getImportercontact());
        String importername=fastentry.getImportername();
        String importerfullname=importername.replaceAll(" ","%20");

        if (fastentry.getGrossweight()>0 && fastentry.getUnloadweight()>0){
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
        return "redirect:/fastentry";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        fastEntryService.delete(id);
        return "redirect:/fastentry";
    }

}
