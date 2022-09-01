package woowang.util.parser;

import org.jsoup.nodes.Element;
import woowang.problem.ProbInfo;
import woowang.problem.Problem;

import java.util.List;

public interface ProblemParser {

    // 꽉 찬 Problem
    Problem toProblem(Element element);

    // 꽉 찬 element
    Element toElement(Problem problem);

    // id title level url 가져옴
    Problem getProblem(int probId);

    // 파일 이름으로 ext, path, name, id 채워옴
    ProbInfo toProbInfo(String fileName);

    // problems -> probSection (div element)
    Element toSection(List<Element> elements);

}
