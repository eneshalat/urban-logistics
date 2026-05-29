package com.kayseriexpress;

import com.kayseriexpress.config.Branding;
import com.kayseriexpress.io.DataParser;
import com.kayseriexpress.model.PackageEntity;
import com.kayseriexpress.structures.graph.CityMapGraph;
import com.kayseriexpress.structures.graph.DijkstraEngine;
import com.kayseriexpress.structures.graph.MSTEngine;
import com.kayseriexpress.structures.linear.IntakeBufferDLL;
import com.kayseriexpress.structures.linear.MasterRegistrySLL;
import com.kayseriexpress.structures.linear.StandardDeliveryQueue;
import com.kayseriexpress.structures.linear.TruckLoadingStack;
import com.kayseriexpress.structures.tree.AddressDirectoryAVL;

public class UrbanLogisticsController {

    public static void main(String[] args) {
        // 1. Branding Bootstrapper
        Branding.printBanner();

        // 2. Initialize Data Structures
        MasterRegistrySLL masterRegistry = new MasterRegistrySLL();
        IntakeBufferDLL intakeBuffer = new IntakeBufferDLL();
        StandardDeliveryQueue deliveryQueue = new StandardDeliveryQueue();
        TruckLoadingStack loadingStack = new TruckLoadingStack();
        AddressDirectoryAVL addressDirectory = new AddressDirectoryAVL();
        CityMapGraph cityMap = new CityMapGraph();

        // 3. Load Data
        System.out.println("--- System Initialization ---");
        DataParser.loadMapData("data/mapData.txt", cityMap);
        DataParser.loadPackageData("data/packageData.txt", intakeBuffer, addressDirectory);
        System.out.println("-----------------------------\n");

        // 4. Process Intake Buffer
        System.out.println("--- Processing Incoming Packages ---");
        PackageEntity currentPkg;
        while ((currentPkg = intakeBuffer.removeFromHead()) != null) {
            // Add to immutable daily log
            masterRegistry.addRecord(currentPkg);

            // Fetch Customer ID from Address Directory
            String customerId = addressDirectory.search(currentPkg.getTargetDestination());
            System.out.println("Processing " + currentPkg.getPackageID() + " for Customer: " + customerId + " at " + currentPkg.getTargetDestination());

            // Assign to Standard Delivery Queue
            deliveryQueue.enqueue(currentPkg);
        }
        System.out.println("------------------------------------\n");

        // 5. Loading Trucks
        System.out.println("--- Loading Delivery Van (LIFO) ---");
        while (!deliveryQueue.isEmpty()) {
            PackageEntity pkg = deliveryQueue.dequeue();
            System.out.println("Loading onto van: " + pkg.getPackageID());
            loadingStack.push(pkg);
        }
        System.out.println("-----------------------------------\n");

        // 6. City Routing Optimization
        System.out.println("--- Routing Optimization ---");
        // Dijkstra's Shortest Path from Meydan to Talas
        DijkstraEngine.calculateShortestPath(cityMap, Branding.HUB_NAME, "Talas");
        DijkstraEngine.calculateShortestPath(cityMap, Branding.HUB_NAME, "Ildem");

        System.out.println();
        // Calculate MST for overall network efficiency
        MSTEngine.calculateMST(cityMap);
        System.out.println("----------------------------\n");

        // 7. End of Day Audit
        masterRegistry.displayLog();
    }
}
