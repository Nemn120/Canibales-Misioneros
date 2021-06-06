import java.util.ArrayList;
import java.util.List;

public class EstadoCanibalMisionero implements Estado {

    private static final Integer POSICION_OESTE = 1;
    private static final Integer POSICION_ESTE = 0;

    private Nodo posicionActual;

    public EstadoCanibalMisionero(Nodo posicionActual) {
        this.posicionActual = posicionActual;
    }

    public EstadoCanibalMisionero(Integer cantidadMisionero, Integer cantidadCanibal, Integer posicion) {
        this.posicionActual = new Nodo(cantidadMisionero, cantidadCanibal, posicion);
    }

    @Override
    public boolean esMeta() {
        return this.posicionActual.esMeta();
    }

    @Override
    public boolean igual(Estado e) {
        return posicionActual.equals(((EstadoCanibalMisionero) e).getPosicionActual());
    }

    @Override
    public void mostrarEstado() {
        posicionActual.imprimirPosicion();
    }

    public Nodo getPosicionActual() {
        return posicionActual;
    }

    // Copia el Nodo y agrega las posiciones al array
    private void agregarEstado(Integer misionero, Integer canibal, Integer posicion, List<Estado> sucesores) {
        Nodo nodoCopia = new Nodo(posicionActual);
        nodoCopia.agregarValores(misionero, canibal, posicion);
        sucesores.add(new EstadoCanibalMisionero(nodoCopia));
    }

    @Override
    public ArrayList<Estado> generarSucesores() {
        ArrayList<Estado> sucesores = new ArrayList<>();

        if (POSICION_OESTE.equals(posicionActual.getPosicion())) {
            // ADD 1 MISIONERO
            movimientoMisioneroEste(sucesores, 1);
            // ADD 2 MISIONEROS
            movimientoMisioneroEste(sucesores, 2);
            // ADD 1 Canibal
            movimientoCanibalEste(sucesores, 1);
            // ADD 2 Canibales
            movimientoCanibalEste(sucesores, 2);
            // ADD 1 MISIONERO Y 1 Canibal
            movimientoMisioneroCanibal(sucesores);
        }
        if (POSICION_ESTE.equals(posicionActual.getPosicion())) {
            // ADD 1 MISIONERO
            movimientoMisioneroOeste(sucesores, 1);
            // ADD 2 MISIONEROS
            movimientoMisioneroOeste(sucesores, 2);
            // ADD 1 Canibal
            movimientoCanibalOeste(sucesores, 1);
            // ADD 2 Canibal
            movimientoCanibalOeste(sucesores, 2);
            // ADD 1 MISIONERO Y 1 Canibal
            movimientoMisioneroCanibalOeste(sucesores);
        }
        return sucesores;
    }

    private void movimientoMisioneroCanibalOeste(ArrayList<Estado> sucesores) {
        if (posicionActual.getMisioneros() + 1 <= 3 && posicionActual.getCanibales() + 1 >= 3 && estadoNoValido(posicionActual.getMisioneros() + 1, posicionActual.getCanibales() + 1))
            agregarEstado(+1, +1, POSICION_OESTE, sucesores);
    }

    private void movimientoCanibalOeste(ArrayList<Estado> sucesores, int i) {
        if (posicionActual.getCanibales() + i <= 3 && estadoNoValido(posicionActual.getMisioneros(), posicionActual.getCanibales() + i))
            agregarEstado(0, +i, POSICION_OESTE, sucesores);
    }

    private void movimientoMisioneroOeste(ArrayList<Estado> sucesores, int i) {
        if (posicionActual.getMisioneros() + i <= 3 && estadoNoValido(posicionActual.getMisioneros() + i, posicionActual.getCanibales()))
            agregarEstado(+i, 0, POSICION_OESTE, sucesores);
    }

    private void movimientoMisioneroCanibal(ArrayList<Estado> sucesores) {
        if (posicionActual.getMisioneros() - 1 >= 0 && posicionActual.getCanibales() - 1 >= 0
                && estadoNoValido(posicionActual.getMisioneros() - 1, posicionActual.getCanibales() - 1))
            agregarEstado(-1, -1, POSICION_ESTE, sucesores);
    }

    private void movimientoCanibalEste(ArrayList<Estado> sucesores, int i) {
        if (posicionActual.getCanibales() - i >= 0 && estadoNoValido(posicionActual.getMisioneros(), posicionActual.getCanibales() - i))
            agregarEstado(0, -i, POSICION_ESTE, sucesores);
    }

    private void movimientoMisioneroEste(ArrayList<Estado> sucesores, int i) {
        if (posicionActual.getMisioneros() - i >= 0 && estadoNoValido(posicionActual.getMisioneros() - i, posicionActual.getCanibales()))
            agregarEstado(-i, 0, POSICION_ESTE, sucesores);
    }

    // RESTRINGE A SOLO MOVIMIENTOS VALIDOS
    public boolean estadoNoValido(Integer misionero, Integer canibal) {
        return (misionero >= canibal || misionero == 0) && (misionero <= canibal || misionero == 3);
    }

    public static void main(String[] args) {
        // ESTADO INICIAL ( 3,3,1 )
        EstadoCanibalMisionero estadoCanibalMisionero = new EstadoCanibalMisionero(3, 3, POSICION_OESTE);
        estadoCanibalMisionero.generarSucesores().forEach(Estado::mostrarEstado);
        System.out.println("_____________________");
        // ESTADO INICIAL (3,1,0)
        EstadoCanibalMisionero estado2 = new EstadoCanibalMisionero(3, 1, POSICION_ESTE);
        estado2.generarSucesores().forEach(Estado::mostrarEstado);
    }
}
