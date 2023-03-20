package br.com.buzatto.service;

import br.com.buzatto.model.Item;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelUploadService {

    public static boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<Item> getItensDataFromExcel(InputStream inputStream) {
        List<Item> itens = new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Item item = new Item();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 1 -> item.setRequisicao((long) cell.getNumericCellValue());
                        case 2 -> item.setGuia((long) cell.getNumericCellValue());
                        case 3 -> item.setCarteirinha((long) cell.getNumericCellValue());
                        case 4 -> item.setBeneficiario(cell.getStringCellValue());
                        case 9 -> item.setCodigo((long) cell.getNumericCellValue());
                        case 10 -> item.setDescricao(cell.getStringCellValue());
                        default -> {
                        }
                    }
                    cellIndex++;
                }
                itens.add(item);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return itens;
    }
}
