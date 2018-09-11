package control.inventory.br.inventorycontrol.Models;

/**
 * Created by biafo on 10/08/2018.
 */

public class Login {

    private Integer ID;
    private String email;
    private String password;
    private Integer funcionarioID;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getFuncionarioID() {
        return funcionarioID;
    }

    public void setFuncionarioID(Integer funcionarioID) {
        this.funcionarioID = funcionarioID;
    }
}
