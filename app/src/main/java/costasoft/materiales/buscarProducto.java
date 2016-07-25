package costasoft.materiales;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class buscarProducto extends AppCompatActivity {
    private EditText datoBuscar;
    private Button btnVolver,btnBuscar;
    public static String buscar = "dato";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_producto);

        datoBuscar = (EditText)findViewById(R.id.detalle);
        btnVolver = (Button)findViewById(R.id.btn_volver);
        btnBuscar = (Button)findViewById(R.id.btn_buscar);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void irResultados(View v) {
        Intent i = new Intent(this,resulBusqueda.class);
        i.putExtra(buscar, datoBuscar.getText().toString());
        startActivity(i);
    }
    public void volver(View view){
        Intent backView = new Intent(this, MainActivity.class);
        startActivity(backView);
        this.finish();
    }

}
