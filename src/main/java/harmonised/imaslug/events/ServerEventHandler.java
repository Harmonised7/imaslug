package harmonised.imaslug.events;

import harmonised.imaslug.ImASlugMod;
import harmonised.imaslug.network.MessageKeypress;
import harmonised.imaslug.network.NetworkHandler;
import harmonised.imaslug.util.ClientHandler;
import harmonised.imaslug.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber(value = Dist.DEDICATED_SERVER, modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEventHandler
{
    @SubscribeEvent
    public static void playerTickEvent( TickEvent.PlayerTickEvent event )
    {
        if( ImASlugMod.isCrawling.contains( event.player.getUniqueID() ) )
            event.player.setPose( Pose.SWIMMING );
        System.out.println( event.player.getPose() );
    }
}