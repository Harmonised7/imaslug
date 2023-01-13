package harmonised.imaslug.util;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

public class ClientHandler
{
    public static final KeyMapping CRAWL_KEY = new KeyMapping("key.imaslug.crawl", GLFW.GLFW_KEY_C, "category.imaslug");
    public static final KeyMapping CRAWL_TOGGLE_KEY = new KeyMapping("key.imaslug.crawlToggle", GLFW.GLFW_KEY_V, "category.imaslug");

    public static void init(RegisterKeyMappingsEvent event)
    {
        event.register(CRAWL_KEY);
        event.register(CRAWL_TOGGLE_KEY);
    }
}
