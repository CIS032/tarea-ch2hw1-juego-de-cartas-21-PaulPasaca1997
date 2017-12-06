/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JuegoCartas;

import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class BlackjackGUI {
    public static void main(String[] args) {
   
      int dinero;          // Cantidad de dinero que tiene el usuario.
      int apuesta;            // Cantidad de apuestas del usuario en una partida.
      boolean usuarioGana;   // ¿Ganó el usuario el juego?
      
      JOptionPane.showMessageDialog(null,"Bienvenido al juego de Blackjack.");
    
      
      dinero = 100;  // El usuario comienza con $100.
   
      while (true) {
          JOptionPane.showMessageDialog(null,"Tu tienes " + dinero + " dolares.");
          
          do {
             apuesta = Integer.parseInt(JOptionPane.showInputDialog(null,"¿Cuántos dólares quieres apostar? (Ingrese 0 para finalizar)"));
             
            
             if (apuesta < 0 || apuesta > dinero)
                 JOptionPane.showMessageDialog(null, "Su respuesta debe estar entre 0 y" + dinero + '.');
                 
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
             JOptionPane.showMessageDialog(null, "¡Parece que te has quedado sin dinero!");
             break;
          }
      }
      
      JOptionPane.showMessageDialog(null, "Te vas con $" + dinero + '.');
   
}//Fin de main
       
       
       
       
 
   /**
    Deje que el usuario juegue un juego de Blackjack, con el ordenador como repartidor.
    Devuelve true si el usuario gana el juego, false si el usuario pierde.
    */  
   static boolean playBlackjack() {

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
          String m="El distribuidor tiene el " + manoRepartidor.obtenerCarta(0) + " y el " + manoRepartidor.obtenerCarta(1) + ".\n"
                   + "El usuario tiene el " + manoUsuario.obtenerCarta(0) + " y el " + manoUsuario.obtenerCarta(1) + ".\n"
                   + "El repartidor tiene Blackjack.  El repartidor gana.";
          JOptionPane.showMessageDialog(null, m);
         
           return false;
      }
      
      if (manoUsuario.obtenerValorBlackjack() == 21) {
          String m1 = "El distribuidor tiene el " + manoRepartidor.obtenerCarta(0) + " y el " + manoRepartidor.obtenerCarta(1) + ".\n"
                      + "El usuario tiene el " + manoUsuario.obtenerCarta(0) + " y el " + manoUsuario.obtenerCarta(1) + ".\n"
                      + "Tienes Blackjack.  Tú ganas.";
           JOptionPane.showMessageDialog(null, m1);
           return true;
      }
      
      /*  Si ninguno de los dos jugadores tiene Blackjack, juega el juego.  Primero el usuario 
          tiene la oportunidad de robar cartas (es decir,"Golpear").  El bucle while termina 
          cuando el usuario elige "Stand".  Si el usuario supera los 21,
          el usuario pierde inmediatamente.
      */
      
      while (true) {
          
           /* Muestra las cartas de los usuarios y deja que el usuario decida si golpea o se para. */

           System.out.println();
           System.out.println();
           System.out.println();
           String aux1="";
           for ( int i = 0; i < manoUsuario.obtenerConteoCartas(); i++ ){
             aux1=aux1+"   "+manoUsuario.obtenerCarta(i)+"\n";
           }
           JOptionPane.showMessageDialog(null, "Tus cartas son: \n" 
                                               + aux1+"\n"
                                               + "Su total es " + manoUsuario.obtenerValorBlackjack()+"\n\n"
                                               +"El concesionario está mostrando el " + manoRepartidor.obtenerCarta(0));
          
           char userAction= JOptionPane.showInputDialog(null,"¿Pulse (H) o Pararse (S)? ").charAt(0);
          
                 //  char x = JOptionPane.showInputDialog(null,"Ingres... el dato").charAt(0);
                 char opcion;
           do {
              opcion=Character.toUpperCase(userAction);
              if (opcion != 'H' && opcion != 'S')
              
              JOptionPane.showMessageDialog(null,"Por favor responda H o S:  " );
           } while (opcion != 'H' && opcion != 'S');

           /* Si el usuario acierta, el usuario recibe una tarjeta.  Si el usuario se para, el bucle termina 
             (y es el turno de la banca para robar cartas).
           */
           
           if ( opcion == 'S' ) {
                   // Termina el bucle; el usuario termina de coger las tarjetas.
               break;
           }
           else {  // La accion del usuario es' H'.  Dar al usuario una tarjeta.  
                   // Si el usuario pasa de 21, el usuario pierde.
               Card newCard = mazo.cartaReparto();
               manoUsuario.agregarCarta(newCard);
          
               System.out.println();
               System.out.println();
               System.out.println();
               
               JOptionPane.showMessageDialog(null, "Aciertos de usuario.\n"
                                                  + "Su carta es el " + newCard+"\n"
                                                  + "Su total es ahora " + manoUsuario.obtenerValorBlackjack());
               if (manoUsuario.obtenerValorBlackjack() > 21) {
                   
                   JOptionPane.showMessageDialog(null, "Pasaste por encima de los 21.  Tú pierdes.\n"
                                                       + "La otra carta del repartidor era el " + manoRepartidor.obtenerCarta(1));
                   return false;  
               }
           }
           
      } // end while loop
      
      /* Si llegamos a este punto, el usuario tiene un nivel  Ahora, es
         la oportunidad del traficante de dibujar.  El Dealer coge cartas hasta que el dealer
         total es > 16.  Si el concesionario pasa de 21, pierde.
      */

      
      JOptionPane.showMessageDialog(null, "Usuario parado.\n"
                                          +"Las cartas del distribuidor son\n"
                                          +"    " + manoRepartidor.obtenerCarta(0)+"\n"
                                          + "    " + manoRepartidor.obtenerCarta(1));
      while (manoRepartidor.obtenerValorBlackjack() <= 16) {
         Card newCard = mazo.cartaReparto();
      
         JOptionPane.showMessageDialog(null, "El distribuidor golpea y obtiene el " + newCard);
         manoRepartidor.agregarCarta(newCard);
         if (manoRepartidor.obtenerValorBlackjack() > 21) {
            
            JOptionPane.showMessageDialog(null, "Distribuidor detenido por pasar de 21. Usted gana.");
            return true;
         }
      }
     
      JOptionPane.showMessageDialog(null, "El total del distribuidor es " + manoRepartidor.obtenerValorBlackjack());
      /* Si llegamos a este punto, ambos jugadores tienen 21 o menos.  
        Podemos determinar el ganador comparando los valores de sus manos. */
      
      System.out.println();
      if (manoRepartidor.obtenerValorBlackjack() == manoUsuario.obtenerValorBlackjack()) {
          JOptionPane.showMessageDialog(null, "El concesionario gana por empate.  Tú pierdes.");
      
         return false;
      }
      else if (manoRepartidor.obtenerValorBlackjack() > manoUsuario.obtenerValorBlackjack()) {
        
         JOptionPane.showMessageDialog(null, "El concesionario gana, " + manoRepartidor.obtenerValorBlackjack() 
                          + " puntos para " + manoUsuario.obtenerValorBlackjack() + ".");
         return false;
      }
      else {
        
         JOptionPane.showMessageDialog(null, "Tu ganas, " + manoUsuario.obtenerValorBlackjack() 
                          + " puntos para " + manoRepartidor.obtenerValorBlackjack() + ".");
         return true;
      }

   }  // end playBlackjack()
}
