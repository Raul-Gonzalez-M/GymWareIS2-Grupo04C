import java.util.ArrayList;
import java.util.List;
import src.BackEnd.BackEnd;
import src.Model.Usuario;
import src.Model.Cliente;
import src.Model.Personal;
import src.Model.Aula;
import src.Model.Venta;
import src.Model.Encuesta;


public class Controller {
    
    private BackEnd backEnd;
    private Usuario currentUser;

    public Controller(BackEnd backEnd) {
        this.backEnd = backEnd;
    }

    public void login() {
        String nombreUsuario = vista.getNombreUsuario();
        String contrasena = vista.getContrasena();
        Usuario usuario = backend.validarUsuario(nombreUsuario, contrasena);
        if (usuario != null) {
            vista.mostrarNombreUsuario(usuario.getNombreCompleto());
        } else {
            vista.mostrarMensajeError("Nombre de usuario o contraseña incorrectos");
        }
    }

    public void logout() {
        currentUser = null;
    }

    public Usuario getCurrentUser() {
        return currentUser;
    }

    public List<Usuario> getUsuarios() {
        return backEnd.getUsuarios();
    }

    public List<Cliente> getClientes() {
        List<Cliente> clientes = new ArrayList<>();
        for (Usuario usuario : getUsuarios()) {
            if (usuario instanceof Cliente) {
                clientes.add((Cliente) usuario);
            }
        }
        return clientes;
    }

    public List<Usuario> getPersonal() {
        List<Usuario> personal = new ArrayList<>();
        for (Usuario usuario : getUsuarios()) {
            if (usuario instanceof Personal) {
                personal.add(usuario);
            }
        }
        return personal;
    }

    public void addCliente(String nombre, String apellidos, String dni, String correo, String telefono, String direccion, String fechaNacimiento, String fechaAlta) {
        Cliente cliente = new Cliente(nombre, apellidos, dni, correo, telefono, direccion, fechaNacimiento, fechaAlta);
        backEnd.addCliente(cliente);
    }

    public void removeCliente(Cliente cliente) {
        backEnd.removeCliente(cliente);
    }

    public void addPersonal(String nombre, String apellidos, String dni, String correo, String telefono, String direccion, String fechaNacimiento, String fechaAlta, String puesto, String especialidad) {
        Personal personal = new Personal(nombre, apellidos, dni, correo, telefono, direccion, fechaNacimiento, fechaAlta, puesto, especialidad);
        backEnd.addPersonal(personal);
    }

    public void removePersonal(Personal personal) {
        backEnd.removePersonal(personal);
    }

    public void addAula(String nombre, String tipo, int capacidad) {
        Aula aula = new Aula(nombre, tipo, capacidad);
        backEnd.addAula(aula);
    }

    public void removeAula(Aula aula) {
        backEnd.removeAula(aula);
    }

    public void addVenta(Cliente cliente, Personal personal, String fecha, String concepto, double importe) {
        Venta venta = new Venta(cliente, personal, fecha, concepto, importe);
        backEnd.addVenta(venta);
    }

    public void removeVenta(Venta venta) {
        backEnd.removeVenta(venta);
    }

    public List<Aula> getAulas() {
        return backEnd.getAulas();
    }

    public List<Cliente> getClientes() {
        return backEnd.getClientes();
    }

    public List<Personal> getPersonales() {
        return backEnd.getPersonales();
    }

    public List<Venta> getVentas() {
        return backEnd.getVentas();
    }

    public List<Encuesta> getEncuestas() {
        return backEnd.getEncuestas();
    }

    public void addEncuesta(Cliente cliente, Personal personal, String fecha, String pregunta, String respuesta) {
        Encuesta encuesta = new Encuesta(cliente, personal, fecha, pregunta, respuesta);
        backEnd.addEncuesta(encuesta);
    }

    public void removeEncuesta(Encuesta encuesta) {
        backEnd.removeEncuesta(encuesta);
    }
}
