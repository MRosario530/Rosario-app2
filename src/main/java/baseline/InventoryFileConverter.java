/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 Miguel Rosario
 */
package baseline;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.apache.commons.io.FilenameUtils;
import org.hildan.fxgson.FxGson;
import java.io.*;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InventoryFileConverter {
    private static final String END = "</td>";
    private static final String START = "\t\t\t<td>";

    public List<Item> importFile(File file) {
        // Get the file extension from the file parameter.
        String extension = FilenameUtils.getExtension(file.getName());
        // Create a new list of items.
        List<Item> items = new ArrayList<>();
        // Depending on the extension of the file (HTML, JSON, TXT), called the corresponding import method
        // and set the result to the list of items.
        if (extension.equalsIgnoreCase("html")) {
            items = importHTML(file);
        } else if (extension.equalsIgnoreCase("json")) {
            items = importJSON(file);
        } else if (extension.equalsIgnoreCase("txt")) {
            items = importTSV(file);
        }
        // Return the new list of items.
        return items;
    }

    public void exportFile(File file, List<Item> currentInventory) {
        // Get the file extension from the file parameter.
        String extension = FilenameUtils.getExtension(file.getName());
        // Depending on the extension of the file (HTML, JSON, TXT), called the corresponding export method.
        if (extension.equalsIgnoreCase("html")) {
            exportHTML(file, currentInventory);
        } else if (extension.equalsIgnoreCase("json")) {
            exportJSON(file, currentInventory);
        } else if (extension.equalsIgnoreCase("txt")) {
            exportTSV(file, currentInventory);
        }
    }

    private void exportHTML(File file, List<Item> currentInventory) {
        // Create a PrintWriter inside a try/catch.
        try (PrintWriter writer = new PrintWriter(file)) {
            // Get the text for the HTML file.
            String fileText = createInventoryString(currentInventory);
            // Write the created string into the file.
            writer.write(fileText);
        } catch (IOException e) {
            // If an error occurs print the stacktrace.
            e.printStackTrace();
        }
    }

    private String createInventoryString(List<Item> currentInventory) {
        // Create a StringBuilder.
        StringBuilder fileText = new StringBuilder();
        // Start the StringBuilder with the doctype, html, header, title, and style information and tags.
        fileText.append("""
                <!DOCTYPE html> \s
                <html> \s
                <head> \s
                \t<title>Inventory</title> \s
                \t<style> \s
                \ttable, th, td { \s
                \t  border: 1px solid black; \s
                \t  border-collapse: collapse; \s
                \t} \s
                \tth, td { \s
                \t  padding: 10px; \s
                \t} \s
                \t</style> \s
                </head> \s
                """);
        // Add the body, table header, and table tags.
        fileText.append("""
                <body> \s
                \t<table> \s
                \t\t<tr> \s
                \t\t\t<th>Item Name</th> \s
                \t\t\t<th>Monetary Value</th> \s
                \t\t\t<th>Serial Number</th> \s
                \t\t</tr> \s""");
        // Loop through the list of items and add each to the StringBuilder with the appropriate tags.
        for (Item item: currentInventory) {
            fileText.append("\t\t<tr>\n");
            fileText.append(START).append(item.getItemName()).append("</td>\n").append(START).append("$").
                    append(item.getItemValue()).append("</td>\n\t\t\t<td>").append(item.getSerialNumber())
                    .append("</td>\n\t\t</tr>\n");
        }
        // Add the closing table, body, and html tags.
        fileText.append("""
                \t</table> \s
                </body> \s
                </html> \s""");
        // Return the string.
        return fileText.toString();
    }

    private void exportJSON(File file, List<Item> currentInventory) {
        // Create a GSON object.
        Gson gson = FxGson.create();
        // Within a try/catch create a PrintWriter at the file location.
        try (PrintWriter writer = new PrintWriter(file)) {
            // Use the toJson method on the currentInventory list to create a json string.
            String jsonList = gson.toJson(currentInventory);
            writer.write(jsonList);
        } catch (IOException e) {
            // If an error occurs print the stacktrace.
            e.printStackTrace();
        }
    }

    private void exportTSV(File file, List<Item> currentInventory) {
        // Create a StringBuilder.
        StringBuilder fileText = new StringBuilder();
        // Have the first line contain the names of the variables beneath them.
        fileText.append("Item Name\tMonetary Value\tSerial Number\n");
        // Loop through the list, adding the components of each item to the StringBuilder.
        for (Item item: currentInventory) {
            fileText.append(item.getItemName()).append("\t").append("$").append(item.getItemValue()).append("\t")
                    .append(item.getSerialNumber()).append("\n");
        }
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(fileText.toString());
        } catch (IOException e) {
            // If an error occurs print the stacktrace.
            e.printStackTrace();
        }
        // Open the file and paste the StringBuilder in the file using a try/catch.
    }

    private List<Item> importHTML(File file) {
        // Create a temporary list of items.
        List<Item> items = new ArrayList<>();
        // Create a String to hold the current line in the document, and a string array to hold the 3 strings
        // that will create an item.
        String currentLine;
        String[] itemParts = new String[3];
        // Set up a try/catch to read through the document.
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // Loop through the file until the EOF is reached.
            while ((currentLine = br.readLine()) != null) {
                // When a line with <td> is found, insert the current and next 2 lines into the string array.
                if (currentLine.contains("<td>")) {
                    itemParts[0] = currentLine;
                    currentLine = br.readLine();
                    itemParts[1] = currentLine;
                    currentLine = br.readLine();
                    itemParts[2] = currentLine;
                    // Call parseHTMLTable on the string array and add the returned item to the list.
                    items.add(parseHTMLTable(itemParts));
                }
            }
        } catch (IOException e) {
            // If an error occurs print the stacktrace.
            e.printStackTrace();
        }
        // Return the list of items.
        return items;
    }

    private Item parseHTMLTable(String[] itemParts) {
        // Remove the html components of each string to get the item name, value, and serial number.
        itemParts[0] = itemParts[0].replace(START, "");
        itemParts[0] = itemParts[0].replace(END, "");
        itemParts[1] = itemParts[1].replace("\t\t\t<td>$", "");
        itemParts[1] = itemParts[1].replace(END, "");
        itemParts[2] = itemParts[2].replace(START, "");
        itemParts[2] = itemParts[2].replace(END, "");
        // Return a new item using the 3 strings from the array.
        return new Item(itemParts[0],new BigDecimal(itemParts[1]), itemParts[2]);
    }

    private List<Item> importJSON(File file) {
        // Create a new list of items.
        List<Item> items = new ArrayList<>();
        // Within a try/catch create a json reader at the file location.
        try (JsonReader reader = new JsonReader(new FileReader(file))) {
            // Create a Gson object and call fromJson using the collectionType.
            Gson gson = FxGson.create();
            Type collectionType = new TypeToken<List<Item>>(){}.getType();
            items = gson.fromJson(reader, collectionType);
        } catch (IOException e) {
            // If an error occurs print the stacktrace.
            e.printStackTrace();
        }
        // Return the list of items.
        return items;
    }

    private List<Item> importTSV(File file) {
        // Create a list of items and a string for the current line of the BufferedReader.
        List<Item> items = new ArrayList<>();
        String currentLine;
        // Set up a try/catch to read through the document.
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // Pass the first line because it only contains the titles of the columns.
            currentLine = br.readLine();
            // Then loop until the end of file with the line being passed into the parseTSVFile method.
            while ((currentLine = br.readLine()) != null) {
                items.add(parseTSVFile(currentLine));
            }
        } catch (IOException e) {
            // If an error occurs print the stacktrace.
            e.printStackTrace();
        }
        return items;
    }

    private Item parseTSVFile(String itemText) {
        // From the string given split the string into an array of strings based on the \t character.
        String[] itemParts = itemText.split("\t",3);
        // Remove the $ from the value before converting it to a BigDecimal.
        itemParts[1] = itemParts[1].replace("$","");
        // Create and return an item from the 3 strings within the array.
        return new Item(itemParts[0], new BigDecimal(itemParts[1]), itemParts[2]);
    }
}
