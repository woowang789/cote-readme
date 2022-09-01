package woowang.parser;

import org.junit.jupiter.api.Test;
import woowang.util.parser.BaekjoonParser;

class BaekjoonParserTest {

    @Test
    void getInfo() {
        BaekjoonParser parser = new BaekjoonParser();
        parser.toProbInfo("P_123.java");

    }

}