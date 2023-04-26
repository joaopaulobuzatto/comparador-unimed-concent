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

    public Response comparaUnimedConcent(MultipartFile fileUnimed, MultipartFile fileConcent) {
        log.info("proccess files");
        List<Item> itensUnimed = getItensFromRelatorio(fileUnimed, true, false, false);
        List<Item> itensConcent = getItensFromRelatorio(fileConcent, false, true, false);

        List<Item> itensDivergenteOrigemUnimed = getItensDivergente(itensUnimed, itensConcent, true);
        List<Item> itensDivergenteOrigemConcent = getItensDivergente(itensUnimed, itensConcent, false);

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

    public Response comparaUnimedNetris(MultipartFile fileUnimed, MultipartFile fileNetRis) {
        log.info("compara unimed netris");
        List<Item> itensUnimed = getItensFromRelatorio(fileUnimed, true, false, false);
        List<Item> itensNetRis = getItensFromRelatorio(fileNetRis, false, false, true);

        List<Item> itensDivergenteOrigemUnimed = getItensDivergente(itensUnimed, itensNetRis, true);
        List<Item> itensDivergenteOrigemNetRis = getItensDivergente(itensUnimed, itensNetRis, false);

        Response response = new Response();
        response.setTotalItensUnimedProcessados((long) itensUnimed.size());
        response.setTotalItensUnimedComDivergencia((long) itensDivergenteOrigemUnimed.size());
        response.setTotalItensUnimedComSucesso(response.getTotalItensUnimedProcessados() - response.getTotalItensUnimedComDivergencia());
        response.setItensDivergenteOrigemUnimed(itensDivergenteOrigemUnimed);

        response.setTotalItensNetRisProcessados((long) itensNetRis.size());
        response.setTotalItensNetRisComDivergencia((long) itensDivergenteOrigemNetRis.size());
        response.setTotalItensNetRisComSucesso(response.getTotalItensConcentProcessados() - response.getTotalItensConcentComDivergencia());
        response.setItensDivergenteOrigemNetRis(itensDivergenteOrigemNetRis);

        return response;
    }

    private List<Item> getItensDivergente(List<Item> itensUnimed, List<Item> itensComparacao, boolean isOrigemUnimed) {
        log.info("get itens divergente");
        return isOrigemUnimed
                ? getItensDivergente(itensUnimed, itensComparacao)
                : getItensDivergente(itensComparacao, itensUnimed);
    }

    public List<Item> getItensDivergente(List<Item> itensOrigem, List<Item> itensComparacao) {
        log.info("get itens divergente");
        List<Item> itens = new ArrayList<>();
        for (Item itemOrigem : itensOrigem) {
            if (CODIGOS_OBRIGATORIO_CONFERENCIA_MANUAL.contains(itemOrigem.getCodigo())) {
                itens.add(itemOrigem);
            } else {
                boolean itemOrigemExisteNosItensConsulta = itensComparacao.stream()
                        .anyMatch(itemConsulta -> itemConsulta.getCodigo().equals(itemOrigem.getCodigo())
                                && itemConsulta.getGuia().equals(itemOrigem.getGuia()));

                if (!itemOrigemExisteNosItensConsulta) {
                    for (String variacao : Exame.getVariacoesByCodigo(itemOrigem.getCodigo())) {
                        if (!itemOrigemExisteNosItensConsulta) {
                            itemOrigemExisteNosItensConsulta = itensComparacao.stream()
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

    private List<Item> getItensFromRelatorio(MultipartFile file, boolean isUnimed, boolean isConcent, boolean isNetRis) {
        log.info("get itens");
        List<Item> itens = new ArrayList<>();
        if (ExcelUploadService.isValidExcelFile(file)) {
            try {
                if (isUnimed) {
                    itens = ExcelUploadService.getItensFromRelatorioUnimed(file.getInputStream());
                } else if (isConcent) {
                    itens = ExcelUploadService.getItensFromRelatorioConcent(file.getInputStream());
                } else if (isNetRis) {
                    itens = ExcelUploadService.getItensFromRelatorioNetRis(file.getInputStream());
                }
            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel");
            }
        }
        return itens;
    }
}
