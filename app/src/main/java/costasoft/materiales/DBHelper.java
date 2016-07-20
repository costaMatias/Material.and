package costasoft.materiales;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by matia on 19/07/2016.
 */
public class DBHelper {
    private static final String TAG = "DBHelper";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "materiales.db";
    private static final String MATERIALES_TABLE_NAME = "material";



    public static final class Materiales implements BaseColumns {
        private Materiales(long aLong, String string, String cursorString, float aFloat){}
        private static final String ID = "id";
        private static final String DETALLE = "detalle";
        private static final String MARCA = "marca";
        private static final String PRECIO = "precio";
    }

    private Context context;
    private SQLiteDatabase db;
    private MyDBOpenHelper openHelper;

    public DBHelper(Context context) {
        this.context = context;
        this.openHelper = new MyDBOpenHelper(this.context);
    }

    public DBHelper open(){
        this.db = openHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        this.db.close();
    }

    private static class MyDBOpenHelper extends SQLiteOpenHelper {
        MyDBOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + MATERIALES_TABLE_NAME + " ("
                    + Materiales._ID + "INTEGER PRIMARY KEY AUTO_INCREMENT,"
                    + Materiales.DETALLE + "TEXT,"
                    + Materiales.MARCA + "TEXT,"
                    + Materiales.PRECIO + "FLOAT"
                    + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Actualizando base de datos desde versión " + oldVersion + " a la versión "
                    + newVersion + ", se eliminarán todos los datos de la versión anterior");
            db.execSQL("DROP TABLE IF EXISTS " + MATERIALES_TABLE_NAME);
            onCreate(db);
        }
    }

        public Materiales seleccionarMaterial(long id){
            Materiales material = null;
            Cursor cursor = db.query(MATERIALES_TABLE_NAME,
                    null, Materiales._ID+"=?", new String[] {Long.toString(id)},
                    null,null,null);
            cursor.moveToFirst();
            material = new Materiales(cursor.getLong(0),cursor.getString(1),
                    cursor.getString(2),cursor.getFloat(3));
            return material;
        }

        public ArrayList<material> buscarTodosMateriales(){
            ArrayList<material> list = new ArrayList<material>();
            Cursor cursor = this.db.query(MATERIALES_TABLE_NAME,
                    null, null, null, null, null, Materiales.DETALLE+ "ASC");
            if(cursor.moveToFirst()){
                do{
                    material producto = new material(cursor.getString(1),
                            cursor.getString(2),cursor.getFloat(3));
                    list.add(producto);
                }while (cursor.moveToNext());
            }
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }

            return list;
        }

        public long insertarProducto(material producto){
            ContentValues values = new ContentValues();
            values.put(Materiales.DETALLE, producto.getDetalle());
            values.put(Materiales.MARCA, producto.getMarca());
            values.put(Materiales.PRECIO, producto.getPrecio());
            long id = db.insert(MATERIALES_TABLE_NAME, null,values);
            return id;
        }

        public void actualizarProductos(material producto){
            ContentValues valores = new ContentValues();
            valores.put(Materiales.DETALLE, producto.getDetalle());
            valores.put(Materiales.MARCA, producto.getMarca());
            valores.put(Materiales.PRECIO, producto.getPrecio());
            db.update(MATERIALES_TABLE_NAME, valores, Materiales._ID+"=?", new String[] {Long.toString(producto.getId())});
        }

        public void eliminarProducto(material producto){
            db.delete(MATERIALES_TABLE_NAME, Materiales._ID+"=?", new String[] {Long.toString(producto.getId())});;
        }

}
