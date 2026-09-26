package bo.edu.usfx.descuentos;

/**
 * Calcula el precio final de un producto despues de aplicar un descuento.
 *
 * Reglas:
 *  - porcentajeDescuento fuera de [0, 100]  -> IllegalArgumentException
 *  - precioOriginal <= 0                    -> IllegalArgumentException
 *  - el resultado se redondea a dos decimales (medio hacia arriba)
 *  - descuento 0   -> el precio no cambia
 *  - descuento 100 -> el precio final es 0
 */
public class CalculadoraDescuentos {

    public double calcularPrecioFinal(double precioOriginal, double porcentajeDescuento) {
        if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
            throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 100");
        }
        if (precioOriginal <= 0) {
            throw new IllegalArgumentException("El precio original debe ser mayor que cero");
        }
        double precioFinal = precioOriginal * (1 - porcentajeDescuento / 100);
        return redondear(precioFinal);
    }

    public double descuentoPorCantidad(int cantidad, double precioUnitario) {
        if (cantidad <= 0 || precioUnitario <= 0) {
            throw new IllegalArgumentException("Cantidad y precio deben ser positivos");
        }
        if (cantidad >= 10) {
            return calcularPrecioFinal(precioUnitario * cantidad, 15);
        } else if (cantidad >= 5) {
            return calcularPrecioFinal(precioUnitario * cantidad, 10);
        } else {
            return redondear(precioUnitario * cantidad);
        }
    }

    public double descuentoMayorista(int cantidad, double precioUnitario) {
        if (cantidad <= 0 || precioUnitario <= 0) {
            throw new IllegalArgumentException("Cantidad y precio deben ser positivos");
        }
        if (cantidad >= 50) {
            return calcularPrecioFinal(precioUnitario * cantidad, 20);
        } else {
            return descuentoPorCantidad(cantidad, precioUnitario);
        }
    }

    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
