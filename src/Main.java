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


    int option;
    boolean salidaMenu;
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

            case 1: //Opción de obtener el ticket
                IO.println("Opción no implementada todavía");
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
                                recaudacionMultas +=3;
                                IO.println("""
                                        \nCantidad multas: %d
                                        Recaudacion total: %.2f
                                        """.formatted(cantidadMultas,recaudacionMultas));
                            }
                            else
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