package br.com.buzatto.service;

import br.com.buzatto.model.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    @DisplayName("Nao existe itens divergentes entre origem e consulta")
    void testGetItemsDivergente_whenNaoExisteItemsDivergentes() {
        List<Item> itensOrigem = new ArrayList<>();
        Item itemOrigem = new Item();
        itemOrigem.setRequisicao("111111111");
        itemOrigem.setGuia("111111111");
        itemOrigem.setCarteirinha("111111111111111");
        itemOrigem.setBeneficiario("Fulano");
        itemOrigem.setCodigo("40302075");
        itemOrigem.setDescricao("Exame x");
        itemOrigem.setQuantidade("1");
        itensOrigem.add(itemOrigem);

        List<Item> itensConsulta = new ArrayList<>();
        Item itemConsulta = new Item();
        itemConsulta.setRequisicao("111111111");
        itemConsulta.setGuia("111111111");
        itemConsulta.setCarteirinha("111111111111111");
        itemConsulta.setBeneficiario("Fulano");
        itemConsulta.setCodigo("40302733");
        itemConsulta.setDescricao("Exame x");
        itemConsulta.setQuantidade("1");
        itensConsulta.add(itemConsulta);

        List<Item> itensDivergentes = itemService.getItensDivergente(itensOrigem, itensConsulta);

        assertEquals(0, itensDivergentes.size());
    }

    @Test
    @DisplayName("Existe itens divergentes entre origem e consulta")
    void testGetItemsDivergente_whenExisteItemsDivergentes() {
        List<Item> itensOrigem = new ArrayList<>();
        Item itemOrigem = new Item();
        itemOrigem.setRequisicao("111111111");
        itemOrigem.setGuia("111111111");
        itemOrigem.setCarteirinha("111111111111111");
        itemOrigem.setBeneficiario("Fulano");
        itemOrigem.setCodigo("40302075");
        itemOrigem.setDescricao("Exame x");
        itemOrigem.setQuantidade("1");
        itensOrigem.add(itemOrigem);

        List<Item> itensConsulta = new ArrayList<>();
        Item itemConsulta = new Item();
        itemConsulta.setRequisicao("111111111");
        itemConsulta.setGuia("111111111");
        itemConsulta.setCarteirinha("111111111111111");
        itemConsulta.setBeneficiario("Fulano");
        itemConsulta.setCodigo("40316157");
        itemConsulta.setDescricao("Exame x");
        itemConsulta.setQuantidade("1");
        itensConsulta.add(itemConsulta);

        List<Item> itensDivergentes = itemService.getItensDivergente(itensOrigem, itensConsulta);

        assertNotEquals(0, itensDivergentes.size());
    }
}
