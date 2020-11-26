package rpsystem.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import rpsystem.Main;

public class TitleCommand {
    Main main = null;

    public TitleCommand(Main plugin) {
        main = plugin;
    }

    public void titleBook(CommandSender sender, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            // check permission
            if (player.hasPermission("rp.title") || player.hasPermission("rp.default")) {

                // check if they're holding a book
                if (player.getInventory().getItemInMainHand().getType() == Material.WRITABLE_BOOK) {

                    // args check
                    if (args.length > 0) {

                        String newTitle = createStringFromArgs(args);

                        ItemStack book = player.getInventory().getItemInMainHand();

                        ItemMeta meta = book.getItemMeta();

                        meta.setDisplayName(newTitle);

                        book.setItemMeta(meta);

                        player.getInventory().setItemInMainHand(book);

                        player.sendMessage(ChatColor.GREEN + "Книга переименована!");
                    }
                    else {
                        player.sendMessage(ChatColor.RED + "Использование: /title (новый заголовок)");
                    }

                }
                else {
                    player.sendMessage(ChatColor.RED + "Ты должен держать книгу с чернилами!");
                }

            }
            else {
                player.sendMessage(ChatColor.RED + "Sorry! In order to use this command, you need the following permission: 'rp.title'");
            }
        }

    }

    public String createStringFromArgs(String[] args) {
        String toReturn = args[0];
        for (int i = 1; i < args.length; i++) {
            toReturn = toReturn + " " + args[i];
        }
        return toReturn;
    }
}