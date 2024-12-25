package morph.avaritia.compat.botania;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.Loader;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.tile.TileTinyPotato;

public class RenderTileInfinitato extends TileEntitySpecialRenderer<TileTinyPotato> {

    public static boolean drawHalo = true;
    private static final ResourceLocation texture = new ResourceLocation("avaritia", "textures/blocks/infinitato.png");
    public static final ResourceLocation halo = new ResourceLocation("avaritia", "textures/items/halo128.png");
    private static final ModelInfinitato model = new ModelInfinitato();

    @Override
    public void render(TileTinyPotato var1, double x, double y, double z, float partialTicks, int destroyStage, float unused) {
        TileInfinitato potato = (TileInfinitato) var1;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder worldrenderer = tessellator.getBuffer();
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.color(1F, 1F, 1F, 1F);
        GlStateManager.translate(x, y, z);

        Minecraft mc = Minecraft.getMinecraft();


        GlStateManager.translate(0.5F, 1.5F, 0.5F);
        GlStateManager.scale(1F, -1F, -1F);
        int meta = potato.getWorld() == null ? 3 : potato.getBlockMetadata();

        if (drawHalo) {
            mc.renderEngine.bindTexture(halo);
            GlStateManager.pushMatrix();

            double xdiff = (potato.getPos().getX() + 0.5) - mc.getRenderManager().viewerPosX;//.renderPosX;
            double ydiff = (potato.getPos().getY() + 0.4) - mc.getRenderManager().viewerPosY;//.renderPosY;
            double zdiff = (potato.getPos().getZ() + 0.5) - mc.getRenderManager().viewerPosZ;//.renderPosZ;

            double len = Math.sqrt(xdiff * xdiff + ydiff * ydiff + zdiff * zdiff);

            xdiff /= len;
            ydiff /= len;
            zdiff /= len;

            GlStateManager.translate(-xdiff, ydiff, zdiff);

            //GL11.glRotatef(-rotZ, 0F, 0F, 1F);
            //GL11.glRotatef(-rotY, 0F, 1F, 0F);
            GlStateManager.scale(1F, -1F, -1F);

            GlStateManager.translate(0F, -1.15F, 0F);
            GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);

            //GL11.glTranslatef(0F, 0F, -1F);
            float f = 1.6F;
            float f1 = 0.016666668F * f;
            GlStateManager.scale(f1, f1, f1);
            GlStateManager.enableLighting();
            //GL11.glTranslatef(0.0F, 0F / f1, 0.0F);
            GlStateManager.depthMask(false);
            //GL11.glDisable(GL11.GL_DEPTH_TEST);
            GlStateManager.enableBlend();
            GlStateManager.enableAlpha();
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            int i = 60;
            worldrenderer.color(0.0F, 0.0F, 0.0F, 1.0F);
            worldrenderer.pos(-i, -i, 0.0D).tex(0, 0).endVertex();
            worldrenderer.pos(-i, i, 0.0D).tex(1, 0).endVertex();
            worldrenderer.pos(i, i, 0.0D).tex(1, 1).endVertex();
            worldrenderer.pos(i, -i, 0.0D).tex(0, 1).endVertex();
            tessellator.draw();
            GlStateManager.depthMask(true);
            //GL11.glEnable(GL11.GL_DEPTH_TEST);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.enableAlpha();
            GlStateManager.color(1F, 1F, 1F, 1F);
            //GL11.glScalef(1F / -f1, 1F / -f1, 1F / f1);

            GlStateManager.popMatrix();
        }


        float rotY = meta * 90F - 180F;
        GlStateManager.rotate(rotY, 0F, 1F, 0F);

        float jump = potato.jumpTicks * 0.5f;
        if (jump > 0)
            jump += partialTicks * 0.5f;

        float up = (float) -Math.abs(Math.sin(jump / 10 * Math.PI)) * 0.2F;
        float rotZ = (float) Math.sin(jump / 10 * Math.PI) * 2;


