package com.landport.tamabil.utils;

import com.landport.tamabil.model.Importer;
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

public class ExcelExportImporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Iterable<Importer> listImporter;

    public ExcelExportImporter(Iterable<Importer> listImporter) {
        this.listImporter = listImporter;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Importer Report");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Importer Name", style);
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

        for (Importer importer : listImporter) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, importer.getId()+"", style);
            createCell(row, columnCount++, importer.getImportername()+"", style);
            createCell(row, columnCount++, importer.getAddress()+"", style);
            createCell(row, columnCount++, importer.getProprietorname()+"", style);
            createCell(row, columnCount++, importer.getCellphone()+"", style);
            createCell(row, columnCount++, importer.getEmail()+"", style);
            createCell(row, columnCount++, importer.getCreatedAt()+"", style);
            createCell(row, columnCount++, importer.getCreatedBy()+"", style);

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
