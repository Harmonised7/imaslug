package harmonised.imaslug;

import harmonised.imaslug.network.NetworkHandler;
import harmonised.imaslug.util.ClientHandler;
import harmonised.imaslug.util.Reference;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Mod(Reference.MOD_ID)
public class ImASlugMod
{
    private static String PROTOCOL_VERSION = "1";
    public static Set<UUID> isCrawling = new HashSet<>();
    public static SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(Reference.MOD_ID, "main_channel"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();


    public ImASlugMod()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::modsLoading);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientHandler::init);
    }

    private void modsLoading(FMLCommonSetupEvent event)
    {
        NetworkHandler.registerPackets();
        MinecraftForge.EVENT_BUS.register(ClientHandler.class);
    }
}
