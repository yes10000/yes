package me.peanut.hydrogen;

import com.thealtening.auth.AltService;
import com.vdurmont.semver4j.Semver;
import me.peanut.hydrogen.file.FileManager;
import me.peanut.hydrogen.module.ModuleManager;
import me.peanut.hydrogen.ui.clickgui.ClickGui;
import me.peanut.hydrogen.ui.ingame.ArrayList;
import me.peanut.hydrogen.utils.FontHelper;
import me.peanut.hydrogen.utils.KeybindManager;
import me.peanut.hydrogen.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import me.peanut.hydrogen.altmanager.account.AccountManager;
import me.peanut.hydrogen.command.CommandManager;
import me.peanut.hydrogen.settings.SettingsManager;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

@Mod(modid = Hydrogen.modid, name = Hydrogen.name, version = Hydrogen.version_number, useMetadata = true)
public class Hydrogen {

    public static final String modid = "hydrogen";
    public static final String name = "Hydrogen";
    public static final String devs = "zPeanut & UltramoxX";
    public static final String github = "https://github.com/zpeanut/hydrogen/";
    public static final String release = github + "releases/";

    public static final String version_number = "1.10";
    private static final String version_suffix = "-pre";

    // this basically only exists so the semver comparison doesnt result in an error when it tries to compare a full release version (i.e. no patch version) e.g "1.9" with "1.8.3"
    // the error "no patch version" results as "1.9" should be expected to be written as "1.9.0"
    // ive refrained from just renaming the version number to 1.9.0 to keep unity with all the other full release versions
    private static final String semantic_version = "1.10.0-pre";

    public static final String version = "v" + version_number + version_suffix;

    public String prefix = "§7[§b" + name + "§7]";

    private static Hydrogen instance;
    private static Utils utils;

    public ModuleManager moduleManager;
    public SettingsManager settingsManager;
    public KeybindManager keybindManager;
    public CommandManager commandManager;
    public FileManager fileManager;
    public AccountManager accountManager;
    public AltService altService;
    public ClickGui clickgui;
    public File directory;

    public boolean outdated = false;
    public boolean panic = false;
    public String newversion;

    public Hydrogen() {
        instance = this;
    }

    public void startClient() {
        panic = false;
        directory = new File(Minecraft.getMinecraft().mcDataDir, name);
        if (!this.directory.exists()) {
            directory.mkdir();
        }
        settingsManager = new SettingsManager();
        moduleManager = new ModuleManager();
        keybindManager = new KeybindManager();
        commandManager = new CommandManager();
        accountManager = new AccountManager(new File(this.directory.toString()));
        altService = new AltService();
        utils = new Utils();
        clickgui = new ClickGui();
        FontHelper.loadFonts();
        moduleManager.addModules();
        new ArrayList();
        this.isOutdated();
        if(settingsManager.getSettingByName("Startup Sound").isEnabled()) {
            Utils.playSound("startup.wav");
        }
    }

    public static Hydrogen getClient() {
        return instance;
    }

    public static Utils getUtils() {
        return utils;
    }

    public void stopClient() {}

    public void isOutdated() {
        try {
            URL url = new URL("https://raw.githubusercontent.com/zPeanut/Resources/master/semversion-hydrogen");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                Semver semver = new Semver(line);
                if (semver.isGreaterThan(semantic_version)) {
                    outdated = true;
                    newversion = line;
                } else {
                    outdated = false;
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchToMojang() {
        try {
            this.altService.switchService(AltService.EnumAltService.MOJANG);
        } catch (NoSuchFieldException e) {
            Utils.errorLog("Couldn't switch to Mojang AltService");
        } catch (IllegalAccessException e) {
            Utils.errorLog("Couldn't switch to Mojang AltService");
        }
    }

    public void switchToAltening() {
        try {
            this.altService.switchService(AltService.EnumAltService.THEALTENING);
        } catch (NoSuchFieldException e) {
            Utils.errorLog("Couldn't switch to TheAltening AltService");
        } catch (IllegalAccessException e) {
            Utils.errorLog("Couldn't switch to TheAltening AltService");
        }
    }
}

