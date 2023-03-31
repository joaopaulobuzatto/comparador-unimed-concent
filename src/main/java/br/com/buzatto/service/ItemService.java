package br.com.buzatto.service;

import br.com.buzatto.enums.Exame;
import br.com.buzatto.model.Item;
import br.com.buzatto.model.Response;
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

    private static final List<String> CODIGOS_OBRIGATORIO_CONFERENCIA_MANUAL = List.of("40307255", "40307263");

    public Response proccessFiles(MultipartFile fileUnimed, MultipartFile fileConcent) {
        log.info("proccess files");
        List<Item> itensUnimed = getItens(fileUnimed, true, false);
        List<Item> itensConcent = getItens(fileConcent, false, true);

        List<Item> itensDivergenteOrigemUnimed = getItensDivergente(itensUnimed, itensConcent, true, false);
        List<Item> itensDivergenteOrigemConcent = getItensDivergente(itensUnimed, itensConcent, false, true);

        Response response = new Response();
        response.setTotalItensUnimedProcessados((long) itensUnimed.size());
        response.setTotalItensUnimedComDivergencia((long) itensDivergenteOrigemUnimed.size());
        response.setTotalItensUnimedComSucesso(response.getTotalItensUnimedProcessados() - response.getTotalItensUnimedComDivergencia());
        response.setItensDivergenteOrigemUnimed(itensDivergenteOrigemUnimed);

        response.setTotalItensConcentProcessados((long) itensConcent.size());
        response.setTotalItensConcentComDivergencia((long) itensDivergenteOrigemConcent.size());
        response.setTotalItensConcentComSucesso(response.getTotalItensConcentProcessados() - response.getTotalItensConcentComDivergencia());
        response.setItensDivergenteOrigemConcent(itensDivergenteOrigemConcent);

        return response;
    }

    private List<Item> getItensDivergente(List<Item> itensUnimed, List<Item> itensConcent, boolean isOrigemUnimed, boolean isOrigemConcent) {
        log.info("get itens divergente");
        List<Item> itensDivergente = new ArrayList<>();
        if (isOrigemUnimed) {
            itensDivergente = getItensDivergente(itensUnimed, itensConcent);
        } else if (isOrigemConcent) {
            itensDivergente = getItensDivergente(itensConcent, itensUnimed);
        }
        return itensDivergente;
    }

    public List<Item> getItensDivergente(List<Item> itensOrigem, List<Item> itensConsulta) {
        log.info("get itens divergente");
        List<Item> itens = new ArrayList<>();
        for (Item itemOrigem : itensOrigem) {
            if (CODIGOS_OBRIGATORIO_CONFERENCIA_MANUAL.contains(itemOrigem.getCodigo())) {
                itens.add(itemOrigem);
            } else {
                boolean itemOrigemExisteNosItensConsulta = itensConsulta.stream()
                        .anyMatch(itemConsulta -> itemConsulta.getCodigo().equals(itemOrigem.getCodigo())
                                && itemConsulta.getGuia().equals(itemOrigem.getGuia()));

                if (!itemOrigemExisteNosItensConsulta) {
                    for (String variacao : Exame.getVariacoesByCodigo(itemOrigem.getCodigo())) {
                        if (!itemOrigemExisteNosItensConsulta) {
                            itemOrigemExisteNosItensConsulta = itensConsulta.stream()
                                    .anyMatch(itemConsulta -> itemConsulta.getCodigo().equals(variacao)
                                            && itemConsulta.getGuia().equals(itemOrigem.getGuia()));
                        }
                    }
                }

                if (!itemOrigemExisteNosItensConsulta) {
                    itens.add(itemOrigem);
                }
            }
        }
        return itens;
    }

    private List<Item> getItens(MultipartFile file, boolean isUnimed, boolean isConcent) {
        log.info("get itens");
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