        GlStateManager.translate(0F, up, 0F);
        GlStateManager.rotate(rotZ, 0F, 0F, 1F);

        mc.renderEngine.bindTexture(texture);
        model.render();

        GlStateManager.pushMatrix();
        String name = potato.name.toLowerCase();
        mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        float scale = 1F / 4F;
        GlStateManager.translate(0F, 1F, 0F);
        GlStateManager.scale(scale, scale, scale);
        if (name.equals("armstrong")) {
            GlStateManager.scale(1.75F, 1.75F, 1.25F);
            GlStateManager.rotate(180F, 0F, 0F, 1F);
            GlStateManager.translate(-0.5F, -0.55F, -0.8F);
            renderIcon(MiscellaneousIcons.INSTANCE.armstrong);
        } else if (name.startsWith("moo") && name.endsWith("oon")) {
            GlStateManager.scale(2.5F, 2.5F, 1.25F);
            GlStateManager.rotate(180F, 0F, 0F, 1F);
            GlStateManager.translate(-0.5F, -0.6F, -0.8F);
            renderIcon(MiscellaneousIcons.INSTANCE.moon);
        } else if (name.equals("egbert")) {
            GlStateManager.scale(1.25F, 1.25F, 1.25F);
            GlStateManager.rotate(180F, 0F, 0F, 1F);
            GlStateManager.translate(-0.5F, -1.4F, -0.8F);
            renderIcon(MiscellaneousIcons.INSTANCE.egbert);
        } else if (name.equals("popetato")) {
            GlStateManager.scale(1.75F, 1.75F, 1.25F);
            GlStateManager.rotate(180F, 0F, 0F, 1F);
            GlStateManager.translate(-0.5F, -0, -0.8F);
            renderIcon(MiscellaneousIcons.INSTANCE.francis);
        } else if (name.startsWith("foxtato") && Loader.isModLoaded("tails")) {
            InfiniteFoxes.renderInfinitatoFluff(partialTicks);
        }

        GlStateManager.popMatrix();


        GlStateManager.rotate(-rotZ, 0F, 0F, 1F);
        GlStateManager.rotate(-rotY, 0F, 1F, 0F);
        GlStateManager.color(1F, 1F, 1F);
        GlStateManager.scale(1F, -1F, -1F);

        RayTraceResult pos = mc.objectMouseOver;
        if (!potato.name.isEmpty() && pos != null && pos.getBlockPos().equals(potato.getPos())) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(0F, -0.4F, 0F);
            GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
            float f = 1.6F;
            float f1 = 0.016666668F * f;
            GlStateManager.scale(-f1, -f1, f1);
            GlStateManager.disableLighting();
            GlStateManager.depthMask(false);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
                    GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.disableTexture2D();

            worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            int i = mc.fontRenderer.getStringWidth(potato.name) / 2;
            worldrenderer.pos(-i - 1, -1.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            worldrenderer.pos(-i - 1, 8.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            worldrenderer.pos(i + 1, 8.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            worldrenderer.pos(i + 1, -1.0D, 0.0D).color(0.0F, 0.0F, 0.0F, 0.25F).endVertex();
            tessellator.draw();

            GlStateManager.enableTexture2D();
            GlStateManager.depthMask(true);
            mc.fontRenderer.drawString(potato.name, -mc.fontRenderer.getStringWidth(potato.name) / 2, 0, 0xFFFFFF);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }

        GlStateManager.popMatrix();
    }

    private void renderIcon(TextureAtlasSprite icon) {
        float f = icon.getMinU();
        float f1 = icon.getMaxU();
        float f2 = icon.getMinV();
        float f3 = icon.getMaxV();
        IconHelper.renderIconIn3D(Tessellator.getInstance(), f1, f2, f, f3, icon.getIconWidth(), icon.getIconHeight(), 1F / 16F);
    }

    private void renderItem(ItemStack stack) {
        Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.HEAD);
    }

    private void renderBlock(Block block) {
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(block.getDefaultState(), 1.0F);
    }
}
