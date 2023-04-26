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

    @PostMapping("/compara-unimed-concent")
    public Response comparaUnimedConcent(@RequestParam("fileUnimed") MultipartFile fileUnimed,
                                         @RequestParam("fileConcent") MultipartFile fileConcent) {
        return this.itemService.comparaUnimedConcent(fileUnimed, fileConcent);
    }

    @PostMapping("/compara-unimed-netris")
    public Response comparaUnimedNetris(@RequestParam("fileUnimed") MultipartFile fileUnimed,
                                        @RequestParam("fileNetRis") MultipartFile fileNetRis) {
        return this.itemService.comparaUnimedNetris(fileUnimed, fileNetRis);
    }
}
