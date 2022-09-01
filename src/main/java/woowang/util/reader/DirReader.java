package woowang.util.reader;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DirReader {

    static public List<String> getProbs(String probPath,String type) {
        final String PROBLEM_PATH = probPath+"/"+type;

        File problems_dir = new File(PROBLEM_PATH);
        if(!problems_dir.exists()) throw new RuntimeException("problem 경로를 찾을 수 없습니다.");
        File[] problems = problems_dir.listFiles();
        return Arrays.stream(problems).map(File::getName).collect(Collectors.toList());
    }

}
