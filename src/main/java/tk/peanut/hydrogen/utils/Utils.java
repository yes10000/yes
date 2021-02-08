package tk.peanut.hydrogen.utils;

import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Session;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.module.modules.render.NameTags;

import java.awt.*;
import java.net.Proxy;
import java.util.Random;

public class Utils {
    private static final Random RANDOM = new Random();
    public static Utils instance;

    /**
     * This function returns a random value between min and max
     * If <code>min >= max</code> the function will return min
     *
     * @param min Minimal
     * @param max Maximal
     * @return The value
     */

    public Utils() {
        instance = this;
    }
    public static int random(int min, int max) {
        if (max <= min) return min;

        return RANDOM.nextInt(max - min) + min;
    }

    public static final ScaledResolution getScaledRes() {
        final ScaledResolution scaledRes = new ScaledResolution(Minecraft.getMinecraft());
        return scaledRes;
    }

    public static int getRainbow(float seconds, float saturation, float brightness, long index) {
        float hue = ((System.currentTimeMillis() + index) % (int)(seconds * 1000)) / (float)(seconds * 1000);
        int color = Color.HSBtoRGB(hue, saturation, brightness);
        return color;
    }

    public static void rect(float x1, float y1, float x2, float y2, int fill) {
        GlStateManager.color(0, 0, 0);
        GL11.glColor4f(0, 0, 0, 0);

        float f = (fill >> 24 & 0xFF) / 255.0F;
        float f1 = (fill >> 16 & 0xFF) / 255.0F;
        float f2 = (fill >> 8 & 0xFF) / 255.0F;
        float f3 = (fill & 0xFF) / 255.0F;

        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);

        GL11.glPushMatrix();
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glBegin(7);
        GL11.glVertex2d(x2, y1);
        GL11.glVertex2d(x1, y1);
        GL11.glVertex2d(x1, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glPopMatrix();

        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }

    public static void rectBorder(float x1, float y1, float x2, float y2, int outline) {
        rect(x1, y2-1, x2, y2, outline);
        rect(x1, y1, x2, y1+1, outline);
        rect(x1, y1, x1+1, y2, outline);
        rect(x2-1, y1, x2, y2, outline);
    }

