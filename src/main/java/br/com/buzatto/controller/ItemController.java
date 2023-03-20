package br.com.buzatto.controller;

import br.com.buzatto.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/upload-itens-data")
    public ResponseEntity<?> uploadItensData(@RequestParam("file") MultipartFile file) {
        this.itemService.readItens(file);
        return ResponseEntity
                .ok(Map.of("Message", " Itens data uploaded and readed successfully"));
    }
}
