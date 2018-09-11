package control.inventory.br.inventorycontrol.Controllers;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import control.inventory.br.inventorycontrol.Models.Employee;
import control.inventory.br.inventorycontrol.Models.DAO.EmployeeDAO;
import control.inventory.br.inventorycontrol.Models.Login;
import control.inventory.br.inventorycontrol.Models.DAO.LoginDAO;
import control.inventory.br.inventorycontrol.R;

public class RegisterLoginsActivity extends AppCompatActivity {

    EditText Name;
    EditText Password;
    EditText Confirm;
    EditText Badge;
    EditText CPF;
    EditText Email;
    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_logins);

        Name=findViewById(R.id.txtName);
        Password=findViewById(R.id.txtPassword);
        Confirm=findViewById(R.id.txtConfirm);
        Badge=findViewById(R.id.txtNumber);
        CPF=findViewById(R.id.txtCPF);
        Email=findViewById(R.id.txtEmail);


        Intent it = getIntent();
        if(it!=null && it.hasExtra("employee")) {
            employee = (Employee) it.getSerializableExtra("employee");
            Name.setText(employee.getNome());
            CPF.setText(employee.getCPF());
            Badge.setText(employee.getCracha());

            Login login = LoginDAO.searchEmployeeLogin(employee);

            if (login != null) {
                Email.setText(login.getEmail());
                Password.setText(login.getPassword());
            } else {
                Toast.makeText(this, "Nada encontrado", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void Save(View view) {

        switch (validation()){
            case 1:
                AlertDialog builder= new AlertDialog.Builder(this)
                        .setTitle("Verifique campos")
                        .setMessage("Verifique se algum campo está vazio !")
                        .setNeutralButton("OK",null)
                        .show();
                break;

            case 2:
                AlertDialog b= new AlertDialog.Builder(this)
                        .setTitle("Verifique campos")
                        .setMessage("Verifique se as senhas coincidem e se algum campo está vazio !")
                        .setNeutralButton("OK",null)
                        .show();
                break;

            case 3:
                AlertDialog build= new AlertDialog.Builder(this)
                        .setTitle("CPF já cadastrado")
                        .setMessage("Esse CPF já possui um funcionário cadastrado !")
                        .setNeutralButton("OK",null)
                        .show();
                break;

            default:
                if(employee!=null){
                    SaveEmployee(employee);
                    SaveLogin(LoginDAO.searchEmployeeLogin(employee));
                }else{
                    SaveEmployee(new Employee());
                    SaveLogin(new Login());
                }

                Intent intent = new Intent(this,ListingEmployeesActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void SaveLogin(Login login){

            login.setEmail(Email.getText().toString());
            login.setPassword(Password.getText().toString());

            Employee e = EmployeeDAO.SearchCPF(CPF.getText().toString());
            if(e != null) {
                login.setFuncionarioID(e.getID());
                LoginDAO.save(login);
            }else{
                AlertDialog builder= new AlertDialog.Builder(this)
                        .setTitle("Ocorreu erro ao salvar !")
                        .setMessage("Erro ao salvar no banco funcionário ! Tente novamente.")
                        .setNeutralButton("OK",null)
                        .show();
            }

    }

    private void SaveEmployee(Employee employee){
        try{
            employee.setNome(Name.getText().toString().trim());
            employee.setCPF(CPF.getText().toString().trim());
            employee.setCracha(Badge.getText().toString().trim());

            EmployeeDAO.Save(employee);
            Toast.makeText(getApplicationContext(), "Salvo com sucesso! " , Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            AlertDialog builder= new AlertDialog.Builder(this)
                    .setTitle("Ocorreu erro ao salvar !")
                    .setMessage("Erro ao salvar no banco funcionário ! Tente novamente.")
                    .setNeutralButton("OK",null)
                    .show();
        }
    }

    private int validation(){

        Employee employee = EmployeeDAO.SearchCPF(CPF.getText().toString());

        if((Name.getText().toString().isEmpty()) || (CPF.getText().toString().isEmpty()) || (Email.getText().toString().isEmpty()) || (Password.getText().toString().isEmpty()) || (Confirm.getText().toString().isEmpty())){
            return  1;
        }else if(!(Password.getText().toString().equals(Confirm.getText().toString()))){
            return  2;
        }else if(employee != null){
            if(CPF.getText().toString().equals(employee.getCPF())) {
                return 3;
            }
        }

        return 4;
    }

}
