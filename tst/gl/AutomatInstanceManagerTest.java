package gl;

import gl.persistencestrategy.LoadStrategy;
import gl.persistencestrategy.SaveStrategy;
import io.LoadWithJOS;
import io.SaveWithJBP;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class AutomatInstanceManagerTest {

    @Test
    void getInstance() {
        AutomatInstanceManager automatInstanceManager = AutomatInstanceManager.getInstance();
        AutomatInstanceManager automatInstanceManager2 = AutomatInstanceManager.getInstance();
        assertEquals(automatInstanceManager, automatInstanceManager2);
    }

    @Test
    void getAutomat() {
        Automat automat = new Automat(3);
        AutomatInstanceManager automatInstanceManager = AutomatInstanceManager.getInstance();
        automatInstanceManager.setAutomat(automat);
        assertEquals(automat, automatInstanceManager.getAutomat());
    }

    @Test
    void setAutomat() {
        Automat spyAutomat = new Automat(3);
        AutomatInstanceManager automatInstanceManager = AutomatInstanceManager.getInstance();
        automatInstanceManager.setAutomat(spyAutomat);
        assertNotNull(automatInstanceManager.getAutomat());
    }

    @Test
    void setSaveStrategy() {
        Automat automat = new Automat(3);
        Automat spyAutomat = spy(automat);
        AutomatInstanceManager automatInstanceManager = AutomatInstanceManager.getInstance();
        automatInstanceManager.setAutomat(spyAutomat);
        SaveStrategy saveStrategy = new SaveWithJBP();
        automatInstanceManager.setSaveStrategy(saveStrategy);
        verify(spyAutomat).setSaveStrategy(saveStrategy);
    }

    @Test
    void setLoadStrategy() {
        Automat automat = new Automat(3);
        Automat spyAutomat = spy(automat);
        AutomatInstanceManager automatInstanceManager = AutomatInstanceManager.getInstance();
        automatInstanceManager.setAutomat(spyAutomat);
        LoadStrategy loadStrategy = new LoadWithJOS();
        automatInstanceManager.setLoadStrategy(loadStrategy);
        verify(spyAutomat).setLoadStrategy(loadStrategy);
    }

    @Test
    void loadAutomat_normal() {
        Automat automat = new Automat(3);
        Automat mockAutomat = mock(Automat.class);
        when(mockAutomat.load("status")).thenReturn(automat);

        AutomatInstanceManager automatInstanceManager = AutomatInstanceManager.getInstance();
        automatInstanceManager.setAutomat(mockAutomat);
        assertTrue(automatInstanceManager.loadAutomat("status"));
    }

    @Test
    void loadAutomat_noStatusLoaded() {
        Automat mockAutomat = mock(Automat.class);
        when(mockAutomat.load("status")).thenReturn(null);

        AutomatInstanceManager automatInstanceManager = AutomatInstanceManager.getInstance();
        automatInstanceManager.setAutomat(mockAutomat);
        assertFalse(automatInstanceManager.loadAutomat("status"));
    }
}