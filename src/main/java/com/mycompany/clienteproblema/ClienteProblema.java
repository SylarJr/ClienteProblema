
package com.mycompany.clienteproblema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClienteProblema {

    public static void main(String[] args) throws IOException {
  Socket salida = new Socket("localhost", 8080);
        PrintWriter escritor = new PrintWriter(salida.getOutputStream(), true);
        BufferedReader lector = new BufferedReader(new InputStreamReader(salida.getInputStream()));
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

        boolean esperandoMenu = true; 
        String opcion = "";

        while (!opcion.equalsIgnoreCase("fin")) {

           
            if (esperandoMenu) {
                System.out.println("\nSeleccione una opción:"
                        + "\n[1]-Ingresar"
                        + "\n[2]-Registrar se"
                        + "\n[3]-Salir"
                        + "\n[4]-Ver mensajes"
                        + "\n[5]-Borrar mensajes específicos de usuario"
                        + "\n[6]-Borrar todos los mensajes");
                opcion = teclado.readLine();
                escritor.println(opcion);
            }

            esperandoMenu = true; 

            String mensaje;
            while ((mensaje = lector.readLine()) != null) {

                
                if (mensaje.equals("FIN_HISTORIAL")) {
                    break;
                }

                System.out.println(mensaje);

       
                if (mensaje.endsWith(":")) {
                    String respuesta = teclado.readLine();
                    escritor.println(respuesta);
                    esperandoMenu = false; 
                }

           
                if (mensaje.startsWith("Pudo iniciar sesion")) {
                    boolean enChat = true;
                    while (enChat) {
                        System.out.print("Tu: ");
                        String mensajeChat = teclado.readLine();
                        escritor.println(mensajeChat);

                        if (mensajeChat.equalsIgnoreCase("fin")) {
                            enChat = false;
                            break;
                        }

                        String respuestaServidor = lector.readLine();
                        if (respuestaServidor != null) {
                            System.out.println(respuestaServidor);
                        }
                    }
                }

               
                if (mensaje.equalsIgnoreCase("Operación cancelada. Regresando al menu") ||
                        mensaje.equalsIgnoreCase("Mensaje borrado correctamente") ||
                        mensaje.equalsIgnoreCase("No hay historial de chat para") ||
                        mensaje.equalsIgnoreCase("No hay mensajes para borrar") ||
                        mensaje.equalsIgnoreCase("Numero inválido. Regresando al menu") ||
                        mensaje.equalsIgnoreCase("Numero fuera de rango. Regresando al menu") ||
                        mensaje.equalsIgnoreCase("Su usuario se registro exitosamente ") ||
                        mensaje.equalsIgnoreCase("No se puede poner ese nombre de usuario")) {
                    break;
                }

            }

        }

        salida.close();
        System.out.println("Conexion cerrada.");
    }
}
