package control.inventory.br.inventorycontrol.Models;

/**
 * Created by biafo on 10/08/2018.
 */

public class Product {

    private Integer ID;
    private String Produto;
    private String Categoria;
    private double Preco;
    private String localizacao;
    private String Detalhes;
    private String Path;


    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getProduto() {
        return Produto;
    }

    public void setProduto(String produto) {
        Produto = produto;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public double getPreco() {
        return Preco;
    }

    public void setPreco(double preco) {
        Preco = preco;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDetalhes() {
        return Detalhes;
    }

    public void setDetalhes(String detalhes) {
        Detalhes = detalhes;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }
}
