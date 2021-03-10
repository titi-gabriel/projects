package gfx;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontLoader
{

    public static Font loadFont(String path, float size)
    {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, size); //crearea fontului
        }
        catch (FontFormatException | IOException e) //verificarea erorilor
        {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
