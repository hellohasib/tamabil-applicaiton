package com.landport.tamabil.utils;

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
import java.util.List;

public class ExcelExportWeight {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Iterable<Weight> listWeight;

    public ExcelExportWeight(Iterable<Weight> listWeight) {
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
        createCell(row, 1, "Import ID", style);
        createCell(row, 2, "Date", style);
        createCell(row, 3, "Importer ID", style);
        createCell(row, 4, "Load Weight", style);
        createCell(row, 5, "Unload Weight", style);
        createCell(row, 6, "Net Weight", style);
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

        for (Weight weight : listWeight) {
            System.out.println("checkprint:"+weight.getNetweight());
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, weight.getId()+"", style);
            createCell(row, columnCount++, weight.getImportid()+"", style);
            createCell(row, columnCount++, weight.getCreatedAt()+"", style);
            createCell(row, columnCount++, weight.getImporterid()+"", style);
            createCell(row, columnCount++, weight.getLoadweight()+"", style);
            createCell(row, columnCount++, weight.getUnloadweight()+"", style);
            createCell(row, columnCount++, weight.getNetweight()+"", style);
            createCell(row, columnCount++, weight.getCreatedBy()+"", style);


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
