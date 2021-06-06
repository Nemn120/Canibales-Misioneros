import java.util.Objects;

public class Nodo {

    private Integer misioneros;
    private Integer canibales;
    private Integer posicion;

    public Nodo(Integer misioneros, Integer canibales, Integer posicion) {
        this.misioneros = misioneros;
        this.canibales = canibales;
        this.posicion = posicion;
    }

    public Nodo(Nodo nodo){
        this.misioneros = nodo.getMisioneros();
        this.canibales = nodo.getCanibales();
        this.posicion = nodo.getPosicion();
    }

    public Integer getMisioneros() {
        return misioneros;
    }

    public void setMisioneros(Integer misioneros) {
        this.misioneros = misioneros;
    }

    public Integer getCanibales() {
        return canibales;
    }

    public void setCanibales(Integer canibales) {
        this.canibales = canibales;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void imprimirPosicion(){
        System.out.println("("+misioneros+" | "+ canibales +" | "+posicion+")");
    }

    public Nodo agregarValores(Integer misioneros, Integer canivales, Integer posicion){
        this.misioneros+=misioneros;
        this.canibales +=canivales;
        this.posicion = posicion;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nodo nodo = (Nodo) o;
        return Objects.equals(misioneros, nodo.misioneros) && Objects.equals(canibales, nodo.canibales) && Objects.equals(posicion, nodo.posicion);
    }

    public boolean esMeta(){
        return this.posicion == 0 && this.getCanibales() == 0 && this.getMisioneros() == 0;
    }
}
