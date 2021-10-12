package com.landport.tamabil.utils;

import com.landport.tamabil.model.FastEntry;
import com.landport.tamabil.model.Weight;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExcelExportFastEntry {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Iterable<FastEntry> listWeight;

    public ExcelExportFastEntry(Iterable<FastEntry> listWeight) {
        this.listWeight = listWeight;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Weight Report");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Truck Serial No", style);
        createCell(row, 2, "Driver Name", style);
        createCell(row, 3, "Vehicle Reg No", style);
        createCell(row, 4, "Exporter Name", style);
        createCell(row, 5, "Importer Name", style);
        createCell(row, 6, "Importer Contact", style);
        createCell(row, 7, "Goods Name", style);
        createCell(row, 8, "Gross Weight", style);
        createCell(row, 9, "Net Weight", style);
        createCell(row, 10, "Number of Package", style);
        createCell(row, 11, "Lc No", style);
        createCell(row, 12, "Cnf Agent", style);
        createCell(row, 13, "Created By", style);
        createCell(row, 14, "Date", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (FastEntry weight : listWeight) {
            System.out.println("checkprint:"+weight.getNetweight());
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, weight.getId()+"", style);
            createCell(row, columnCount++, weight.getTruckserialno()+"", style);
            createCell(row, columnCount++, weight.getDrivername()+"", style);
            createCell(row, columnCount++, weight.getVehicleregno()+"", style);
            createCell(row, columnCount++, weight.getExportername()+"", style);
            createCell(row, columnCount++, weight.getImportername()+"", style);
            createCell(row, columnCount++, weight.getImportercontact()+"", style);
            createCell(row, columnCount++, weight.getGoodsname()+"", style);
            createCell(row, columnCount++, weight.getGrossweight()+"", style);
            createCell(row, columnCount++, weight.getNetweight()+"", style);
            createCell(row, columnCount++, weight.getNumberofpackage()+"", style);
            createCell(row, columnCount++, weight.getLcno()+"", style);
            createCell(row, columnCount++, weight.getCnfagent()+"", style);
            createCell(row, columnCount++, weight.getCreatedBy()+"", style);
            createCell(row, columnCount++, weight.getCreatedAt()+"", style);


        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
