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
                IO.println("Opción no implementada todavía.");
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