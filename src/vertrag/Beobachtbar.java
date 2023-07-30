package vertrag;

public interface Beobachtbar {
    boolean meldeAn(Beobachter beobachter);
    boolean meldeAb(Beobachter beobachter);
    void benachrichtige();
}
