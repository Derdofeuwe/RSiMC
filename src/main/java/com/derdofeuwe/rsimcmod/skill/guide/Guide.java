package com.derdofeuwe.rsimcmod.skill.guide;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.opengl.GL11;

public abstract class Guide
implements Comparable<Guide> {
    protected String mVerb = "use";
    public static final DecimalFormat sDf = new DecimalFormat("#.##");

    public abstract String getName();

    public abstract List<String> getDescription();

    public final String getVerb() {
        return this.mVerb;
    }

    public final void setVerb(String verb) {
        this.mVerb = verb;
    }

    @SideOnly(value=Side.CLIENT)
    public int getExtraTooltipHeight() {
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public void drawTooltipExtras(GuiSkillGuide gui, int x, int y) {
    }

    @SideOnly(value=Side.CLIENT)
    public void renderGuide(int left, int top, GuiSkillGuide skillGuide) {
        FontRenderer fontRenderer = skillGuide.getFontRenderer();
        int nextLeft = left + 2;
        if (this.getRenderable() != null) {
            this.getRenderable().render(skillGuide, left, top);
            nextLeft += 16;
        }
        if (this instanceof LevelledGuide && ((LevelledGuide)((Object)this)).getLevel() != -1) {
            int level = ((LevelledGuide)((Object)this)).getLevel();
            level = Math.max(0, level);
            boolean playerHasLevel = skillGuide.mSkill.getLevelEffective() >= level;
            String levelString = "" + level + "";
            int levelWidth = fontRenderer.func_78256_a(levelString);
            fontRenderer.func_78276_b(levelString, left + 38 - levelWidth, top + 5, playerHasLevel ? Colors.COLOR_GREEN : Colors.COLOR_RED);
            nextLeft += 20;
        }
        RenderUtils.drawScaledFitText(this.getStrippedName(skillGuide), 1.0f, left + 170 - nextLeft - 8, nextLeft += 4, top + 5, Colors.COLOR_WHITE);
    }

    public IChatComponent generateNotification() {
        return new ChatComponentText("New skill tasks are available! Check the skill guide for more info.");
    }

    @SideOnly(value=Side.CLIENT)
    public abstract GuideRenderable getRenderable();

    @SideOnly(value=Side.CLIENT)
    public String getStrippedName(GuiSkillGuide skillGuide) {
        String name = this.getName();
        ArrayList<String> nameParts = new ArrayList<String>(Arrays.asList(name.split(" ")));
        if (nameParts.get(0).equals(skillGuide.getCurrentCategory())) {
            nameParts.remove(0);
            name = StringUtils.join(nameParts, (String)" ");
        }
        return name;
    }

    @SideOnly(value=Side.CLIENT)
    public void drawTooltip(GuiSkillGuide gui, int mouseX, int mouseY) {
        GL11.glDisable((int)32826);
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2929);
        RenderHelper.func_74518_a();
        int maxWidth = 0;
        for (String string : this.getDescription()) {
            int stringWidth = gui.getFontRenderer().func_78256_a(string);
            maxWidth = Math.max(maxWidth, stringWidth);
        }
        int x = mouseX + 12;
        int y = mouseY - 12;
        int height = 8 + this.getExtraTooltipHeight();
        if (this.getDescription().size() > 1) {
            height += (this.getDescription().size() - 1) * 10;
        }
        int minY = y - 4;
        int maxY = y + height + 4;
        if (maxY > gui.field_146295_m) {
            y = gui.field_146295_m - height - 4;
        } else if (minY < 0) {
            y = 4;
        }
        gui.setZLevel(300.0f);
        int color1 = gui.mSkillBase.getPrimaryColor();
        gui.drawGradientRectangle(x - 3, y - 4, x + maxWidth + 3, y - 3, color1, color1);
        gui.drawGradientRectangle(x - 3, y + height + 3, x + maxWidth + 3, y + height + 4, color1, color1);
        gui.drawGradientRectangle(x - 3, y - 3, x + maxWidth + 3, y + height + 3, color1, color1);
        gui.drawGradientRectangle(x - 4, y - 3, x - 3, y + height + 3, color1, color1);
        gui.drawGradientRectangle(x + maxWidth + 3, y - 3, x + maxWidth + 4, y + height + 3, color1, color1);
        int color2 = gui.mSkillBase.getSecondaryColor();
        int color3 = (color2 & 16711422) >> 1 | color2 & -16777216;
        gui.drawGradientRectangle(x - 3, y - 3 + 1, x - 3 + 1, y + height + 3 - 1, color2, color3);
        gui.drawGradientRectangle(x + maxWidth + 2, y - 3 + 1, x + maxWidth + 3, y + height + 3 - 1, color2, color3);
        gui.drawGradientRectangle(x - 3, y - 3, x + maxWidth + 3, y - 3 + 1, color2, color2);
        gui.drawGradientRectangle(x - 3, y + height + 2, x + maxWidth + 3, y + height + 3, color3, color3);
        for (int i = 0; i < this.getDescription().size(); ++i) {
            String string = this.getDescription().get(i);
            gui.getFontRenderer().func_78276_b(string, x, y, gui.mSkillBase.getSecondaryColor());
            y += 10;
        }
        this.drawTooltipExtras(gui, x, y);
        gui.setZLevel(0.0f);
    }

    @Override
    public int compareTo(Guide o) {
        int f = 0;
        if (this instanceof LevelledGuide && o instanceof LevelledGuide) {
            int level = ((LevelledGuide)((Object)this)).getLevel();
            int oLevel = ((LevelledGuide)((Object)o)).getLevel();
            f = Integer.compare(level, oLevel);
        }
        return f != 0 ? f : this.getName().compareTo(o.getName());
    }
}