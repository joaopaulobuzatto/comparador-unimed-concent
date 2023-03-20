package br.com.buzatto.service;

import br.com.buzatto.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private final Logger log = LoggerFactory.getLogger(ItemService.class);

    public void readItens(MultipartFile fileUnimed, MultipartFile fileConcent) {
        List<Item> itensUnimed = new ArrayList<>();
        List<Item> itensConcent = new ArrayList<>();
        if (ExcelUploadService.isValidExcelFile(fileUnimed)) {
            try {
                itensUnimed = ExcelUploadService.getItensDataFromExcelUnimed(fileUnimed.getInputStream());
            } catch (IOException e) {
                throw new IllegalArgumentException("The fileUnimed is not a valid excel");
            }
        }
        itensUnimed.forEach(item -> log.info(item.toString()));

        log.info("==============================================");

        if (ExcelUploadService.isValidExcelFile(fileConcent)) {
            try {
                itensConcent = ExcelUploadService.getItensDataFromExcelConcent(fileConcent.getInputStream());
            } catch (IOException e) {
                throw new IllegalArgumentException("The fileConcent is not a valid excel");
            }
        }
        itensConcent.forEach(item -> log.info(item.toString()));
    }
}
