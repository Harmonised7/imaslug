package harmonised.imaslug.util;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class ClientHandler
{
    public static final KeyBinding CRAWL_KEY = new KeyBinding( "key.imaslug.crawl", GLFW.GLFW_KEY_C, "category.imaslug" );
    public static final KeyBinding CRAWL_TOGGLE_KEY = new KeyBinding( "key.imaslug.crawlToggle", GLFW.GLFW_KEY_V, "category.imaslug" );

    public static void init()
    {
        ClientRegistry.registerKeyBinding( CRAWL_KEY );
        ClientRegistry.registerKeyBinding( CRAWL_TOGGLE_KEY );
    }
}
