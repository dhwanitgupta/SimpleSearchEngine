package tokenator;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleTokenator implements Tokenator {

    private static Pattern pattern = Pattern.compile("[a-z0-9]+");

    @Override
    public Set<String> getTokens(String text) {
        if (text.isEmpty()) return  new HashSet<String>();

        Matcher matcher = pattern.matcher(text.toLowerCase());

        Set<String> tokens = new HashSet<String>();

        while(matcher.find()) {
            tokens.add(matcher.group());
        }

        return tokens;
    }
}
