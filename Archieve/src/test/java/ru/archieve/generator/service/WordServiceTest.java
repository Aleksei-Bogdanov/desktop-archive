package ru.archieve.generator.service;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.assertNotNull;

public class WordServiceTest {
    private WordService wordService = new WordService();
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    @Test
    public void testCreateWordArchiveHierarchy() throws IOException {
        File folder = temporaryFolder.newFolder("reports");
        wordService.createWordArchiveHierarchy(folder.getPath() + "\\archive_hierarchy.docx");
        File file = new File(folder.getPath() + "\\archive_hierarchy.docx");
        assertNotNull(file);
    }
}