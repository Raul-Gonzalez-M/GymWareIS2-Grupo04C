public class UsuarioController {
    
    private UsuarioService usuarioService;
    
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    public void obtenerUsuario(int idUsuario) {
        Usuario usuario = usuarioService.obtenerUsuario(idUsuario);
        // Mostrar los detalles del usuario en la vista correspondiente
    }
    
    public void agregarUsuario(Usuario usuario) {
        usuarioService.agregarUsuario(usuario);
        // Mostrar mensaje de éxito en la vista correspondiente
    }
    
    public void actualizarUsuario(int idUsuario, Usuario usuario) {
        usuario.setIdUsuario(idUsuario);
        usuarioService.actualizarUsuario(usuario);
        // Mostrar mensaje de éxito en la vista correspondiente
    }
    
    public void eliminarUsuario(int idUsuario) {
        usuarioService.eliminarUsuario(idUsuario);
        // Mostrar mensaje de éxito en la vista correspondiente
    }
    
    public void realizarEncuesta(int idUsuario, String respuesta) {
        Encuesta encuesta = new Encuesta(gymModel.getEncuestaService().obtenerSiguienteId(), idUsuario, respuesta);
        gymModel.getEncuestaService().guardarEncuesta(encuesta);
    }
    
}
