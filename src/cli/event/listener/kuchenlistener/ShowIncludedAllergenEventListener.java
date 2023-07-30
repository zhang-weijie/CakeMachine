package cli.event.listener.kuchenlistener;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import gl.AutomatInstanceManager;
import cli.event.CMDEvent;
import cli.event.CMDEventListener;
import vertrag.Allergen;

import java.util.HashSet;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class ShowIncludedAllergenEventListener extends CMDEventListener {


    public ShowIncludedAllergenEventListener(AutomatInstanceManager automatInstanceManager) {
        super(automatInstanceManager);
    }

    @Override
    public void onCmdEvent(CMDEvent cmdEvent) {
        HashSet<Allergen> allergene = getAutomat().getVorhandeneAllergene();
        if (allergene != null) {
            System.out.println("-----------------------------");
            for (Allergen allergen : allergene) {
                System.out.println(allergen);
            }
            System.out.println("-----------------------------");
        }
    }
}
