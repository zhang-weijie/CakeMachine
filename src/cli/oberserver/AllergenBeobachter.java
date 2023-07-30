package cli.oberserver;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import gl.Automat;
import vertrag.Allergen;
import vertrag.Beobachter;

import java.util.HashSet;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class AllergenBeobachter implements Beobachter {
    private HashSet<Allergen> oldAllergenSet;
    private Automat automat;

    public AllergenBeobachter(Automat automat) {
        this.automat = automat;
        oldAllergenSet = new HashSet<>(automat.getVorhandeneAllergene());
    }

    @Override
    public void aktualisiere() {
        HashSet<Allergen> newAllergenSet = new HashSet<>(automat.getVorhandeneAllergene());

        if (!oldAllergenSet.equals(newAllergenSet)){
            HashSet<Allergen> removedAllergenSet = new HashSet<>();
            HashSet<Allergen> addedAllergenSet = new HashSet<>();
            for (Allergen allergen : Allergen.values()){
                if (oldAllergenSet.contains(allergen) && !newAllergenSet.contains(allergen)){
                    removedAllergenSet.add(allergen);
                }
                if (!oldAllergenSet.contains(allergen) && newAllergenSet.contains(allergen)){
                    addedAllergenSet.add(allergen);
                }
            }
            if (removedAllergenSet.size() != 0){
                System.out.println("Allergene " + removedAllergenSet + " wurden entfernt!");
            }
            if (addedAllergenSet.size() != 0){
                System.out.println("Allergene " + addedAllergenSet + " wurden hinzugefuegt!");
            }
        }

        oldAllergenSet = new HashSet<>(automat.getVorhandeneAllergene());
    }
}
