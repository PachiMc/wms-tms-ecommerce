package ar.utn.frsf.icsw.tms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * TMS: consolidación, asignación de transporte, ruta y costo.
 * Caso de estudio: optimiza el movimiento DESDE el depósito AL cliente.
 */
public class TmsService {

    public record Envio(String destino, double pesoKg) {}

    /** 1. Consolidación: agrupa paquetes en un único envío/contenedor. */
    public List<Envio> consolidar(List<Envio> paquetes) {
        return Collections.unmodifiableList(new ArrayList<>(paquetes));
    }

    /** 2. Asignación del transporte (carrier) según peso total. */
    public String asignarTransporte(double pesoTotalKg) {
        if (pesoTotalKg <= 0) {
            throw new IllegalArgumentException("El peso total debe ser mayor a 0");
        }
        if (pesoTotalKg <= 30) {
            return "MOTO";
        }
        if (pesoTotalKg <= 500) {
            return "CAMIÓN LIVIANO";
        }
        return "CAMIÓN PESADO";
    }

    /** 3. Optimización de rutas (simplificada: orden alfabético de destinos). */
    public List<String> optimizarRuta(List<Envio> envios) {
        List<String> destinos = new ArrayList<>();
        for (Envio e : envios) {
            destinos.add(e.destino());
        }
        Collections.sort(destinos);
        return destinos;
    }

    /** 4. Cálculo de costos logísticos. */
    public double calcularCosto(double pesoTotalKg, int cantidadDestinos, double costoPorKm, double kmEstimados) {
        if (pesoTotalKg <= 0 || cantidadDestinos <= 0) {
            throw new IllegalArgumentException("Peso y destinos deben ser mayores a 0");
        }
        return costoPorKm * kmEstimados + pesoTotalKg * 50 + cantidadDestinos * 500;
    }

    /** 5. Tracking: estado del envío (en tránsito, entregado, reprogramado). */
    public String tracking(String paqueteId, String estado) {
        if (paqueteId == null || paqueteId.isBlank() || estado == null || estado.isBlank()) {
            throw new IllegalArgumentException("Paquete y estado no pueden ser nulos ni vacíos");
        }
        return "Paquete " + paqueteId + ": " + estado;
    }

    /** 6. Reporte de SLA: porcentaje de entregas a tiempo. */
    public String reporteSla(int entregasATiempo, int totalEntregas) {
        if (totalEntregas <= 0) {
            throw new IllegalArgumentException("El total de entregas debe ser mayor a 0");
        }
        double pct = 100.0 * entregasATiempo / totalEntregas;
        return "SLA: " + String.format(Locale.ROOT, "%.1f", pct) + "% a tiempo (" + entregasATiempo + "/" + totalEntregas + ")";
    }
}
