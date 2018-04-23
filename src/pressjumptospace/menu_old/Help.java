package pressjumptospace.menu_old;

import java.util.Hashtable;

@Deprecated
public class Help {
    // public static final String NEWLINE = System.getProperty("line.separator");
    public Help() {
        content = new Hashtable<String, String>();
        content.put("controls", "<html>A: walk left<br>D: walk right<br>SPACE: jump<br>ENTER: toggle game mode</html>");
    }

    // I just wanna be cool...
    public Hashtable<String, String> content;
}