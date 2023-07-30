package gl;

import gl.element.Element;
import gl.element.ElementBean;
import gl.element.belag.*;
import gl.element.boden.Boden;
import gl.element.boden.Hefeteig;
import gl.element.boden.Muerbeteig;
import vertrag.Allergen;

import java.time.Duration;
import java.util.*;

public class PersistenceUtil {
    public static AutomatBean convertToAutomatBean(Automat automat) {
        AutomatBean automatBean = new AutomatBean();

        automatBean.setMaxCapacityProperty(automat.getMaxCapacity());

        ArrayList<HerstellerImplBean> herstellerImplBeans = new ArrayList<>();
        for (HerstellerImpl hersteller : automat.readAllHersteller()){
            herstellerImplBeans.add(convertToHerstellerBean(hersteller));
        }
        automatBean.setHerstellerListProperty(herstellerImplBeans);

        ArrayList<KuchenImplBean> kuchenImplBeans = new ArrayList<>();
        for (KuchenImpl kuchen : automat.readAllKuchen()){
            kuchenImplBeans.add(convertToKuchenBean(kuchen));
        }
        automatBean.setKuchenListProperty(kuchenImplBeans);

        return automatBean;
    }

    public static HerstellerImplBean convertToHerstellerBean(HerstellerImpl hersteller) {
        HerstellerImplBean herstellerImplBean = new HerstellerImplBean();
        herstellerImplBean.setNameProperty(hersteller.getName());
        return herstellerImplBean;
    }

    public static KuchenImplBean convertToKuchenBean(KuchenImpl kuchen) {
        KuchenImplBean kuchenImplBean = new KuchenImplBean();
        kuchenImplBean.setElementProperty(convertToElementBean(kuchen.getElement()));
        kuchenImplBean.setHerstellerNameProperty(kuchen.getHersteller().getName());
        kuchenImplBean.setFachnummerProperty(kuchen.getFachnummer());
        kuchenImplBean.setInsertionsdatumProperty(kuchen.getInsertionsdatum().toEpochMilli());
        kuchenImplBean.setInspektionsdatumProperty(kuchen.getInspektionsdatum());
        return kuchenImplBean;
    }

    public static ElementBean convertToElementBean(Element element) {
        ElementBean elementBean = new ElementBean();
        if (element instanceof Boden) {
            elementBean.setElementProperty(null);
            elementBean.setNameProperty(element.getName());
            long haltbarkeitProperty;
            if (element instanceof Hefeteig){
                haltbarkeitProperty = Duration.ofHours(10).toMillis();
            } else {
                haltbarkeitProperty = Duration.ofHours(12).toMillis();
            }
            elementBean.setHaltbarkeitProperty(haltbarkeitProperty);
            ArrayList<String> allergenStrs = null;
            if (element.getAllergene() != null) {
                allergenStrs = new ArrayList<>();
                for (Allergen allergen : element.getAllergene()) {
                    allergenStrs.add(allergen.name());
                }
            }
            elementBean.setAllergeneProperty(allergenStrs == null ? null : allergenStrs.toArray(new String[allergenStrs.size()]));
            elementBean.setNaehrwertProperty(element.getNaehrwert());
            elementBean.setPreisProperty(element.getPreis().toString());
        } else {
            String nameProperty;
            long haltbarkeitProperty;
            String[] allergeneProperty;
            int naehrwertPropety;
            String preisProperty;
            Element subElement;
            if (element instanceof Apfelbelag){
                nameProperty = "Apfel";
                haltbarkeitProperty = Duration.ofHours(4).toMillis();
                allergeneProperty = new String[]{"Gluten"};
                naehrwertPropety = 100;
                preisProperty = "4";
                subElement = ((Apfelbelag) element).getElement();
            } else if (element instanceof Birnebelag){
                nameProperty = "Birne";
                haltbarkeitProperty = Duration.ofHours(3).toMillis();
                allergeneProperty = new String[]{"Haselnuss"};
                naehrwertPropety = 90;
                preisProperty = "3";
                subElement = ((Birnebelag) element).getElement();
            } else if (element instanceof Kirschebelag){
                nameProperty = "Kirsche";
                haltbarkeitProperty = Duration.ofHours(2).toMillis();
                allergeneProperty = new String[]{"Erdnuss"};
                naehrwertPropety = 80;
                preisProperty = "2";
                subElement = ((Kirschebelag) element).getElement();
            } else if (element instanceof Puddingbelag){
                nameProperty = "Pudding";
                haltbarkeitProperty = Duration.ofHours(5).toMillis();
                allergeneProperty = new String[]{"Haselnuss", "Erdnuss"};
                naehrwertPropety = 150;
                preisProperty = "5";
                subElement = ((Puddingbelag) element).getElement();
            } else {
                nameProperty = "Sahne";
                haltbarkeitProperty = Duration.ofHours(6).toMillis();
                allergeneProperty = new String[]{"Gluten", "Erdnuss", "Haselnuss", "Sesamsamen"};
                naehrwertPropety = 200;
                preisProperty = "6";
                subElement = ((Sahnebelag) element).getElement();
            }

            elementBean.setNameProperty(nameProperty);
            elementBean.setHaltbarkeitProperty(haltbarkeitProperty);
            elementBean.setAllergeneProperty(allergeneProperty);
            elementBean.setNaehrwertProperty(naehrwertPropety);
            elementBean.setPreisProperty(preisProperty);
            elementBean.setElementProperty(convertToElementBean(subElement));
        }
        
        return elementBean;
    }

    public static Element convertToElement(ElementBean elementBean) {
        if (elementBean.getElementProperty() == null) {
            String bodenTyp = elementBean.getNameProperty().split("\\+")[0];
            switch (bodenTyp){
                case "Muerbeteig":
                    return new Muerbeteig();
                default://"Hefeteig"
                    return new Hefeteig();
            }
        } else {
            Element subElement = convertToElement(elementBean.getElementProperty());
            String belegTyp = elementBean.getNameProperty().split("\\+")[0];
            switch (belegTyp){
                case "Apfel":
                    return new Apfelbelag(subElement);
                case "Birne":
                    return new Birnebelag(subElement);
                case "Kirsche":
                    return new Kirschebelag(subElement);
                case "Pudding":
                    return new Puddingbelag(subElement);
                default://"Sahne"
                    return new Sahnebelag(subElement);
            }
        }
    }
}