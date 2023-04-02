package controller;

public class GymController {

    private GymView gymView;
    private GymModel gymModel;
    private UsuarioController usuarioController;

    public GymController(GymView gymView, GymModel gymModel) {
        this.gymView = gymView;
        this.gymModel = gymModel;
        this.usuarioController = new UsuarioController(gymModel.getUsuarioService());
    }

    public void iniciar() {
        gymView.mostrarMenu();
        int opcion = gymView.leerOpcion();
        while (opcion != 0) {
            switch (opcion) {
                case 1:
                    usuarioController.agregarUsuario(gymView.crearUsuario());
                    break;
                case 2:
                    usuarioController.obtenerUsuario(gymView.leerIdUsuario());
                    break;
                case 3:
                    usuarioController.actualizarUsuario(gymView.leerIdUsuario(), gymView.crearUsuario());
                    break;
                case 4:
                    usuarioController.eliminarUsuario(gymView.leerIdUsuario());
                    break;
                default:
                    gymView.mostrarMensaje("Opción inválida. Intente de nuevo.");
            }
            gymView.mostrarMenu();
            opcion = gymView.leerOpcion();
        }
        gymView.mostrarMensaje("Gracias por usar la aplicación del gimnasio.");
    }

}
