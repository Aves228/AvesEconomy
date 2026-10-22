package ru.aves.economy;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.Player;

import java.io.File;

public class AvesEconomy extends PluginBase {

    private Config balances;

    @Override
    public void onEnable() {
        getDataFolder().mkdirs();

        balances = new Config(
            new File(getDataFolder(), "balances.yml"),
            Config.YAML
        );

        getLogger().info("AvesEconomy enabled!");
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Эта команда доступна только игроку.");
            return true;
        }

        if (command.getName().equalsIgnoreCase("balance")) {
            String key = player.getUniqueId().toString();
            double amount = balances.getDouble(key, 0.0);
            player.sendMessage("§aТвой баланс: §f" + amount + " AVC");
            return true;
        }

        if (command.getName().equalsIgnoreCase("avc")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("card")) {
                String key = player.getUniqueId().toString();
                double amount = balances.getDouble(key, 0.0);

                player.sendMessage("§6===== AVC BANK =====");
                player.sendMessage("§fТвой баланс: §a" + amount + " AVC");
                player.sendMessage("§fВладелец: §e" + player.getName());
                player.sendMessage("§7Интерфейс перевода будет добавлен позже.");
                return true;
            }

            player.sendMessage("§eИспользование: /avc card");
            return true;
        }

        return false;
    }
}
