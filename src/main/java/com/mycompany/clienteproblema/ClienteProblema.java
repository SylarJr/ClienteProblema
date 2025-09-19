
package com.mycompany.clienteproblema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClienteProblema {

    public static void main(String[] args) throws IOException {
               System.out.println("Bienvenido, seleccione. "
                + "\n[1]-Ingresar"+ 
                       "\n[2]-registrar se" + 
                       "\n[3]-salir" + 
                       "\n[4]-Ver mensajes" + 
                       "\n[5]-Borrar mensajes espesificos de usuario"+
                       "\n[6]- Borrar todos los mensajes");
                       
        
       Socket salida = new Socket("localhost", 8080);
        PrintWriter escritor = new PrintWriter(salida.getOutputStream(), true);
        BufferedReader lector = new BufferedReader(new InputStreamReader(salida.getInputStream()));
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        String cadena = teclado.readLine();
        String mensaje;
        

       while (!cadena.equalsIgnoreCase("fin")) {
    escritor.println(cadena);

    
    while ((mensaje = lector.readLine()) != null) {
        if (mensaje.equals("FIN_HISTORIAL")) {
           
            break;
        }
        System.out.println(mensaje);

        
        if (mensaje.endsWith(":")) {
            
            cadena = teclado.readLine();
            escritor.println(cadena);
        }
    }

   
   System.out.println("Bienvenido, seleccione. "
                + "\n[1]-Ingresar"+ 
                       "\n[2]-registrar se" + 
                       "\n[3]-salir" + 
                       "\n[4]-Ver mensajes" + 
                       "\n[5]-Borrar mensajes espesificos de usuario"+
                       "\n[6]- Borrar todos los mensajes");
                       

    cadena = teclado.readLine();

}
        salida.close();

    
    }
}
