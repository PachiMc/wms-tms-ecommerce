package ar.utn.frsf.icsw;

import ar.utn.frsf.icsw.tms.TmsService;
import ar.utn.frsf.icsw.tms.TmsService.Envio;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TmsServiceTest {

    @Test
    void asignaTransporteSegunPeso() {
        TmsService tms = new TmsService();
        assertEquals("MOTO", tms.asignarTransporte(10));
        assertEquals("CAMIÓN LIVIANO", tms.asignarTransporte(100));
        assertEquals("CAMIÓN PESADO", tms.asignarTransporte(800));
    }

    @Test
    void costoRechazaPesoCero() {
        TmsService tms = new TmsService();
        assertThrows(IllegalArgumentException.class, () -> tms.calcularCosto(0, 2, 120, 170));
    }

    @Test
    void reporteSlaCalculaPorcentaje() {
        TmsService tms = new TmsService();
        assertEquals("SLA: 90.0% a tiempo (9/10)", tms.reporteSla(9, 10));
    }

    @Test
    void trackingRechazaDatosVacios() {
        TmsService tms = new TmsService();
        assertThrows(IllegalArgumentException.class, () -> tms.tracking(" ", "en tránsito"));
    }

    @Test
    void rutaOrdenada() {
        TmsService tms = new TmsService();
        List<String> ruta = tms.optimizarRuta(List.of(new Envio("Rosario", 8), new Envio("Santa Fe", 12)));
        assertEquals(List.of("Rosario", "Santa Fe"), ruta);
    }
}
