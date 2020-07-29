package harmonised.imaslug.network;

import harmonised.imaslug.ImASlugMod;
import harmonised.imaslug.events.ClientEventHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class MessageKeypress
{
//    int key;
    boolean keyState;

    public MessageKeypress( boolean keyState )
    {
        this.keyState = keyState;
//        this.key = key;
    }

    MessageKeypress()
    {
    }

    public static MessageKeypress decode(PacketBuffer buf )
    {
        MessageKeypress packet = new MessageKeypress();
        packet.keyState = buf.readBoolean();
//        packet.key = buf.readInt();

        return packet;
    }

    public static void encode(MessageKeypress packet, PacketBuffer buf )
    {
        buf.writeBoolean( packet.keyState );
//        buf.writeInt( packet.key );
    }

    public static void handlePacket(MessageKeypress packet, Supplier<NetworkEvent.Context> ctx )
    {
        ctx.get().enqueueWork(() ->
        {
            UUID playerUUID = ctx.get().getSender().getUniqueID();
            if( packet.keyState )
                ImASlugMod.isCrawling.add( playerUUID );
            else
                ImASlugMod.isCrawling.remove( playerUUID );
        });
        ctx.get().setPacketHandled(true);
    }
}
