package com.landport.tamabil.utils;

import com.landport.tamabil.model.CnfAgent;
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

public class ExcelExportCnf {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Iterable<CnfAgent> listCnf;

    public ExcelExportCnf(Iterable<CnfAgent> listCnf) {
        this.listCnf = listCnf;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("C&F Agent Report");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);


        createCell(row, 0, "ID", style);
        createCell(row, 1, "Agent Name", style);
        createCell(row, 2, "Address", style);
        createCell(row, 3, "Proprietor Name", style);
        createCell(row, 4, "Cellphone", style);
        createCell(row, 5, "Email", style);
        createCell(row, 6, "Date", style);
        createCell(row, 7, "Created By", style);

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

        for (CnfAgent cnfAgent : listCnf) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, cnfAgent.getId()+"", style);
            createCell(row, columnCount++, cnfAgent.getAgentname()+"", style);
            createCell(row, columnCount++, cnfAgent.getAddress()+"", style);
            createCell(row, columnCount++, cnfAgent.getProprietorname()+"", style);
            createCell(row, columnCount++, cnfAgent.getCellphone()+"", style);
            createCell(row, columnCount++, cnfAgent.getEmail()+"", style);
            createCell(row, columnCount++, cnfAgent.getCreatedAt()+"", style);
            createCell(row, columnCount++, cnfAgent.getCreatedBy()+"", style);


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
