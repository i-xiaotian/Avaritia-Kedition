/**
 * This class was created by <williewillus>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package morph.avaritia.compat.botania;

import morph.avaritia.Avaritia;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MiscellaneousIcons {

	public static final MiscellaneousIcons INSTANCE = new MiscellaneousIcons();

	public TextureAtlasSprite armstrong;
	public TextureAtlasSprite egbert;
	public TextureAtlasSprite francis;
	public TextureAtlasSprite moon;
	// end dank_memes
	@SubscribeEvent
	public void onTextureStitch(TextureStitchEvent.Pre evt) {
		armstrong = evt.getMap().registerSprite(new ResourceLocation(Avaritia.MOD_ID + ":" + "items" + "/" + "costume_armstrong"));
		egbert = evt.getMap().registerSprite(new ResourceLocation(Avaritia.MOD_ID + ":" + "items" + "/" + "costume_egbert"));
		francis = evt.getMap().registerSprite(new ResourceLocation(Avaritia.MOD_ID + ":" + "items" + "/" + "costume_francis"));
		moon = evt.getMap().registerSprite(new ResourceLocation(Avaritia.MOD_ID + ":" + "items" + "/" + "costume_moon"));
	}

	private MiscellaneousIcons() {}
}