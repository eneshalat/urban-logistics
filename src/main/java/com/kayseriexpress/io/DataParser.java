package com.kayseriexpress.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.kayseriexpress.model.PackageEntity;
import com.kayseriexpress.structures.graph.CityMapGraph;
import com.kayseriexpress.structures.linear.IntakeBufferDLL;
import com.kayseriexpress.structures.tree.AddressDirectoryAVL;

public class DataParser {

    public static void loadMapData(String filepath, CityMapGraph graph) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s+");
                if (parts.length == 3) {
                    String source = parts[0];
                    String destination = parts[1];
                    int weight = Integer.parseInt(parts[2]);
                    // Assuming undirected graph for roads
                    graph.addEdge(source, destination, weight);
                    graph.addEdge(destination, source, weight);
                }
            }
            System.out.println("Map data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error reading map data: " + e.getMessage());
        }
    }

    public static void loadPackageData(String filepath, IntakeBufferDLL buffer, AddressDirectoryAVL directory) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s+");
                if (parts.length >= 2) {
                    String pkgId = parts[0];
                    String dest = parts[1];
                    String customerId = parts.length > 2 ? parts[2] : "CUST_UNKN";
                    
                    PackageEntity pkg = new PackageEntity(pkgId, dest);
                    buffer.insertAtTail(pkg);
                    directory.insert(dest, customerId);
                }
            }
            System.out.println("Package data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error reading package data: " + e.getMessage());
        }
    }
}
