
package JuegoCartas;

/**
 Este programa permite al usuario jugar Blackjack.  La computadora
 actúa como distribuidor.  El usuario tiene una participación de $100, y
 hace una apuesta en cada juego.  El usuario puede salir en cualquier momento,
 cuando pierda todo el dinero.
 Reglas de la casa: El concesionario alcanza un total de 16 o menos.
 y se sitúa en un total de 17 o más.  El distribuidor gana empates.
 Un nuevo mazo de cartas es usado para cada juego.
 */
public class Blackjack {
       public static void main(String[] args) {
   
      int dinero;          // Cantidad de dinero que tiene el usuario.
      int apuesta;            // Cantidad de apuestas del usuario en una partida.
      boolean usuarioGana;   // ¿Ganó el usuario el juego?
      
      System.out.println("Bienvenido al juego de Blackjack.");
      System.out.println();
      
      dinero = 100;  // El usuario comienza con $100.
   
      while (true) {
          System.out.println("Tu tienes " + dinero + " dolares.");
          do {
             System.out.println("¿Cuántos dólares quieres apostar? (Ingrese 0 para finalizar)");
             System.out.print("? ");
             apuesta = TextIO.getlnInt();
             if (apuesta < 0 || apuesta > dinero)
                 System.out.println("Su respuesta debe estar entre 0 y" + dinero + '.');
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
             System.out.println("¡Parece que te has quedado sin dinero!");
             break;
          }
      }
      
      System.out.println();
      System.out.println("Te vas con $" + dinero + '.');
   
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
           System.out.println("El distribuidor tiene el " + manoRepartidor.obtenerCarta(0) + " y el " + manoRepartidor.obtenerCarta(1) + ".");
           System.out.println("El usuario tiene el " + manoUsuario.obtenerCarta(0) + " y el " + manoUsuario.obtenerCarta(1) + ".");
           System.out.println();
           System.out.println("El repartidor tiene Blackjack.  El repartidor gana.");
           return false;
      }
      
      if (manoUsuario.obtenerValorBlackjack() == 21) {
           System.out.println("El distribuidor tiene el " + manoRepartidor.obtenerCarta(0) + " y el " + manoRepartidor.obtenerCarta(1) + ".");
           System.out.println("El usuario tiene el " + manoUsuario.obtenerCarta(0) + " y el " + manoUsuario.obtenerCarta(1) + ".");
           System.out.println();
           System.out.println("Tienes Blackjack.  Tú ganas.");
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
           System.out.println("Tus cartas son: ");
           for ( int i = 0; i < manoUsuario.obtenerConteoCartas(); i++ )
             System.out.println("    " + manoUsuario.obtenerCarta(i));
             System.out.println("Su total es " + manoUsuario.obtenerValorBlackjack());
             System.out.println();
             System.out.println("El concesionario está mostrando el " + manoRepartidor.obtenerCarta(0));
             System.out.println();
             System.out.print("¿Pulse (H) o Pararse (S)? ");
           char userAction;  // User's response, 'H' or 'S'.
           do {
              userAction = Character.toUpperCase( TextIO.getlnChar() );
              if (userAction != 'H' && userAction != 'S')
                 System.out.print("Por favor responda H o S:  ");
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
               System.out.println();
               System.out.println("Aciertos de usuario.");
               System.out.println("Su tarjeta es el " + newCard);
               System.out.println("Su total es ahora " + manoUsuario.obtenerValorBlackjack());
               if (manoUsuario.obtenerValorBlackjack() > 21) {
                   System.out.println();
                   System.out.println("Pasaste por encima de los 21.  Tú pierdes.");
                   System.out.println("La otra carta del concesionario era el " + manoRepartidor.obtenerCarta(1));
                   return false;  
               }
           }
           
      } // end while loop
      
      /* Si llegamos a este punto, el usuario tiene un nivel  Ahora, es
         la oportunidad del traficante de dibujar.  El Dealer coge cartas hasta que el dealer
         total es > 16.  Si el concesionario pasa de 21, pierde.
      */

      System.out.println();
      System.out.println("Usuario parado.");
      System.out.println("Las cartas del distribuidor son");
      System.out.println("    " + manoRepartidor.obtenerCarta(0));
      System.out.println("    " + manoRepartidor.obtenerCarta(1));
      while (manoRepartidor.obtenerValorBlackjack() <= 16) {
         Card newCard = mazo.cartaReparto();
         System.out.println("El distribuidor golpea y obtiene el " + newCard);
         manoRepartidor.agregarCarta(newCard);
         if (manoRepartidor.obtenerValorBlackjack() > 21) {
            System.out.println();
            System.out.println("Distribuidor detenido por pasar de 21. Usted gana.");
            return true;
         }
      }
      System.out.println("El total del distribuidor es " + manoRepartidor.obtenerValorBlackjack());
      
      /* Si llegamos a este punto, ambos jugadores tienen 21 o menos.  
        Podemos determinar el ganador comparando los valores de sus manos. */
      
      System.out.println();
      if (manoRepartidor.obtenerValorBlackjack() == manoUsuario.obtenerValorBlackjack()) {
         System.out.println("El concesionario gana por empate.  Tú pierdes.");
         return false;
      }
      else if (manoRepartidor.obtenerValorBlackjack() > manoUsuario.obtenerValorBlackjack()) {
         System.out.println("El concesionario gana, " + manoRepartidor.obtenerValorBlackjack() 
                          + " puntos para " + manoUsuario.obtenerValorBlackjack() + ".");
         return false;
      }
      else {
         System.out.println("Tu ganas, " + manoUsuario.obtenerValorBlackjack() 
                          + " puntos para " + manoRepartidor.obtenerValorBlackjack() + ".");
         return true;
      }

   }  // end playBlackjack()
       
}