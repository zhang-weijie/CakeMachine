package gl;

import gl.element.belag.*;
import gl.element.boden.Hefeteig;
import gl.element.boden.Muerbeteig;
import io.LoadWithJBP;
import io.LoadWithJOS;
import io.SaveWithJBP;
import io.SaveWithJOS;
import cli.oberserver.AllergenBeobachter;
import cli.oberserver.CapacityBeobachter;
import cli.oberserver.KuchenBeobachter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vertrag.Allergen;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class AutomatTest {
    Automat automat;

    @BeforeEach
    void setUp() {
        automat = new Automat(3);
        automat.addHersteller("A");
    }

    @Test
    void kapselung(){
        automat.insertKuchen(new KuchenImpl(new Muerbeteig(), automat.getHersteller("A")));
        automat.insertKuchen(new KuchenImpl(new Muerbeteig(), automat.getHersteller("A")));
        List<KuchenImpl> kuchenList = automat.readAllKuchen();
        kuchenList.clear();
        assertFalse(automat.readAllKuchen().isEmpty());
    }

    @Test
    void addHersteller_knownHersteller() {
        assertFalse(automat.addHersteller("A"));
    }

    @Test
    void addHersteller_unknownHersteller() {
        assertTrue(automat.addHersteller("B"));
    }

    @Test
    void getHersteller_knownHersteller() {
        assertNotNull(automat.getHersteller("A"));
    }

    @Test
    void getHersteller_unknownHersteller() {
        assertNull(automat.getHersteller("B"));
    }

    @Test
    void readAllHersteller() {
        HerstellerImpl hersteller = automat.getHersteller("A");
        ArrayList<HerstellerImpl> herstellers = new ArrayList<>();
        herstellers.add(hersteller);
        assertEquals(herstellers, automat.readAllHersteller());
    }

    @Test
    void removeHersteller_knownHersteller_hasNoKuchen() {
        assertTrue(automat.removeHersteller("A"));
    }

    @Test
    void removeHersteller_knownHersteller_hasKuchen() {
        automat.insertKuchen(new KuchenImpl(new Muerbeteig(), automat.getHersteller("A")));
        assertTrue(automat.removeHersteller("A"));
    }

    @Test
    void removeHersteller_unknownHersteller() {
        assertFalse(automat.removeHersteller("B"));
    }

    @Test
    void fachHatObjekt_validFachnummer_hasObject() {
        automat.insertKuchen(new KuchenImpl(new Muerbeteig(), automat.getHersteller("A")));
        assertTrue(automat.fachHatObjekt(1));
    }

    @Test
    void fachHatObjekt_validFachnummer_hasNoObject() {
        assertFalse(automat.fachHatObjekt(1));
    }

    @Test
    void fachHatObjekt_invalidFachnummer_nonPositive() {
        assertFalse(automat.fachHatObjekt(-1));
    }

    @Test
    void fachHatObjekt_invalidFachnummer_greaterThanMaxCapacity() {
        assertFalse(automat.fachHatObjekt(automat.getMaxCapacity() + 1));
    }

    @Test
    void getLeerFach_hasEmptyFach() {
        assertEquals(1, automat.getLeerFach());
    }

    @Test
    void getLeerFach_hasNoEmptyFach() {
        automat.insertKuchen(new KuchenImpl(new Muerbeteig(), automat.getHersteller("A")));
        automat.insertKuchen(new KuchenImpl(new Muerbeteig(), automat.getHersteller("A")));
        automat.insertKuchen(new KuchenImpl(new Muerbeteig(), automat.getHersteller("A")));
        assertEquals(-1, automat.getLeerFach());
    }

    @Test
    void save() {
        automat.setSaveStrategy(new SaveWithJBP());
        assertTrue(automat.save("status"));
        File file = new File("status");
        if (file.exists()){
            file.delete();
        }
    }

    @Test
    void save_invalidJBPFileName() {
        automat.setSaveStrategy(new SaveWithJBP());
        assertFalse(automat.save(""));
    }

    @Test
    void save_invalidJOSFileName() {
        automat.setSaveStrategy(new SaveWithJOS());
        assertFalse(automat.save(""));
    }

    @Test
    void save_noSaveStrategy(){
        assertFalse(automat.save("status"));
    }

    @Test
    void load_withJBP() {
        automat.insertKuchen(new KuchenImpl(new Muerbeteig(), automat.getHersteller("A")));
        automat.insertKuchen(new KuchenImpl(new Apfelbelag(new Birnebelag(new Kirschebelag(new Puddingbelag(new Sahnebelag(new Hefeteig()))))), automat.getHersteller("A")));
        automat.meldeAn(new CapacityBeobachter(automat));
        automat.meldeAn(new AllergenBeobachter(automat));
        automat.meldeAn(new KuchenBeobachter(automat));
        automat.setSaveStrategy(new SaveWithJBP());
        automat.save("status");
        automat.setLoadStrategy(new LoadWithJBP());
        assertNotNull(automat.load("status"));
        File file = new File("status");
        if (file.exists()){
            file.delete();
        }
    }

    @Test
    void load_withJOS() {
        automat.insertKuchen(new KuchenImpl(new Muerbeteig(), automat.getHersteller("A")));
        automat.insertKuchen(new KuchenImpl(new Apfelbelag(new Birnebelag(new Kirschebelag(new Puddingbelag(new Sahnebelag(new Hefeteig()))))), automat.getHersteller("A")));
        automat.meldeAn(new CapacityBeobachter(automat));
        automat.meldeAn(new AllergenBeobachter(automat));
        automat.meldeAn(new KuchenBeobachter(automat));
        automat.setSaveStrategy(new SaveWithJOS());
        automat.save("status");
        automat.setLoadStrategy(new LoadWithJOS());
        assertNotNull(automat.load("status"));
        File file = new File("status");
        if (file.exists()){
            file.delete();
        }
    }

    @Test
    void load_noLoadStrategy() {
        automat.setSaveStrategy(new SaveWithJBP());
        automat.save("status");
        assertNull(automat.load("status"));
        File file = new File("status");
        if (file.exists()){
            file.delete();
        }
    }

    @Test
    void load_falseJBPFile() {
        automat.setSaveStrategy(new SaveWithJBP());
        automat.save("status");
        automat.setLoadStrategy(new LoadWithJBP());
        assertNull(automat.load("statuss"));
        File file = new File("status");
        if (file.exists()){
            file.delete();
        }
    }

    @Test
    void load_falseJOSFile() {
        automat.setSaveStrategy(new SaveWithJOS());
        automat.save("status");
        automat.setLoadStrategy(new LoadWithJOS());
        assertNull(automat.load("statuss"));
        File file = new File("status");
        if (file.exists()){
            file.delete();
        }
    }

    @Test
    void insertKuchen_knownHersteller_hasEmptyFach() {
        assertFalse(automat.insertKuchen(new KuchenImpl(new Muerbeteig(), new HerstellerImpl("A"))));
    }

    @Test
    void insertKuchen_unknownHersteller() {
        assertFalse(automat.insertKuchen(new KuchenImpl(new Muerbeteig(), new HerstellerImpl("B"))));
    }

    @Test
    void insertKuchen_hasNoEmptyFach() {
        automat.insertKuchen(new KuchenImpl(new Muerbeteig(), automat.getHersteller("A")));
        automat.insertKuchen(new KuchenImpl(new Muerbeteig(), automat.getHersteller("A")));
        automat.insertKuchen(new KuchenImpl(new Muerbeteig(), automat.getHersteller("A")));
        assertFalse(automat.insertKuchen(new KuchenImpl(new Muerbeteig(), automat.getHersteller("A"))));
    }

    @Test
    void readAllKuchen() {
        KuchenImpl kuchen = new KuchenImpl(new Muerbeteig(), automat.getHersteller("A"));
        automat.insertKuchen(kuchen);
        ArrayList<KuchenImpl> kuchens = new ArrayList<>();
        kuchens.add(kuchen);
        assertEquals(kuchens, automat.readAllKuchen());
    }

    @Test
    void updateKuchen(){
        KuchenImpl kuchen = new KuchenImpl(new Muerbeteig(), automat.getHersteller("A"));
        automat.insertKuchen(kuchen);
        automat.updateKuchen(kuchen.getFachnummer());
        assertNotNull(kuchen.getInspektionsdatum());
    }

    @Test
    void updateKuchen_invalidFachnummer() {
        assertFalse(automat.updateKuchen(0));
    }

    @Test
    void updateKuchen_emptyFach() {
        assertFalse(automat.updateKuchen(1));
    }

    @Test
    void deleteKuchen() {
        KuchenImpl kuchen = new KuchenImpl(new Muerbeteig(), automat.getHersteller("A"));
        automat.insertKuchen(kuchen);
        assertTrue(automat.deleteKuchen(kuchen.getFachnummer()));
    }

    @Test
    void deleteKuchen_invalidFachnummer() {
        assertFalse(automat.deleteKuchen(0));
    }

    @Test
    void deleteKuchen_emptyFach() {
        assertFalse(automat.deleteKuchen(1));
    }

    @Test
    void meldeAn() {
        CapacityBeobachter capacityBeobachter = new CapacityBeobachter(automat);
        assertTrue(automat.meldeAn(capacityBeobachter));
    }

    @Test
    void meldeAb() {
        CapacityBeobachter capacityBeobachter = new CapacityBeobachter(automat);
        automat.meldeAn(capacityBeobachter);
        assertTrue(automat.meldeAb(capacityBeobachter));
    }

    @Test
    void benachrichtige() {
        Automat spyAutomat = spy(automat);
        CapacityBeobachter capacityBeobachter = new CapacityBeobachter(automat);
        spyAutomat.meldeAn(capacityBeobachter);
        spyAutomat.insertKuchen(new KuchenImpl(new Muerbeteig(), automat.getHersteller("A")));
        verify(spyAutomat).benachrichtige();
    }

    @Test
    void getCurCapacity() {
        assertEquals(3, automat.getCurCapacity());
    }

    @Test
    void getCurCapacity_afterInsert() {
        automat.insertKuchen(new KuchenImpl(new Muerbeteig(), automat.getHersteller("A")));
        assertEquals(2, automat.getCurCapacity());
    }

    @Test
    void getMaxCapacity() {
        assertEquals(3, automat.getMaxCapacity());
    }

    @Test
    void readMuerbeKuchen() {
        KuchenImpl muerbeKuchen = new KuchenImpl(new Muerbeteig(), automat.getHersteller("A"));
        KuchenImpl hefeKuchen = new KuchenImpl(new Hefeteig(), automat.getHersteller("A"));
        automat.insertKuchen(muerbeKuchen);
        automat.insertKuchen(hefeKuchen);
        ArrayList<KuchenImpl> muerbekuchens = new ArrayList<>();
        muerbekuchens.add(muerbeKuchen);
        assertEquals(muerbekuchens, automat.readMuerbeKuchen());
    }

    @Test
    void readHefeKuchen() {
        KuchenImpl muerbeKuchen = new KuchenImpl(new Muerbeteig(), automat.getHersteller("A"));
        KuchenImpl hefeKuchen = new KuchenImpl(new Hefeteig(), automat.getHersteller("A"));
        automat.insertKuchen(muerbeKuchen);
        automat.insertKuchen(hefeKuchen);
        ArrayList<KuchenImpl> hefekuchens = new ArrayList<>();
        hefekuchens.add(hefeKuchen);
        assertEquals(hefekuchens, automat.readHefeKuchen());
    }

    @Test
    void getVorhandeneAllergene() {
        KuchenImpl kuchen = new KuchenImpl(new Muerbeteig(), automat.getHersteller("A"));
        automat.insertKuchen(kuchen);
        assertEquals(kuchen.getAllergene(), automat.getVorhandeneAllergene());
    }

    @Test
    void getNichtVorhandeneAllergene() {
        KuchenImpl kuchen = new KuchenImpl(new Muerbeteig(), automat.getHersteller("A"));
        automat.insertKuchen(kuchen);
        HashSet<Allergen> allergens = new HashSet<>(Arrays.asList(Allergen.values()));
        allergens.removeAll(kuchen.getAllergene());
        assertEquals(allergens, automat.getNichtVorhandeneAllergene());
    }
}