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
    int option, diaSimulacion, mesSimulacion, anioSimulacion, horaSimulacion, minutosSimulacion, numeroTicket = 0, numeroMulta = 0;

    double dineroUsuario = 0, importeFinal = 0, horasTramo = 0, minutosTramo = 0, segundosTramo = 0, recaudacionTicket = 0, recaudacionMultas = 0;
    //Tarifa de precios para los residentes
    final double PRECIO_PRIMER_TRAMO_RESIDENTE = 0.10, PRECIO_SEGUNDO_TRAMO_RESIDENTE = 0.20;
    final double PRECIO_TERCER_TRAMO_RESIDENTE = 0.40, PRECIO_CUARTO_TRAMO_RESIDENTE = 0.60;
    final double PRECIO_QUINTO_TRAMO_RESIDENTE = 0.80;


    //Tarifa de precios para los no residentes.
    final double PRECIO_PRIMER_TRAMO_NO_RESIDENTE = 0.25, PRECIO_SEGUNDO_TRAMO_NO_RESIDENTE = 0.45;
    final double PRECIO_TERCER_TRAMO_NO_RESIDENTE = 0.85, PRECIO_CUARTO_TRAMO_NO_RESIDENTE = 1.25;
    final double PRECIO_QUINTO_TRAMO_NO_RESIDENTE = 1.70;

    // Tramos horarios.

    final int HORAS_PRIMER_TRAMO = 0, MINUTOS_PRIMER_TRAMO = 15, SEGUNDOS_PRIMER_TRAMO = 0;
    final int HORAS_SEGUNDO_TRAMO = 0, MINUTOS_SEGUNDO_TRAMO = 29, SEGUNDOS_SEGUNDO_TRAMO = 50;
    final int HORAS_TERCER_TRAMO = 0, MINUTOS_TERCER_TRAMO = 59, SEGUNDOS_TERCER_TRAMO = 31;
    final int HORAS_CUARTO_TRAMO = 1, MINUTOS_CUARTO_TRAMO = 29, SEGUNDOS_CUARTO_TRAMO = 12;
    final int HORAS_QUINTO_TRAMO = 2, MINUTOS_QUINTO_TRAMO = 0, SEGUNDOS_QUINTO_TRAMO = 0;
    boolean salidaMenu, datoValido, dineroValido, residente, fechaValida = false;
    String datosUsuario, respuestaSimulacion, codigoTicket = "";
    do {
        //Limpiamos la pantalla
        for (int i = 0; i < 30; i++) {
            IO.println();
        }
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
                LocalDateTime fechaActual = LocalDateTime.now();
                LocalDateTime fechaSalida = LocalDateTime.now();
                LocalDateTime fechaSimulada;
                LocalDateTime horaLimite;
                datoValido = false;
                dineroValido = false;
                residente = false;
                codigoTicket = "";

                //Limpiamos la pantalla
                for (int i = 0; i < 30; i++) {
                    IO.println();
                }

                do {
                    // Bloque para validar que el usuario es residente o no.
                    datosUsuario = IO.readln("¿Es usted residente? (S/N): ");
                    if (datosUsuario.equalsIgnoreCase("si")) {
                        residente = true;
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
                    if (!residente) {
                        /*
                        Controlamos que los no residentes no metan menos de 0.25 y que no pueda meter la tarifa más
                        baja de los residentes y vicerversa.
                         */

                        if (dineroUsuario > 2 || dineroUsuario < 0.25) {
                            IO.println("Error al introducir el importe.");
                        } else {
                            dineroValido = true;
                        }
                    } else {
                        //Controlamos que los residentes no puedan meter menos del mínimo de su categoría.
                        if (dineroUsuario > 2 || dineroUsuario < 0.10) {
                            IO.println("Error al introducir el importe.");
                        } else {
                            dineroValido = true;
                        }
                    }
                }
                while (!dineroValido);

                //A continuación vamos a asignar los tramos según el importe introducido

                //Tarifas para los residentes.
                int tramo = 0;

                if (residente) {
                    if (dineroUsuario >= PRECIO_PRIMER_TRAMO_RESIDENTE && dineroUsuario < PRECIO_SEGUNDO_TRAMO_RESIDENTE) {
                        //Añadimos con la función.plus los tramos que corresponda al importe introducido.
                        tramo = 1;
                    } else if (dineroUsuario >= PRECIO_SEGUNDO_TRAMO_RESIDENTE && dineroUsuario < PRECIO_TERCER_TRAMO_RESIDENTE) {
                        tramo = 2;
                    } else if (dineroUsuario >= PRECIO_TERCER_TRAMO_RESIDENTE && dineroUsuario < PRECIO_CUARTO_TRAMO_RESIDENTE) {
                        tramo = 3;
                    } else if (dineroUsuario >= PRECIO_CUARTO_TRAMO_RESIDENTE && dineroUsuario < PRECIO_QUINTO_TRAMO_RESIDENTE) {
                        tramo = 4;
                    } else {
                        // Cuando llegamos aquí es que el importe es superior al tramo máximo y le damos la máxima
                        //Duración  en el parking
                        tramo = 5;
                    }
                } else {
                    //Tarifas para NO residentes.
                    if (dineroUsuario >= PRECIO_PRIMER_TRAMO_NO_RESIDENTE && dineroUsuario < PRECIO_SEGUNDO_TRAMO_NO_RESIDENTE) {
                        tramo = 1;
                    } else if (dineroUsuario >= PRECIO_SEGUNDO_TRAMO_NO_RESIDENTE && dineroUsuario < PRECIO_TERCER_TRAMO_NO_RESIDENTE) {
                        tramo = 2;
                    } else if (dineroUsuario >= PRECIO_TERCER_TRAMO_NO_RESIDENTE && dineroUsuario < PRECIO_CUARTO_TRAMO_NO_RESIDENTE) {
                        tramo = 3;
                    } else if (dineroUsuario >= PRECIO_CUARTO_TRAMO_NO_RESIDENTE && dineroUsuario < PRECIO_QUINTO_TRAMO_NO_RESIDENTE) {
                        tramo = 4;
                    } else {
                        tramo = 5;
                    }
                }
                //todo asignar los valores de los tramos a las nuevas Variables.
                // Una vez que conocemos el tramo unificamos las variables de horas minutos segundos.
                switch (tramo) {
                    case 1:
                        horasTramo = HORAS_PRIMER_TRAMO;
                        minutosTramo = MINUTOS_PRIMER_TRAMO;
                        segundosTramo = SEGUNDOS_PRIMER_TRAMO;
                        break;

                    case 2:
                        horasTramo = HORAS_SEGUNDO_TRAMO;
                        minutosTramo = MINUTOS_SEGUNDO_TRAMO;
                        segundosTramo = SEGUNDOS_SEGUNDO_TRAMO;

                        break;
                    case 3:
                        horasTramo = HORAS_TERCER_TRAMO;
                        minutosTramo = MINUTOS_TERCER_TRAMO;
                        segundosTramo = SEGUNDOS_TERCER_TRAMO;
                        break;
                    case 4:
                        horasTramo = HORAS_CUARTO_TRAMO;
                        minutosTramo = MINUTOS_CUARTO_TRAMO;
                        segundosTramo = SEGUNDOS_CUARTO_TRAMO;
                        break;
                    case 5:
                        horasTramo = HORAS_QUINTO_TRAMO;
                        minutosTramo = MINUTOS_QUINTO_TRAMO;
                        segundosTramo = SEGUNDOS_QUINTO_TRAMO;
                        break;

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
                            fechaActual = fechaSimulada;
                            fechaValida = true;
                        } catch (DateTimeException e) {
                            IO.println("Error de validación " + e.getMessage());
                            fechaValida = false;
                        }
                    } else if (respuestaSimulacion.equalsIgnoreCase("no")) {
                        fechaActual = LocalDateTime.now();
                        fechaValida = true;
                    } else {
                        IO.println("Opción no valida.");
                    }
                } while (!respuestaSimulacion.equalsIgnoreCase("si") && !respuestaSimulacion.equalsIgnoreCase("no"));

                //Trabajamos solamente con la fecha del usuario, teniendo en cuenta de si es simulación o no.

                switch (fechaActual.getDayOfWeek()) {
                    case DayOfWeek.MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY:
                        //Tramo de menos de las 9 de la mañana.
                        if (fechaActual.getHour() < 9) {
                            fechaSalida = fechaActual.withHour(9).withMinute(0).withSecond(0);
                            fechaSalida = fechaSalida.plusHours((long) horasTramo).plusMinutes((long) minutosTramo).plusSeconds((long) segundosTramo);
                            IO.println("Fecha Salida: " + fechaSalida);

                        }
                        //Tramo desde las 9 hasta las 2.
                        if (fechaActual.getHour() >= 9 && fechaActual.getHour() < 14) {
                            fechaSalida = fechaActual;
                            horaLimite = fechaActual.withHour(14).withMinute(0).withSecond(0);
                            Duration horasQuedan = Duration.between(fechaActual, horaLimite);
                            Duration duracionTotal = Duration.ofHours((long) horasTramo).plusMinutes((long) minutosTramo).plusSeconds((long) segundosTramo);
                            if (horasQuedan.compareTo(duracionTotal) > 0) {
                                fechaSalida = fechaSalida.plusHours((long) horasTramo).plusMinutes((long) minutosTramo).plusSeconds((long) segundosTramo);
                            } else {
                                //Lógica para controlar el tiempo de parking en las zonas de desborde.
                                //Primero calculamos el tiempo consumido hasta la hora de cierre.
                                Duration tiempoConsumido = Duration.between(fechaActual, horaLimite);
                                //Duración de tu ticket.

                                // Diferencia entre el tiempo consumido y el tiempo total de tu ticket
                                Duration tiempoRestante = duracionTotal.minus(tiempoConsumido);
                                //Reseteamos la fecha de salida a las 17:00
                                fechaSalida = fechaActual.withHour(17).withMinute(0).withSecond(0);
                                //Le añadimos el tiempo restante.
                                fechaSalida = fechaSalida.plus(tiempoRestante);


                            }
                        }
                        //Tramo desde las 14 hasta las 17

                        if (fechaActual.getHour() >= 14 && fechaActual.getHour() < 17) {
                            //Reseteamos el reloj a las 17:00
                            fechaSalida = fechaActual.withHour(17).withMinute(0).withSecond(0);
                            //Le sumamos la duración de nuestro ticket
                            fechaSalida = fechaSalida.plusHours((long) horasTramo).plusMinutes((long) minutosTramo).plusSeconds((long) segundosTramo);
                        }

                        //Tramo Desde las 17 a 21:00

                        if (fechaActual.getHour() >= 17 && fechaActual.getHour() < 21) {
                            fechaSalida = fechaActual;
                            horaLimite = fechaActual.withHour(21).withMinute(0).withSecond(0);
                            Duration horasQuedan = Duration.between(fechaActual, horaLimite);
                            Duration duracionTotal = Duration.ofHours((long) horasTramo).plusMinutes((long) minutosTramo).plusSeconds((long) segundosTramo);
                            if (horasQuedan.compareTo(duracionTotal) > 0) {
                                fechaSalida = fechaSalida.plusHours((long) horasTramo).plusMinutes((long) minutosTramo).plusSeconds((long) segundosTramo);
                            } else {
                                //Lógica para controlar el tiempo de parking en las zonas de desborde.
                                //Primero calculamos el tiempo consumido hasta la hora de cierre.
                                Duration tiempoConsumido = Duration.between(fechaActual, horaLimite);
                                //Duración de tu ticket.

                                // Diferencia entre el tiempo consumido y el tiempo total de tu ticket
                                Duration tiempoRestante = duracionTotal.minus(tiempoConsumido);
                                //Reseteamos la fecha de salida a las 17:00
                                fechaSalida = fechaActual.withHour(9).withMinute(0).withSecond(0);
                                fechaSalida = fechaSalida.plusDays(1);

                                //Le añadimos el tiempo restante.
                                fechaSalida = fechaSalida.plus(tiempoRestante);


                            }
                        }
                        //Tramo 21 hacia adelante.

                        if (fechaActual.getHour() >= 21) {
                            fechaSalida = fechaActual.withHour(9).withMinute(0).withSecond(0);
                            fechaSalida = fechaSalida.plusDays(1);

                            fechaSalida = fechaSalida.plusHours((long) horasTramo).plusMinutes((long) minutosTramo).plusSeconds((long) segundosTramo);
                        }


                        break;
                    case DayOfWeek.SATURDAY: {
                        //Tramo antes de las 9
                        if (fechaActual.getHour() < 9) {
                            fechaSalida = fechaActual.withHour(9).withMinute(0).withSecond(0);
                            fechaSalida = fechaSalida.plusHours((long) horasTramo).plusMinutes((long) minutosTramo).plusSeconds((long) segundosTramo);
                        }
                        //Tramo desde las 9 hasta las 2.
                        if (fechaActual.getHour() >= 9 && fechaActual.getHour() < 14) {
                            fechaSalida = fechaActual;
                            horaLimite = fechaActual.withHour(14).withMinute(0).withSecond(0);
                            Duration horasQuedan = Duration.between(fechaActual, horaLimite);
                            Duration duracionTotal = Duration.ofHours((long) horasTramo).plusMinutes((long) minutosTramo).plusSeconds((long) segundosTramo);
                            if (horasQuedan.compareTo(duracionTotal) > 0) {
                                fechaSalida = fechaSalida.plusHours((long) horasTramo).plusMinutes((long) minutosTramo).plusSeconds((long) segundosTramo);
                            } else {
                                //Lógica para controlar el tiempo de parking en las zonas de desborde.
                                //Primero calculamos el tiempo consumido hasta la hora de cierre.
                                Duration tiempoConsumido = Duration.between(fechaActual, horaLimite);
                                //Duración de tu ticket.

                                // Diferencia entre el tiempo consumido y el tiempo total de tu ticket
                                Duration tiempoRestante = duracionTotal.minus(tiempoConsumido);
                                //Reseteamos la fecha de salida a las 17:00
                                fechaSalida = fechaActual.withHour(9).withMinute(0).withSecond(0);
                                fechaSalida = fechaSalida.plusDays(2);
                                //Le añadimos el tiempo restante.
                                fechaSalida = fechaSalida.plus(tiempoRestante);


                            }

                        }
                        //Tramo desde las 2 hacia adelante.
                        if (fechaActual.getHour() >= 14) {
                            fechaSalida = fechaActual.withHour(9).withMinute(0).withSecond(0);
                            fechaSalida = fechaSalida.plusDays(2);

                            fechaSalida = fechaSalida.plusHours((long) horasTramo).plusMinutes((long) minutosTramo).plusSeconds((long) segundosTramo);
                        }

                    }
                    break;
                    case DayOfWeek.SUNDAY: {
                        //Para los domingos
                        fechaSalida = fechaActual.withHour(9).withMinute(0).withSecond(0);
                        fechaSalida = fechaSalida.plusDays(1);

                        fechaSalida = fechaSalida.plusHours((long) horasTramo).plusMinutes((long) minutosTramo).plusSeconds((long) segundosTramo);
                        break;
                    }
                }
                // Recaudamos tanto el dinero como el número de ticket si la fecha es correcta.
                if (fechaValida) {
                    recaudacionTicket += dineroUsuario; //Vamos metiendo la recaudación de cada ticket.
                    numeroTicket++; //Sumamos un ticket.
                }

                //Generamos un código.
                if (fechaValida) {
                    DateTimeFormatter fechaCodigoTicket = DateTimeFormatter.ofPattern("MMdd");
                    codigoTicket += fechaActual.format(fechaCodigoTicket);
                    codigoTicket += residente ? "SR" : "NR";
                    codigoTicket += "MAR";
                    codigoTicket += String.valueOf(numeroTicket);
                }


                DateTimeFormatter fechaFormateada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String fechaTextoEmitido = fechaActual.format(fechaFormateada);
                String fechaTextoVencimiento = fechaSalida.format(fechaFormateada);
                //TODO crear una variable booleana para imprimir SOLO una fecha valida.
                // Esto es un tickect orientativo falta actualizar los datos  cuando hay que hacer cambio de días.

                //Limpiamos la pantalla
                for (int i = 0; i < 30; i++) {
                    IO.println();
                }

                if (fechaValida) {
                    //Creamos el efecto de estar generando el ticket.
                    IO.print("Generando ticket");
                    for (int i = 0; i <= 2; i++) {
                        IO.print(".");
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    IO.println();
                    //Imprimimos el ticket
                    IO.println("""
                            ╔════════════════════════════════════════╗
                            ║        ZONA AZUL CIUDAD DE MARTOS      ║
                            ║                PARKING                 ║
                            ╠════════════════════════════════════════╣
                            ║ Nº Ticket:     %-12s            ║
                            ║ Emitido:       %s - %tT   ║
                            ╠════════════════════════════════════════╣
                            ║ Vencimiento:   %tT                ║
                            ║ Fecha Vto:     %s              ║
                            ╠════════════════════════════════════════╣
                            ║ Importe:       %.2f €                  ║
                            ║ Tipo:          %s            ║
                            ╚════════════════════════════════════════╝
                            """.formatted(codigoTicket, fechaTextoEmitido, fechaActual, fechaSalida, fechaTextoVencimiento, dineroUsuario,
                            (residente) ? "Residente   " : "No residente"));
                }
                IO.readln("Pulsa enter para volver al menú."); //Esperamos a que el usuario pulse enter para continuar


                break;
            case 2:

                int opcionMultas, importeMulta = 0; //Variable para las opciones del menu multas
                String ticketMulta =""; // numero de ticket a comprobar si es valido.
                String ticketAnulado = ""; // numero de ticket generado al anular la multa.
                boolean ticketValido = true; // ticket válido o no.
                LocalDateTime fechaMulta = LocalDateTime.now();
                DateTimeFormatter fechaMultaFormateada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String fechaMultaTexto = fechaMulta.format(fechaMultaFormateada);

                //Limpiamos la pantalla
                for (int i = 0; i < 30; i++) {
                    IO.println();
                }

                do {

                    for (int i = 0; i < 30; i++) {
                        IO.println();
                    }

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
                            int diaMulta, mesMulta;
                            ticketValido = true;

                            //Limpiamos la pantalla
                            for (int i = 0; i < 30; i++) {
                                IO.println();
                            }

                            //Pedimos el numero de ticket y comprobamos si es valido
                            ticketMulta = IO.readln("Introduce el número del ticket: ");

                            //Comprobamos la longitud (minimo 10)
                            if (ticketMulta.length() < 10) {
                                ticketValido = false;
                            } else {
                                //Comprobamos que los 4 primeros numeros sean digitos
                                for (int i = 0; i < 4; i++) {
                                    if (!Character.isDigit(ticketMulta.charAt(i))) {
                                        ticketValido = false;
                                        break;
                                    }
                                }

                                //Comprobamos que sea una fecha válida
                                if (ticketValido) { //Pongo condicion para que solo haga esto si previamente ha detectado 4 digitos.
                                    mesMulta = Integer.parseInt(ticketMulta.substring(0,2));
                                    diaMulta = Integer.parseInt(ticketMulta.substring(2,4));
                                    try {
                                        MonthDay.of(mesMulta,diaMulta);
                                    } catch (DateTimeException e) {
                                        ticketValido = false;
                                    }
                                }

                                //Comprobamos las posiciones 4 y 5 (SR o NR)
                                //if (!ticketMulta.startsWith("SR", 4) && !ticketMulta.startsWith("NR", 4)) ticketValido = false;
                                if (!ticketMulta.substring(4,6).equals("SR") && !ticketMulta.substring(4,6).equals("NR")) ticketValido = false;

                                //Comprobamos las posiciones 4 y 5 (SR o NR)
                                if (!ticketMulta.substring(6,9).equals("MAR")) ticketValido = false;

                                //Comprobamos que no haya un 0 en la posicion 9
                                if (ticketMulta.charAt(9) == '0') ticketValido = false;

                                //Comprobamos que los últimos numeros sean digitos
                                for (int i = 9; i < ticketMulta.length(); i++) {
                                    if (!Character.isDigit(ticketMulta.charAt(i))) {
                                        ticketValido = false;
                                        break;
                                    }
                                }

                            }
                            //Anulamos la multa y generamos ticket de anulación si es valido el ticket.
                            if (ticketValido) {
                                IO.println("El ticket correcto. ");
                                do {
                                    importeMulta = Integer.parseInt(IO.readln("Ingrese 3 € para anular la multa: "));
                                    if (importeMulta != 3) {
                                        IO.println("Importe insuficiente.");
                                    }

                                } while (importeMulta != 3);

                                ticketAnulado = "4321AA"; //TODO aquí deberemos generar un código    1392A2
                                numeroMulta++;
                                recaudacionMultas += 3;

                            } else
                                IO.println("%s no es un ticket válido. No se ha anulado ninguna multa.".formatted(ticketMulta));

                            break;
                        case 2: //Anular multa por carecer de ticket

                            //Limpiamos la pantalla
                            for (int i = 0; i < 30; i++) {
                                IO.println();
                            }

                            do {
                                importeMulta = Integer.parseInt(IO.readln("Ingrese 6 € para anular la multa: "));
                                if (importeMulta != 6) {
                                    IO.println("Importe Erroneo.");
                                }

                            } while (importeMulta != 6);
                            ticketAnulado = "4321BB"; //TODO aquí deberemos generar un código    1392A2
                            numeroMulta++;
                            recaudacionMultas += 6;

                            break;
                        case 0: //Volver al menu principal
                            ticketValido = false;
                            break;
                        default:
                            ticketValido = false;
                            IO.println("Opción introducido no valida.");
                            break;
                    }
                    if (ticketValido) {
                        //Limpiamos la pantalla
                        for (int i = 0; i < 30; i++) {
                            IO.println();
                        }

                        //Creamos el efecto de estar generando el ticket.
                        IO.print("Generando ticket");
                        for (int i = 0; i <= 2; i++) {
                            IO.print(".");
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        IO.println();
                        //Imprimimos el ticket
                        IO.println("""
                            ╔════════════════════════════════════════╗
                            ║        ZONA AZUL CIUDAD DE MARTOS      ║
                            ║                PARKING                 ║
                            ╠════════════════════════════════════════╣
                            ║ Ticket multa:     %-12s         ║
                            ║ Fecha:            %s           ║
                            ╠════════════════════════════════════════╣
                            ║ Importe:          %s €                  ║
                            ║ Tipo:             %-12s     ║
                            ╚════════════════════════════════════════╝
                            """.formatted(ticketAnulado,fechaMultaTexto,(opcionMultas == 1 ? "3" : "6"),(opcionMultas == 1 ? "Exceso de tiempo" : "Carecer de ticket")));
                    }

                    IO.readln("Pulsa enter para volver al menú."); //Esperamos a que el usuario pulse enter para continuar
                } while (opcionMultas != 0);

                break;
            case 3:

                int opcionConsultas; //Variable para las opciones del menu consultas.

                //Limpiamos la pantalla
                for (int i = 0; i < 30; i++) {
                    IO.println();
                }

                do { //Creamos un menú para visualizar las distintas opciones

                    for (int i = 0; i < 30; i++) {
                        IO.println();
                    }

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

                            //Limpiamos la pantalla
                            for (int i = 0; i < 30; i++) {
                                IO.println();
                            }

                            // 2. Imprimimos la tabla:
                            IO.println("""
                                      ┌──────────────────────────────────────────────────┐
                                      │               FECHA Y HORA ACTUAL                │
                                      ├──────────┬───────────────────────────────────────┤
                                      │ Fecha    │ %-37s │
                                      │ Hora     │ %-37s │
                                      └──────────┴───────────────────────────────────────┘
                                    """.formatted(valorFecha, valorHora));
                            IO.readln("Pulsa enter para volver al menú."); //Esperamos a que el usuario pulse enter para continuar

                            break;
                        case 2: //Mostrar tarifa

                            //Limpiamos la pantalla
                            for (int i = 0; i < 30; i++) {
                                IO.println();
                            }

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
                            IO.readln("Pulsa enter para volver al menú."); //Esperamos a que el usuario pulse enter para continuar
                            break;
                        case 3: //Mostrar horarios

                            //Limpiamos la pantalla
                            for (int i = 0; i < 30; i++) {
                                IO.println();
                            }

                            IO.println("""
                                      ┌──────────────────────────────────────────────────┐
                                      │                HORARIO ZONA AZUL                 │
                                      ├──────────────────┬───────────────────────────────┤
                                      │ Lunes a viernes  │ 09:00 - 14:00 y 17:00 - 21:00 │
                                      │ Sábado           │ 09:00 - 14:00                 │
                                      └──────────────────┴───────────────────────────────┘
                                    """);
                            IO.readln("Pulsa enter para volver al menú."); //Esperamos a que el usuario pulse enter para continuar
                            break;
                        case 0: //Volver al menú principal
                            break;
                        default:
                            IO.println("Opción introducida no valida.");
                    }
                } while (opcionConsultas != 0);

                break;
            case 4:
                //Variables para el login del menú Administrador
                String usuario = "Root";
                int contrasenia = 1234;
                boolean loggin = false;

                //Limpiamos la pantalla
                for (int i = 0; i < 30; i++) {
                    IO.println();
                }

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

                    //Limpiamos la pantalla
                    for (int i = 0; i < 30; i++) {
                        IO.println();
                    }

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

                            //Limpiamos la pantalla
                            for (int i = 0; i < 30; i++) {
                                IO.println();
                            }



                            IO.println("""
                            ┌──────────────────────────────────────────────┐
                            │               INFORME TICKET.                │
                            ├──────────────────────────────────────────────┤
                            │  Recaudación Total: %.2f €                   │
                            │  Número de tickets generados: %d              │
                            └──────────────────────────────────────────────┘
                            """.formatted(recaudacionTicket,numeroTicket));

                            IO.readln("Pulsa enter para volver al menú."); //Esperamos a que el usuario pulse enter para continuar
                            break;
                        case 2:

                            //Limpiamos la pantalla
                            for (int i = 0; i < 30; i++) {
                                IO.println();
                            }

                            IO.println("""
                            ┌──────────────────────────────────────────────┐
                            │               INFORME TICKET.                │
                            ├──────────────────────────────────────────────┤
                            │  Recaudación Total: %.2f €                   │
                            │  Número de multas generadas: %d               │
                            └──────────────────────────────────────────────┘
                            """.formatted(recaudacionMultas,numeroMulta));
                            IO.readln("Pulsa enter para volver al menú."); //Esperamos a que el usuario pulse enter para continuar
                            break;
                        case 3:

                            //Limpiamos la pantalla
                            for (int i = 0; i < 30; i++) {
                                IO.println();
                            }

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
    }
    while (!salidaMenu);
}