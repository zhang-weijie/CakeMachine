package com.example.cakemachine.gl;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import com.example.cakemachine.gl.element.Element;
import com.example.cakemachine.gl.element.ElementBean;
import com.example.cakemachine.gl.persistencestrategy.LoadStrategy;
import com.example.cakemachine.gl.persistencestrategy.SaveStrategy;
import com.example.cakemachine.vertrag.Allergen;
import com.example.cakemachine.vertrag.Beobachtbar;
import com.example.cakemachine.vertrag.Beobachter;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class Automat implements Beobachtbar, Serializable {
    private static final long serialVersionUID = 1L;
    private int maxCapacity;
    private int curCapacity;
    private List<HerstellerImpl> herstellerList = new CopyOnWriteArrayList<>();
    private Map<Integer, KuchenImpl> fachnummerKuchenMap = new HashMap<>();

    public Automat(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.curCapacity = maxCapacity;
        for (int i = 0; i < maxCapacity; i++) {
            fachnummerKuchenMap.put(i + 1, null);
        }
    }

    public synchronized boolean addHersteller(String name) {
        for (HerstellerImpl hersteller : herstellerList) {
            if (hersteller.getName().equals(name)) {
                return false;
            }
        }
        herstellerList.add(new HerstellerImpl(name));
        return true;
    }

    public synchronized HerstellerImpl getHersteller(String name) {
        for (HerstellerImpl hersteller : herstellerList) {
            if (name.equals(hersteller.getName())) {
                return hersteller;
            }
        }
        return null;
    }

    public synchronized List<HerstellerImpl> readAllHersteller() {
        return new ArrayList<>(this.herstellerList);
    }

    public synchronized boolean removeHersteller(String name) {
        HerstellerImpl hersteller = null;
        for (HerstellerImpl eachHersteller : herstellerList) {
            if (name.equals(eachHersteller.getName())) {
                hersteller = eachHersteller;
                break;
            }
        }
        if (hersteller != null) {
            for (Map.Entry<Integer, KuchenImpl> entry : fachnummerKuchenMap.entrySet()) {
                if (entry.getValue() != null && entry.getValue().getHersteller() == hersteller) {
                    deleteKuchen(entry.getValue().getFachnummer());
                }
            }
            benachrichtige();
            return herstellerList.remove(hersteller);
        }
        return false;
    }

    synchronized boolean fachHatObjekt(int fachnummer) {
        return (fachnummer >= 1 && fachnummer <= maxCapacity) && (fachnummerKuchenMap.get(fachnummer) != null);
    }

    synchronized int getLeerFach() {
        int fachnummer = -1;
        for (int i = 0; i < maxCapacity; i++) {
            if (fachnummerKuchenMap.get(i + 1) == null) {
                fachnummer = i + 1;
                break;
            }
        }
        return fachnummer;
    }

    private SaveStrategy saveStrategy;

    synchronized void setSaveStrategy(SaveStrategy saveStrategy) {
        this.saveStrategy = saveStrategy;
    }

    public synchronized boolean save(String filename) {
        if (saveStrategy == null){
            return false;
        }
        return saveStrategy.doSave(this, filename);
    }

    private LoadStrategy loadStrategy;

    synchronized void setLoadStrategy(LoadStrategy loadStrategy) {
        this.loadStrategy = loadStrategy;
    }

    synchronized Automat load(String filename) {
        if (loadStrategy == null){
            return null;
        }

        AutomatBean automatBean = loadStrategy.doLoad(filename);
        if (automatBean == null) {
            return null;
        }

        Automat automat = new Automat(automatBean.getMaxCapacityProperty());
        List<HerstellerImplBean> herstellerListProperty = automatBean.getHerstellerListProperty();
        for (HerstellerImplBean herstellerImplBean : herstellerListProperty) {
            automat.addHersteller(herstellerImplBean.getNameProperty());
        }

        List<KuchenImplBean> kuchenImplBeans = automatBean.getKuchenListProperty();
        kuchenImplBeans.sort(new Comparator<KuchenImplBean>() {
            @Override
            public int compare(KuchenImplBean o1, KuchenImplBean o2) {
                return Integer.compare(o1.getFachnummerProperty(), o2.getFachnummerProperty());
            }
        });

        for (KuchenImplBean kuchenImplBean : kuchenImplBeans){
            ElementBean elementBean = kuchenImplBean.getElementProperty();
            Element element = PersistenceUtil.convertToElement(elementBean);
            String herstellerNameProperty = kuchenImplBean.getHerstellerNameProperty();
            KuchenImpl kuchen = new KuchenImpl(element, automat.getHersteller(herstellerNameProperty));

            int fachnummerProperty = kuchenImplBean.getFachnummerProperty();
            Date inspektionsdatumProperty = kuchenImplBean.getInspektionsdatumProperty();
            long insertionsdatumProperty = kuchenImplBean.getInsertionsdatumProperty();
            kuchen.setFachnummer(fachnummerProperty);
            kuchen.setInspektionsdatum(inspektionsdatumProperty);
            kuchen.setInsertionsdatum(Instant.ofEpochMilli(insertionsdatumProperty));

            automat.insertKuchen(kuchen);
        }
        return automat;
    }

    public synchronized boolean insertKuchen(KuchenImpl kuchen) {
        if (!herstellerList.contains(kuchen.getHersteller())) {
            return false;
        }

        if (kuchen.getFachnummer() == 0) {
            int fachnummer = getLeerFach();
            if (fachnummer == -1) {
                return false;
            } else {
                kuchen.setFachnummer(fachnummer);
            }
        }

        if (kuchen.getInsertionsdatum() == null) {
            kuchen.setInsertionsdatum(Instant.now());
        }

        fachnummerKuchenMap.replace(kuchen.getFachnummer(), kuchen);
        curCapacity -= 1;
        vorhandeneAllergene.addAll(kuchen.getAllergene());
        benachrichtige();
        return true;
    }

    public synchronized List<KuchenImpl> readAllKuchen() {
        List<KuchenImpl> kuchenList = new ArrayList<>();
        for (KuchenImpl kuchen : fachnummerKuchenMap.values()) {
            if (kuchen != null) {
                kuchenList.add(kuchen);
            }
        }
        return kuchenList;
    }

    public synchronized boolean updateKuchen(int fachnummer) {
        if (!fachHatObjekt(fachnummer)) {
            return false;
        }
        KuchenImpl targetKuchen = fachnummerKuchenMap.get(fachnummer);
        targetKuchen.setInspektionsdatum(new Date());
        benachrichtige();
        return true;
    }

    public synchronized boolean deleteKuchen(int fachnummer) {
        if (!fachHatObjekt(fachnummer)) {
            return false;
        }
        fachnummerKuchenMap.replace(fachnummer, null);
        curCapacity += 1;
        vorhandeneAllergene.clear();
        for (KuchenImpl remainedKuchen : readAllKuchen()) {
            vorhandeneAllergene.addAll(remainedKuchen.getAllergene());
        }
        benachrichtige();
        return true;
    }

    private transient List<Beobachter> beobachterList = new LinkedList<>();

    @Override
    public synchronized boolean meldeAn(Beobachter beobachter) {
        return beobachterList.add(beobachter);
    }

    @Override
    public synchronized boolean meldeAb(Beobachter beobachter) {
        return beobachterList.remove(beobachter);
    }

    @Override
    public synchronized void benachrichtige() {
        for (Beobachter beobachter : beobachterList) {
            beobachter.aktualisiere();
        }
    }

    public synchronized int getCurCapacity() {
        return curCapacity;
    }

    public synchronized int getMaxCapacity() {
        return maxCapacity;
    }


    public synchronized List<KuchenImpl> readMuerbeKuchen() {
        List<KuchenImpl> allKuchenList = readAllKuchen();
        List<KuchenImpl> muerbeKuchenList = new LinkedList<>();
        for (KuchenImpl kuchen : allKuchenList) {
            if (kuchen.getName().contains("Muerbeteig")) {
                muerbeKuchenList.add(kuchen);
            }
        }
        return muerbeKuchenList;
    }

    public synchronized List<KuchenImpl> readHefeKuchen() {
        List<KuchenImpl> allKuchenList = readAllKuchen();
        List<KuchenImpl> hefeKuchenList = new LinkedList<>();
        for (KuchenImpl kuchen : allKuchenList) {
            if (kuchen.getName().contains("Hefeteig")) {
                hefeKuchenList.add(kuchen);
            }
        }
        return hefeKuchenList;
    }

    private HashSet<Allergen> vorhandeneAllergene = new HashSet<>();

    public synchronized HashSet<Allergen> getVorhandeneAllergene() {
        return new HashSet<>(vorhandeneAllergene);
    }

    public synchronized HashSet<Allergen> getNichtVorhandeneAllergene() {
        HashSet<Allergen> allAllergene = new HashSet<>(Arrays.asList(Allergen.values()));
        allAllergene.removeAll(vorhandeneAllergene);
        return allAllergene;
    }
}