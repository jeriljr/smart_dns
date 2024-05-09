import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MetadataUpdater {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Hostname.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("updated_data.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                String hostname = parts[0].trim();
                String ipAddress = parts[1].trim();
                String metadata = getTitleFromURL("http://" + hostname);

                // Write updated entry to the output file
                writer.write(hostname + " | " + ipAddress + " | " + metadata + "\n");
            }

            reader.close();
            writer.close();

            System.out.println("Metadata updated and stored successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getTitleFromURL(String urlString) {
        try {
            Document doc = Jsoup.connect(urlString).get();
            return doc.title();
        } catch (IOException e) {
            return "Error retrieving title";
        }
    }
}

