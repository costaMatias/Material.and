package costasoft.materiales;

/**
 * Created by matia on 19/07/2016.
 */
public class material {
    private long id;
    private String detalle;
    private String marca;
    private float precio;

    public material( String detalle, String marca, float precio) {
        this.id = 0;
        this.detalle = detalle;
        this.marca = marca;
        this.precio = precio;
    }

    public long getId() {
        return id;
    }

    public String getDetalle() {
        return detalle;
    }

    public String getMarca() {
        return marca;
    }

    public float getPrecio() {
        return precio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
