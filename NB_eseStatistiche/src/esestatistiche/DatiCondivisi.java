/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esestatistiche;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author scuola
 */
public class DatiCondivisi {

    private char buffer;
    private int numCarDaGenerare;
    private int puntiInseriti, puntiLetti;
    private int spaziInseriti, spaziLetti;
    private boolean ferma;

    private Semaphore syncDatiDaVisualizzare;
    private Semaphore syncDatiVisualizzati;

    private Semaphore syncControlloPunti;
    private Semaphore syncBufferLettoPunti;
    private Semaphore syncControlloSpazi;
    private Semaphore syncBufferLettoSpazi;

    public DatiCondivisi(int daGene) {
        buffer = 0;
        numCarDaGenerare = daGene;
        syncDatiVisualizzati = new Semaphore(1);
        syncDatiDaVisualizzare = new Semaphore(0);
        syncBufferLettoPunti = new Semaphore(1);
        syncControlloPunti = new Semaphore(0);
        syncBufferLettoSpazi = new Semaphore(1);
        syncControlloSpazi = new Semaphore(0);
        ferma = false;
    }

    public synchronized void fermaTutti() {
        ferma = true;
    }

    public synchronized boolean fermaEsecuzione() {
        return ferma;
    }

    public synchronized void push(Character val) {
        buffer = val;
    }

    public synchronized void addSpaziInseriti() {
        spaziInseriti++;
    }

    public synchronized void addPuntiInseriti() {
        puntiInseriti++;
    }

    public synchronized void addSpaziLetti() {
        spaziLetti++;
    }

    public synchronized void addPuntiLetti() {
        puntiLetti++;
    }

    public synchronized int getNumGenerare() {
        return numCarDaGenerare;
    }

    public synchronized char getBuffer() {
        return buffer;
    }

    public synchronized void visualizza() {
        System.out.print("\nSpazi Inseriti:" + spaziInseriti);
        System.out.print(" Spazi Letti:" + spaziLetti);

        System.out.print(" Punti Inseriti:" + puntiInseriti);
        System.out.print(" Punti Letti:" + puntiLetti);
        System.out.println("------------");

    }

    void aspettoPresenzaDatiDaVisualizzare() {
        try {
            syncDatiDaVisualizzare.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(DatiCondivisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void segnaloPresenzaDatiDaVisualizzare() {
        syncDatiDaVisualizzare.release();
    }

    void segnaloDatiVisualizzati() {
        syncDatiVisualizzati.release();
    }

    void aspettoDatiVisualizzati() {
        try {
            syncDatiVisualizzati.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(DatiCondivisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void aspettoFineControlloPuntiBuffer() {
        try {
            syncControlloPunti.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(DatiCondivisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void segnaloFineControlloPuntiBuffer() {
        syncControlloPunti.release();
    }

    void aspettoPresenzaDatiBufferPerControlloPunti() {
        try {
            syncBufferLettoPunti.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(DatiCondivisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void segnaloPresenzaDatiBufferPerControlloPunti() {
        syncBufferLettoPunti.release();
    }

    void aspettoFineControlloSpaziBuffer() {
        try {
            syncControlloSpazi.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(DatiCondivisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void segnaloFineControlloSpaziBuffer() {
        syncControlloSpazi.release();
    }

    void aspettoPresenzaDatiBufferPerControlloSpazi() {
        try {
            syncBufferLettoSpazi.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(DatiCondivisi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void segnaloPresenzaDatiBufferPerControlloSpazi() {
        syncBufferLettoSpazi.release();
    }

}
