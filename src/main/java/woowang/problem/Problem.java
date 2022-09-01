package woowang.problem;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter @AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Problem {
    protected int id;
    protected String title;
    protected String level;
    protected String url;

    protected String memo;
    protected String language;
    protected String path;

    protected LocalDateTime dateTime;

    public Problem(int id, String title, String level, String url) {
        this.id = id;
        this.title = title;
        this.level = level;
        this.url = url;
    }

    public void addInfo(String language, String path) {
        this.language = language;
        this.path = path;
        this.memo = "";
        this.dateTime = LocalDateTime.now();
    }
}
