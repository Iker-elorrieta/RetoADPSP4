package modelo;



public class Usuario implements java.io.Serializable {

	
	private static final long serialVersionUID = -4188035944807949080L;

	
	private String usuario;
	private String email;
	private String contrasena;
	

	public Usuario() {
	}
	public Usuario(String usuario, String email, String contrasena) {
		super();
		this.usuario = usuario;
		this.email = email;
		this.contrasena = contrasena;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + ", email=" + email + ", contrasena=" + contrasena + "]";
	}
	

	
	
	
}