    public static void passSpecialRenderNameTags(EntityLivingBase p_77033_1_, double x, double y, double z) {
        if(Hydrogen.getClient().moduleManager.getModule(NameTags.class).isEnabled()) {
            if((p_77033_1_.getEntityId() != -3 && p_77033_1_ != Minecraft.getMinecraft().thePlayer) && !(p_77033_1_.isInvisible()))
            {
                if (Hydrogen.getClient().moduleManager.getModule(NameTags.class).isEnabled()) {
                    String p_147906_2_ = p_77033_1_.getDisplayName().getFormattedText();


                    double[] pos = Utils.entityWorldPos(p_77033_1_);
                    double[] pos2 = Utils.entityWorldPos(Minecraft.getMinecraft().thePlayer);
                    float xd = (float)(pos2[0]-pos[0]);
                    float yd = (float)(pos2[1]-pos[1]);
                    float zd = (float)(pos2[2]-pos[2]);
                    double dist = MathHelper.sqrt_float(xd*xd+yd*yd+zd*zd);

                    float distance = (float)dist;
                    float scaleFactor = distance < 10.0F ? 0.9F : distance / 11.11F;

                    int color = 16777215;
                    float height = 0.0F;
                    if ((p_77033_1_ instanceof EntityPlayer)) {
                        EntityPlayer player = (EntityPlayer) p_77033_1_;
                        if (Hydrogen.getClient().settingsManager.getSettingByName("Health").isEnabled()) {
                            if (player.getHealth() > 16.0F) {
                                p_147906_2_ = p_147906_2_ + " \u00a7a" + (int)player.getHealth();
                            } else if (player.getHealth() > 8.0F) {
                                p_147906_2_ = p_147906_2_ + " \u00a7e" + (int)player.getHealth();
                            } else {
                                p_147906_2_ = p_147906_2_ + " \u00a7c" + (int)player.getHealth();
                            }
                        }


                        if (Hydrogen.getClient().settingsManager.getSettingByName("State").isEnabled()) {
                            if (player.isSneaking()) {
                                p_147906_2_ += " \u00a74[S]";
                            } else if (player.isInvisible()) {
                                p_147906_2_ += " \u00a7f[I]";
                            }
                        }

                        if (distance >= 10.0F) {
                            height = (float) (height + (distance / 40.0F - 0.25D));
                        }
                    }
                    FontRenderer var12 = Minecraft.getMinecraft().fontRendererObj;
                    if (var12 == null) {
                        return;
                    }

                    p_147906_2_+= "";

                    float var13 = 1.6F;
                    float var14 = 0.016666668F * var13;
                    GlStateManager.pushMatrix();
                    GlStateManager.translate((float)x + 0.0F, (float)y + p_77033_1_.height + 0.5F, (float)z);
                    GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
                    GlStateManager.rotate(Minecraft.getMinecraft().getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
                    GlStateManager.scale(-var14*scaleFactor, -var14*scaleFactor, var14*scaleFactor);
                    GlStateManager.disableLighting();
                    GlStateManager.depthMask(false);
                    GlStateManager.disableDepth();
                    GlStateManager.enableBlend();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                    Tessellator var15 = Tessellator.getInstance();
                    WorldRenderer var16 = var15.getWorldRenderer();

                    GlStateManager.disableTexture2D();

                    int var18 = var12.getStringWidth(p_147906_2_) / 2;
                    float w = var18;
                    float h = var12.FONT_HEIGHT;
                    float offY = 0;

                    Utils.rectBorder(-w-4, -4+offY, w+4, h+3+offY, 0x77111111);
                    Utils.rect(-w-3, -3+offY, w+3, h+2+offY, 0x33111111);

                    GlStateManager.enableTexture2D();
                    int co = -1;
                    var12.drawString(p_147906_2_, -var12.getStringWidth(p_147906_2_) / 2, 0, co);


                    if (Hydrogen.getClient().settingsManager.getSettingByName("Items").isEnabled())
                        NameTags.instance.renderArmorESP(p_77033_1_);
                }

                GlStateManager.enableDepth();
                GlStateManager.depthMask(true);
                GlStateManager.enableLighting();
                GlStateManager.disableBlend();
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.popMatrix();
                return;
            }
        }
    }


    public static void errorLog(String message) {
        System.out.println("[ERROR] [Phosphor] " + message);
    }

    public static void drawRect(double left, double top, double right, double bottom, int color) {
        int j;
        if (left < right) {
            j = (int) left;
            left = right;
            right = j;
        }

        if (top < bottom) {
            j = (int) top;
            top = bottom;
            bottom = j;
        }

        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos((double)left, (double)bottom, 0.0D).endVertex();
        worldrenderer.pos((double)right, (double)bottom, 0.0D).endVertex();
        worldrenderer.pos((double)right, (double)top, 0.0D).endVertex();
        worldrenderer.pos((double)left, (double)top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static double[] entityWorldPos(Entity e) {
        float p_147936_2_ = Minecraft.getMinecraft().timer.renderPartialTicks;

        double x = (e.lastTickPosX + (e.posX - e.lastTickPosX) * (double)p_147936_2_);
        double y = (e.lastTickPosY + (e.posY - e.lastTickPosY) * (double)p_147936_2_);
        double z = (e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * (double)p_147936_2_);

        return new double[] {x, y, z};
    }





    public static Session createSession(String username, String password, @NotNull Proxy proxy) throws Exception {
        YggdrasilAuthenticationService service = new YggdrasilAuthenticationService(proxy, "");
        YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication) service
                .createUserAuthentication(Agent.MINECRAFT);

        auth.setUsername(username);
        auth.setPassword(password);

        auth.logIn();
        return new Session(auth.getSelectedProfile().getName(), auth.getSelectedProfile().getId().toString(),
                auth.getAuthenticatedToken(), "mojang");
    }

    /**
     * @return The direction of the player's movement in radians
     */
    public static double getDirection() {
        Minecraft mc = Minecraft.getMinecraft();

        float yaw = mc.thePlayer.rotationYaw;

        if (mc.thePlayer.moveForward < 0.0F) yaw += 180.0F;

        float forward = 1.0F;

        if (mc.thePlayer.moveForward < 0.0F) forward = -0.5F;
        else if (mc.thePlayer.moveForward > 0.0F) forward = 0.5F;

        if (mc.thePlayer.moveStrafing > 0.0F) yaw -= 90.0F * forward;

        if (mc.thePlayer.moveStrafing < 0.0F) yaw += 90.0F * forward;

        return Math.toRadians(yaw);
    }

    /*
     * By DarkStorm
     */
    public static Point calculateMouseLocation() {
        Minecraft minecraft = Minecraft.getMinecraft();
        int scale = minecraft.gameSettings.guiScale;
        if (scale == 0)
            scale = 1000;
        int scaleFactor = 0;
        while (scaleFactor < scale && minecraft.displayWidth / (scaleFactor + 1) >= 320 && minecraft.displayHeight / (scaleFactor + 1) >= 240)
            scaleFactor++;
        return new Point(Mouse.getX() / scaleFactor, minecraft.displayHeight / scaleFactor - Mouse.getY() / scaleFactor - 1);
    }
}