package ar.utn.frsf.icsw;

import ar.utn.frsf.icsw.wms.WmsService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WmsServiceTest {

    @Test
    void recepcionYPickingDescuentanStock() {
        WmsService wms = new WmsService();
        wms.recibir("A100-TV", 500);

        List<String> secuencia = wms.prepararPicking(Map.of("A100-TV", 2));

        assertEquals(498, wms.stockDe("A100-TV"));
        assertEquals(1, secuencia.size());
        assertTrue(secuencia.get(0).contains("A100-TV"));
    }

    @Test
    void recepcionConSkuVacioLanzaError() {
        WmsService wms = new WmsService();
        assertThrows(IllegalArgumentException.class, () -> wms.recibir("  ", 10));
    }

    @Test
    void pickingSinStockLanzaError() {
        WmsService wms = new WmsService();
        wms.recibir("B200-LIBRO", 1);
        assertThrows(IllegalStateException.class, () -> wms.prepararPicking(Map.of("B200-LIBRO", 5)));
    }

    @Test
    void etiquetarFragilMarcaPaquete() {
        WmsService wms = new WmsService();
        assertEquals("Paquete PED-001 - FRAGIL: manipulacion especial", wms.etiquetarFragil("PED-001"));
    }
}
