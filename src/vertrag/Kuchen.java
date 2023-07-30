package vertrag;

import java.time.Instant;
import java.util.Date;

public interface Kuchen {
    Hersteller getHersteller();
    int getFachnummer();
    Instant getInsertionsdatum();
    Date getInspektionsdatum();
}