package tk.peanut.phosphor.modules.modules.player;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.lwjgl.input.Keyboard;
import scala.collection.parallel.ParIterableLike;
import tk.peanut.phosphor.Phosphor;
import tk.peanut.phosphor.events.EventRender2D;
import tk.peanut.phosphor.events.EventUpdate;
import tk.peanut.phosphor.modules.Category;
import tk.peanut.phosphor.modules.Module;
import tk.peanut.phosphor.ui.clickgui.settings.Setting;

import java.awt.*;
import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;

public class FastPlace extends Module {


    public FastPlace() {
        super("FastPlace", "Removes right click cooldown", Keyboard.KEY_U, Category.Player, -1);
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        if(this.isToggled()) {
                this.mc.rightClickDelayTimer = 0;
        }
    }
}
