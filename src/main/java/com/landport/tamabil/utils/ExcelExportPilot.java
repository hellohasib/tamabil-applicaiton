package com.landport.tamabil.utils;

import com.landport.tamabil.model.Pilot;
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

public class ExcelExportPilot {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Iterable<Pilot> listPilot;

    public ExcelExportPilot(Iterable<Pilot> listPilot) {
        this.listPilot = listPilot;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Pilot Report");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);


        createCell(row, 0, "ID", style);
        createCell(row, 1, "Pilot Name", style);
        createCell(row, 2, "Address", style);
        createCell(row, 3, "Date of Birth", style);
        createCell(row, 4, "Cell Phone", style);
        createCell(row, 5, "Nationality", style);
        createCell(row, 6, "Driving License", style);
        createCell(row, 7, "Date", style);
        createCell(row, 8, "Created By", style);


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

        for (Pilot pilot : listPilot) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, pilot.getId()+"", style);
            createCell(row, columnCount++, pilot.getPilotname()+"", style);
            createCell(row, columnCount++, pilot.getAddress()+"", style);
            createCell(row, columnCount++, pilot.getDob()+"", style);
            createCell(row, columnCount++, pilot.getCellphone()+"", style);
            createCell(row, columnCount++, pilot.getNationality()+"", style);
            createCell(row, columnCount++, pilot.getDrivinglicense()+"", style);
            createCell(row, columnCount++, pilot.getCreatedAt()+"", style);
            createCell(row, columnCount++, pilot.getCreatedBy()+"", style);


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
