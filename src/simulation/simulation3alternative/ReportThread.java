package simulation.simulation3alternative;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import gl.Automat;
import gl.HerstellerImpl;
import gl.KuchenImpl;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class ReportThread extends Thread {
    private Automat automat;
    private long period;

    public ReportThread(Automat automat, long period) {
        this.automat = automat;
        this.period = period;
    }

    void report() {
        List<HerstellerImpl> herstellerList = automat.readAllHersteller();
        List<KuchenImpl> kuchenList = automat.readAllKuchen();
        System.out.println("---------Aktuelle Herstelleranzahl: " + herstellerList.size() + "--------");
        for (HerstellerImpl hersteller : herstellerList){
            int kuchenNum = 0;
            for (KuchenImpl kuchen : kuchenList){
                if (hersteller == kuchen.getHersteller()){
                    kuchenNum++;
                }
            }
            System.out.println(hersteller.getName() + ", Kuchenanzahl: " + kuchenNum);
        }
        System.out.println("---------Aktuelle Kuchenanzahl: " + kuchenList.size() + "--------");
        for (KuchenImpl kuchen : kuchenList) {
            System.out.println(kuchen);
        }
        System.out.println("-----------------------------------------");
    }

    @Override
    public void run() {
        ScheduledExecutorService executor;
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> report(), 1000, period, TimeUnit.MILLISECONDS);
    }
}