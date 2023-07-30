package gl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class HerstellerImplTest {
    @Test
    void getName_normal() {
        HerstellerImpl hersteller = new HerstellerImpl("A");
        assertEquals("A", hersteller.getName());
    }
}