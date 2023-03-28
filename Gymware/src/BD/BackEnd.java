import java.util.ArrayList;
import java.util.List;
import model.Aula;
import model.Cliente;
import model.Gimnasio;
import model.Personal;

public class BackEnd {

    private static BackEnd instance = null;
    private Gimnasio gym;

    private BackEnd() {
        gym = new Gimnasio();
    }

    public static BackEnd getInstance() {
        if (instance == null) {
            instance = new BackEnd();
        }
        return instance;
    }

    public List<Aula> getAulas() {
        return gym.getAulas();
    }

    public List<Cliente> getClientes() {
        return gym.getClientes();
    }

    public List<Personal> getPersonales() {
        return gym.getPersonales();
    }

    public List<Venta> getVentas() {
        return gym.getVentas();
    }

    public void addAula(Aula aula) {
    	gym.addAula(aula);
    }

    public void addCliente(Cliente cliente) {
    	gym.addCliente(cliente);
    }

    public void addPersonal(Personal personal) {
    	gym.addPersonal(personal);
    }

    public void addVenta(Venta venta) {
    	gym.addVenta(venta);
    }

    public void removeAula(Aula aula) {
    	gym.removeAula(aula);
    }

    public void removeCliente(Cliente cliente) {
    	gym.removeCliente(cliente);
    }

    public void removePersonal(Personal personal) {
    	gym.removePersonal(personal);
    }

    public void removeVenta(Venta venta) {
    	gym.removeVenta(venta);
    }
}
