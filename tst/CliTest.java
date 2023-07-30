import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

class CliTest {
    @Test
    void insertHersteller() {
        InputStream in = System.in;
        PrintStream out = System.out;

        String cmds =
                ":c" + System.lineSeparator() +
                        "Hersteller1" + System.lineSeparator() +
                        "exit" + System.lineSeparator();

        ByteArrayInputStream bis = new ByteArrayInputStream(cmds.getBytes());
        System.setIn(bis);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        String expected =
                "Modus: :r. Geben Sie einen Befehl ein: " +
                        "Modus: :c. Geben Sie einen Befehl ein: " +
                        "Hersteller wurde eingefuegt!" + System.lineSeparator() +
                        "Modus: :c. Geben Sie einen Befehl ein: " +
                        "Exiting..." + System.lineSeparator();

        try {
            Cli.main(new String[]{"3"});
            assertEquals(expected, bos.toString());
        } catch (Exception exception) {
            fail();
        } finally {
            System.setIn(in);
            System.setOut(out);
        }
    }

    @Test
    void showHersteller() {
        InputStream in = System.in;
        PrintStream out = System.out;

        String cmds =
                ":c" + System.lineSeparator() +
                        "Hersteller1" + System.lineSeparator() +
                        ":r" + System.lineSeparator() +
                        "hersteller" + System.lineSeparator() +
                        "exit" + System.lineSeparator();

        ByteArrayInputStream bis = new ByteArrayInputStream(cmds.getBytes());
        System.setIn(bis);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        String expected =
                "Modus: :r. Geben Sie einen Befehl ein: " +
                        "Modus: :c. Geben Sie einen Befehl ein: " +
                        "Hersteller wurde eingefuegt!" + System.lineSeparator() +
                        "Modus: :c. Geben Sie einen Befehl ein: " +
                        "Modus: :r. Geben Sie einen Befehl ein: " +
                        "-----------------------------" + System.lineSeparator() +
                        "Hersteller1\tKuchenanzahl: 0" + System.lineSeparator() +
                        "-----------------------------" + System.lineSeparator() +
                        "Modus: :r. Geben Sie einen Befehl ein: " +
                        "Exiting..." + System.lineSeparator();

        try {
            Cli.main(new String[]{"3"});
            assertEquals(expected, bos.toString());
        } catch (Exception exception) {
            fail();
        } finally {
            System.setIn(in);
            System.setOut(out);
        }
    }
}