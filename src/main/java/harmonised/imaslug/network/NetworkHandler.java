package harmonised.imaslug.network;

import harmonised.imaslug.ImASlugMod;

public class NetworkHandler
{
    public static void registerPackets()
    {
        ImASlugMod.HANDLER.registerMessage( 0, MessageKeypress.class, MessageKeypress::encode, MessageKeypress::decode, MessageKeypress::handlePacket );
    }

    public static void sendToServer( MessageKeypress packet )
    {
        ImASlugMod.HANDLER.sendToServer( packet );
    }
}