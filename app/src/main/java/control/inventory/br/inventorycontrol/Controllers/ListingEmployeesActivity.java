package control.inventory.br.inventorycontrol.Controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import control.inventory.br.inventorycontrol.Controllers.Adapters.EmployeeAdapter;
import control.inventory.br.inventorycontrol.Models.DAO.EmployeeDAO;
import control.inventory.br.inventorycontrol.Models.Employee;
import control.inventory.br.inventorycontrol.R;

public class ListingEmployeesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_employees);

        ListView listView = findViewById(R.id.listEmployee);

        ArrayList<Employee> arrayList = new EmployeeDAO().listar();

        if(arrayList.size()==0){

            AlertDialog builder = new AlertDialog.Builder(this)
                    .setTitle("Nada cadastrado")
                    .setMessage("Não foi cadastrado nenhum funcionário !")
                    .setNeutralButton("OK",null)
                    .show();

            builder.show();

        }else {
            EmployeeAdapter employeeAdapter = new EmployeeAdapter(new EmployeeDAO().listar(), this);
            listView.setAdapter(employeeAdapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Employee e = (Employee) adapterView.getItemAtPosition(position);
                Intent it = new Intent(ListingEmployeesActivity.this,RegisterLoginsActivity.class);
                it.putExtra("employee", e);
                startActivity(it);
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Employee employee = (Employee) adapterView.getItemAtPosition(position);
                Alert(employee,(EmployeeAdapter) adapterView.getAdapter());
                return false;
            }
        });

    }

    private void Alert(final Employee employee, final EmployeeAdapter adapterView){
        AlertDialog.Builder builder= new AlertDialog.Builder( this)
                .setTitle("Remover ")
                .setMessage(" Deseja remover o funcionário ?")
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                try {
                    new EmployeeDAO().Delete(employee);
                    adapterView.DeleteEmployee(employee);
                    adapterView.notifyDataSetChanged();
                    Toast.makeText(ListingEmployeesActivity.this, "Removido !", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(ListingEmployeesActivity.this,"Erro ao excluir funcionário",Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.show();

    }


}


