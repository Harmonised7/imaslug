package harmonised.imaslug.events;

import harmonised.imaslug.network.MessageKeypress;
import harmonised.imaslug.network.NetworkHandler;
import harmonised.imaslug.util.ClientHandler;
import harmonised.imaslug.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Pose;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler
{
    private static boolean wasCrawling = false, wasCrawlingToggle = false, crawlToggled = false;
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
                if( !wasCrawling )
                    crawlToggled = false;
                NetworkHandler.sendToServer( new MessageKeypress( ClientHandler.CRAWL_KEY.isKeyDown() ) );
            }

            if( wasCrawlingToggle != ClientHandler.CRAWL_TOGGLE_KEY.isKeyDown() )
            {
                wasCrawlingToggle = ClientHandler.CRAWL_TOGGLE_KEY.isKeyDown();
                if( wasCrawlingToggle )
                {
                    crawlToggled = !crawlToggled;
                    if( ClientHandler.CRAWL_TOGGLE_KEY.isKeyDown() )
                        NetworkHandler.sendToServer( new MessageKeypress( crawlToggled ) );
                }
            }

            if( ClientHandler.CRAWL_KEY.isKeyDown() || crawlToggled )
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