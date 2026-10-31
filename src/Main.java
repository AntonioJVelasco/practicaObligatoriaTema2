//importamos herramientas para trabajar con fecha y hora.

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

void main() {
    //Creación del proyecto en git, creacción del directorio src y del main.
    // Comandos para trabajar en git.
    /*
     * git add para preparar los cambios
     * git commit -m "Mensaje con los cambios hechos"
     * git push para subirlos a GitHub
     * */

    //Portada de nuestro menú

    // Variables de nuestro programa.
    int option, diaSimulacion, mesSimulacion, anioSimulacion, horaSimulacion, minutosSimulacion;
    double dineroUsuario = 0, importeFinal = 0;
    //Tarifa de precios para los residentes
    final double PRECIO_PRIMER_TRAMO_RESIDENTE = 0.10, PRECIO_SEGUNDO_TRAMO_RESIDENTE = 0.20;
    final double PRECIO_TERCER_TRAMO_RESIDENTE = 0.40, PRECIO_CUARTO_TRAMO_RESIDENTE = 0.60;
    final double PRECIO_QUINTO_TRAMO_RESIDENTE = 0.80;


    //Tarifa de precios para los no residentes.
    final double PRECIO_PRIMER_TRAMO_NO_RESIDENTE = 0.25, PRECIO_SEGUNDO_TRAMO_NO_RESIDENTE = 0.45;
    final double PRECIO_TERCER_TRAMO_NO_RESIDENTE = 0.85, PRECIO_CUARTO_TRAMO_NO_RESIDENTE = 1.25;
    final double PRECIO_QUINTO_TRAMO_NO_RESIDENTE = 1.70;

    // Tramos horarios.

    final int MINUTOS_PRIMER_TRAMO = 15, MINUTOS_SEGUNDO_TRAMO = 29, MINUTOS_TERCER_TRAMO = 59;
    final int MINUTOS_CUARTO_TRAMO = 29;
    final int SEGUNDOS_SEGUNDO_TRAMO = 50, SEGUNDOS_TERCER_TRAMO = 31, SEGUNDOS_CUARTO_TRAMO = 12;
    final int HORA_CUARTO_TRAMO = 1, HORA_QUINTO_TRAMO = 2;
    boolean salidaMenu, datoValido, dineroValido;
    String datosUsuario, respuestaSimulacion;
    do {
        option = Integer.parseInt(IO.readln("""
                              /\\\\_.-'''-._
                            _/           \\\\_
                           /               \\\\  +------+
                          / CIUDAD DE MARTOS\\\\ |  []  |
                       _.'                   '-|      |
                   _.-'                        |  []  |
                  '--..._______________________|______|
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
                👉 Elija una opción:"""));//Estructura de nuestro menú
        salidaMenu = false;
        switch (option) {

            case 1://Opción de obtener el ticket
                LocalDateTime horaActual = LocalDateTime.now();
                LocalDateTime horaSalida = LocalDateTime.now();
                LocalDateTime fechaSimulada  = LocalDateTime.now();
                DateTimeFormatter fechaFormateada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String fechaTexto = horaActual.format(fechaFormateada);
                datoValido = false;
                dineroValido = false;
                do {
                    // Bloque para validar que el usuario es residente o no.
                    datosUsuario = IO.readln("¿Es usted residente? (S/N): ");
                    if (datosUsuario.equalsIgnoreCase("si")) {
                        datoValido = true;
                    } else if (datosUsuario.equalsIgnoreCase("no")) {
                        datoValido = true;
                    } else {
                        IO.println("Opción invalida");
                    }
                } while (!datoValido);

                //Bloque para validar que el dinero introducido es el correcto.

                do {
                    dineroUsuario = Double.parseDouble(IO.readln("""
                            Introduce el importe.
                            Máximo 2 euros.
                            Mínimo 0.10 (Si eres residente), 0.25 (si no eres residente)
                            """));
                    if (datosUsuario.equalsIgnoreCase("no")) {
                        /*
                        Controlamos que los no residentes no metan menos de 0.25 y que no pueda meter la tarifa más
                        baja de los residentes y vicerversa.
                         */

                        if (dineroUsuario > 2 || dineroUsuario < 0.25) {
                            IO.println("Error al introducir el importe.");
                        } else {
                            dineroValido = true;
                        }
                    } else if (datosUsuario.equalsIgnoreCase("Si")) {
                        //Controlamos que los residentes no puedan meter menos del mínimo de su categoría.
                        if (dineroUsuario > 2 || dineroUsuario < 0.10) {
                            IO.println("Error al introducir el importe.");
                        } else {
                            dineroValido = true;
                        }
                    }
                }
                while (!dineroValido);

                //A continuación vamos asignar los tramos según el importe introducido

                //Tarifas para los residentes.


                if (datosUsuario.equalsIgnoreCase("si")) {
                    if (dineroUsuario >= PRECIO_PRIMER_TRAMO_RESIDENTE && dineroUsuario < PRECIO_SEGUNDO_TRAMO_RESIDENTE) {
                        //Añadimos con la función .plus los tramos que corresponda al importe introducido.
                        horaSalida = horaActual.plusMinutes(MINUTOS_PRIMER_TRAMO);

                    } else if (dineroUsuario >= PRECIO_SEGUNDO_TRAMO_RESIDENTE && dineroUsuario < PRECIO_TERCER_TRAMO_RESIDENTE) {
                        horaSalida = horaActual.plusMinutes(MINUTOS_SEGUNDO_TRAMO).plusSeconds(SEGUNDOS_SEGUNDO_TRAMO);
                    } else if (dineroUsuario >= PRECIO_TERCER_TRAMO_RESIDENTE && dineroUsuario < PRECIO_CUARTO_TRAMO_RESIDENTE) {
                        horaSalida = horaActual.plusMinutes(MINUTOS_TERCER_TRAMO).plusSeconds(SEGUNDOS_TERCER_TRAMO);
                    } else if (dineroUsuario >= PRECIO_CUARTO_TRAMO_RESIDENTE && dineroUsuario < PRECIO_QUINTO_TRAMO_RESIDENTE) {
                        horaSalida = horaActual.plusHours(HORA_CUARTO_TRAMO).plusMinutes(MINUTOS_CUARTO_TRAMO).plusSeconds(SEGUNDOS_CUARTO_TRAMO);
                    } else {
                        // Cuando llegamos aquí es que el importe es superior al tramo máximo y le damos la máxima
                        //Duración  en el parking
                        horaSalida = horaActual.plusHours(HORA_QUINTO_TRAMO);

                    }
                } else {
                    //Tarifas para NO residentes.
                    if (dineroUsuario >= PRECIO_PRIMER_TRAMO_NO_RESIDENTE && dineroUsuario < PRECIO_SEGUNDO_TRAMO_NO_RESIDENTE) {
                        horaSalida = horaActual.plusMinutes(MINUTOS_PRIMER_TRAMO);

                    } else if (dineroUsuario >= PRECIO_SEGUNDO_TRAMO_NO_RESIDENTE && dineroUsuario < PRECIO_TERCER_TRAMO_NO_RESIDENTE) {
                        horaSalida = horaActual.plusMinutes(MINUTOS_SEGUNDO_TRAMO).plusSeconds(SEGUNDOS_SEGUNDO_TRAMO);
                    } else if (dineroUsuario >= PRECIO_TERCER_TRAMO_NO_RESIDENTE && dineroUsuario < PRECIO_CUARTO_TRAMO_NO_RESIDENTE) {
                        horaSalida = horaActual.plusMinutes(MINUTOS_TERCER_TRAMO).plusSeconds(SEGUNDOS_TERCER_TRAMO);
                    } else if (dineroUsuario >= PRECIO_CUARTO_TRAMO_NO_RESIDENTE && dineroUsuario < PRECIO_QUINTO_TRAMO_NO_RESIDENTE) {
                        horaSalida = horaActual.plusHours(HORA_CUARTO_TRAMO).plusMinutes(MINUTOS_CUARTO_TRAMO).plusSeconds(SEGUNDOS_CUARTO_TRAMO);
                    } else {
                        horaSalida = horaActual.plusHours(HORA_QUINTO_TRAMO);

                    }
                }

                //Preguntamos al usuario si quiere simular una fecha o no.
                do {
                    respuestaSimulacion = IO.readln("Quieres simular una fecha?: ");
                    if (respuestaSimulacion.equalsIgnoreCase("si")) {
                        //Pedimos los datos de la fecha simulada.
                        anioSimulacion = Integer.parseInt(IO.readln("Introduce el año: "));
                        mesSimulacion = Integer.parseInt(IO.readln("Introduce el mes: "));
                        diaSimulacion = Integer.parseInt(IO.readln("Introduce el día: "));
                        horaSimulacion = Integer.parseInt(IO.readln("Introduce  la hora: "));
                        minutosSimulacion = Integer.parseInt(IO.readln("Introduce los minutos: "));
                        try {
                            fechaSimulada = LocalDateTime.of(anioSimulacion, mesSimulacion, diaSimulacion, horaSimulacion, minutosSimulacion);

                        } catch (DateTimeException e) {
                            IO.println("Error de validación " + e.getMessage());
                        }

                        //TODO Hacer las comprobaciones cuando el usuario quiera sacar un ticket en los tramos no permitidos.

                    } else if (respuestaSimulacion.equalsIgnoreCase("no")) {
                        IO.println("NO");
                    } else {
                        IO.println("Opción no valida.");
                    }
                } while (!respuestaSimulacion.equalsIgnoreCase("si") && !respuestaSimulacion.equalsIgnoreCase("no"));

                    // Esto es un tickect orientativo falta actualizar los datos  cuando hay que hacer cambio de días.
                IO.println("""
                        ╔════════════════════════════════════════╗
                        ║        ZONA AZUL CIUDAD DE MARTOS      ║
                        ║                PARKING                 ║
                        ╠════════════════════════════════════════╣
                        ║ Nº Ticket:                             ║
                        ║ Emitido:       %s - %tT        ║
                        ╠════════════════════════════════════════╣
                        ║ Vencimiento:   %tT             ║
                        ║ Fecha Vto:     %s            ║
                        ╠════════════════════════════════════════╣
                        ║ Importe:       %.2f €                  ║
                        ║ Tipo:          No Residente            ║
                        ╠════════════════════════════════════════╣
                        ║ Control Autenticidad: OK               ║
                        ╚════════════════════════════════════════╝
                        """.formatted(fechaTexto, horaActual, horaSalida, fechaTexto, dineroUsuario));


                break;
            case 2:

                int opcionMultas; //Variable para las opciones del menu multas
                String ticketMulta; // numero de ticket a comprobar si es valido.
                String ticketAnulado; // numero de ticket generado al anular la multa.
                boolean ticketValido; // ticket válido o no.
                int cantidadMultas = 0;
                double recaudacionMultas = 0; //Podria ser entero para esta práctica. Pero en un futuro si cambian los precios, podriamos necesitar un double.

                do {
                    opcionMultas = Integer.parseInt(IO.readln("""
                              ┌──────────────────────────────────────────────────┐
                              │                  ANULAR MULTA                    │
                              ├──────────────────────────────────────────────────┤
                              │   1. Por exceder el tiempo permitido             │
                              │   2. Por carecer de ticket                       │
                              │   0. Volver al menu principal                    │
                              └──────────────────────────────────────────────────┘
                            👉 Elija una opción:"""));
                    switch (opcionMultas) {
                        case 1: //Anular multa por exceso de tiempo permitido
                            ticketMulta = IO.readln("Introduce el número del ticket: ");

                            //Comprobamos si es valido
                            if (ticketMulta.equals("1234")) //TODO aquí deberemos comprobar un código
                                ticketValido = true;
                            else
                                ticketValido = false;

                            //Anulamos la multa y generamos ticket de anulación si es valido el ticket.
                            if (ticketValido) {
                                IO.readln("Ticket correcto. Ingrese 3 € y pulse -Enter- para continuar.");
                                ticketAnulado = "4321AA"; //TODO aquí deberemos generar un código
                                IO.println("Tu ticket de anulación es: %s".formatted(ticketAnulado));
                                cantidadMultas++;
                                recaudacionMultas += 3;
                                IO.println("""
                                        \nCantidad multas: %d
                                        Recaudacion total: %.2f
                                        """.formatted(cantidadMultas, recaudacionMultas));
                            } else
                                IO.println("%s no es un ticket válido. No se ha anulado ninguna multa.".formatted(ticketMulta));

                            break;
                        case 2: //Anular multa por carecer de ticket
                            IO.println("Has seleccionado anular multa por carecer de ticket");
                            break;
                        case 0: //Volver al menu principal
                            break;
                        default:
                            IO.println("Opción introducido no valida.");
                            break;
                    }
                } while (opcionMultas != 0);

                break;
            case 3:

                int opcionConsultas; //Variable para las opciones del menu consultas.
                do { //Creamos un menú para visualizar las distintas opciones
                    opcionConsultas = Integer.parseInt(IO.readln("""
                              ┌──────────────────────────────────────────────────┐
                              │                  MENÚ CONSULTAS                  │
                              ├──────────────────────────────────────────────────┤
                              │   1. Fecha y hora actual                         │
                              │   2. Tarifas                                     │
                              │   3. Horarios                                    │
                              │   0. Volver al menu principal                    │
                              └──────────────────────────────────────────────────┘
                            👉 Elija una opción:"""));
                    switch (opcionConsultas) {
                        case 1: //Mostrar fecha y hora actual
                            //Variables para la muestra de datos de fecha y hora
                            LocalDateTime ahora = LocalDateTime.now(); //registramos la fecha en el momento que pulsa esta opción del menú
                            String diaSemana = String.valueOf(ahora.getDayOfWeek()); //Dia de la semana. Ahora mismo lo imprime en inglés.
                            int dia = ahora.getDayOfMonth(); //Día del mes
                            int mes = ahora.getMonthValue(); //Mes del año
                            int anio = ahora.getYear(); //Año
                            int hora = ahora.getHour(); //Hora
                            int minuto = ahora.getMinute(); //Minuto
                            int segundo = ahora.getSecond(); //Segundo
                            // 1. Preparamos los textos con formato (añadiendo %02d para que los números menores de 10 tengan un 0 delante, ej: 09:05:02)
                            // 1. Preparamos solo los datos (manteniendo los ceros a la izquierda para el formato)
                            String valorFecha = "%s - %02d/%02d/%d".formatted(diaSemana, dia, mes, anio);
                            String valorHora = "%02d:%02d:%02d".formatted(hora, minuto, segundo);
                            // 2. Imprimimos la tabla:
                            IO.println("""
                                      ┌──────────────────────────────────────────────────┐
                                      │               FECHA Y HORA ACTUAL                │
                                      ├──────────┬───────────────────────────────────────┤
                                      │ Fecha    │ %-37s │
                                      │ Hora     │ %-37s │
                                      └──────────┴───────────────────────────────────────┘
                                    """.formatted(valorFecha, valorHora));

                            break;
                        case 2: //Mostrar tarifa
                            IO.println("""
                                      ┌───────────────────────────────────────────────┐
                                      │               TARIFAS ZONA AZUL               │
                                      ├────────────────┬─────────────────┬────────────┤
                                      │    Duración    │  No residentes  │ Residentes │
                                      ├────────────────┼─────────────────┼────────────┤
                                      │    00:15:00    │      0,25 €     │   0,10 €   │
                                      │    00:29:50    │      0,45 €     │   0,20 €   │
                                      │    00:59:31    │      0,85 €     │   0,40 €   │
                                      │    01:29:12    │      1,25 €     │   0,60 €   │
                                      │    02:00:00    │      1,70 €     │   0,80 €   │
                                      └────────────────┴─────────────────┴────────────┘
                                    """);
                            break;
                        case 3: //Mostrar horarios
                            IO.println("""
                                      ┌──────────────────────────────────────────────────┐
                                      │                HORARIO ZONA AZUL                 │
                                      ├──────────────────┬───────────────────────────────┤
                                      │ Lunes a viernes  │ 09:00 - 14:00 y 17:00 - 21:00 │
                                      │ Sábado           │ 09:00 - 14:00                 │
                                      └──────────────────┴───────────────────────────────┘
                                    """);
                            break;
                        case 0: //Volver al menú principal
                            break;
                        default:
                            IO.println("Opción introducido no valida.");
                    }
                } while (opcionConsultas != 0);

                break;
            case 4:
                //Variables para el login del menú Administrador
                String usuario = "Root";
                int contrasenia = 1234;
                boolean loggin = false;

                //Validación del usuario y contraseña.

                while (!loggin) { // Salida del menú o cuando el usuario supere los intentos o porque el usuario mete los datos correctamente.
                    String usuarioCliente = IO.readln("Introduce tu Usuario: ");
                    int contraseniaCliente = Integer.parseInt(IO.readln("Introduce tu contraseña: "));
                    if (contrasenia == contraseniaCliente && usuario.equalsIgnoreCase(usuarioCliente)) { // Aquí validamos que los dos datos introducidos sean los mismos que los que tenemos almacenados
                        IO.println("Acceso correcto");
                        loggin = true;//Salida de nuestro bucle por loguearse.
                    } else {

                        IO.println("Usuario o contraseña no valida.");

                    }
                }
                int opcionAdministrador = 0;
                do {
                    opcionAdministrador = Integer.parseInt(IO.readln("""
                            ┌──────────────────────────────────────────────┐
                            │           MENÚ DE ADMINISTRADOR              │
                            ├──────────────────────────────────────────────┤
                            │  1. Dinero ingresado en tickets y generados  │
                            │  2. Dinero por multas y multas anuladas      │
                            │  3. Apagar el software.                      │
                            │  4. Volver al menú Inicial.                  │
                            └──────────────────────────────────────────────┘
                            👉 Elige una opción:"""));
                    switch (opcionAdministrador) {
                        case 1:
                            IO.println("Opción no implementada");
                            break;
                        case 2:
                            IO.println("Opción no implementada");
                            break;
                        case 3:
                            IO.println("""
                                    Saliendo de la aplicación.
                                    ¡¡¡Vuelva pronto!!!
                                    """);
                            salidaMenu = true;
                    }
                } while (opcionAdministrador != 4 && opcionAdministrador != 3);
                break;
            default:
                IO.println("Opción introducido no valida.");
        }
    } while (!salidaMenu);
}