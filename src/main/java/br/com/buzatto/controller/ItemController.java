package br.com.buzatto.controller;

import br.com.buzatto.model.Response;
import br.com.buzatto.service.ItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/proccess-files")
    public Response proccessFiles(@RequestParam("fileUnimed") MultipartFile fileUnimed,
                                  @RequestParam("fileConcent") MultipartFile fileConcent) {
        return this.itemService.proccessFiles(fileUnimed, fileConcent);
    }
}
