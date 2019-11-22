
package Modelo;

import java.sql.Date;

public class Plantilla {

    public static String idplantilla = "";
    public static String tabla[][];
    public static String tipoIVA = "";
    public static String saldo = "";
    public static String concepto = "";
    public static String fecha = "";
    public static String botonIVA="";
    public static boolean usar = false;
    public static int filas;
    public static String def = "";

    public static String getDef() {
        return def;
    }

    public static void setDef(String def) {
        Plantilla.def = def;
    }

    public static int getFilas() {
        return filas;
    }

    public static void setFilas(int filas) {
        Plantilla.filas = filas;
    }

    public static boolean isUsar() {
        return usar;
    }

    public static void setUsar(boolean usar) {
        Plantilla.usar = usar;
    }

    public static String getBotonIVA() {
        return botonIVA;
    }

    public static void setBotonIVA(String botonIVA) {
        Plantilla.botonIVA = botonIVA;
    }


    public static String getSaldo() {
        return saldo;
    }

    public static void setSaldo(String saldo) {
        Plantilla.saldo = saldo;
    }

    public static String getConcepto() {
        return concepto;
    }

    public static void setConcepto(String concepto) {
        Plantilla.concepto = concepto;
    }

    public static String getFecha() {
        return fecha;
    }

    public static void setFecha(String fecha) {
        Plantilla.fecha = fecha;
    }

    public static String getTipoIVA() {
        return tipoIVA;
    }

    public static void setTipoIVA(String tipoIVA) {
        Plantilla.tipoIVA = tipoIVA;
    }

    public static String[][] getTabla() {
        return tabla;
    }

    public static void setTabla(String[][] tabla) {
        Plantilla.tabla = tabla;
    }

    public static String getIdplantilla() {
        return idplantilla;
    }

    public static void setIdplantilla(String idplantilla) {
        Plantilla.idplantilla = idplantilla;
    }

}
