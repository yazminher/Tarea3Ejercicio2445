package com.example.tarea3ejercicio2445;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tarea3ejercicio2445.R;
import com.example.tarea3ejercicio2445.datos.conexion;
import com.example.tarea3ejercicio2445.datos.consulta;

public class ActivityModificar extends AppCompatActivity {
    private EditText text_nombre,text_apellido,text_edad,text_correo,text_direccion,text_buscar;
    private int turno=1;
    private conexion conectar;
    private String indice;
    private Button borrar,cargar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        text_nombre=(EditText) findViewById(R.id.txt_nombre2);
        text_apellido=(EditText) findViewById(R.id.txt_apellido2);
        text_edad=(EditText) findViewById(R.id.txt_edad2);
        text_correo=(EditText) findViewById(R.id.txt_correo2);
        text_buscar=(EditText) findViewById(R.id.buscar_correo);
        text_direccion=(EditText) findViewById(R.id.txt_direccion2);
        conectar=new conexion(this, consulta.DataBase,null,1);
        borrar=(Button) findViewById(R.id.bt_eliminar);
        cargar=(Button) findViewById(R.id.bt_actualizar);
    }
    public boolean verificar(String dato,int numero){
        String opcion1="[A-Z,Á,É,Í,Ó,Ú,Ñ][a-z,á,é,í,ó,ú,ñ]{1,20}";
        String opcion2="[A-Z,Á,É,Í,Ó,Ú,Ñ][a-z,á,é,í,ó,ú,ñ]{1,20}[ ][A-Z,Á,É,Í,Ó,Ú,Ñ][a-z,á,é,í,ó,ú,ñ]{1,20}";
        String opcion3="[A-Z,Á,É,Í,Ó,Ú,Ñ][a-z,á,é,í,ó,ú,ñ]{1,20}[ ][A-Z,Á,É,Í,Ó,Ú,Ñ][a-z,á,é,í,ó,ú,ñ]{1,20}[ ][A-Z,Á,É,Í,Ó,Ú,Ñ][a-z,á,é,í,ó,ú,ñ]{1,20}";
        String opcion4="[A-Z,Á,É,Í,Ó,Ú,Ñ][a-z,á,é,í,ó,ú,ñ]{1,20}[ ][A-Z,Á,É,Í,Ó,Ú,Ñ][a-z,á,é,í,ó,ú,ñ]{1,20}[ ][A-Z,Á,É,Í,Ó,Ú,Ñ][a-z,á,é,í,ó,ú,ñ]{1,20}[ ][A-Z,Á,É,Í,Ó,Ú,Ñ][a-z,á,é,í,ó,ú,ñ]{1,20}";
        String opcion5="[0-9]{1,3}";
        String opcion6="[a-z,0-9]{1,100}[@][a-z,_,-]{1,100}[.][a-z]{1,10}";
        String opcion7="[a-z,0-9]{1,100}[.,_,-][a-z,0-9]{1,100}[@][a-z,_,-]{1,100}[.][a-z]{1,10}";
        String opcion8="[a-z,0-9]{1,100}[.,_,-][a-z,0-9]{1,100}[.,_,-][a-z,0-9]{1,100}[@][a-z,_,-]{1,100}[.][a-z]{1,10}";
        String opcion9="[a-z,0-9]{1,100}[.,_,-][a-z,0-9]{1,100}[.,_,-][a-z,0-9]{1,100}[.,_,-][a-z,0-9]{1,100}[@][a-z,_,-]{1,100}[.][a-z]{1,10}";
        String opcion10="[A-Z,Á,É,Í,Ó,Ú,Ñ,á,é,í,ó,ú,ñ,' ',a-z]{1,200}";
        switch(numero){
            case 1:{return dato.matches(opcion1+"|"+opcion2+"|"+opcion3+"|"+opcion4);}
            case 2:{return dato.matches(opcion1+"|"+opcion2+"|"+opcion3+"|"+opcion4);}
            case 3:{return dato.matches(opcion5);}
            case 4:{return dato.matches(opcion6+"|"+opcion7+"|"+opcion8+"|"+opcion9);}
            case 5:{return dato.matches(opcion10);}
            default:{return false;}
        }
    }
    public void validar_buscar(View view){
        turno=4;
        if(verificar(text_buscar.getText().toString().trim(),turno)){
            turno=1;
            buscar();
        }
        else{
            turno=1;
            Toast.makeText(this,"Correo no valido",Toast.LENGTH_LONG).show();
        }
    }
    public void validar2(View view){
        if(verificar(text_nombre.getText().toString().trim(),turno)){
            turno=2;
            if(verificar(text_apellido.getText().toString().trim(),turno)){
                turno=3;
                if(verificar(text_edad.getText().toString().trim(),turno)){
                    turno=4;
                    if(verificar(text_correo.getText().toString().trim(),turno)){
                        if(correo(text_correo.getText().toString().trim())){
                            turno=5;
                            if(verificar(text_direccion.getText().toString().trim(),turno)){
                                turno=1;
                                actializar();
                            }
                            else{
                                Toast.makeText(this,"Direccion no valido",Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            turno=1;
                            Toast.makeText(this,"Correo ya registrado",Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        turno=1;
                        Toast.makeText(this,"Correo no valido",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    turno=1;
                    Toast.makeText(this,"Edad no valido",Toast.LENGTH_LONG).show();
                }
            }
            else{
                turno=1;
                Toast.makeText(this,"Apellido no valido",Toast.LENGTH_LONG).show();
            }
        }
        else{
            turno=1;
            Toast.makeText(this,"Nombre no valido",Toast.LENGTH_LONG).show();
        }
    }
    public void buscar(){
        try{
            SQLiteDatabase db= conectar.getWritableDatabase();
            String[] parametro={text_buscar.getText().toString()};
            String[] folders={consulta.id,
                    consulta.nombre,
                    consulta.apellido,
                    consulta.edad,
                    consulta.correo,
                    consulta.direccion
            };
            String condicion=consulta.correo+"=?";
            Cursor data=db.query(consulta.persona,folders,condicion,parametro,null,null,null);
            data.moveToFirst();
            if(data.getCount()>0){
                indice=data.getString(0);
                text_nombre.setText(data.getString(1));
                text_apellido.setText(data.getString(2));
                text_edad.setText(data.getString(3));
                text_correo.setText(data.getString(4));
                text_direccion.setText(data.getString(5));
                Toast.makeText(getApplicationContext(),"Se encontro este resultado.",Toast.LENGTH_LONG).show();
                text_nombre.setEnabled(true);
                text_apellido.setEnabled(true);
                text_edad.setEnabled(true);
                text_correo.setEnabled(true);
                text_direccion.setEnabled(true);
                cargar.setEnabled(true);
                borrar.setEnabled(true);
            }
            else{
                Toast.makeText(getApplicationContext(),"Correo no registrado.",Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),Toast.LENGTH_LONG).show();
        }
    }
    public void eliminar(View view){
        SQLiteDatabase db= conectar.getWritableDatabase();
        if(db!=null){
            db.execSQL("DELETE FROM "+ consulta.persona+
                    " WHERE "+consulta.id+"="+Integer.parseInt(indice));
            db.close();
            Toast.makeText(getApplicationContext(),"Se elimino el registro.",Toast.LENGTH_LONG).show();
            desactivar();
            limpiar();
        }
        else{
            Toast.makeText(getApplicationContext(),"Error al eliminar",Toast.LENGTH_LONG).show();
        }
    }
    public void actializar(){
        SQLiteDatabase db= conectar.getWritableDatabase();
        if(db!=null){
            db.execSQL("UPDATE "+consulta.persona+" SET "+
                    consulta.nombre+"='"+text_nombre.getText().toString()+"', "+
                    consulta.apellido+"='"+text_apellido.getText().toString()+"', "+
                    consulta.edad+"='"+text_edad.getText().toString()+"', "+
                    consulta.correo+"='"+text_correo.getText().toString()+"', "+
                    consulta.direccion+"='"+text_direccion.getText().toString()+"' "+
                    "WHERE "+consulta.id+"="+Integer.parseInt(indice));
            db.close();
            Toast.makeText(getApplicationContext(),"Se actualizo los datos.",Toast.LENGTH_LONG).show();
            desactivar();
            limpiar();
        }
        else{
            Toast.makeText(getApplicationContext(),"Error en actualizar",Toast.LENGTH_LONG).show();
            desactivar();
        }
    }
    public boolean correo(String dato) {
        if ((text_buscar.getText().toString().trim()).equals(dato)) {
            return true;
        } else {
            try {
                SQLiteDatabase db = conectar.getWritableDatabase();
                String[] parametro = {dato};
                String[] folders = {
                        consulta.correo,
                };
                String condicion = consulta.correo + "=?";
                Cursor data = db.query(consulta.persona, folders, condicion, parametro, null, null, null);
                data.moveToFirst();
                if (data.getCount() > 0) {
                    db.close();
                    return false;
                }
                else {
                    db.close();
                    return true;
                }
            }
            catch (Exception ex) {
            Toast.makeText(this, "Intentalo más tarde", Toast.LENGTH_LONG).show();
            }
            return false;
        }
    }
    public void limpiar(){
        text_nombre.setText("");
        text_apellido.setText("");
        text_edad.setText("");
        text_correo.setText("");
        text_direccion.setText("");
        text_buscar.setText("");
    }
    public void crear(View view){
        limpiar();
        desactivar();
        Intent agregar=new Intent(this,MainActivity.class);
        startActivity(agregar);
    }
    public void desactivar(){
        text_nombre.setEnabled(false);
        text_apellido.setEnabled(false);
        text_edad.setEnabled(false);
        text_correo.setEnabled(false);
        text_direccion.setEnabled(false);
        cargar.setEnabled(false);
        borrar.setEnabled(false);
    }
}