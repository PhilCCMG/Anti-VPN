package me.egg82.antivpn.commands;

import cloud.commandframework.paper.PaperCommandManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import me.egg82.antivpn.commands.internal.ReloadCommand;
import me.egg82.antivpn.config.ConfigUtil;
import me.egg82.antivpn.lang.BukkitLocaleCommandUtil;
import me.egg82.antivpn.lang.BukkitLocalizedCommandSender;
import me.egg82.antivpn.lang.LocalizedArgumentDescription;
import me.egg82.antivpn.lang.MessageKey;
import me.egg82.antivpn.logging.GELFLogger;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

public class AntiVPNCommands extends CommandHolder {
    public AntiVPNCommands(@NotNull Plugin plugin) {
        super();

        PaperCommandManager<BukkitLocalizedCommandSender> commandManager = BukkitLocaleCommandUtil.getCommandManager();

        ConfigurationNode config = ConfigUtil.getConfig();

        String[] baseAliases = getAliases(config, "base");

        commands.add(
            commandManager.commandBuilder("antivpn", baseAliases)
                .literal("reload", LocalizedArgumentDescription.of(MessageKey.COMMAND_DESC__RELOAD), getAliases(config, "reload")) // TODO: Localization
                .permission(ConfigUtil.getCachedConfig().getAdminPermissionNode())
                .handler(new ReloadCommand(commandManager, plugin.getDataFolder(), plugin))
                .build()
        );

        /*commands.add(
            commandManager.commandBuilder("antivpn", baseAliases)
                .literal("import", LocalizedArgumentDescription.of(MessageKey.COMMAND_DESC__IMPORT), getAliases(config, "import")) // TODO: Localization
                .permission(ConfigUtil.getCachedConfig().getAdminPermissionNode())
                .argument(StringArgument.<BukkitLocalizedCommandSender>newBuilder("master").withSuggestionsProvider(storageSuggestions).build(), ArgumentDescription.of("<master>")) // TODO: Localization
                .argument(StringArgument.<BukkitLocalizedCommandSender>newBuilder("slave").withSuggestionsProvider(storageSuggestions).build(), ArgumentDescription.of("<slave>")) // TODO: Localization
                .argument(IntegerArgument.<BukkitLocalizedCommandSender>newBuilder("batchSize").withMin(1).asOptionalWithDefault("50").build(), ArgumentDescription.of("[batchSize]")) // TODO: Localization
                .handler(new ReloadCommand(commandManager, plugin.getDataFolder(), plugin))
                .build()
        );

        commands.add(
            commandManager.commandBuilder("antivpn", baseAliases)
                .literal("kick", LocalizedArgumentDescription.of(MessageKey.COMMAND_DESC__KICK), getAliases(config, "kick")) // TODO: Localization
                .permission(ConfigUtil.getCachedConfig().getAdminPermissionNode())
                .argument(SinglePlayerSelectorArgument.of("player"), ArgumentDescription.of("<player>")) // TODO: Localization
                .argument(StringArgument.<BukkitLocalizedCommandSender>newBuilder("type").withSuggestionsProvider(kickTypeSuggestions).withParser(kickTypeParser).build(), ArgumentDescription.of("[type]]")) // TODO: Localization
                .handler(new KickCommand(commandManager))
                .build()
        );

        commands.add(
            commandManager.commandBuilder("antivpn", baseAliases)
                .literal("test", LocalizedArgumentDescription.of(MessageKey.COMMAND_DESC__TEST), getAliases(config, "test")) // TODO: Localization
                .permission(ConfigUtil.getCachedConfig().getAdminPermissionNode())
                .argument(StringArgument.<BukkitLocalizedCommandSender>newBuilder("ip").withParser(ipParser).build(), ArgumentDescription.of("<ip>")) // TODO: Localization
                .handler(new TestCommand(commandManager))
                .build()
        );

        commands.add(
            commandManager.commandBuilder("antivpn", baseAliases)
                .literal("score", LocalizedArgumentDescription.of(MessageKey.COMMAND_DESC__SCORE), getAliases(config, "score")) // TODO: Localization
                .permission(ConfigUtil.getCachedConfig().getAdminPermissionNode())
                .argument(StringArgument.<BukkitLocalizedCommandSender>newBuilder("source").withSuggestionsProvider(sourceSuggestions).withParser(surceParser).build(), ArgumentDescription.of("<source>")) // TODO: Localization
                .handler(new ScoreCommand(commandManager))
                .build()
        );

        commands.add(
            commandManager.commandBuilder("antivpn", baseAliases)
                .literal("check", LocalizedArgumentDescription.of(MessageKey.COMMAND_DESC__CHECK), getAliases(config, "check")) // TODO: Localization
                .permission(ConfigUtil.getCachedConfig().getAdminPermissionNode())
                .argument(StringArgument.<BukkitLocalizedCommandSender>newBuilder("check").withParser(checkParser).build(), ArgumentDescription.of("<ip|player>")) // TODO: Localization
                .handler(new CheckCommand(commandManager))
                .build()
        );

        MinecraftHelp<BukkitLocalizedCommandSender> help = new MinecraftHelp<>(
            "/antivpn help",
            BukkitLocalizedCommandSender::getMappedAudience,
            commandManager
        );

        commandManager.command(
            commandManager.commandBuilder("antivpn", baseAliases)
                .literal("help", LocalizedArgumentDescription.of(MessageKey.COMMAND__DESC__HELP), getAliases(config, "help")) // TODO: Localization
                .argument(StringArgument.optional("query", StringArgument.StringMode.GREEDY))
                .handler(context -> help.queryCommands(context.getOrDefault("query", ""), context.getSender()))
        );*/

        registerAll();
    }

    private @NotNull String @NotNull [] getAliases(@NotNull ConfigurationNode config, @NotNull String command) {
        Set<String> retVal;
        try {
            retVal = new HashSet<>(!config.node("aliases", command).empty() ? config.node("aliases", command).getList(String.class) : new ArrayList<>());
        } catch (SerializationException ex) {
            GELFLogger.exception(logger, ex);
            retVal = new HashSet<>();
        }
        retVal.removeIf(action -> action == null || action.isEmpty());

        return retVal.toArray(new String[0]);
    }
}
