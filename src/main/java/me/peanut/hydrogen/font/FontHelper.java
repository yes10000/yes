package me.peanut.hydrogen.font;

import java.awt.*;
import java.io.InputStream;

/**
 * Created by peanut on 15/02/2021
 */
public class FontHelper {

    public static H2FontRenderer verdana;

    public static H2FontRenderer sf_r;
    public static H2FontRenderer sf_l;
    public static H2FontRenderer sf_l2;
    public static H2FontRenderer sf_l_mm;

    public static H2FontRenderer comfortaa_r;

    public static H2FontRenderer comfortaa_r2;
    public static H2FontRenderer comfortaa_rb;
    public static H2FontRenderer comfortaa_l;

    public static H2FontRenderer rubik;


    public static void loadFonts() {
        try {

            InputStream stream4 = FontHelper.class.getResourceAsStream("/assets/hydrogen/Panton-Trial-Black.ttf");
            Font font4 = Font.createFont(Font.TRUETYPE_FONT, stream4).deriveFont(36f);
            comfortaa_r = new H2FontRenderer(font4, true, 8);

            InputStream stream9 = FontHelper.class.getResourceAsStream("/assets/hydrogen/RubikMonoOne-Regular.ttf");
            Font font9 = Font.createFont(Font.TRUETYPE_FONT, stream9).deriveFont(190f);
            rubik = new H2FontRenderer(font9, true, 8);

            InputStream stream5 = FontHelper.class.getResourceAsStream("/assets/hydrogen/Panton-Trial-Black.ttf");
            Font font5 = Font.createFont(Font.TRUETYPE_FONT, stream5).deriveFont(200f);
            comfortaa_rb = new H2FontRenderer(font5, true, 8);

            InputStream stream6 = FontHelper.class.getResourceAsStream("/assets/hydrogen/Panton-Trial-Black.ttf");
            Font font6 = Font.createFont(Font.TRUETYPE_FONT, stream6).deriveFont(120f);
            comfortaa_r2 = new H2FontRenderer(font6, true, 8);

            verdana = new H2FontRenderer(new Font("Verdana", Font.PLAIN, 32), true, 8);

            InputStream stream = FontHelper.class.getResourceAsStream("/assets/hydrogen/Roboto-Light.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(36f);
            sf_l = new H2FontRenderer(font, true, 8);

            InputStream stream33 = FontHelper.class.getResourceAsStream("/assets/hydrogen/Roboto-Light.ttf");
            Font font33 = Font.createFont(Font.TRUETYPE_FONT, stream33).deriveFont(72f);
            sf_l2 = new H2FontRenderer(font33, true, 8);

            InputStream stream34 = FontHelper.class.getResourceAsStream("/assets/hydrogen/Roboto-Light.ttf");
            Font font34 = Font.createFont(Font.TRUETYPE_FONT, stream34).deriveFont(200f);
            sf_l_mm = new H2FontRenderer(font34, true, 8);

            InputStream stream2 = FontHelper.class.getResourceAsStream("/assets/hydrogen/AvenirLTStd-Black.otf");
            Font font2 = Font.createFont(Font.TRUETYPE_FONT, stream2).deriveFont(36f);
            sf_r = new H2FontRenderer(font2, true, 8);

            InputStream stream3 = FontHelper.class.getResourceAsStream("/assets/hydrogen/Panton-Trial-Light.ttf");
            Font font3 = Font.createFont(Font.TRUETYPE_FONT, stream3).deriveFont(36f);
            comfortaa_l = new H2FontRenderer(font3, true, 8);

        } catch (Exception exception) {}
    }
}
