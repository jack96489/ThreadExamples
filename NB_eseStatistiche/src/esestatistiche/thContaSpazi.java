/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esestatistiche;

/**
 *
 * @author scuola
 */
public class thContaSpazi extends Thread {

    private DatiCondivisi ptrDati;

    public thContaSpazi(DatiCondivisi ptrDati) {
        this.ptrDati = ptrDati;
    }

    @Override
    public void run() {
        System.out.println("START CONTROLLO SPAZI ");

        while (!ptrDati.fermaEsecuzione()) {

            ptrDati.aspettoPresenzaDatiBufferPerControlloSpazi();
            if (ptrDati.getBuffer() == ' ') {
                ptrDati.addSpaziLetti();
                ptrDati.segnaloPresenzaDatiDaVisualizzare();
                ptrDati.aspettoDatiVisualizzati();
            }
            ptrDati.segnaloFineControlloSpaziBuffer();
        }
        System.out.println("STOP CONTEGGIO SPAZI");
    }
}
