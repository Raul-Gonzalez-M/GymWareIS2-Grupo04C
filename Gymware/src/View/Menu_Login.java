import javax.swing.*;

public class MainMenu extends JMenuBar {

    public MainMenu() {
        // Crear menú principal
        JMenu menuInicio = new JMenu("Inicio");
        add(menuInicio);

        // Crear submenús
        JMenuItem itemLogin = new JMenuItem("Iniciar sesión");
        JMenuItem itemRegistro = new JMenuItem("Registrarse");
        JMenuItem itemSalir = new JMenuItem("Salir");

        // Añadir submenús al menú principal
        menuInicio.add(itemLogin);
        menuInicio.add(itemRegistro);
        menuInicio.addSeparator();
        menuInicio.add(itemSalir);
    }
}
