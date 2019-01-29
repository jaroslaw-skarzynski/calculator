package pl.sda.pl.sda.jvm.demo;

import java.util.*;

public class SystemPropertiesSample {
    public static void main(String[] args) {
        Set<String> names = System.getProperties().stringPropertyNames();

        Map<String, List<String>> groupedProperties = new TreeMap<>();
        for (String name : names) {
            String[] parts = name.split("\\.");
            String prefix = parts[0];
            List<String> propertiesNames = groupedProperties.computeIfAbsent(prefix, str -> new ArrayList<>());
            propertiesNames.add(name);
        }

        groupedProperties.forEach((prefix, list) -> {
            System.out.println(prefix.toUpperCase());
            for (String name : list) {
                System.out.println("  " + name + "=" + System.getProperty(name));
            }
        });
    }
}
