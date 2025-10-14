package com.hiretrack.message_extractor.Service;

import com.hiretrack.message_extractor.Entity.SourceMessage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelReaderService {


    public String extractText(SourceMessage sourceMessage) throws IOException {
        try(InputStream is= new ByteArrayInputStream(sourceMessage.getFileData());
            Workbook workbook = new XSSFWorkbook(is)){
            Sheet sheet = workbook.getSheetAt(0);
//            List<Map<String, Object>> rows = new ArrayList<>();
            StringBuilder text = new StringBuilder();
            // Read header
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) return List.of();

            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                text.append(cell.getStringCellValue());
            }
            text.append("\n");
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                for (int j = 0; j < headers.size(); j++) {
                    Cell cell = row.getCell(j);
                    Object value = getCellValue(cell);
                    text.append(cell.getStringCellValue());
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
