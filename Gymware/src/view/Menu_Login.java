package view;
import javax.swing.*;

public class Menu_Login extends JMenuBar {

    public Menu_Login() {
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
