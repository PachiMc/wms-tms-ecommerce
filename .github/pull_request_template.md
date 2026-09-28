# Describí tu cambio para que podamos revisarlo rápido (Punto 8b)

## ¿Qué cambia?
<!-- Resumen en 1-3 líneas. Ej: corrige cálculo de costo de envío en TMS -->

## ¿Por qué?
<!-- Problema / motivo. Ej: el costo daba 0 cuando el peso era 0. Issue #N si aplica. -->

## Tipo de cambio
<!-- Marcá con una X lo que corresponda -->
- [ ] Fix (corrección de bug)
- [ ] Feature (nueva funcionalidad)
- [ ] Release / Hotfix (Gitflow)
- [ ] Docs / CI / refactor (sin cambio funcional)

## ¿Cómo se probó?
<!-- Comandos y resultado. Ej: `mvn -B verify` OK, caso manual: ... -->
-
-

## Checklist
- [ ] `mvn -B verify` pasa en local
- [ ] No subí archivos locales (`.env`, `application-local.*`, `target/`, `.idea/`)
- [ ] La rama base es correcta (`feature/*` → `develop`, `release/*` y `hotfix/*` → `main` + `develop`)
- [ ] Agregué/actualicé tests si corresponde
