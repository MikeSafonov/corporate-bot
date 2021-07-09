import com.github.msafonov.corporate.bot.UniqueCode;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;


public class UniqueCodeTest {
    private UniqueCode uniqueCode=new UniqueCode();



    @Test
    public void generatedCodeNumberIsNumberTest() {




        String text = uniqueCode.generateCodeNumber(Mockito.mock(EntityManager.class));
        Pattern pattern = Pattern.compile("\\D");
        Matcher matcher = pattern.matcher(text);
        assertFalse(matcher.find());
    }

    @Test
    public void generatedCodeNumberIsSixTest() {

        MockitoAnnotations.initMocks(this);
        String text = uniqueCode.generateCodeNumber(Mockito.mock(EntityManager.class));
        assertEquals(6, text.length());
    }
}
