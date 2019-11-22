
package Modelo;

/**
 *
 * @author Antonio Aranda
 */
public class Libro {
    public static boolean crear = false;
    public static int anterior = 0;

    public static int getAnterior() {
        return anterior;
    }

    public static void setAnterior(int anterior) {
        Libro.anterior = anterior;
    }

    public static boolean isCrear() {
        return crear;
    }

    public static void setCrear(boolean crear) {
        Libro.crear = crear;
    }
}
