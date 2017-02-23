package com.iramconsulting.turnosnow.cls;

import java.util.List;

public class User {

	String nombre;
	String apellido;
	String telefono;
	String contraseña;
	String dni;
	String mail;
	String sexo;
	String fechDeNacimiento;
	List<Address> address;

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getFechDeNacimiento() {
		return fechDeNacimiento;
	}

	public void setFechDeNacimiento(String fechDeNacimiento) {
		this.fechDeNacimiento = fechDeNacimiento;
	}

	public String getcontraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	@Override
	public String toString() {
		return "User [nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", contraseña=" + contraseña + ", dni=" + dni + ", mail=" + mail + ", sexo=" + sexo + ", fechDeNacimiento=" + fechDeNacimiento + ", address=" + address + "]";
	}


}
