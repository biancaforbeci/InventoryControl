package control.inventory.br.inventorycontrol.Models.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import control.inventory.br.inventorycontrol.Infra.Database;
import control.inventory.br.inventorycontrol.Models.Employee;
import control.inventory.br.inventorycontrol.Models.Login;

/**
 * Created by biafo on 10/08/2018.
 */

public class LoginDAO {

    public static void save(Login login) {
        SQLiteDatabase conn = Database.getInstance().getWritableDatabase(); //consegue escrever no bd, pegando o objeto do banco.

        ContentValues values = new ContentValues();
        values.put("email", login.getEmail());
        values.put("senha", login.getPassword());
        values.put("funcionarioID", login.getFuncionarioID());

        if (login.getID() == null) {
            conn.insert("login", null, values); //tabela e valores para inserir.
        } else {
            conn.update("login", values, "id = ?", new String[]{login.getID().toString()});
        }
    }

    public static Login searchLogin(String email) {
        SQLiteDatabase conn = Database.getInstance().getReadableDatabase();
        Login login = new Login();
        Cursor cursor = conn.query("login", new String[]{"id", "email", "senha", "funcionarioID"}, "email" + "= ?", new String[]{email.trim()}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                login.setID(cursor.getInt(0));
                login.setEmail(cursor.getString(1));
                login.setPassword(cursor.getString(2));
                login.setFuncionarioID(cursor.getInt(3));
            } else {
                return null;
            }
        }

        return login;
    }

    public static Login SearchID(Integer id){
        Login login = new Login();

        SQLiteDatabase db = Database.getInstance().getReadableDatabase();

        Cursor cursor = db.query("login", new String[]{"id", "email",
                "senha", "funcionarioID"}, "funcionarioID" + "= ? ", new String[]{id.toString()}, null, null, "id");

        if(cursor!=null){
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                login.setID(cursor.getInt(0));
                login.setEmail(cursor.getString(1));
                login.setPassword(cursor.getString(2));
                login.setFuncionarioID(cursor.getInt(3));
            } else {
                return null;
            }
        }
        db.close();
        return login;
    }

    public static Login searchEmployeeLogin(Employee employee) {
        SQLiteDatabase conn = Database.getInstance().getReadableDatabase();
        Login login = new Login();
        Cursor cursor = conn.query("login", new String[]{"id", "email", "senha", "funcionarioID"}, "funcionarioID" + "= ?", new String[]{employee.getID().toString()}, null, null, "id");

        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                login.setID(cursor.getInt(0));
                login.setEmail(cursor.getString(1));
                login.setPassword(cursor.getString(2));
                login.setFuncionarioID(cursor.getInt(3));
            } else {
                return null;
            }
        }

        return login;
    }

    public static void Delete(Login login){
        if(login != null){
            SQLiteDatabase db = Database.getInstance().getWritableDatabase();
            db.delete("login","id = ?", new String[]{login.getID().toString()});
        }
    }

}
