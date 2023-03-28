import java.util.ArrayList;
import java.util.List;

public class BackEnd {

    private static BackEnd instance = null;
    private GymModel gymModel;

    private BackEnd() {
        gymModel = new GymModel();
    }

    public static BackEnd getInstance() {
        if (instance == null) {
            instance = new BackEnd();
        }
        return instance;
    }

    public List<Aula> getAulas() {
        return gymModel.getAulas();
    }

    public List<Cliente> getClientes() {
        return gymModel.getClientes();
    }

    public List<Personal> getPersonales() {
        return gymModel.getPersonales();
    }

    public List<Venta> getVentas() {
        return gymModel.getVentas();
    }

    public void addAula(Aula aula) {
        gymModel.addAula(aula);
    }

    public void addCliente(Cliente cliente) {
        gymModel.addCliente(cliente);
    }

    public void addPersonal(Personal personal) {
        gymModel.addPersonal(personal);
    }

    public void addVenta(Venta venta) {
        gymModel.addVenta(venta);
    }

    public void removeAula(Aula aula) {
        gymModel.removeAula(aula);
    }

    public void removeCliente(Cliente cliente) {
        gymModel.removeCliente(cliente);
    }

    public void removePersonal(Personal personal) {
        gymModel.removePersonal(personal);
    }

    public void removeVenta(Venta venta) {
        gymModel.removeVenta(venta);
    }
}
