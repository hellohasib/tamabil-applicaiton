package com.landport.tamabil.utils;

import com.landport.tamabil.model.Cargo;
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

public class ExcelExportCargo {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Iterable<Cargo> listCargo;

    public ExcelExportCargo(Iterable<Cargo> listCargo) {
        this.listCargo = listCargo;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Cargo Report");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Import ID", style);
        createCell(row, 2, "Serial No", style);
        createCell(row, 3, "Lc No", style);
        createCell(row, 4, "Lc Date", style);
        createCell(row, 5, "Lc Bank Branch", style);
        createCell(row, 6, "Vehicle Id", style);
        createCell(row, 7, "Pilot Id", style);
        createCell(row, 8, "Goods Type", style);
        createCell(row, 9, "Description of Good", style);
        createCell(row, 10, "Declared Weight", style);
        createCell(row, 11, "Importer Id", style);
        createCell(row, 12, "C&F Id", style);
        createCell(row, 13, "Date", style);
        createCell(row, 14, "Created By", style);


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

        for (Cargo cargo : listCargo) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, cargo.getId()+"", style);
            createCell(row, columnCount++, cargo.getImportid()+"", style);
            createCell(row, columnCount++, cargo.getSerialno()+"", style);
            createCell(row, columnCount++, cargo.getLcno()+"", style);
            createCell(row, columnCount++, cargo.getLcdate()+"", style);
            createCell(row, columnCount++, cargo.getLcbankbranch()+"", style);
            createCell(row, columnCount++, cargo.getVehicleid()+"", style);
            createCell(row, columnCount++, cargo.getPilotid()+"", style);
            createCell(row, columnCount++, cargo.getGoodstype()+"", style);
            createCell(row, columnCount++, cargo.getDescriptionofgood()+"", style);
            createCell(row, columnCount++, cargo.getDeclaredweight()+"", style);
            createCell(row, columnCount++, cargo.getImporterid()+"", style);
            createCell(row, columnCount++, cargo.getCnfid()+"", style);
            createCell(row, columnCount++, cargo.getCreatedAt()+"", style);
            createCell(row, columnCount++, cargo.getCreatedBy()+"", style);


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
