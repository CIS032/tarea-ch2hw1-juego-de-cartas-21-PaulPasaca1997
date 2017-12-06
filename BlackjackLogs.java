/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JuegoCartas;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

/**
 *
 * @author DELL
 */
public class BlackjackLogs {
      
      
  public static void main(String[] args) throws IOException {

    
        //Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
        File archivo=new File("logs.txt");

        //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
        FileWriter escribir=new FileWriter(archivo,true);

   
      int dinero;          // Cantidad de dinero que tiene el usuario.
      int apuesta;            // Cantidad de apuestas del usuario en una partida.
      boolean usuarioGana;   // ¿Ganó el usuario el juego?
      
      String inicio="Bienvenido al juego de Blackjack.\r\n";
      
      System.out.println(inicio);
      System.out.println();
      
      //Escribimos en el archivo con el metodo write
        escribir.write(inicio);//////////////////////////////
        
      dinero = 100;  // El usuario comienza con $100.
      String mensaje1="";
      while (true) {
           mensaje1="Tu tienes " + dinero + " dolares.";
           
          System.out.println(mensaje1);
          
          escribir.write(mensaje1);//////////////////////////////
        
          do {
              String mensaje2="¿Cuántos dólares quieres apostar? (Ingrese 0 para finalizar)\r\n";
             
             System.out.println(mensaje2);
             System.out.print("? ");
             escribir.write(mensaje2);//////////////////////////////
             apuesta = TextIO.getlnInt();
             escribir.write(apuesta+"\r\n");//////////////////////////////
             String mensaje3="";
             if (apuesta < 0 || apuesta > dinero)
                 mensaje3 = "Su respuesta debe estar entre 0 y" + dinero + ".\r\n";
            
                  System.out.println(mensaje3);
                 escribir.write(mensaje3);//////////////////////////////
          } while (apuesta < 0 || apuesta > dinero);
          if (apuesta == 0)
             break;
          usuarioGana = playBlackjack();
          if (usuarioGana)
             dinero = dinero + apuesta;
          else
             dinero = dinero - apuesta;
          System.out.println();
          if (dinero == 0) {
              String mensaje4="¡Parece que te has quedado sin dinero!\r\n\r\n";
             
             System.out.println(mensaje4);
             escribir.write(mensaje4);//////////////////////////////
             break;
          }
      }
      
      System.out.println();
      String mensaje5="Te vas con $" + dinero + ".\r\n";
     
      System.out.println(mensaje5);
      escribir.write(mensaje5);//////////////////////////////
      
      //Cerramos la conexion
        escribir.close();
     
}//Fin de main
       
       
       
       
 
