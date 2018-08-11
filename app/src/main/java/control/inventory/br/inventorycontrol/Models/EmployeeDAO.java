package control.inventory.br.inventorycontrol.Models;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import control.inventory.br.inventorycontrol.Infra.Database;

/**
 * Created by biafo on 11/08/2018.
 */

public class EmployeeDAO {
    public void save(Employee employee){
        SQLiteDatabase conn= Database.getInstance().getWritableDatabase(); //consegue escrever no bd, pegando o objeto do banco.

        ContentValues values = new ContentValues();
        values.put("nome", employee.getNome());
        values.put("cracha", employee.getCracha());
        values.put("cpf", employee.getCPF());

        if(employee.getID()==null){
            conn.insert("login",null,values); //tabela e valores para inserir.
        }else{
            conn.update("login",values, "id = ?", new String[]{employee.getID().toString()});
        }
    }
}
