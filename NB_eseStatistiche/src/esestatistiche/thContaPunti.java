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
public class thContaPunti extends Thread {

    private DatiCondivisi ptrDati;

    public thContaPunti(DatiCondivisi ptrDati) {
        this.ptrDati = ptrDati;
    }

    @Override
    public void run() {
        System.out.println("START CONTROLLO PUNTI ");

        while (!ptrDati.fermaEsecuzione()) {

            ptrDati.aspettoPresenzaDatiBufferPerControlloPunti();
            if (ptrDati.getBuffer() == '.') {
                ptrDati.addPuntiLetti();
                ptrDati.segnaloPresenzaDatiDaVisualizzare();
                ptrDati.aspettoDatiVisualizzati();
            }
            ptrDati.segnaloFineControlloPuntiBuffer();
        }
        System.out.println("STOP CONTEGGIO PUNTI");
    }
}
