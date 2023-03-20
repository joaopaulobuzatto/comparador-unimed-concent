package br.com.buzatto.model;

import java.util.Objects;

public class Item {

    private Long requisicao;
    private Long guia;
    private Long carteirinha;
    private String beneficiario;
    private Long codigo;
    private String descricao;

    public Long getRequisicao() {
        return requisicao;
    }

    public void setRequisicao(Long requisicao) {
        this.requisicao = requisicao;
    }

    public Long getGuia() {
        return guia;
    }

    public void setGuia(Long guia) {
        this.guia = guia;
    }

    public Long getCarteirinha() {
        return carteirinha;
    }

    public void setCarteirinha(Long carteirinha) {
        this.carteirinha = carteirinha;
    }

    public String getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Item{" +
                "requisicao=" + requisicao +
                ", guia=" + guia +
                ", carteirinha=" + carteirinha +
                ", beneficiario='" + beneficiario + '\'' +
                ", codigo=" + codigo +
                ", descricao='" + descricao + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(guia, item.guia) && Objects.equals(codigo, item.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guia, codigo);
    }
}
