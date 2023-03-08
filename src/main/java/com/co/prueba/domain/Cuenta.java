package com.co.prueba.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cuenta")
public class Cuenta implements Serializable {

	private static final long serialVersionUID = -5277804195968835171L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "idcuenta")
	private Long idCuenta;

	@Column(nullable = false, name = "numerocuenta")
	private Long numeroCuenta;

	@Column(nullable = false, name = "tipocuenta")
	private String tipoCuenta;

	@Column(nullable = false, name = "saldoinicial")
	private Long saldoInicial;

	@Column(nullable = false, name = "estado")
	private Boolean estado;

	@JoinColumn(name = "IDCLIENTEPK", referencedColumnName = "IDCLIENTE", nullable = false)
	@ManyToOne(optional = false)
	private Cliente idClientePK;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "idCuentaPK", cascade = CascadeType.ALL)
	private List<Movimientos> movimientoList;

	public Long getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
	}

	public Long getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(Long numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Long getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(Long saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Cliente getidClientePK() {
		return idClientePK;
	}

	public void setidClientePK(Cliente idClientePK) {
		this.idClientePK = idClientePK;
	}

	public List<Movimientos> getMovimientoList() {
		return movimientoList;
	}

	public void setMovimientoList(List<Movimientos> movimientoList) {
		this.movimientoList = movimientoList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(estado, idClientePK, idCuenta, movimientoList, numeroCuenta, saldoInicial, tipoCuenta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta other = (Cuenta) obj;
		return Objects.equals(estado, other.estado) && Objects.equals(idClientePK, other.idClientePK)
				&& Objects.equals(idCuenta, other.idCuenta) && Objects.equals(movimientoList, other.movimientoList)
				&& Objects.equals(numeroCuenta, other.numeroCuenta) && Objects.equals(saldoInicial, other.saldoInicial)
				&& Objects.equals(tipoCuenta, other.tipoCuenta);
	}

	@Override
	public String toString() {
		return "Cuenta [idCuenta=" + idCuenta + ", numeroCuenta=" + numeroCuenta + ", tipoCuenta=" + tipoCuenta
				+ ", saldoInicial=" + saldoInicial + ", estado=" + estado + ", idClientePK=" + idClientePK
				+ ", movimientoList=" + movimientoList + "]";
	}

}
