package model;

public class Cliente extends Usuario {
	private String fechaAlta;
	private String fechaBaja;
    private double saldo;
    
    public Cliente(String DNI,String nombre, String contrasena, String fechaAlta, String fechaBaja, double saldo) {
        super(DNI,nombre,contrasena);
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.saldo = saldo;
    }

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
    
}