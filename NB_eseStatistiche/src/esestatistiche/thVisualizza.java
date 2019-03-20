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
public class thVisualizza extends Thread {

    private DatiCondivisi ptrDati;

    public thVisualizza(DatiCondivisi ptrDati) {
        this.ptrDati = ptrDati;
    }

    @Override
    public void run() {
        while (!ptrDati.fermaEsecuzione()) {
            ptrDati.aspettoPresenzaDatiDaVisualizzare();

            ptrDati.visualizza();

            ptrDati.segnaloDatiVisualizzati();
        }
        System.out.println("FINE VISUALIZZAZIONE");
    }
}
