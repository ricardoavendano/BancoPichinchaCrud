package com.co.prueba.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "movimientos")
public class Movimientos implements Serializable {

	private static final long serialVersionUID = 8033124121813877325L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "idmovimiento")
	private Long idMovimiento;

	@Column(nullable = false, name = "fecha")
	private Date fecha;

	@Column(nullable = false, name = "tipomovimiento")
	private String tipoMovimiento;

	@Column(nullable = false, name = "valor")
	private Long valor;

	@Column(nullable = false, name = "saldo")
	private Long saldo;

	@JoinColumn(name = "IDCUENTAPK", referencedColumnName = "IDCUENTA", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private Cuenta idCuentaPK;

	public Long getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(Long idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public Long getSaldo() {
		return saldo;
	}

	public void setSaldo(Long saldo) {
		this.saldo = saldo;
	}

	public Cuenta getIdCuentaPK() {
		return idCuentaPK;
	}

	public void setIdCuentaPK(Cuenta idCuentaPK) {
		this.idCuentaPK = idCuentaPK;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha, idCuentaPK, idMovimiento, saldo, tipoMovimiento, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimientos other = (Movimientos) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(idCuentaPK, other.idCuentaPK)
				&& Objects.equals(idMovimiento, other.idMovimiento) && Objects.equals(saldo, other.saldo)
				&& Objects.equals(tipoMovimiento, other.tipoMovimiento) && Objects.equals(valor, other.valor);
	}

	@Override
	public String toString() {
		return "Movimientos [idMovimiento=" + idMovimiento + ", fecha=" + fecha + ", tipoMovimiento=" + tipoMovimiento
				+ ", valor=" + valor + ", saldo=" + saldo + ", idCuentaPK=" + idCuentaPK + "]";
	}

}
