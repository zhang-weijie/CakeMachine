package gl;
/*
 * Name: Weijie Zhang
 * Matrikelnummer: s0582504
 */

import gl.persistencestrategy.LoadStrategy;
import gl.persistencestrategy.SaveStrategy;

/**
 * @author Weijie Zhang, weijiezhangpku@gmail.com
 * @version 1.0
 */
public class AutomatInstanceManager extends Automat{
    private AutomatInstanceManager(int maxCapacity) {
        super(maxCapacity);
    }
    private static AutomatInstanceManager automatInstanceManager = new AutomatInstanceManager(1);

    public static AutomatInstanceManager getInstance(){
        return automatInstanceManager;
    }

    private Automat automat;

    public Automat getAutomat() {
        return automat;
    }

    public void setAutomat(Automat automat) {
        this.automat = automat;
    }

    public void setSaveStrategy(SaveStrategy saveStrategy){
        automat.setSaveStrategy(saveStrategy);
    }

    public void setLoadStrategy(LoadStrategy loadStrategy){
        automat.setLoadStrategy(loadStrategy);
    }

    public boolean loadAutomat(String filename){
        Automat loadedAutomat = automat.load(filename);
        if (loadedAutomat == null){
            return false;
        }
        setAutomat(loadedAutomat);
        return true;
    }
}
