package ar.utn.frsf.icsw.wms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * WMS mínimo: recepción, slotting, picking y packing.
 * Caso de estudio: administra lo que ocurre DENTRO del depósito.
 */
public class WmsService {

    private final Map<String, Integer> stock = new HashMap<>();
    private final Map<String, String> ubicaciones = new HashMap<>();

    /** 1. Recepción del producto por parte del proveedor. */
    public void recibir(String sku, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        stock.merge(sku, cantidad, Integer::sum);
        ubicaciones.putIfAbsent(sku, asignarUbicacion(sku));
    }

    /** 2. Slotting: ubicación ideal según rotación/tamaño (simplificado). */
    public String asignarUbicacion(String sku) {
        // Regla didáctica: SKUs de alta rotación (empiezan con "A") van al frente.
        if (sku != null && sku.startsWith("A")) {
            return "PAS-01-EST-A (alta rotación)";
        }
        return "PAS-05-EST-B (baja rotación)";
    }

    public String ubicacionDe(String sku) {
        return ubicaciones.getOrDefault(sku, "NO UBICACIÓN");
    }

    public int stockDe(String sku) {
        return stock.getOrDefault(sku, 0);
    }

    /** 3-4. Picking: secuencia de recolección para una orden. */
    public List<String> prepararPicking(Map<String, Integer> orden) {
        List<String> secuencia = new ArrayList<>();
        for (Map.Entry<String, Integer> item : orden.entrySet()) {
            String sku = item.getKey();
            int pedido = item.getValue();
            int disponible = stockDe(sku);
            if (disponible < pedido) {
                throw new IllegalStateException("Sin stock de " + sku + ": pedido=" + pedido + ", disponible=" + disponible);
            }
            stock.put(sku, disponible - pedido);
            secuencia.add("Recoger " + pedido + " x " + sku + " en " + ubicacionDe(sku));
        }
        return secuencia;
    }

    /** 5. Packing: deja el paquete listo para despacho (TMS). */
    public String empacar(String pedidoId, List<String> itemsRecolectados) {
        return "Paquete " + pedidoId + " listo para despacho";
    }
}
