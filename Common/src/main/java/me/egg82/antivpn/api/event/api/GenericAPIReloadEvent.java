package me.egg82.antivpn.api.event.api;

import me.egg82.antivpn.api.VPNAPI;
import me.egg82.antivpn.api.event.AbstractEvent;
import me.egg82.antivpn.api.model.ip.IPManager;
import me.egg82.antivpn.api.model.player.PlayerManager;
import me.egg82.antivpn.api.model.source.SourceManager;
import org.jetbrains.annotations.NotNull;

public class GenericAPIReloadEvent extends AbstractEvent implements APIReloadEvent {
    private final IPManager newIpManager;
    private final PlayerManager newPlayerManager;
    private final SourceManager newSourceManager;

    public GenericAPIReloadEvent(@NotNull VPNAPI api, @NotNull IPManager newIpManager, @NotNull PlayerManager newPlayerManager, @NotNull SourceManager newSourceManager) {
        super(api);
        this.newIpManager = newIpManager;
        this.newPlayerManager = newPlayerManager;
        this.newSourceManager = newSourceManager;
    }

    public @NotNull IPManager getNewIPManager() { return newIpManager; }

    public @NotNull PlayerManager getNewPlayerManager() { return newPlayerManager; }

    public @NotNull SourceManager getNewSourceManager() { return newSourceManager; }
}
