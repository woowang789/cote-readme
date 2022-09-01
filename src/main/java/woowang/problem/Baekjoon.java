package woowang.problem;

import java.time.LocalDateTime;

public class Baekjoon extends Problem {
    public Baekjoon(int id, String title, String level, String url) {
        super(id, title, level, url);
    }

    public Baekjoon(int id, String title, String level, String url, String memo, String language, String path, LocalDateTime dateTime) {
        super(id, title, level, url, memo, language, path, dateTime);
    }

}
