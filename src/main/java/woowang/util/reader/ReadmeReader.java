package woowang.util.reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadmeReader {

    public static List<Element> getProbs(String readmePath,String type) {
        try {
            Document doc = Jsoup.parse(new File(readmePath));
            Element section = doc.getElementById(type);
            Element tbody = section.getElementsByTag("tbody").first();
            return new ArrayList<>(tbody.getElementsByTag("tr"));

        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
