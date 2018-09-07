package control.inventory.br.inventorycontrol.Infra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by biafo on 10/08/2018.
 */

public class Database  extends SQLiteOpenHelper{

    private static Database conexao;

    public static Database getInstance(){ //retorna a instancia do bd, tipo singleton.
        return conexao;
    }

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        conexao=this;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String statement = " create table product ( " +
                " id integer primary key autoincrement,"+
                " produto varchar(300),  " +
                " categoria  varchar(300), "  +
                " preco decimal(13,4)," +
                " localizacao varchar(300)," +
                " detalhes varchar(300)," +
                " quantidade varchar(300)," +
                " path varchar(600)" +
                ")";


        String logins = " create table login ( " +
                " id integer primary key autoincrement,"+
                " email varchar(70),  " +
                " senha varchar(10), "  +
                " funcionarioID integer , " +
                "FOREIGN KEY(funcionarioID) REFERENCES funcionarios(id)" +
                ")";

        String funcionarios = " create table funcionarios ( " +
                " id integer primary key autoincrement,"+
                " nome varchar(70),  " +
                " cracha varchar(10), "  +
                " cpf varchar(11)"  +
                ")";

        sqLiteDatabase.execSQL(statement);
        sqLiteDatabase.execSQL(logins);
        sqLiteDatabase.execSQL(funcionarios);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
