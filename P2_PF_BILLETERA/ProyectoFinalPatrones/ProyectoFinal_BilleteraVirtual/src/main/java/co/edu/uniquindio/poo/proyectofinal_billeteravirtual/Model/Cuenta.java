package co.edu.uniquindio.poo.proyectofinal_billeteravirtual.Model;

/**
 * Clase que representa una cuenta bancaria en la billetera virtual
 */
public class Cuenta implements ICuenta {

    private String IdCuenta;
    private String nombreBanco;
    private TipoCuenta tipoCuenta;
    private String numeroCuenta;
    private double saldo;
    private boolean activa;

    /**
     * Constructor sin parámetros para serialización
     */
    public Cuenta() {
        this.IdCuenta = "";
        this.nombreBanco = "";
        this.tipoCuenta = TipoCuenta.AHORRO;
        this.numeroCuenta = "";
        this.saldo = 0.0;
        this.activa = true;
    }

    /**
     * Constructor de la clase Cuenta
     * @param idCuenta ID único de la cuenta
     * @param nombreBanco Nombre del banco
     * @param tipoCuenta Tipo de cuenta (AHORRO o CORRIENTE)
     * @param numeroCuenta Número de cuenta
     */
    public Cuenta(String idCuenta, String nombreBanco, TipoCuenta tipoCuenta, String numeroCuenta) {
        IdCuenta = idCuenta;
        this.nombreBanco = nombreBanco;
        this.tipoCuenta = tipoCuenta;
        this.numeroCuenta = numeroCuenta;
        this.saldo = 0.0;
        this.activa = true;
    }

    /**
     * Constructor completo
     */
    public Cuenta(String idCuenta, String nombreBanco, TipoCuenta tipoCuenta, String numeroCuenta, double saldo) {
        this(idCuenta, nombreBanco, tipoCuenta, numeroCuenta);
        this.saldo = saldo;
    }

    /**
     * Constructor simplificado para la interfaz de usuario
     * @param nombre Nombre de la cuenta (se usa como nombre del banco)
     * @param tipo Tipo de cuenta como String
     * @param saldoInicial Saldo inicial de la cuenta
     */
    public Cuenta(String nombre, String tipo, double saldoInicial) {
        this.IdCuenta = java.util.UUID.randomUUID().toString();
        this.nombreBanco = nombre;
        this.numeroCuenta = java.util.UUID.randomUUID().toString().substring(0, 8);
        this.saldo = saldoInicial;
        this.activa = true;

        // Convertir el tipo de String a TipoCuenta
        if (tipo.equalsIgnoreCase("Cuenta Corriente")) {
            this.tipoCuenta = TipoCuenta.CORRIENTE;
        } else if (tipo.equalsIgnoreCase("Cuenta de Ahorros")) {
            this.tipoCuenta = TipoCuenta.AHORRO;
        } else if (tipo.equalsIgnoreCase("Tarjeta de Crédito")) {
            this.tipoCuenta = TipoCuenta.CREDITO;
        } else {
            this.tipoCuenta = TipoCuenta.OTRO;
        }
    }

    public String getIdCuenta() {
        return IdCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        IdCuenta = idCuenta;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    /**
     * Obtiene el nombre de la cuenta para mostrar en la interfaz de usuario
     * @return Nombre de la cuenta
     */
    public String getNombre() {
        return nombreBanco;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    /**
     * Obtiene el tipo de cuenta como String para mostrar en la interfaz de usuario
     * @return Tipo de cuenta como String
     */
    public String getTipo() {
        switch (tipoCuenta) {
            case AHORRO:
                return "Cuenta de Ahorros";
            case CORRIENTE:
                return "Cuenta Corriente";
            case CREDITO:
                return "Tarjeta de Crédito";
            case EFECTIVO:
                return "Efectivo";
            case OTRO:
            default:
                return "Otro";
        }
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    /**
     * Realiza un depósito en la cuenta
     * @param monto Monto a depositar
     * @return true si el depósito fue exitoso, false en caso contrario
     */
    public boolean depositar(double monto) {
        if (!activa || monto <= 0) {
            return false;
        }

        saldo += monto;
        return true;
    }

    /**
     * Realiza un retiro de la cuenta
     * @param monto Monto a retirar
     * @return true si el retiro fue exitoso, false en caso contrario
     */
    public boolean retirar(double monto) {
        if (!activa || monto <= 0 || saldo < monto) {
            return false;
        }

        saldo -= monto;
        return true;
    }

    /**
     * Realiza una transferencia a otra cuenta
     * @param cuentaDestino Cuenta destino
     * @param monto Monto a transferir
     * @return true si la transferencia fue exitosa, false en caso contrario
     */
    public boolean transferir(Cuenta cuentaDestino, double monto) {
        if (!activa || !cuentaDestino.isActiva() || monto <= 0 || saldo < monto) {
            return false;
        }

        saldo -= monto;
        cuentaDestino.depositar(monto);
        return true;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "IdCuenta='" + IdCuenta + '\'' +
                ", nombreBanco='" + nombreBanco + '\'' +
                ", tipoCuenta=" + tipoCuenta +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                ", activa=" + activa +
                '}';
    }

    @Override
    public void CrearCuenta() {
        System.out.println("Cuenta creada: " + this);
    }

    @Override
    public void ModificarCuenta() {
        System.out.println("Cuenta modificada: " + this);
    }

    @Override
    public void EliminarCuenta() {
        this.activa = false;
        System.out.println("Cuenta desactivada: " + this);
    }
}
