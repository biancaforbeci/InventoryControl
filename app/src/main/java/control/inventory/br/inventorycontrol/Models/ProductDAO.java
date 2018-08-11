package control.inventory.br.inventorycontrol.Models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import control.inventory.br.inventorycontrol.Infra.Database;

/**
 * Created by biafo on 10/08/2018.
 */

public class ProductDAO {

    public void save(Product product){
    SQLiteDatabase conn= Database.getInstance().getWritableDatabase(); //consegue escrever no bd, pegando o objeto do banco.

    ContentValues values = new ContentValues();
    values.put("produto", product.getProduto());
    values.put("categoria", product.getCategoria());
    values.put("preco", product.getPreco());
    values.put("localizacao", product.getLocalizacao());
    values.put("detalhes", product.getDetalhes());
    values.put("path", product.getPath());

    if(product.getID()==null){
        conn.insert("products",null,values); //tabela e valores para inserir.
    }else{
        conn.update("products",values, "id = ?", new String[]{product.getID().toString()});
    }
    }

    public ArrayList<Product> listing(){
        SQLiteDatabase conn = Database.getInstance().getReadableDatabase();
        Cursor c = conn.query("products", new String[]{"id"," produto","categoria","preco","localizacao","quantidade","detalhes"},null,null,null,null,"nome");
        ArrayList<Product> products= new ArrayList<Product>();
        if(c.moveToFirst()){ //move para o primeiro da lista
            do{
                Product p= new Product();
                p.setID(c.getInt(0));
                p.setProduto(c.getString(1));
                p.setCategoria(c.getString(2));
                p.setPreco(Integer.valueOf(c.getString(3)));
                p.setLocalizacao(c.getString(4));
                p.setQuantidade(Integer.valueOf(c.getString(4)));
                p.setDetalhes(c.getString(6));
                products.add(p);
            }while(c.moveToNext());
        }
        return products;
    }

    public void delete(Product product){
        SQLiteDatabase conn= Database.getInstance().getWritableDatabase();
        conn.delete("products","id = ?", new String[]{product.getID().toString()});
    }
}
