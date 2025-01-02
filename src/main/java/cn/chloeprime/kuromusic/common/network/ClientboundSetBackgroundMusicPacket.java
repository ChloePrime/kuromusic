package cn.chloeprime.kuromusic.common.network;

import cn.chloeprime.kuromusic.client.ClientNetworkHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class ClientboundSetBackgroundMusicPacket implements Packet {
    public final String url;
    public final float volume;
    public final float pitch;

    public ClientboundSetBackgroundMusicPacket(String url, float pVolume, float pPitch) {
        this.url = url;
        this.volume = pVolume;
        this.pitch = pPitch;
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(url);
        buf.writeFloat(volume);
        buf.writeFloat(pitch);
    }

    public ClientboundSetBackgroundMusicPacket(FriendlyByteBuf buf) {
        this.url = buf.readUtf();
        this.volume = buf.readFloat();
        this.pitch = buf.readFloat();
    }

    @Override
    public void handle(NetworkEvent.Context context) {
        ClientNetworkHandler.handleSetBgmPacket(this);
    }
}