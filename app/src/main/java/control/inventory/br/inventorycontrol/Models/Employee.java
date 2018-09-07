package control.inventory.br.inventorycontrol.Models;

import java.io.Serializable;

/**
 * Created by biafo on 11/08/2018.
 */

public class Employee implements Serializable {

    private Integer ID;
    private String Nome;
    private String Cracha;
    private String CPF;

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCracha() {
        return Cracha;
    }

    public void setCracha(String cracha) {
        Cracha = cracha;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
}

