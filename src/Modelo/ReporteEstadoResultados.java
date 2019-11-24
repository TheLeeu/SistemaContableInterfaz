
package Modelo;

/**
 *
 * @author Antonio Aranda
 */
public class ReporteEstadoResultados {
    private String detalle,saldo;

    public ReporteEstadoResultados(String detalle, String saldo) {
        this.detalle = detalle;
        this.saldo = saldo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
    
}
