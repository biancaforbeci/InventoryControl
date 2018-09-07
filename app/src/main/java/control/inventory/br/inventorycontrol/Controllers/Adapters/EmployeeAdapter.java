package control.inventory.br.inventorycontrol.Controllers.Adapters;

import android.app.Activity;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import control.inventory.br.inventorycontrol.Models.Employee;
import control.inventory.br.inventorycontrol.R;

public class EmployeeAdapter extends BaseAdapter {

    private List<Employee> employees;
    Activity act;


    public EmployeeAdapter(List<Employee> employees, Activity act){
        this.employees=employees;
        this.act=act;
    }

    @Override
    public int getCount() {
        return this.employees.size();
    }

    @Override
    public Object getItem(int position) {
        return this.employees.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View v = act.getLayoutInflater().inflate(R.layout.activity_listing,viewGroup,false);

        TextView  textView1 = v.findViewById(R.id.txtName);
        TextView textView2 = v.findViewById(R.id.txtCPF);
        TextView textView3 = v.findViewById(R.id.txtNumber);

        Employee e= employees.get(position);

        textView1.setText(e.getNome().toString());
        textView2.setText(e.getCPF().toString());
        textView3.setText(e.getCracha().toString());

        return v;
    }

    public void DeleteEmployee(Employee employee){
        this.employees.remove(employee);
    }
}


