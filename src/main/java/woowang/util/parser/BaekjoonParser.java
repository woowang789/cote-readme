package woowang.util.parser;

import org.json.simple.JSONObject;
import org.jsoup.nodes.Element;
import woowang.problem.Baekjoon;
import woowang.problem.ProbInfo;
import woowang.problem.Problem;
import woowang.util.ProblemExtension;
import woowang.util.RequestSender;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class BaekjoonParser implements ProblemParser {
    private final String API_BASIC_URL = "https://solved.ac/api/v3/problem/show?problemId=";
    private final String PROBLEM_BASIC_URL = "https://www.acmicpc.net/problem/";
    private final String FILE_PATH = "src/main/java/problem/baekjoon/";
    private final String FILENAME_PREFIX = "P_";

    @Override
    public Problem toProblem(Element element) {
        Element idEl = element.getElementById("id");
        int id = Integer.parseInt(idEl.text());
        String url = idEl.getElementsByTag("a").attr("href");

        String title = element.getElementById("title").text();
        String level = element.getElementById("level").text();

        Element langEl = element.getElementById("lang");
        String lang = langEl.text();
        String path = langEl.getElementsByTag("a").attr("href");

        LocalDateTime dateTime = LocalDateTime.parse(element.getElementById("date").attr("value"));
        String memo = element.getElementById("memo").text();
        return new Baekjoon(id, title, level, url, memo, lang, path, dateTime);
    }

    @Override
    public Element toElement(Problem problem) {
        Element tr = new Element("tr");

        Element idEl = new Element("td").id("id").appendChild(
                new Element("a").attr("href",problem.getUrl()).text(Integer.toString(problem.getId()))
        );
        Element titleEl = new Element("td").id("title").text(problem.getTitle());
        Element levelEl = new Element("td").id("level").text(problem.getLevel());
        Element langEl = new Element("td").id("lang").appendChild(
                new Element("a").attr("href", problem.getPath()).text(problem.getLanguage())
        );
        Element dataEl = new Element("td").id("date").attr("value", problem.getDateTime().toString())
                .text(problem.getDateTime().toLocalDate().toString());
        Element memoEl = new Element("td").id("memo").text(problem.getMemo());

        return tr.appendChildren(Arrays.asList(idEl, titleEl, levelEl, langEl, dataEl, memoEl));
    }

    @Override
    public Problem getProblem(int probId) {
        String apiUrl = API_BASIC_URL + probId;
        JSONObject json = RequestSender.sendApi(apiUrl, new HashMap<>());

        int id = ((Long) json.get("problemId")).intValue();
        String title = (String) json.get("titleKo");
        String level = ((Long) json.get("level")).toString();
        String url = PROBLEM_BASIC_URL + probId;

        return new Baekjoon(id, title, level, url);
    }

    @Override
    public ProbInfo toProbInfo(String fileName) {
        ProbInfo info = new ProbInfo();
        info.setFileName(fileName);

        int extPos = fileName.lastIndexOf(".") + 1;
        int prePos = fileName.indexOf(FILENAME_PREFIX) + FILENAME_PREFIX.length();
        String ext = fileName.substring(extPos);
        String language = ProblemExtension.valueOf(ext).lang;
        int id = Integer.parseInt(fileName.substring(prePos, extPos-1));
        info.setId(id);
        info.setLanguage(language);
        info.setPath(FILE_PATH + fileName);
        return info;
    }

    @Override
    public Element toSection(List<Element> elements) {
        String type = "baekjoon";
        Element element = new Element("div").id(type);

        Element h2 = new Element("h2").text(type.toUpperCase());
        Element tr = new Element("tr")
                .appendChild(new Element("th").text("ID"))
                .appendChild(new Element("th").text("Title"))
                .appendChild(new Element("th").text("Level"))
                .appendChild(new Element("th").text("Language"))
                .appendChild(new Element("th").text("Date"))
                .appendChild(new Element("th").text("Memo"));
        Element table = new Element("table")
                .appendChild(new Element("thead").appendChild(tr))
                .appendChild(new Element("tbody").appendChildren(elements));

        return element.appendChild(h2).appendChild(table);
    }
}
