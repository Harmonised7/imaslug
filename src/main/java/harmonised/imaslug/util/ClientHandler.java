package harmonised.imaslug.util;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class ClientHandler
{
    public static final KeyMapping CRAWL_KEY = new KeyMapping("key.imaslug.crawl", GLFW.GLFW_KEY_C, "category.imaslug");
    public static final KeyMapping CRAWL_TOGGLE_KEY = new KeyMapping("key.imaslug.crawlToggle", GLFW.GLFW_KEY_V, "category.imaslug");

    public static void init()
    {
        ClientRegistry.registerKeyBinding(CRAWL_KEY);
        ClientRegistry.registerKeyBinding(CRAWL_TOGGLE_KEY);
    }
}
