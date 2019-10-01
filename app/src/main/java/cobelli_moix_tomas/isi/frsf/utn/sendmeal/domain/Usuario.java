package cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain;

import java.util.Objects;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.enumeration.TipoCuenta;

public class Usuario {

    private Integer id;
    private String nombre;
    private String mail;
    private String clave;
    private Boolean notificarEmail;
    private Double credito;
    private CuentaBancaria cuentaBancaria;
    private TarjetaCredito tarjetaCredito;
    private TipoCuenta tipoCuenta;

    public Usuario() {
    }

    public Usuario(Integer id, String nombre, String mail, String clave, Boolean notificarEmail, Double credito, CuentaBancaria cuentaBancaria, TarjetaCredito tarjetaCredito, TipoCuenta tipoCuenta) {
        this.id = id;
        this.nombre = nombre;
        this.mail = mail;
        this.clave = clave;
        this.notificarEmail = notificarEmail;
        this.credito = credito;
        this.cuentaBancaria = cuentaBancaria;
        this.tarjetaCredito = tarjetaCredito;
        this.tipoCuenta = tipoCuenta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getNotificarEmail() {
        return notificarEmail;
    }

    public void setNotificarEmail(Boolean notificarEmail) {
        this.notificarEmail = notificarEmail;
    }

    public Double getCredito() {
        return credito;
    }

    public void setCredito(Double credito) {
        this.credito = credito;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public TarjetaCredito getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(TarjetaCredito tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return getId().equals(usuario.getId()) &&
                getNombre().equals(usuario.getNombre()) &&
                getMail().equals(usuario.getMail()) &&
                getClave().equals(usuario.getClave()) &&
                getNotificarEmail().equals(usuario.getNotificarEmail()) &&
                getCredito().equals(usuario.getCredito()) &&
                getCuentaBancaria().equals(usuario.getCuentaBancaria()) &&
                getTarjetaCredito().equals(usuario.getTarjetaCredito()) &&
                getTipoCuenta() == usuario.getTipoCuenta();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getMail(), getClave(), getNotificarEmail(), getCredito(), getCuentaBancaria(), getTarjetaCredito(), getTipoCuenta());
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", mail='" + mail + '\'' +
                ", clave='" + clave + '\'' +
                ", notificarEmail=" + notificarEmail +
                ", credito=" + credito +
                ", cuentaBancaria=" + cuentaBancaria +
                ", tarjetaCredito=" + tarjetaCredito +
                ", tipoCuenta=" + tipoCuenta +
                '}';
    }

}
