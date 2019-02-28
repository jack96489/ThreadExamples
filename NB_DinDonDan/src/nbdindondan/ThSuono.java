/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbdindondan;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Princess Joy Padua
 *
 * @brief Classe per la gestione dei suoni
 *
 *
 */
public class ThSuono extends Thread {

    /**
     * Dichiaro due varibili di tipo boolean per effettuare lo sleep e lo yield.
     */
    private boolean faiSleep, faiYield;
    /**
     * Dichiaro una variabile di tipo int che servirà a scegliere se attivare
     * solo lo sleep oppure sleep+yield.
     *
     */
    private int scelta;
    /**
     * Dichiaro variabile di tipo String che decide quale suono eseguire.
     */
    private String suono;

    /**
     * Creo classe di tipo DatiCondivi che va a contare i suoni effettuati.
     */
    private DatiCondivisi ptrdati;
    private final Semaphore toWait, toSignal;

    /**
     * @param p
     * @brief Costruttore con parametri
     *
     * @param x Gli passo il suo da eseguire
     * @param y Scelta opzione
     */
    public ThSuono(String x, int y, DatiCondivisi p, Semaphore toWait, Semaphore toSignal) {
        suono = x;
        scelta = y;
        if (scelta == 1) {
            faiSleep = true;
            faiYield = false;
        }
        if (scelta == 2) {
            faiSleep = true;
            faiYield = true;
        }
        if (scelta == 3) {
            faiYield = true;
            faiSleep = false;
        }
        ptrdati = p;
        this.toSignal = toSignal;
        this.toWait = toWait;
    }

    /**
     * @brief Metodo per eseguire l'istruzione.
     *
     */
    public void run() {
        boolean verify = true;
        try {
            while (verify == true) {
                toWait.acquire();
                if (faiSleep) {
                    sleep(10);
                }
                if (faiYield) {
                    yield();
                }
                ptrdati.aggiungi(suono);
                if (suono.equals("DIN")) {
                    ptrdati.setContaDIN(ptrdati.getContaDIN() + 1);
                }
                if (suono.equals("DON")) {
                    ptrdati.setContaDON(ptrdati.getContaDON() + 1);
                }
                if (suono.equals("DAN")) {
                    ptrdati.setContaDAN(ptrdati.getContaDAN() + 1);
                }
                int min = 100;
                int max = 1000;
                int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
                sleep(randomNum);

                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                toSignal.release();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ThSuono.class.getName()).log(Level.SEVERE, null, ex);
        }
        ptrdati.getJoinSemaphore().release();
    }
}
