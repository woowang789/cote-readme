package woowang.problem;

import java.time.LocalDateTime;

public class Programmers extends Problem{
    public Programmers(int id, String title, String level, String url, String memo, String language, String path, LocalDateTime dateTime) {
        super(id, title, level, url, memo, language, path, dateTime);
    }

    public Programmers(int id, String title, String level, String url) {
        super(id, title, level, url);
    }

}
