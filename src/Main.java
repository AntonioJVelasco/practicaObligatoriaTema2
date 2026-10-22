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
                                          (o)  (o)   (o)  (o)
                                         /  \\\\ /  \\\\  /  \\\\ /  \\\\
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
                IO.println("Opción no implementada todavía.");
                break;
            case 3:
                LocalDateTime ahora = LocalDateTime.now();
                String diaSemana = String.valueOf(ahora.getDayOfWeek());
                int dia = ahora.getDayOfMonth();
                String mes = String.valueOf(ahora.getMonth());
                int anio = ahora.getYear();
                IO.println("\t - Fecha: %s - %d of %s of %d".formatted(diaSemana,dia,mes,anio));
                int hora = ahora.getHour();
                int minuto = ahora.getMinute();
                int segundo = ahora.getSecond();
                IO.println("\t - Hora actual: %d:%d:%d".formatted(hora,minuto,segundo));
                IO.println("""
        \t - Tarifas
        
        Duración      No residentes    Residentes
        00:15:00      0,25 €           0,10 €
        00:29:50      0,45 €           0,20 €
        00:59:31      0,85 €           0,40 €
        01:29:12      1,25 €           0,60 €
        02:00:00      1,70 €           0,80 €
        """);
                IO.println("""
        \t - Horario
        
        Lunes a viernes    09:00 - 14:00  y  17:00 - 21:00
        Sábado             09:00 - 14:00
        """);
                break;
            case 4:
                //Variables para el login del menú Administrador
                String usuario = "Paco";
                int contrasenia = 1234, contador = 0;
                boolean loggin = false;

                //Validación del usuario y contraseña.

                while (contador < 3 && !loggin){ // Salida del menú o cuando el usuario supere los intentos o porque el usuario mete los datos correctamente.
                    String usuarioCliente = IO.readln("Introduce tu Usuario: ");
                    int contraseniaCliente = Integer.parseInt(IO.readln("Introduce tu contraseña: "));
                    if(contrasenia == contraseniaCliente && usuario.equalsIgnoreCase(usuarioCliente)){ // Aquí validamos que los dos datos introducidos sean los mismos que los que tenemos almacenados
                        IO.println(" Acceso correcto");
                        loggin = true;//Salida de nuestro bucle por loguearse.
                    }else{
                        contador++;//Sumamos un fallo si introduce un dato erróneo.
                        IO.println("Usuario o contraseña no valida. Te quedan: " + (3 - contador) + " intentos");

                    }
                    if(contador == 3){
                        IO.println("""
                                Numero de Intentos máximos permitidos.
                                Acceso bloqueado.
                                """);
                        return; //Salida del flujo del menú principal. //Preguntarle si esta opción es viable y si cuando se bloquea el usuario se cierra la app o volvemos al flujo del menú principal.
                    }
                }
                int opcionAdministrador = Integer.parseInt(IO.readln("""
        ┌──────────────────────────────────────────────┐
        │           MENÚ DE ADMINISTRADOR              │
        ├──────────────────────────────────────────────┤
        │  1. Dinero ingresado en tickets y generados  │
        │  2. Dinero por multas y multas anuladas      │
        │  3. Apagar el software                       │
        └──────────────────────────────────────────────┘
        👉 Elige una opción:
        """));
                switch (opcionAdministrador){
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
                break;
            default:
                IO.println("Opción introducido no valida.");
        }
    } while (!salidaMenu);
}