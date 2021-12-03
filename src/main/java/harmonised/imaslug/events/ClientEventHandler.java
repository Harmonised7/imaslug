package harmonised.imaslug.events;

import harmonised.imaslug.ImASlugMod;
import harmonised.imaslug.network.MessageKeypress;
import harmonised.imaslug.network.NetworkHandler;
import harmonised.imaslug.util.ClientHandler;
import harmonised.imaslug.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEventHandler
{
    private static boolean wasCrawling = false, wasCrawlingToggle = false, crawlToggled = false;

    @SubscribeEvent
    public static void keyPressEvent(net.minecraftforge.client.event.InputEvent.KeyInputEvent event)
    {
        if(Minecraft.getInstance().player != null)
        {
            if(wasCrawling != ClientHandler.CRAWL_KEY.isDown())
            {
                wasCrawling = ClientHandler.CRAWL_KEY.isDown();
                if(!wasCrawling)
                    crawlToggled = false;
                NetworkHandler.sendToServer(new MessageKeypress(ClientHandler.CRAWL_KEY.isDown()));
            }

            if(wasCrawlingToggle != ClientHandler.CRAWL_TOGGLE_KEY.isDown())
            {
                wasCrawlingToggle = ClientHandler.CRAWL_TOGGLE_KEY.isDown();
                if(wasCrawlingToggle)
                {
                    crawlToggled = !crawlToggled;
                    if(ClientHandler.CRAWL_TOGGLE_KEY.isDown())
                        NetworkHandler.sendToServer(new MessageKeypress(crawlToggled));
                }
            }

            if(ClientHandler.CRAWL_KEY.isDown() || crawlToggled)
                ImASlugMod.isCrawling.add(Minecraft.getInstance().player.getUUID());
            else
                ImASlugMod.isCrawling.remove(Minecraft.getInstance().player.getUUID());
        }
    }

    @SubscribeEvent
    public static void playerTickEvent(TickEvent.PlayerTickEvent event)
    {
        if(ImASlugMod.isCrawling.contains(event.player.getUUID()))
            event.player.setPose(Pose.SWIMMING);
    }
}