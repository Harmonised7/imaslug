package harmonised.imaslug.events;

import harmonised.imaslug.ImASlugMod;
import harmonised.imaslug.util.Reference;
import net.minecraft.world.entity.Pose;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.DEDICATED_SERVER, modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEventHandler
{
    @SubscribeEvent
    public static void playerTickEvent(TickEvent.PlayerTickEvent event)
    {
        if(ImASlugMod.isCrawling.contains(event.player.getUUID()))
            event.player.setPose(Pose.SWIMMING);
    }
}