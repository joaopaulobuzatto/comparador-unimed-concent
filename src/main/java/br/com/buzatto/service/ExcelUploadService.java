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

    public static List<Item> getItensFromRelatorioUnimed(InputStream inputStream) {
        List<Item> itens = new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();
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
                        case 1 -> item.setRequisicao(dataFormatter.formatCellValue(row.getCell(cellIndex)));
                        case 2 -> item.setGuia(dataFormatter.formatCellValue(row.getCell(cellIndex)));
                        case 3 -> item.setCarteirinha(dataFormatter.formatCellValue(row.getCell(cellIndex)));
                        case 4 -> item.setBeneficiario(dataFormatter.formatCellValue(row.getCell(cellIndex)));
                        case 9 -> item.setCodigo(dataFormatter.formatCellValue(row.getCell(cellIndex)));
                        case 10 -> item.setDescricao(dataFormatter.formatCellValue(row.getCell(cellIndex)));
                        case 11 -> item.setQuantidade(dataFormatter.formatCellValue(row.getCell(cellIndex)));
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

    public static List<Item> getItensFromRelatorioConcent(InputStream inputStream) {
        List<Item> itens = new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();
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
                        case 2 -> item.setRequisicao(dataFormatter.formatCellValue(row.getCell(cellIndex)));
                        case 4 -> item.setBeneficiario(dataFormatter.formatCellValue(row.getCell(cellIndex)).trim());
                        case 9 -> item.setGuia(dataFormatter.formatCellValue(row.getCell(cellIndex)));
                        case 10 -> item.setCarteirinha(dataFormatter.formatCellValue(row.getCell(cellIndex)));
                        case 11 -> item.setCodigo(dataFormatter.formatCellValue(row.getCell(cellIndex)));
                        case 12 -> item.setDescricao(dataFormatter.formatCellValue(row.getCell(cellIndex)).trim());
                        case 14 -> item.setQuantidade(dataFormatter.formatCellValue(row.getCell(cellIndex)));
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

    public static List<Item> getItensFromRelatorioNetRis(InputStream inputStream) {
        List<Item> itens = new ArrayList<>();
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();
            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0 || rowIndex == 1 || rowIndex == 2) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Item item = new Item();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> item.setBeneficiario(dataFormatter.formatCellValue(row.getCell(cellIndex)).trim());
                        case 2 -> item.setCodigo(dataFormatter.formatCellValue(row.getCell(cellIndex)));
                        case 3 -> item.setDescricao(dataFormatter.formatCellValue(row.getCell(cellIndex)).trim());
                        case 6 -> item.setCarteirinha(dataFormatter.formatCellValue(row.getCell(cellIndex)));
                        case 7 -> item.setRequisicao(dataFormatter.formatCellValue(row.getCell(cellIndex)));
                        case 8 -> item.setGuia(dataFormatter.formatCellValue(row.getCell(cellIndex)));
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
