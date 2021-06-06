
import java.util.ArrayList;

public interface Estado {
    
    boolean esMeta();
    
    ArrayList<Estado> generarSucesores();
    
    void mostrarEstado();
    
    boolean igual(Estado e);
    
    
}
