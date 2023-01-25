package com.codecool.histogram;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TextReaderTest {

    private TextReader textReader;

    @Test
    public void read_wrongFileName_throwsFileNotFoundException() {
        textReader = new TextReader("src/test/resources/nonExistingFile.txt");
        assertThrows(FileNotFoundException.class, () -> textReader.read());
    }

    @Test
    public void read_existingButEmptyFile_returnsEmptyString() throws IOException {
        textReader = new TextReader("src/test/resources/empty.txt");
        assertEquals("", textReader.read());
    }

    @Test
    public void read_oneLineTextInTextFile_returnsStringWithText() throws IOException {
        textReader = new TextReader("src/test/resources/test.txt");
        assertEquals("Harry Potter and the Sorcerer's Stone" + System.lineSeparator(), textReader.read());
    }

    @Test
    public void read_multipleLinesInTextFile_returnsStringWithText() throws IOException {
        textReader = new TextReader("src/test/resources/text.txt");
        assertEquals(33, textReader.read().split(System.lineSeparator()).length);
        assertEquals("SORTING HAT SONG", textReader.read().split(System.lineSeparator())[0]);
        assertEquals("Sweet Hufflepuff from valley broad", textReader.read().split(System.lineSeparator())[6]);
        assertEquals("Unite all the houses and we'll fight as one.", textReader.read().split(System.lineSeparator())[32]);
    }
    
}
