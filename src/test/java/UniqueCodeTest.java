import com.github.msafonov.corporate.bot.UniqueCode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class UniqueCodeTest {
    private static final UniqueCode uniqueCode = new UniqueCode();
    static String text = uniqueCode.generateCodeNumber(new ArrayList<>());

    @Test
    public void generatedCodeNumberIsNumberTest() {
        Pattern pattern = Pattern.compile("\\D");
        Matcher matcher = pattern.matcher(text);
        assertFalse(matcher.find());
    }

    @Test
    public void generatedCodeNumberIsSixTest() {
        assertEquals(6, text.length());
    }
}
