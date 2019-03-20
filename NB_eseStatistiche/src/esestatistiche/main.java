/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esestatistiche;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author scuola
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int daGenerare;
        Scanner ts = new Scanner(System.in);
        System.out.print("Numeri da generare:");
        daGenerare = ts.nextInt();

        DatiCondivisi dati = new DatiCondivisi(daGenerare);

        thGenera genera = new thGenera(dati);
        thContaSpazi contaPunti = new thContaSpazi(dati);
        thContaPunti contaSpazi = new thContaPunti(dati);
        thVisualizza visualizza = new thVisualizza(dati);

        contaPunti.start();
        contaSpazi.start();
        genera.start();
        visualizza.start();

        try {
            genera.join();
            contaPunti.join();
            contaSpazi.join();
            visualizza.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
