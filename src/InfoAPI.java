import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.util.Scanner;

public class InfoAPI {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Input your URL to Scrape: ");
        String url = userInput.nextLine();// Website URL
        int listSize = 1;
        System.out.println("Input maximum size for list: ");
        int maximumListSize = userInput.nextInt();

        Scanner JSONNaming = new Scanner(System.in);
        System.out.println("Name JSON start: ");
        String firstName = JSONNaming.nextLine();
        System.out.println("Named " + firstName + "!");
        System.out.print("Name second JSON name: ");
        String secondName = JSONNaming.nextLine();
        System.out.println("Named " + secondName + "!");

        try {
            PrintWriter JSONWriter = new PrintWriter("scrape.json"); // Creates txt file to print info
            Document doc = Jsoup.connect(url).get(); // Gets Website info
            Elements htmlGet = doc.select("div.mtitle"); // Selects the element of the website
            JSONWriter.println("{\n" + "   " + "\"" + firstName +"\": [");
            for (Element movieList:htmlGet) { // Runs while there's movies
                if (listSize <= (maximumListSize - 1)) {
                    listSize++;
                    JSONWriter.print("      " + "{\n" + "         " + "\"" + secondName + "\": " + "\"" + movieList.getElementsByTag("a").first().text() + "\"\n" + "      " + "}" + "," + "\n"); // Writes to file
                    System.out.println(movieList.getElementsByTag("a").first().text()); // Prints to console, check if it's correct!
                } else if (listSize == maximumListSize) {
                    listSize++;
                    JSONWriter.print("      " + "{\n" + "         " + "\"" + secondName + "\": " + "\"" + movieList.getElementsByTag("a").first().text() + "\"\n" + "      " + "}" + "\n");
                    System.out.println(movieList.getElementsByTag("a").first().text());
                }
            } JSONWriter.println("   " + "]" + "\n}");
            JSONWriter.close(); // Ends the writer
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
