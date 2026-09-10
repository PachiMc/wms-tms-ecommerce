package ar.utn.frsf.icsw;

import ar.utn.frsf.icsw.tms.TmsService;
import ar.utn.frsf.icsw.tms.TmsService.Envio;
import ar.utn.frsf.icsw.wms.WmsService;

import java.util.List;
import java.util.Map;

/** Demo: WMS deja paquetes listos, TMS los consolida y despacha. */
public class App {
    public static void main(String[] args) {
        WmsService wms = new WmsService();
        wms.recibir("A100-TV", 500);
        wms.recibir("B200-LIBRO", 100);

        List<String> picking = wms.prepararPicking(Map.of("A100-TV", 2, "B200-LIBRO", 1));
        picking.forEach(System.out::println);
        System.out.println(wms.empacar("PED-001", picking));

        TmsService tms = new TmsService();
        List<Envio> envios = List.of(new Envio("Santa Fe", 12.5), new Envio("Rosario", 8.0));
        double pesoTotal = envios.stream().mapToDouble(Envio::pesoKg).sum();
        System.out.println("Transporte: " + tms.asignarTransporte(pesoTotal));
        System.out.println("Ruta: " + tms.optimizarRuta(envios));
        System.out.println("Costo: $" + tms.calcularCosto(pesoTotal, envios.size(), 120, 170));
        System.out.println(tms.tracking("PED-001", "en tránsito"));
    }
}
