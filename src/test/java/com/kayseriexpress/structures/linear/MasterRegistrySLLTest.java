package com.kayseriexpress.structures.linear;

import com.kayseriexpress.model.PackageEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class MasterRegistrySLLTest {
    private MasterRegistrySLL registry;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        registry = new MasterRegistrySLL();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testAddSingleRecord() {
        PackageEntity pkg = new PackageEntity("PKG1", "Talas");
        registry.addRecord(pkg);
        registry.displayLog();
        assertTrue(outContent.toString().contains("PKG1 -> Talas"));
    }

    @Test
    public void testAddMultipleRecords() {
        registry.addRecord(new PackageEntity("PKG1", "Talas"));
        registry.addRecord(new PackageEntity("PKG2", "Belsin"));
        registry.displayLog();
        String output = outContent.toString();
        assertTrue(output.contains("PKG1 -> Talas"));
        assertTrue(output.contains("PKG2 -> Belsin"));
    }

    @Test
    public void testDisplayEmptyLog() {
        registry.displayLog();
        String output = outContent.toString();
        assertTrue(output.contains("--- Master Registry Audit Log ---"));
        // Should only contain the headers
        assertEquals(2, output.trim().split("\n").length);
    }

    @Test
    public void testOrderOfRecords() {
        registry.addRecord(new PackageEntity("P1", "A"));
        registry.addRecord(new PackageEntity("P2", "B"));
        registry.displayLog();
        String output = outContent.toString();
        assertTrue(output.indexOf("P1 -> A") < output.indexOf("P2 -> B"));
    }

    @Test
    public void testLargeNumberOfRecords() {
        for (int i = 0; i < 100; i++) {
            registry.addRecord(new PackageEntity("P" + i, "Dest" + i));
        }
        registry.displayLog();
        String output = outContent.toString();
        assertTrue(output.contains("P0 -> Dest0"));
        assertTrue(output.contains("P99 -> Dest99"));
    }
}
