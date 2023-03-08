package com.co.prueba.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = -5936329650033303260L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "idcliente")
	private Long idCliente;

	@Column(nullable = false, name = "contrasena")
	private String contrasena;

	@Column(nullable = false, name = "estado")
	private Boolean estado;

	@OneToOne
	@JoinColumn(name = "IDPERSONAPK", referencedColumnName = "IDPERSONA", updatable = false, nullable = false)
	private Persona idPersonaPK;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idClientePK")
	private List<Cuenta> cuentaList;

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Persona getidPersonaPK() {
		return idPersonaPK;
	}

	public void setidPersonaPK(Persona idPersonaPK) {
		this.idPersonaPK = idPersonaPK;
	}

	public List<Cuenta> getCuentaList() {
		return cuentaList;
	}

	public void setCuentaList(List<Cuenta> cuentaList) {
		this.cuentaList = cuentaList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contrasena, cuentaList, estado, idCliente, idPersonaPK);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(contrasena, other.contrasena) && Objects.equals(cuentaList, other.cuentaList)
				&& Objects.equals(estado, other.estado) && Objects.equals(idCliente, other.idCliente)
				&& Objects.equals(idPersonaPK, other.idPersonaPK);
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", contrasena=" + contrasena + ", estado=" + estado
				+ ", idPersonaPK=" + idPersonaPK + ", cuentaList=" + cuentaList + "]";
	}

}
