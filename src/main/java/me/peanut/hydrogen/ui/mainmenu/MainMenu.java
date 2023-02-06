package me.peanut.hydrogen.ui.mainmenu;

import me.peanut.hydrogen.font.FontHelper;
import me.peanut.hydrogen.font.FontUtil;
import me.peanut.hydrogen.utils.ParticleGenerator;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiOverlayDebug;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Loader;
import me.peanut.hydrogen.Hydrogen;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.*;

/**
 * Created by peanut on 26/02/2021
 */
public class MainMenu extends GuiScreen {

    static String splashText;

    public static final Minecraft mc = Minecraft.getMinecraft();
    public static final ParticleGenerator particleGenerator = new ParticleGenerator(100, mc.displayWidth, mc.displayHeight);

    public MainMenu() {
        try {
            URL url = new URL("https://raw.githubusercontent.com/zPeanut/Resources/master/splash-hydrogen");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                splashText = line;
            }
            if(splashText == null) {
                splashText = "Splash could not be loaded!";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void drawMenu(int mouseX, int mouseY) {
        if(Hydrogen.getClient().panic) {
            return;
        }

        // side menu rects (buttons)

        //drawRect(40, 0, 140, Utils.getScaledRes().getScaledHeight(), 0x60000000);
        //drawRect(40, 0, 41, Utils.getScaledRes().getScaledHeight(), 0x60000000);
        //drawRect(139, 0, 140, Utils.getScaledRes().getScaledHeight(), 0x60000000);

        // right hand strings

        //String mds = String.format("%s mods loaded, %s mods active", Loader.instance().getModList().size(), Loader.instance().getActiveModList().size());
        //String fml = String.format("Powered by Forge %s", ForgeVersion.getVersion());
        //String mcp = "MCP 9.19";
        //String mcv = "Minecraft 1.8.9";
        //String name = String.format("%s %s", Hydrogen.name, Hydrogen.version);
        String mname = String.format("Logged in as §7%s", Minecraft.getMinecraft().getSession().getUsername());
        //String devs = String.format("Developed by §7%s §fand §7%s", Hydrogen.devs[0], Hydrogen.devs[1]);

        //FontHelper.comfortaa_r.drawStringWithShadowMainMenu(mds, Utils.getScaledRes().getScaledWidth() - FontHelper.comfortaa_r.getStringWidth(mds) - 4, Utils.getScaledRes().getScaledHeight() - 14, Color.WHITE);
        //FontHelper.comfortaa_r.drawStringWithShadowMainMenu(fml, Utils.getScaledRes().getScaledWidth() - FontHelper.comfortaa_r.getStringWidth(fml) - 4, Utils.getScaledRes().getScaledHeight() - 26, Color.WHITE);
        //FontHelper.comfortaa_r.drawStringWithShadowMainMenu(mcp, Utils.getScaledRes().getScaledWidth() - FontHelper.comfortaa_r.getStringWidth(mcp) - 4, Utils.getScaledRes().getScaledHeight() - 38, Color.WHITE);
        //FontHelper.comfortaa_r.drawStringWithShadowMainMenu(mcv, Utils.getScaledRes().getScaledWidth() - FontHelper.comfortaa_r.getStringWidth(mcv) - 4, Utils.getScaledRes().getScaledHeight() - 50, Color.WHITE);

        //FontHelper.comfortaa_r.drawStringWithShadowMainMenu(name, Utils.getScaledRes().getScaledWidth() - FontHelper.comfortaa_r.getStringWidth(name) - 4, 4, Color.WHITE);
        //FontHelper.comfortaa_r.drawStringWithShadowMainMenu(devs, Utils.getScaledRes().getScaledWidth() - FontHelper.comfortaa_r.getStringWidth(devs) - 4, 16, Color.WHITE);
        FontHelper.comfortaa_r.drawStringWithShadowMainMenu(mname, Utils.getScaledRes().getScaledWidth() - FontHelper.comfortaa_r.getStringWidth(mname) - 4, 8, Color.WHITE);

        // outdated check

        if(Hydrogen.getClient().isStableBuild) {
            if (Hydrogen.getClient().outdated) {
                //FontHelper.comfortaa_r.drawStringWithShadow("§cOutdated!", 144, Utils.getScaledRes().getScaledHeight() - 26, Color.WHITE);
                //FontHelper.comfortaa_r.drawStringWithShadow("Newest Version: §a" + Hydrogen.getClient().newversion, 144, Utils.getScaledRes().getScaledHeight() - 14, Color.WHITE);
            } else {
                //FontHelper.comfortaa_r.drawStringWithShadowMainMenu("§aNo Update available!", 144, Utils.getScaledRes().getScaledHeight() - 14, Color.white);
            }
        }

        // first start

        if(Hydrogen.getClient().firstStart) {
            //FontUtil.drawTotalCenteredStringWithShadowSFL2("Welcome to", Utils.getScaledRes().getScaledWidth() / 2 - 22, Utils.getScaledRes().getScaledHeight() / 2 - (FontHelper.sf_l2.getHeight() / 2) - 35, new Color(207, 238, 255));
        }

        // prerelease / beta / dev check

        if(!Hydrogen.getClient().isStableBuild) {
           // FontHelper.comfortaa_r.drawStringWithShadowMainMenu("§c§lWARNING: §7Non-stable version!", 144, Hydrogen.getClient().outdated ? Utils.getScaledRes().getScaledHeight() - 50 : Utils.getScaledRes().getScaledHeight() - 26, Color.white);
           // FontHelper.comfortaa_r.drawStringWithShadowMainMenu("§7Please report any issues at our §f§n§lGitHub.", 144, Hydrogen.getClient().outdated ? Utils.getScaledRes().getScaledHeight() - 38 : Utils.getScaledRes().getScaledHeight() - 14, Color.white);

            // fps counter

            //FontHelper.comfortaa_r.drawStringWithShadowMainMenu("FPS: " + Minecraft.getDebugFPS(), 144, 2, Color.WHITE);
        }

        // logo

        FontHelper.comfortaa_rb.drawString("Green", Utils.getScaledRes().getScaledWidth() / 2 - 62, Utils.getScaledRes().getScaledHeight() / 2 - 79.5, new Color(15, 15, 15));
        FontHelper.comfortaa_rb.drawString("Green", Utils.getScaledRes().getScaledWidth() / 2 - 65, Utils.getScaledRes().getScaledHeight() / 2 - 78.5, new Color(50, 168, 82));

        FontHelper.comfortaa_r2.drawString("client", Utils.getScaledRes().getScaledWidth() / 2 - 38, Utils.getScaledRes().getScaledHeight() / 2 - 38.5, new Color(15, 15, 15 ));
        FontHelper.comfortaa_r2.drawString("client", Utils.getScaledRes().getScaledWidth() / 2 - 41, Utils.getScaledRes().getScaledHeight() / 2 - 39.5, new Color(96, 171, 75));

        // splash

        //FontUtil.drawTotalCenteredStringWithShadowComfortaa(splashText, Utils.getScaledRes().getScaledWidth() / 2 + (FontHelper.sf_l_mm.getStringWidth("hydrogen") / 2) - 46, Utils.getScaledRes().getScaledHeight() / 2 + 33, Color.WHITE);

        particleGenerator.drawParticles(mouseX, mouseY, false);
    }

}