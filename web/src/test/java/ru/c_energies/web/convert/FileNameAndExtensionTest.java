package ru.c_energies.web.convert;

import org.junit.jupiter.api.*;

public class FileNameAndExtensionTest {
    private final String filename = "aglejrDDFFgalergh.sdg.ehe4";
    @Test
    public void testExtension(){
        FileNameAndExtension fileNameAndExtension = new FileNameAndExtension(this.filename);
        Assertions.assertEquals("ehe4", fileNameAndExtension.extension());
    }

    @Test
    public void testName(){
        FileNameAndExtension fileNameAndExtension = new FileNameAndExtension(this.filename);
        Assertions.assertEquals("aglejrDDFFgalergh.sdg", fileNameAndExtension.name());
    }
}