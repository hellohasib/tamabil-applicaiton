package com.landport.tamabil.controller;

import com.landport.tamabil.model.*;
import com.landport.tamabil.service.*;
import com.landport.tamabil.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("report")
public class ReportController {
    @Autowired
    private WeightController weightController;
    @Autowired
    WeightService weightService;
    @Autowired
    CargoService cargoService;
    @Autowired
    CnfService cnfService;
    @Autowired
    PilotService pilotService;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    ImporterService importerService;

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("report", new Report());
        return "reports/form";

    }

    @PostMapping("/generatereport")
    public void exportToExcel(Report report,HttpServletResponse response ) throws IOException, ParseException {
        System.out.println("reportcheck:"+report.getFromdate()+"and"+report.getTodate());
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue=null;

        if (report.getReporttype().equals("1")){
            headerValue = "attachment; filename=cargo_report_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);
            List<Cargo> listCargo =  cargoService.getSpecificList(report.getFromdate(), report.getTodate());
            ExcelExportCargo excelExportCargo = new ExcelExportCargo(listCargo);
            excelExportCargo.export(response);
        }
        if (report.getReporttype().equals("2")){
            headerValue = "attachment; filename=c&f_report_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);
            List<CnfAgent> cnfAgentList =  cnfService.getSpecificList(report.getFromdate(), report.getTodate());
            ExcelExportCnf excelExportCnf = new ExcelExportCnf(cnfAgentList);
            excelExportCnf.export(response);
        }
        if (report.getReporttype().equals("3")){
            headerValue = "attachment; filename=importer_report_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);
            List<Importer> listImporter =  importerService.getSpecificList(report.getFromdate(), report.getTodate());
            ExcelExportImporter excelExportImporter = new ExcelExportImporter(listImporter);
            excelExportImporter.export(response);
        }
        if (report.getReporttype().equals("4")){
            headerValue = "attachment; filename=pilot_report_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);
            List<Pilot> listPilot =  pilotService.getSpecificList(report.getFromdate(), report.getTodate());
            ExcelExportPilot excelExportPilot = new ExcelExportPilot(listPilot);
            excelExportPilot.export(response);
        }
        if (report.getReporttype().equals("5")){
            headerValue = "attachment; filename=vehicle_report_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);
            List<Vehicle> listVehicle =  vehicleService.getSpecificList(report.getFromdate(), report.getTodate());
            ExcelExportVehicle excelExportVehicle = new ExcelExportVehicle(listVehicle);
            excelExportVehicle.export(response);
        }
        if (report.getReporttype().equals("6")){
            headerValue = "attachment; filename=weight_report_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);
            List<Weight> listWeight =  weightService.getSpecificList(report.getFromdate(), report.getTodate());
            ExcelExportWeight excelExportWeight = new ExcelExportWeight(listWeight);
            excelExportWeight.export(response);
        }


    }
}
