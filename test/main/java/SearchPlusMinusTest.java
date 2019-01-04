package main.java;

import main.java.resources.PropertyManager;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchPlusMinusTest {
    SearchPlusMinus game = new SearchPlusMinus();
    private PropertyManager propertyManager = new PropertyManager();
    protected int nbCase = propertyManager.getNbCase();
    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    public void Given_ResponseCombinaison_When_ResponseOfThePlayer_Then_ReturnResponseCombinaison(){
        if(nbCase==4) {
            String input = "===+";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            String responseCombinaison[] = {"=", "=", "=", "+"};
            assertArrayEquals(responseCombinaison, game.responseOfThePlayer());
        }
    }

    @Test
    public void Given_ComputerCombinaison_When_PropositionOfTheComputer_Then_ReturnComputerCombinaison(){
        String responseCombinaison[] = {"=", "=", "=", "+"};
        int computerCombinaison[] = {6, 0, 0, 0};
        assertEquals(nbCase, game.propositionOfTheComputer(computerCombinaison, responseCombinaison));
    }
}
