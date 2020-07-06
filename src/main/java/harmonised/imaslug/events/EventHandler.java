package harmonised.imaslug.events;

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

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler
{
    private static boolean wasCrawling = false, wasVeining = false, wasOpenMenu = false, wasOpenSettings = false, wasOpenSkills = false, wasOpenGlossary = false, tooltipKeyWasPressed = false;
    public static Set<UUID> isCrawling = new HashSet<>();

    public static void subscribeClientEvents( IEventBus eventBus )
    {
        eventBus.register( EventHandler.class );
    }

    @SubscribeEvent
    public static void keyPressEvent( net.minecraftforge.client.event.InputEvent.KeyInputEvent event )
    {
        if( Minecraft.getInstance().player != null )
        {
            if( wasCrawling != ClientHandler.CRAWL_KEY.isKeyDown() )
            {
                wasCrawling = ClientHandler.CRAWL_KEY.isKeyDown();
                NetworkHandler.sendToServer( new MessageKeypress( ClientHandler.CRAWL_KEY.isKeyDown(), 0 ) );
            }

            if( ClientHandler.CRAWL_KEY.isKeyDown() )
                isCrawling.add( Minecraft.getInstance().player.getUniqueID() );
            else
                isCrawling.remove( Minecraft.getInstance().player.getUniqueID() );
        }
    }

    @SubscribeEvent
    public static void playerTickEvent( TickEvent.PlayerTickEvent event )
    {
        if( isCrawling.contains( event.player.getUniqueID() ) )
            event.player.setPose( Pose.SWIMMING );
    }
}