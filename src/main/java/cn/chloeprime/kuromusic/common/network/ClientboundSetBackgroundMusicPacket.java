package cn.chloeprime.kuromusic.common.network;

import cn.chloeprime.kuromusic.client.ClientNetworkHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientboundSetBackgroundMusicPacket implements Packet {
    public final String url;
    public final long priority;
    public final float volume;
    public final float pitch;

    public ClientboundSetBackgroundMusicPacket(String url, long priority, float pVolume, float pPitch) {
        this.url = url;
        this.priority = priority;
        this.volume = pVolume;
        this.pitch = pPitch;
    }

    @Override
    public void encode(RegistryFriendlyByteBuf buf) {
        buf.writeUtf(url);
        buf.writeVarLong(priority);
        buf.writeFloat(volume);
        buf.writeFloat(pitch);
    }

    public ClientboundSetBackgroundMusicPacket(FriendlyByteBuf buf) {
        this.url = buf.readUtf();
        this.priority = buf.readVarLong();
        this.volume = buf.readFloat();
        this.pitch = buf.readFloat();
    }

    @Override
    public void handle(IPayloadContext context) {
        ClientNetworkHandler.handleSetBgmPacket(this);
    }
}
