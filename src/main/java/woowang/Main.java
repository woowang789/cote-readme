package woowang;

import org.jsoup.nodes.Element;
import woowang.util.ReadmeWriter;
import woowang.util.parser.BaekjoonParser;
import woowang.util.parser.ProgrammersParser;
import woowang.util.reader.DirReader;
import woowang.util.reader.ReadmeReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        final String dirPath = "src/main/java/problem";
        final String readmePath = "README.md";
        File[] probRootDir = new File(dirPath).listFiles();
        Set<String> types = Arrays.stream(probRootDir).map(File::getName).collect(Collectors.toSet());

        List<Element> sections = new ArrayList<>();
        for (String type : types) {
            if (type.equals("baekjoon")) {
                List<String> probIds = DirReader.getProbs(dirPath, type);
                List<Element> probEls = ReadmeReader.getProbs(readmePath, type);
                BaekjoonParser parser = new BaekjoonParser();
                sections.add(Processor.execute(parser, probIds, probEls));
            } else if (type.equals("programmers")) {
                List<String> probIds = DirReader.getProbs(dirPath, type);
                List<Element> probEls = ReadmeReader.getProbs(readmePath, type);
                ProgrammersParser parser = new ProgrammersParser();
                sections.add(Processor.execute(parser, probIds, probEls));
            }
        }
        ReadmeWriter.write(readmePath, sections);

    }
}