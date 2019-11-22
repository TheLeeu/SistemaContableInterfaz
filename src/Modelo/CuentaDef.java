
package Modelo;

public class CuentaDef {
   public static String tabla[][]; 
   public static int nFila;

    public static int getnFila() {
        return nFila;
    }

    public static void setnFila(int nFila) {
        CuentaDef.nFila = nFila;
    }

    public static String[][] getTabla() {
        return tabla;
    }

    public static void setTabla(String[][] tabla) {
        CuentaDef.tabla = tabla;
    }
}
