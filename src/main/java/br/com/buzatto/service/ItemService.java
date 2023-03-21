package br.com.buzatto.service;

import br.com.buzatto.model.Item;
import br.com.buzatto.model.Resultado;
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

    public Resultado readItens(MultipartFile fileUnimed, MultipartFile fileConcent) {
        List<Item> itensUnimed = getItems(fileUnimed, true, false);
        List<Item> itensConcent = getItems(fileConcent, false, true);

        List<Item> itensDivergenteOrigemUnimed = getItemsDivergente(itensUnimed, itensConcent, true, false);
        List<Item> itensDivergenteOrigemConcent = getItemsDivergente(itensUnimed, itensConcent, false, true);

        Resultado resultado = new Resultado();
        resultado.setTotalItensUnimedProcessados((long) itensUnimed.size());
        resultado.setTotalItensUnimedComDivergencia((long) itensDivergenteOrigemUnimed.size());
        resultado.setTotalItensUnimedComSucesso(resultado.getTotalItensUnimedProcessados() - resultado.getTotalItensUnimedComDivergencia());
        resultado.setItensDivergenteOrigemUnimed(itensDivergenteOrigemUnimed);

        resultado.setTotalItensConcentProcessados((long) itensConcent.size());
        resultado.setTotalItensConcentComDivergencia((long) itensDivergenteOrigemConcent.size());
        resultado.setTotalItensConcentComSucesso(resultado.getTotalItensConcentProcessados() - resultado.getTotalItensConcentComDivergencia());
        resultado.setItensDivergenteOrigemConcent(itensDivergenteOrigemConcent);

        return resultado;
    }

    private List<Item> getItemsDivergente(List<Item> itensUnimed, List<Item> itensConcent, boolean isOrigemUnimed, boolean isOrigemConcent) {
        List<Item> itensDivergente = new ArrayList<>();
        if (isOrigemUnimed) {
            itensDivergente = getItemsDivergente(itensUnimed, itensConcent);
        } else if (isOrigemConcent) {
            itensDivergente = getItemsDivergente(itensConcent, itensUnimed);
        }
        return itensDivergente;
    }

    private List<Item> getItemsDivergente(List<Item> itensOrigem, List<Item> itensConsulta) {
        List<Item> itens = new ArrayList<>();
        for (Item itemOrigem : itensOrigem) {
            Item itemConsultaPorChaveItemOrigem = itensConsulta.stream()
                    .filter(itemConsulta -> itemConsulta.getCodigo().equals(itemOrigem.getCodigo())
                            && itemConsulta.getGuia().equals(itemOrigem.getGuia()))
                    .findFirst().orElse(null);
            if (itemConsultaPorChaveItemOrigem == null) {
                itens.add(itemOrigem);
            }
        }
        return itens;
    }

    private List<Item> getItems(MultipartFile file, boolean isUnimed, boolean isConcent) {
        List<Item> itens = new ArrayList<>();
        if (ExcelUploadService.isValidExcelFile(file)) {
            try {
                if (isUnimed) {
                    itens = ExcelUploadService.getItensDataFromExcelUnimed(file.getInputStream());
                } else if (isConcent) {
                    itens = ExcelUploadService.getItensDataFromExcelConcent(file.getInputStream());
                }
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel");
            }
        }
        return itens;
    }
}
