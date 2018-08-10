package control.inventory.br.inventorycontrol.Models;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import control.inventory.br.inventorycontrol.Infra.Database;

/**
 * Created by biafo on 10/08/2018.
 */

public class LoginDAO {

    public void save(Login login){
        SQLiteDatabase conn= Database.getInstance().getWritableDatabase(); //consegue escrever no bd, pegando o objeto do banco.

        ContentValues values = new ContentValues();
        values.put("email", login.getEmail());
        values.put("senha", login.getPassword());

        if(login.getID()==null){
            conn.insert("login",null,values); //tabela e valores para inserir.
        }else{
            conn.update("login",values, "id = ?", new String[]{login.getID().toString()});
        }
    }

}
