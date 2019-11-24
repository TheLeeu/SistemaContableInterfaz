
package Modelo;

/**
 *
 * @author Antonio Aranda
 */
public class ReporteBalanceGeneral {
    private String activo,saldo,pasivo,saldo1;

    public ReporteBalanceGeneral(String activo, String saldo, String pasivo, String saldo1) {
        this.activo = activo;
        this.saldo = saldo;
        this.pasivo = pasivo;
        this.saldo1 = saldo1;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getPasivo() {
        return pasivo;
    }

    public void setPasivo(String pasivo) {
        this.pasivo = pasivo;
    }

    public String getSaldo1() {
        return saldo1;
    }

    public void setSaldo1(String saldo1) {
        this.saldo1 = saldo1;
    }
    
}
