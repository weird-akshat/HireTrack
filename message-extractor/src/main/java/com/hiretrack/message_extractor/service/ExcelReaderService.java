package com.hiretrack.message_extractor.service;

import com.hiretrack.message_extractor.entity.SourceMessage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
;

@Service
public class ExcelReaderService {


    public String extractText(SourceMessage sourceMessage) throws IOException {
        try(InputStream is= new ByteArrayInputStream(sourceMessage.getFileData());
            Workbook workbook = new XSSFWorkbook(is)){
            Sheet sheet = workbook.getSheetAt(0);
            StringBuilder text = new StringBuilder();

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) return "";

            int headerSize=headerRow.getLastCellNum();
            for (Cell cell : headerRow) {
                text.append(getCellValue(cell)).append("\t");
            }
            text.append("\n");
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                for (int j = 0; j < headerSize; j++) {
                    Cell cell = row.getCell(j);
                    Object value = getCellValue(cell);
                    text.append(value).append("\t");
                }
                text.append("\n");

            }
            return text.toString();



        }
        catch (Exception e){
            throw new RuntimeException();
        }

    }
    private Object getCellValue(Cell cell) {
        if (cell == null) return null;
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> DateUtil.isCellDateFormatted(cell)
                    ? cell.getLocalDateTimeCellValue()
                    : cell.getNumericCellValue();
            case BOOLEAN -> cell.getBooleanCellValue();
            default -> null;
        };
    }

}
