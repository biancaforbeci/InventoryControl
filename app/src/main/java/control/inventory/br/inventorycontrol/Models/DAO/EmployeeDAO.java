package control.inventory.br.inventorycontrol.Models.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import control.inventory.br.inventorycontrol.Infra.Database;
import control.inventory.br.inventorycontrol.Models.Employee;
import control.inventory.br.inventorycontrol.Models.Login;

/**
 * Created by biafo on 11/08/2018.
 */

public class EmployeeDAO {

    public static void Save(Employee employee){
        SQLiteDatabase conn= Database.getInstance().getWritableDatabase(); //consegue escrever no bd, pegando o objeto do banco.

        ContentValues values = new ContentValues();
        values.put("nome", employee.getNome());
        values.put("cracha", employee.getCracha());
        values.put("cpf", employee.getCPF());

        if(employee.getID()==null){
            conn.insert("employees",null,values); //tabela e valores para inserir.
        }else{
            conn.update("employees",values, "id = ?", new String[]{employee.getID().toString()});
        }
    }


    public static Employee SearchCPF(String CPF){
            Employee employee= new Employee();

            SQLiteDatabase db = Database.getInstance().getReadableDatabase();

            Cursor cursor = db.query("employees", new String[]{"id", "nome",
                    "cracha", "cpf"}, "cpf" + "= ? ", new String[]{CPF}, null, null, null, null);

            if(cursor!=null){
                cursor.moveToFirst();
                if (cursor.getCount() > 0) {
                    employee.setID(cursor.getInt(0));
                    employee.setNome(cursor.getString(1));
                    employee.setCracha(cursor.getString(2));
                    employee.setCPF(cursor.getString(3));
                } else {
                    return null;
                }
            }
            db.close();
            return employee;
    }


    public ArrayList<Employee> listar(){
        SQLiteDatabase conn = Database.getInstance().getReadableDatabase();
        Cursor c = conn.query("employees", new String[]{"id","nome","cracha","cpf"},null,null,null,null,"nome");
        ArrayList<Employee> employees= new ArrayList<Employee>();
        if(c.moveToFirst()){ //move para o primeiro da lista
            do{
                Employee employee = new Employee();
                employee.setID(c.getInt(0));
                employee.setNome(c.getString(1));
                employee.setCracha(c.getString(2));
                employee.setCPF(c.getString(3));
                employees.add(employee);
            }while(c.moveToNext());
        }
        return employees;
    }

    public static Employee SearchID(Integer id){
        Employee employee= new Employee();

        SQLiteDatabase db = Database.getInstance().getReadableDatabase();

        Cursor cursor = db.query("employees", new String[]{"id", "nome",
                "cracha", "cpf"}, "id" + "= ? ", new String[]{id.toString()}, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                employee.setID(cursor.getInt(0));
                employee.setNome(cursor.getString(1));
                employee.setCracha(cursor.getString(2));
                employee.setCPF(cursor.getString(3));
            } else {
                return null;
            }
        }
        db.close();
        return employee;
    }

    public static void Delete(Employee employee){
        Login login = LoginDAO.searchEmployeeLogin(employee);
        LoginDAO.Delete(login);
        SQLiteDatabase db = Database.getInstance().getWritableDatabase();
        db.delete("employees","id = ?", new String[]{employee.getID().toString()});
    }
}

