<div align="center">

# 🅿️ Sistema de Gestión de Zona Azul — Ciudad de Martos

!\[Java](https://img.shields.io/badge/Java-25%2B-ED8B00?style=for-the-badge\&logo=openjdk\&logoColor=white)
!\[Curso](https://img.shields.io/badge/1º\_DAM-Programación-00599C?style=for-the-badge)
!\[Consola](https://img.shields.io/badge/Interfaz-CLI\_/\_Consola-2EA44F?style=for-the-badge)

**Simulador de parquímetro municipal desarrollado en Java puro para la regulación del estacionamiento limitado en Martos.**

[Inicio Rápido](#-inicio-rápido) • [Funcionalidades](#-funcionalidades) • [Tarifas y Horarios](#-tarifas-y-horarios) • [Lógica Interna](#-lógica-interna) • [Autores](#-autores)

</div>

\---

## 📌 Sobre el Proyecto

Esta aplicación de terminal reproduce el flujo completo de un parquímetro real de la **Zona Azul de Martos**, gestionando tanto la expedición de tickets con cálculo automático de desborde horario como la anulación de denuncias y la auditoría de caja mediante un panel de administración protegido.

### Módulos principales

* **Expendedor Inteligente:** Calcula el tiempo exacto de estacionamiento diferenciando entre residentes y no residentes, saltando automáticamente las franjas no reguladas (mediodías, noches y domingos).
* **Gestor de Sanciones:** Valida estructuralmente los códigos de ticket para tramitar anulaciones por exceso de tiempo (`3 €`) o emite justificantes directos por carecer de ticket (`6 €`).
* **Punto de Información:** Consulta en tiempo real la fecha del sistema en español, las tablas de precios y los turnos operativos.
* **Control de Recaudación:** Acceso restringido con credenciales para supervisar ingresos, contadores y apagar el terminal de forma segura.

\---

## ⚡ Inicio Rápido

### Requisitos previos

El proyecto emplea características modernas introducidas en las últimas versiones del lenguaje (`java.lang.IO` y `Locale.of()`), por lo que requiere **JDK 25** (o JDK 23+ con *preview features* habilitadas).

```bash
# Comprueba tu versión de Java
java -version
```

### Ejecución

Clona o descarga el repositorio, sitúate en la carpeta raíz del proyecto y lanza directamente el archivo fuente:

```bash
git clone https://github.com/DavidCastillo3008/ZonaAzulMartos.git
cd ZonaAzulMartos
java Main.java
```

\---

## 🕒 Tarifas y Horarios

### Horario de Regulación

|Días de la semana|Turno de Mañana|Turno de Tarde|Estado fuera de franja|
|-|:-:|:-:|-|
|**Lunes a Viernes**|`09:00 - 14:00`|`17:00 - 21:00`|Estacionamiento gratuito|
|**Sábados**|`09:00 - 14:00`|*Cerrado*|Estacionamiento gratuito|
|**Domingos**|*Cerrado*|*Cerrado*|Estacionamiento libre todo el día|

### Cuadro de Tarifas

|Tramo|Tiempo Asignado|Tarifa Residente|Tarifa No Residente|
|:-:|:-:|:-:|:-:|
|**1**|`00:15:00`|`0,10 €`|`0,25 €`|
|**2**|`00:29:50`|`0,20 €`|`0,45 €`|
|**3**|`00:59:31`|`0,40 €`|`0,85 €`|
|**4**|`01:29:12`|`0,60 €`|`1,25 €`|
|**5**|`02:00:00`|`0,80 €`|`1,70 €`|

\---

## 🖥️ Funcionalidades y Capturas de Consola

### 1\. Menú Principal y Emisión de Tickets

Al arrancar, el usuario puede elegir entre las 4 opciones del sistema. En la opción **1 (Obtener un ticket)**, tras indicar si es residente e introducir el importe (hasta `2,00 €`), permite operar con la hora actual o **simular cualquier fecha** (`año`, `mes`, `día`, `hora`, `minutos`) para verificar el comportamiento del parquímetro.

<details>
<summary><b>Ver ejemplo de pantalla principal y ticket generado</b></summary>

```text
                      /\\\\.-'''-.
                    /            \\\\
                   /                \\\\  +------+
                  / CIUDAD DE MARTOS\\\\ |  \[]  |
               \_.'                    '-|      |
           \_.-'                         |  \[]  |
          '--...\_\_\_\_\_\_\_\_|\_\_\_|
                   (o)   (o)   (o)   (o)
                  /  \\\\ /  \\\\ /  \\\\ /  \\\\
                 ''''''''''''''''''''''
          ┌────────────────────────────────────────────────────────┐
          │              ZONA AZUL - CIUDAD DE MARTOS              │
          ├────────────────────────────────────────────────────────┤
          │  1. Obtener un ticket                                  │
          │  2. Anular una multa                                   │
          │  3. Consultar fecha y hora actual, tarifas y horarios  │
          │  4. Menú administrador                                 │
          └────────────────────────────────────────────────────────┘
```

```text
╔════════════════════════════════════════╗
║        ZONA AZUL CIUDAD DE MARTOS      ║
║                PARKING                 ║
╠════════════════════════════════════════╣
║ Nº Ticket:     1115SRMAR1              ║
╠════════════════════════════════════════╣
║ Emitido:       15/11/2026              ║
║ Hora:          10:30:00                ║
╠════════════════════════════════════════╣
║ Vencimiento:   15/11/2026              ║
║ Hora:          12:30:00                ║
╠════════════════════════════════════════╣
║ Importe:       0,80 €                  ║
║ Tipo:          Residente               ║
╚════════════════════════════════════════╝
```

</details>

### 2\. Anulación de Denuncias

* **Por exceso de tiempo (`3,00 €`):** Analiza el código de ticket introducido comprobando longitud (`>= 10`), validez de mes/día en los 4 primeros dígitos, modalidad (`SR`/`NR`), localidad (`MAR`) y número correlativo. El resguardo de anulación se genera invirtiendo el código original.
* **Por carecer de ticket (`6,00 €`):** Genera un identificador aleatorio de 5 dígitos seguido de `MAR` y el número de multa tramitada.

### 3\. Panel de Administración

Protegido mediante inicio de sesión (`Usuario: Root` | `Contraseña: 1234`), ofrece:

1. **Balance de Tickets:** Total recaudado y número de tickets emitidos.
2. **Balance de Multas:** Total recaudado y número de sanciones anuladas.
3. **Apagado del Sistema:** Finaliza el bucle principal de ejecución de forma controlada.

\---

## 🛠️ Lógica Interna

* **Gestión de desbordes con `Duration`:** Cuando el tiempo adquirido supera el cierre de turno (`14:00` o `21:00`), el algoritmo resta el tiempo consumido hasta el cierre mediante `Duration.between()` y suma el remanente a la siguiente apertura comercial (`17:00` del mismo día, `09:00` del día siguiente o lunes `09:00` si cae en fin de semana).
* **Validación sin colecciones:** Todo el análisis sintáctico de los códigos de ticket se realiza mediante manipulación directa de cadenas (`substring`, `charAt`, `Character.isDigit`) y validación de calendario con `MonthDay.of()`.
* **Maquetación con Text Blocks:** Las interfaces de consola utilizan bloques de texto multilínea (`"""`) formateados dinámicamente con `.formatted()`.

\---

## 👨‍💻 Autores

|Desarrollador|Perfil de GitHub|
|-|-|
|**Joaquín Recalde Olmo**||
|**Antonio Javier Velasco Chica**||



