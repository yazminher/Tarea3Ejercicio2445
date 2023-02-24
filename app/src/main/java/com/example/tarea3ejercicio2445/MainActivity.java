package com.example.tarea3ejercicio2445;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tarea3ejercicio2445.R;
import com.example.tarea3ejercicio2445.datos.conexion;
import com.example.tarea3ejercicio2445.datos.consulta;

public class MainActivity extends AppCompatActivity {
    private EditText text_nombre,text_apellido,text_edad,text_correo,text_direccion;
    private int turno=1;
    private conexion conectar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_nombre=(EditText) findViewById(R.id.txt_nombre);
        text_apellido=(EditText) findViewById(R.id.txt_apellido);
        text_edad=(EditText) findViewById(R.id.txt_edad);
        text_correo=(EditText) findViewById(R.id.txt_correo);
        text_direccion=(EditText) findViewById(R.id.txt_direccion);
        conectar=new conexion(this, consulta.DataBase,null,1);
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
    public void validar(View view){
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
                                crear();
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
    public void crear(){
        SQLiteDatabase db=conectar.getWritableDatabase();
        ContentValues valores=new ContentValues();
        valores.put(consulta.nombre,text_nombre.getText().toString());
        valores.put(consulta.apellido,text_apellido.getText().toString());
        valores.put(consulta.edad,text_edad.getText().toString());
        valores.put(consulta.correo,text_correo.getText().toString());
        valores.put(consulta.direccion,text_direccion.getText().toString());
        Long resultado=db.insert(consulta.persona,consulta.id,valores);
        Toast.makeText(getApplicationContext(),"Registro guardado",Toast.LENGTH_LONG).show();
        db.close();
        limpiar();
    }
    public boolean correo(String dato){
        try{
            SQLiteDatabase db= conectar.getWritableDatabase();
            String[] parametro={dato};
            String[] folders={
                    consulta.correo,
            };
            String condicion=consulta.correo+"=?";
            Cursor data=db.query(consulta.persona,folders,condicion,parametro,null,null,null);
            data.moveToFirst();
            if(data.getCount()>0){
                db.close();
                return false;
            }
            else{
                db.close();
                return true;
            }
        }
        catch (Exception ex){
            Toast.makeText(this,"Intentalo más tarde",Toast.LENGTH_LONG).show();
        }
        return false;
    }
    public void limpiar(){
        text_nombre.setText("");
        text_apellido.setText("");
        text_edad.setText("");
        text_correo.setText("");
        text_direccion.setText("");
        Intent modificar=new Intent(this,ActivityModificar.class);
        startActivity(modificar);
    }
    public void volver(View view){
        limpiar();
    }
}