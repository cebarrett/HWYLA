package mcp.mobius.waila;

import java.util.logging.Level;

import codechicken.nei.api.API;
import codechicken.nei.api.ItemInfo;
import codechicken.nei.forge.GuiContainerManager;
import cpw.mods.fml.client.registry.KeyBindingRegistry;

import mcp.mobius.waila.addons.ConfigHandler;
import mcp.mobius.waila.addons.buildcraft.HUDHandlerBCTanks;
import mcp.mobius.waila.addons.ic2.HUDHandlerIC2IEnergySource;
import mcp.mobius.waila.addons.ic2.HUDHandlerIC2IEnergySink;
import mcp.mobius.waila.addons.ic2.HUDHandlerIC2IEnergyStorage;
import mcp.mobius.waila.addons.vanillamc.HUDHandlerVanilla;
import mcp.mobius.waila.gui.ConfigKeyHandler;
import mcp.mobius.waila.handlers.HUDHandlerExternal;
import mcp.mobius.waila.handlers.HUDHandlerWaila;
import mcp.mobius.waila.handlers.TooltipHandlerWaila;
import mcp.mobius.waila.server.ProxyServer;

public class ProxyClient extends ProxyServer {

	public ProxyClient() {}
	
	
	@Override
	public void registerHandlers(){
		GuiContainerManager.addTooltipHandler(new TooltipHandlerWaila());
		//GuiContainerManager.addInputHandler(new EnchantementHandler());
		API.registerHighlightHandler(new HUDHandlerExternal(), ItemInfo.Layout.HEADER);
		API.registerHighlightHandler(new HUDHandlerExternal(), ItemInfo.Layout.BODY);
		API.registerHighlightHandler(new HUDHandlerWaila(),    ItemInfo.Layout.FOOTER);
		API.registerHighlightHandler(new HUDHandlerWaila(),    ItemInfo.Layout.HEADER);		
		
		//API.registerHighlightIdentifier(2000, new HUDHandlerExternal());
		KeyBindingRegistry.registerKeyBinding(new ConfigKeyHandler());
		//API.addKeyBind("showenchant", "Display enchantements", Keyboard.KEY_RSHIFT);
		this.registerMods();		
	}	

	public void registerMods(){
		
		HUDHandlerVanilla.register();
		
		/* BETTER BARRELS */
		/*
		try {
			Class ModBetterBarrels = Class.forName("mcp.mobius.betterbarrels.mod_BetterBarrels");
			mod_Waila.log.log(Level.INFO, "BetterBarrel mod found.");
			HUDHandlerBetterBarrels.register();
			ConfigHandler.instance().addConfig("BetterBarrels", "betterbarrels.content", "Barrel content");
		} catch (ClassNotFoundException e){
			mod_Waila.log.log(Level.INFO, "BetterBarrel mod not found. Skipping.");			
		}
		*/
		
		/* BUILDCRAFT */
		try {
			Class ModBuildcraftFactory = Class.forName("buildcraft.BuildCraftFactory");
			mod_Waila.log.log(Level.INFO, "Buildcraft|Factory mod found.");
			HUDHandlerBCTanks.register();
			ConfigHandler.instance().addConfig("Buildcraft", "bc.tankamount", "Liquid amount");
			ConfigHandler.instance().addConfig("Buildcraft", "bc.tanktype",   "Liquid type");
		} catch (ClassNotFoundException e){
			mod_Waila.log.log(Level.INFO, "Buildcraft|Factory mod not found. Skipping.");			
		}		
		
		/* INDUSTRIALCRAFT2 */
		try {
			Class ModIndustrialCraft = Class.forName("ic2.core.IC2");
			mod_Waila.log.log(Level.INFO, "Industrialcraft2 mod found.");
			HUDHandlerIC2IEnergySink.register();
			HUDHandlerIC2IEnergySource.register();
			HUDHandlerIC2IEnergyStorage.register();
			//HUDHandlerIC2Machine.register();
			//HUDHandlerIC2Generator.register();
			
		} catch (ClassNotFoundException e){
			mod_Waila.log.log(Level.INFO, "Industrialcraft2 mod not found. Skipping.");			
		}		
		
	}	
	
}
