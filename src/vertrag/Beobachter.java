package vertrag;

import java.io.Serializable;

public interface Beobachter extends Serializable {
    long serialVersionUID = 1L;
    void aktualisiere();
}
