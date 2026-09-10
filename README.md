# WMS y TMS para e-Commerce — ICSW (UTN FRSF)

Software de ejemplo para el caso de estudio de la materia: implementación de un WMS y un TMS en una empresa de e-Commerce.
El WMS administra lo que pasa dentro del depósito (recepción, ubicación con slotting, control de inventario, picking, packing y despacho).
El TMS optimiza el movimiento desde el depósito hasta el cliente (consolidación de envíos, asignación del transporte, planificación de carga, rutas, tracking, costos y SLA).

Alumno: Patricio Martin — [@PachiMc](https://github.com/PachiMc) — pachimc@outlook.com.ar
Docentes con permiso de lectura: `ocmartinez@gmail.com`, `rodrigobravo@gmail.com`
Entrega: 29/09/2026.

## Estructura

```text
.github/CODEOWNERS                  # Responsables de revisión (punto 2)
.github/workflows/ci.yml            # CI en cada PR y en cada push a main/develop (punto 3)
.github/pull_request_template.md    # Datos que se piden en cada PR (punto 8b)
src/main/java/ar/utn/frsf/icsw/
  wms/WmsService.java               # Recepción, slotting, picking, packing
  tms/TmsService.java               # Consolidación, transporte, ruta, costo, tracking
  App.java                          # Demo: el WMS deja paquetes listos y el TMS los despacha
src/test/java/...                   # Tests JUnit que corren en el CI
```

## Cómo correrlo

Requisitos: Java 17+ y Maven 3.9+.

```powershell
mvn -B verify
```

Es el mismo comando que corre el CI. La demo (`App.java`) muestra la secuencia de picking, el paquete listo, el transporte asignado, la ruta optimizada, el costo y el tracking.

## Flujo de ramas (Gitflow)

- `main` es producción y `develop` es integración. No se commitea directo a `main`.
- Las funcionalidades se hacen en `feature/*` desde `develop` y vuelven a `develop` por pull request.
- La versión se estabiliza en `release/*`: se mergea a `main` con tag (ej: `v1.0.0`) y de vuelta a `develop`.
- Los errores de producción se corrigen en `hotfix/*` desde `main`: se mergea a `main` con tag (ej: `v1.0.1`) y también a `develop` para no perder la corrección.

## 8a) Qué se documenta en este README

Lo necesario para que otra persona pueda entender el proyecto, correrlo y modificarlo: qué es y su relación con el caso WMS/TMS, requisitos y comandos de build y test, cómo es el flujo de ramas y cómo se contribuye, y los supuestos simplificados del código (por ejemplo, el slotting por prefijo y la ruta por orden alfabético, que son reglas didácticas y no del negocio real). También se incluyen acá las respuestas del trabajo, como pide la consigna.
El README se versiona en el repositorio porque si cambia el comportamiento del programa tiene que cambiar su documentación al mismo tiempo, y el historial de git deja constancia de cuándo y por qué cambió cada cosa.

## 8b) Si un externo propone un cambio: qué se le pide y qué ofrece GitHub

Para entender el cambio se le pide: un resumen de qué modifica, el motivo (y el número de issue si corresponde), el tipo de cambio (fix, feature, release/hotfix o docs/CI/refactor), cómo lo probó (comando y resultado, por ejemplo `mvn -B verify`) y un checklist (CI en verde, sin archivos de configuración local como `.env` o `application-local.*`, rama base correcta según Gitflow y tests agregados si corresponde). Pedirlo de entrada evita idas y vueltas y deja constancia de la decisión.
GitHub ayuda con: la plantilla de pull request (este repositorio tiene una en `.github/pull_request_template.md`), el archivo CODEOWNERS que asigna revisores automáticamente, la protección de ramas con checks obligatorios que impide mergear si el CI falla o falta revisión, la vista de diff con comentarios en línea y sugerencias para discutir el cambio, los checks del CI visibles en el PR y el registro de autor, revisores y merges. De esta forma cada cambio que entra tiene quién lo hizo, quién lo revisó, qué tests pasaron y cómo se puede revertir.