   /**
    Deje que el usuario juegue un juego de Blackjack, con el ordenador como repartidor.
    Devuelve true si el usuario gana el juego, false si el usuario pierde.
    */  
   static boolean playBlackjack() throws IOException  {
     
      
      File archivo=new File("logs.txt");
       
       FileWriter escribir=new FileWriter(archivo,true);
       
                     
      Deck mazo;                  // Un mazo de cartas.  Una nueva baraja para cada juego.
      BlackjackHand manoRepartidor;   // La mano del repartidor.
      BlackjackHand manoUsuario;     // La mano del usuario.
      
      mazo = new Deck();
      manoRepartidor = new BlackjackHand();
      manoUsuario = new BlackjackHand();

      //  Baraja el mazo, luego reparte dos cartas a cada jugador.
      
      mazo.barajar();
      manoRepartidor.agregarCarta( mazo.cartaReparto());
      manoRepartidor.agregarCarta( mazo.cartaReparto() );
      manoUsuario.agregarCarta( mazo.cartaReparto() );
      manoUsuario.agregarCarta( mazo.cartaReparto() );
      
      System.out.println();
      System.out.println();
      
      /* Marque si uno de los jugadores tiene Blackjack (dos cartas con un total de 21).
         El jugador con Blackjack gana el juego.  El repartidor gana empates.
      */
      
      if (manoRepartidor.obtenerValorBlackjack() == 21) {
           String mensaje6 = "\r\n\r\n\r\n\r\nEl distribuidor tiene el " + manoRepartidor.obtenerCarta(0) + " y el " + manoRepartidor.obtenerCarta(1) + ".\r\n"
                           + "El usuario tiene el " + manoUsuario.obtenerCarta(0) + " y el " + manoUsuario.obtenerCarta(1) + ".\r\n"
                           + "El repartidor tiene Blackjack.  El repartidor gana.\r\n";
          // mensajes.add(mensaje6);
           System.out.println(mensaje6);
           escribir.write(mensaje6);//////////////////////////////
           escribir.close();
           return false;
      }
      
      if (manoUsuario.obtenerValorBlackjack() == 21) {
          String mensaje7 = "El distribuidor tiene el " + manoRepartidor.obtenerCarta(0) + " y el " + manoRepartidor.obtenerCarta(1) + ".\r\n" 
                            + "El usuario tiene el " + manoUsuario.obtenerCarta(0) + " y el " + manoUsuario.obtenerCarta(1) + ".\r\n\r\n"
                            + "Tienes Blackjack.  Tú ganas.\r\n"; 
        
           System.out.println(mensaje7);
           escribir.write(mensaje7);
           escribir.close();
           return true;
      }
      
      /*  Si ninguno de los dos jugadores tiene Blackjack, juega el juego.  Primero el usuario 
          tiene la oportunidad de robar cartas (es decir,"Golpear").  El bucle while termina 
          cuando el usuario elige "Stand".  Si el usuario supera los 21,
          el usuario pierde inmediatamente.
      */
      
      while (true) {
          
           /* Muestra las cartas de los usuarios y deja que el usuario decida si golpea o se para. */

           
           String mensaje8="Tus cartas son: \r\n";
           
           System.out.println(mensaje8);
           escribir.write(mensaje8);
           
           String mensaje9="";
           for ( int i = 0; i < manoUsuario.obtenerConteoCartas(); i++ ){
               mensaje9 =mensaje9 + "    " + manoUsuario.obtenerCarta(i) +"\r\n";
           } 
           System.out.println(mensaje9);
            escribir.write(mensaje9);  
            String mensajen="Su total es" + manoUsuario.obtenerValorBlackjack()+"\r\n"
                           + "El concesionario está mostrando el " + manoRepartidor.obtenerCarta(0)+"\r\n"
                           + "¿Pulse (H) o Pararse (S)? \r\n";
          
            System.out.println(mensajen);
            escribir.write(mensajen);
                               
             
             
            
           char userAction;  // User's response, 'H' or 'S'.
           do {
              userAction = Character.toUpperCase( TextIO.getlnChar() );
              escribir.write(userAction+"\r\n");
              
              String mensaje10="";
              if (userAction != 'H' && userAction != 'S')
                 mensaje10="Por favor responda H o S:  ";
                 System.out.print(mensaje10);
                 escribir.write(mensaje10+"\r\rn");
              
           } while (userAction != 'H' && userAction != 'S');

           /* Si el usuario acierta, el usuario recibe una tarjeta.  Si el usuario se para, el bucle termina 
             (y es el turno de la banca para robar cartas).
           */

           if ( userAction == 'S' ) {
                   // Termina el bucle; el usuario termina de coger las tarjetas.
               break;
           }
           else {  // La accion del usuario es' H'.  Dar al usuario una tarjeta.  
                   // Si el usuario pasa de 21, el usuario pierde.
               Card newCard = mazo.cartaReparto();
               manoUsuario.agregarCarta(newCard);
               String mensaje11= " \r\n Aciertos de usuario.\r\n"
                                + "Aciertos de usuario.\r\n"
                                + "Su tarjeta es el " + newCard + "\r\n"
                                + "Su total es ahora " + manoUsuario.obtenerValorBlackjack()+"\r\n" ;
              
               System.out.println(mensaje11);
               escribir.write(mensaje11);
               if (manoUsuario.obtenerValorBlackjack() > 21) {
                   String mensaje12="\r\nPasaste por encima de los 21.  Tú pierdes.\r\n"
                                     +"La otra carta del concesionario era el " + manoRepartidor.obtenerCarta(1)+"\r\n";

                   System.out.println(mensaje12);
                   escribir.write(mensaje12);
                   return false;  
               }
           }
           
      } // end while loop
      
      /* Si llegamos a este punto, el usuario tiene un nivel  Ahora, es
         la oportunidad del traficante de dibujar.  El Dealer coge cartas hasta que el dealer
         total es > 16.  Si el concesionario pasa de 21, pierde.
      */

      String mensaje13 = "\r\nUsuario parado.\r\n"
                        + "Las cartas del distribuidor son\r\n"
                        + "    " + manoRepartidor.obtenerCarta(0)+"\r\n"
                        + "    " + manoRepartidor.obtenerCarta(1)+"\r\n";
    
      System.out.println(mensaje13);
      escribir.write(mensaje13);
      
      String mensaje14="";
      while (manoRepartidor.obtenerValorBlackjack() <= 16) {
         Card newCard = mazo.cartaReparto();
         mensaje14="El distribuidor golpea y obtiene el " + newCard+"\r\n";
        
         System.out.println(mensaje14);
         escribir.write(mensaje14);
         
         manoRepartidor.agregarCarta(newCard);
         if (manoRepartidor.obtenerValorBlackjack() > 21) {
            String mensaje15= "\r\nRepartidor detenido por pasar de 21. Usted gana.\r\n";
           
             System.out.println(mensaje15);
            escribir.write(mensaje15);
            escribir.close();
            return true;
         }
      }
      String mensaje16="El total del repartidor es " + manoRepartidor.obtenerValorBlackjack()+"\r\n";
      
      System.out.println(mensaje16);
      escribir.write(mensaje16);
      /* Si llegamos a este punto, ambos jugadores tienen 21 o menos.  
        Podemos determinar el ganador comparando los valores de sus manos. */
      
      System.out.println();
      if (manoRepartidor.obtenerValorBlackjack() == manoUsuario.obtenerValorBlackjack()) {
         String mensaje17="\r\nEl repartidor gana por empate.  Tú pierdes.\r\n";
         
         System.out.println(mensaje17);
         escribir.write(mensaje17);
         escribir.close();
         return false;
      }
      else if (manoRepartidor.obtenerValorBlackjack() > manoUsuario.obtenerValorBlackjack()) {
         String mensaje18="El repartidor gana, " + manoRepartidor.obtenerValorBlackjack() 
                          + " puntos a " + manoUsuario.obtenerValorBlackjack() + ".\r\n";
         
         System.out.println(mensaje18);
         escribir.write(mensaje18);
         escribir.close();
         return false;
      }
      else {
          String mensaje19="Tu ganas, " + manoUsuario.obtenerValorBlackjack() 
                          + " puntos a " + manoRepartidor.obtenerValorBlackjack() + ".\r\n";
         
         System.out.println(mensaje19);
         escribir.write(mensaje19);
         escribir.close();
         return true;
       
      }
      
      
      
   }
   
   
  
}
