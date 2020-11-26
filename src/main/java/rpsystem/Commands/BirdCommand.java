package rpsystem.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import rpsystem.Main;

import static org.bukkit.Bukkit.getServer;
import static rpsystem.UtilityFunctions.createStringFromFirstArgOnwards;

public class BirdCommand {

    Main main = null;

    public BirdCommand(Main plugin) {
        main = plugin;
    }

    public void sendBird(CommandSender sender, String[] args) {
        // player check
        if (!(sender instanceof Player)) {
            return;
        }

        Player player = (Player) sender;

        if (player.hasPermission("rp.bird") || player.hasPermission("rp.default")) {
            if (main.playersWithBusyBirds.contains(player.getUniqueId())) {
                player.sendMessage(ChatColor.RED + "Почтовый голубь в пути!");
                return;
            }

            // zero args check
            if (args.length < 2) {
                player.sendMessage(ChatColor.RED + "Использовние: /bird (имя игрока) (сообщение)");
                return;
            }

            Player targetPlayer = getServer().getPlayer(args[0]);

            if (targetPlayer == null) {
                player.sendMessage(ChatColor.RED + "Игрок не в сети!");
                return;
            }

            String message = createStringFromFirstArgOnwards(args, 1);

            if (!(player.getLocation().getWorld().getName().equalsIgnoreCase(targetPlayer.getLocation().getWorld().getName()))) {
                player.sendMessage(ChatColor.RED + "Ты не можешь отправить голубя так как этот игрок в другом мире.");
                return;
            }

            double distance = player.getLocation().distance(targetPlayer.getLocation());
            int blocksPerSecond = 20;
            int seconds = (int)distance/blocksPerSecond;

            getServer().getScheduler().runTaskLater(main, new Runnable() {
                @Override
                public void run() {
                    targetPlayer.sendMessage(ChatColor.GREEN + "К тебе прилетел почтовый голубь с письмом от " + player.getName() + ". Он пишет:");
                    targetPlayer.sendMessage(ChatColor.GREEN + "" + ChatColor.ITALIC + "'" + message + "'");
                    player.sendMessage(ChatColor.GREEN + "Твой голубь долетел " + targetPlayer.getName() + "!");
                    main.playersWithBusyBirds.remove(player.getUniqueId());

                }
            }, seconds * 20);

            player.sendMessage(ChatColor.GREEN + "Твой голубь улетел с твоим письмом.");
            main.playersWithBusyBirds.add(player.getName());
        }
        else {
            player.sendMessage(ChatColor.RED + "Ты не можешь использолвать данную команду!");
        }

    }
}
