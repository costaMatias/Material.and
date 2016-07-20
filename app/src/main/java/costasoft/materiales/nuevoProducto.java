package costasoft.materiales;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class nuevoProducto extends AppCompatActivity {
    private EditText et1,et2,et3;
    private Button btn_volver,btn_alta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_producto);

        btn_alta = (Button)findViewById(R.id.btn_alta);
        btn_volver = (Button)findViewById(R.id.btn_volver);
        et1=(EditText)findViewById(R.id.editText);
        et2=(EditText)findViewById(R.id.editText2);
        et3=(EditText)findViewById(R.id.editText3);
    }

    public void volver(View view){
        Intent backView = new Intent(this, MainActivity.class);
        startActivity(backView);
        this.finish();
    }

    // -- Dialogo de insercción exitosa
    public AlertDialog Dialog_insertExitoso(){
        AlertDialog.Builder cuerpo = new AlertDialog.Builder(this);
        cuerpo.setTitle("ÉXITO");
        cuerpo.setMessage("El producto se cargó correctamente");
        cuerpo.setPositiveButton("ENTENDIDO", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                nuevoProducto.this.finish();
            }
        });
        return cuerpo.show();
    }
    // -- Operación de añadir un producto en la base de datos
    public void alta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        String descri = et2.getText().toString();
        String pre = et3.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo", cod);
        registro.put("descripcion", descri);
        registro.put("precio", pre);
        bd.insert("articulos", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        //Toast.makeText(this, "Se cargaron los datos del artículo",                Toast.LENGTH_SHORT).show();
        Dialog_insertExitoso();

    }

}
