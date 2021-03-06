package me.egg82.antivpn.api.model.ip;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.UUID;
import me.egg82.antivpn.api.model.source.SourceManager;
import me.egg82.antivpn.config.CachedConfig;
import me.egg82.antivpn.config.ConfigUtil;
import me.egg82.antivpn.lang.BukkitLocaleCommandUtil;
import me.egg82.antivpn.utils.BukkitCommandUtil;
import me.egg82.antivpn.utils.BukkitTailorUtil;
import me.egg82.antivpn.utils.TimeUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BukkitIPManager extends AbstractIPManager {
    private final Plugin plugin;

    public BukkitIPManager(@NotNull Plugin plugin, @NotNull SourceManager sourceManager, @NotNull TimeUtil.Time cacheTime) {
        super(sourceManager, cacheTime, BukkitLocaleCommandUtil.getConsole().getLocalizationManager());
        this.plugin = plugin;
    }

    public boolean kickForVpn(@NotNull String playerName, @NotNull UUID playerUuid, @NotNull String ip) {
        Player p = Bukkit.getPlayer(playerUuid);
        if (p == null) {
            return false;
        }

        CachedConfig cachedConfig = ConfigUtil.getCachedConfig();
        BukkitCommandUtil.dispatchCommands(BukkitTailorUtil.tailorCommands(cachedConfig.getVPNActionCommands(), playerName, playerUuid, ip), Bukkit.getConsoleSender(), plugin);
        if (!cachedConfig.getVPNKickMessage().isEmpty()) {
            p.kickPlayer(BukkitTailorUtil.tailorKickMessage(cachedConfig.getVPNKickMessage(), playerName, playerUuid, ip));
        }
        return true;
    }

    public @Nullable String getVpnKickMessage(@NotNull String playerName, @NotNull UUID playerUuid, @NotNull String ip) {
        CachedConfig cachedConfig = ConfigUtil.getCachedConfig();

        if (!cachedConfig.getVPNKickMessage().isEmpty()) {
            return BukkitTailorUtil.tailorKickMessage(cachedConfig.getVPNKickMessage(), playerName, playerUuid, ip);
        }
        return null;
    }

    public @NotNull List<@NotNull String> getVpnCommands(@NotNull String playerName, @NotNull UUID playerUuid, @NotNull String ip) {
        CachedConfig cachedConfig = ConfigUtil.getCachedConfig();

        if (!cachedConfig.getVPNActionCommands().isEmpty()) {
            return ImmutableList.copyOf(BukkitTailorUtil.tailorCommands(cachedConfig.getVPNActionCommands(), playerName, playerUuid, ip));
        }
        return ImmutableList.of();
    }
}
