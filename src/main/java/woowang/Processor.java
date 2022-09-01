package woowang;

import org.jsoup.nodes.Element;
import woowang.util.parser.ProblemParser;
import woowang.problem.ProbInfo;
import woowang.problem.Problem;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Processor {

    static public Element execute(ProblemParser parser,List<String> probFiles, List<Element> probEls){
        Map<Integer, ProbInfo> probInfoMap = probFiles.stream().map(parser::toProbInfo)
                .collect(Collectors.toMap(ProbInfo::getId, i -> i));
        Map<Integer, Problem> readmeProbMap = probEls.stream().map(parser::toProblem)
                .collect(Collectors.toMap(Problem::getId, i -> i));

        return parser.toSection(syncProblem(parser,probInfoMap, readmeProbMap));
    }

     static private List<Element> syncProblem(ProblemParser parser,Map<Integer, ProbInfo> probInfoMap, Map<Integer,Problem> readmeProbMap){
         Map<Integer, Problem> map = new HashMap<>();
         for (Map.Entry<Integer,ProbInfo> entry : probInfoMap.entrySet()) {
             Integer problemId = entry.getKey();
             ProbInfo info = entry.getValue();
             if (readmeProbMap.containsKey(problemId)) {
                 map.put(problemId, readmeProbMap.get(problemId));
             }else{
                 Problem p = parser.getProblem(problemId);
                 p.addInfo(info.getLanguage(), info.getPath());
                 map.put(problemId,p);
             }
         }
         return map.entrySet().stream()
                 .sorted(Comparator.comparing(p -> p.getValue().getDateTime()))
                 .map(i->parser.toElement(i.getValue())).collect(Collectors.toList());
     }


}
