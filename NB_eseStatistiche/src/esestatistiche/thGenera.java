/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esestatistiche;

import java.util.Random;

/**
 *
 * @author scuola
 */
public class thGenera extends Thread {

    private DatiCondivisi ptrDati;

    public thGenera(DatiCondivisi ptrDati) {
        this.ptrDati = ptrDati;
    }

    @Override
    public void run() {
        System.out.println("GENERO");
        String str = "abcdefghilmnopqrstuvwxyz .";
        int daGenerare = ptrDati.getNumGenerare();
        int i = 0;
        Character selez;
        Random rn = new Random();

        while (i < daGenerare) {
            selez = str.charAt(rn.nextInt(26));//Prendo un carattere dal mio alfabeto

            ptrDati.aspettoFineControlloPuntiBuffer();
            ptrDati.aspettoFineControlloSpaziBuffer();
            ptrDati.push(selez);
            // System.out.print(" *" + i + "='" + selez + "'*");

            ptrDati.segnaloPresenzaDatiBufferPerControlloPunti();
            ptrDati.segnaloPresenzaDatiBufferPerControlloSpazi();

            // Aggiorno i punti e gli spazi inseriti
            if (selez.equals(' ')) {
                // System.out.print(" car" + i + "='" + selez + "' ");
                ptrDati.addSpaziInseriti();
                ptrDati.segnaloPresenzaDatiDaVisualizzare();
                ptrDati.aspettoDatiVisualizzati();
            } else if (selez.equals('.')) {
                // System.out.print(" car" + i + "='" + selez + "' ");
                ptrDati.addPuntiInseriti();
                ptrDati.segnaloPresenzaDatiDaVisualizzare();
                ptrDati.aspettoDatiVisualizzati();
            }

            i++;
        }
        ptrDati.fermaTutti();
        // invio segnali ai thread per essere sicuri che escano dal
        // ciclo con la condizione ptrDati.fermaEsecuzione()
        ptrDati.segnaloFineControlloPuntiBuffer();
        ptrDati.segnaloFineControlloSpaziBuffer();
        ptrDati.segnaloPresenzaDatiDaVisualizzare();

        System.out.println("FINE GENERAZIONE");
    }
}
