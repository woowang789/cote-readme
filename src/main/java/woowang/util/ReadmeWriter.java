package woowang.util;

import org.jsoup.nodes.Element;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReadmeWriter {
    public static void write(String readmePath, List<Element> elements) {
        try (FileWriter writer = new FileWriter(readmePath)){
            Element root = new Element("body").appendChildren(elements);
            writer.write(root.toString());
        } catch (IOException e) {
            throw new RuntimeException("readme write 중 에러 발생");
        }
    }

}
