package rpsystem.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import rpsystem.CharacterCard;
import rpsystem.Main;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getServer;
import static rpsystem.UtilityFunctions.createStringFromFirstArgOnwards;

public class CardCommand {

    Main main = null;

    public CardCommand(Main plugin) {
        main = plugin;
    }

    public static void showCard(CommandSender sender, String[] args, ArrayList<CharacterCard> cards) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("rp.card.show") || player.hasPermission("rp.card.*") || player.hasPermission("rp.default")) {
                for (CharacterCard card : cards) {

                    if (card.getPlayerName().equalsIgnoreCase(player.getName())) {
                        player.sendMessage(ChatColor.BOLD + "" + ChatColor.AQUA + "\n----------\n" + "--Анкета--" + card.getPlayerName() + "\n----------\n");
                        player.sendMessage(ChatColor.AQUA + "Имя: " + card.getName());
                        player.sendMessage(ChatColor.AQUA + "Раса: " + card.getRace());
                        player.sendMessage(ChatColor.AQUA + "Субкультура: " + card.getSubculture());
                        player.sendMessage(ChatColor.AQUA + "Возраст: " + card.getAge());
                        player.sendMessage(ChatColor.AQUA + "Пол: " + card.getGender());
                        player.sendMessage(ChatColor.AQUA + "Религия: " + card.getReligion());
                    }
                }
            }
            else {
                player.sendMessage(ChatColor.RED + "Sorry! In order to use this command, you need the following permission: 'rp.card.show'");
            }

        }
    }

    public static void showHelpMessage(CommandSender sender) {
        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("rp.card.help") || player.hasPermission("rp.card.*") || player.hasPermission("rp.default")) {
                sender.sendMessage(ChatColor.BOLD + "" + ChatColor.AQUA + " == " + "--Команды для анкеты--" + " == ");
                sender.sendMessage(ChatColor.AQUA + "/card - Просмотреть свою анкету.");
                sender.sendMessage(ChatColor.AQUA + "/card (игрок) - Посмотреть анкету игрока.");
                sender.sendMessage(ChatColor.AQUA + "/card name (имя) - Изменить имя персонажа.");
                sender.sendMessage(ChatColor.AQUA + "/card race (раса) - Изменить расу персонажа.");
                sender.sendMessage(ChatColor.AQUA + "/card subculture (субкультура) - Изменить субкультуру.");
                sender.sendMessage(ChatColor.AQUA + "/card age (возраст) - Изменить возраст.");
                sender.sendMessage(ChatColor.AQUA + "/card gender (пол) - Изменить пол.");
                sender.sendMessage(ChatColor.AQUA + "/card religion (религия) - Изменить религию.");
            }
            else {
                player.sendMessage(ChatColor.RED + "Ты не можешь использовать данную команду");
            }
        }

    }

    public void changeName(CommandSender sender, String[] args, ArrayList<CharacterCard> cards) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("rp.card.name") || player.hasPermission("rp.card.*") || player.hasPermission("rp.default")) {
                for (CharacterCard card : cards) {

                        if (card.getPlayerName().equalsIgnoreCase(player.getName())) {

                            if (!main.playersOnNameChangeCooldown.contains(player.getName())) {

                                if (args.length > 1) {
                                    card.setName(createStringFromFirstArgOnwards(args, 1));
                                    player.sendMessage(ChatColor.GREEN + "▎ Имя установлено");

                                    // cooldown
                                    main.playersOnNameChangeCooldown.add(player.getName());

                                    int seconds = 300;
                                    getServer().getScheduler().runTaskLater(main, new Runnable() {
                                        @Override
                                        public void run() {
                                            main.playersOnNameChangeCooldown.remove(player.getName());
                                            player.sendMessage(ChatColor.GREEN + "▎ Ты снова можешь изменить имя.");
                                        }
                                    }, seconds * 20);
                                }
                                else {
                                    player.sendMessage(ChatColor.YELLOW + "▎ Использование: /card name (имя персонажа)");
                                }
                            }
                            else {
                                    player.sendMessage(ChatColor.RED + "▎ Подожди немного чтобы снова менять имя!");
                            }
                        }

                }
            }
            else {
                player.sendMessage(ChatColor.RED + "Sorry! In order to use this command, you need the following permission: 'rp.card.name'");
            }

        }
    }

    public static void changeRace(CommandSender sender, String[] args, ArrayList<CharacterCard> cards) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("rp.card.race") || player.hasPermission("rp.card.*") || player.hasPermission("rp.default")) {
                for (CharacterCard card : cards) {

                    if (card.getPlayerName().equalsIgnoreCase(player.getName())) {

                        if (args.length > 1) {
                            card.setRace(args[1]);
                            player.sendMessage(ChatColor.GREEN + "▎ Раса установлена.");
                        }
                        else {
                            player.sendMessage(ChatColor.YELLOW + "▎ Использование: /card race (имя персонажа)");
                        }
                    }
                }
            }
            else {
                player.sendMessage(ChatColor.RED + "Sorry! In order to use this command, you need the following permission: 'rp.card.race'");
            }

        }
    }

    public static void changeSubculture(CommandSender sender, String[] args, ArrayList<CharacterCard> cards) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("rp.card.subculture") || player.hasPermission("rp.card.*") || player.hasPermission("rp.default")) {
                for (CharacterCard card : cards) {

                    if (card.getPlayerName().equalsIgnoreCase(player.getName())) {

                        if (args.length > 1) {
                            card.setSubculture(args[1]);
                            player.sendMessage(ChatColor.GREEN + "▎ Субкультура установлена.");
                        }
                        else {
                            player.sendMessage(ChatColor.YELLOW + "▎ Использование: /card subculture (cубкультура персонажа)");
                        }
                    }
                }
            }
            else {
                player.sendMessage(ChatColor.RED + "Sorry! In order to use this command, you need the following permission: 'rp.card.subculture'");
            }

        }
    }

    public static void changeReligion(CommandSender sender, String[] args, ArrayList<CharacterCard> cards) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("rp.card.religion") || player.hasPermission("rp.card.*") || player.hasPermission("rp.default")) {
                for (CharacterCard card : cards) {

                    if (card.getPlayerName().equalsIgnoreCase(player.getName())) {

                        if (args.length > 1) {
                            card.setReligion(args[1]);
                            player.sendMessage(ChatColor.GREEN + "▎ Религия установлена.");
                        }
                        else {
                            player.sendMessage(ChatColor.YELLOW + "▎ Использование: /card religion (религия персонажа)");
                        }
                    }
                }
            }
            else {
                player.sendMessage(ChatColor.RED + "Sorry! In order to use this command, you need the following permission: 'rp.card.religion'");
            }

        }
    }

    public static void changeAge(CommandSender sender, String[] args, ArrayList<CharacterCard> cards) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("rp.card.age") || player.hasPermission("rp.card.*") || player.hasPermission("rp.default")) {
                for (CharacterCard card : cards) {

                    if (card.getPlayerName().equalsIgnoreCase(player.getName())) {

                        if (args.length > 1) {
                            card.setAge(Integer.parseInt(args[1]));
                            player.sendMessage(ChatColor.GREEN + "▎ Возраст установлен.");
                        }
                        else {
                            player.sendMessage(ChatColor.YELLOW + "▎ Использование: /card age (возраст персонажа)");
                        }
                    }
                }
            }
            else {
                player.sendMessage(ChatColor.RED + "Sorry! In order to use this command, you need the following permission: 'rp.card.age'");
            }

        }
    }

    public static void changeGender(CommandSender sender, String[] args, ArrayList<CharacterCard> cards) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("rp.card.gender") || player.hasPermission("rp.card.*") || player.hasPermission("rp.default")) {
                for (CharacterCard card : cards) {

                    if (card.getPlayerName().equalsIgnoreCase(player.getName())) {

                        if (args.length > 1) {
                            card.setGender(args[1]);
                            player.sendMessage(ChatColor.GREEN + "▎ Пол установлен.");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "Использование: /card gender (пол персонажа)");
                        }
                    }
                }
            }
            else {
                player.sendMessage(ChatColor.RED + "Sorry! In order to use this command, you need the following permission: 'rp.card.gender'");
            }

        }
    }

    public static void showPlayerInfo(CommandSender sender, String[] args, ArrayList<CharacterCard> cards) {
        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("rp.card.show.others") || player.hasPermission("rp.card.*") || player.hasPermission("rp.default")) {
                for (CharacterCard card : cards) {
                    if (args.length > 0) {
                        if (card.getPlayerName().equals(args[0])) {
                            sender.sendMessage(ChatColor.BOLD + "" + ChatColor.AQUA + "\n----------\n" + "--Анкета--" + card.getPlayerName() + "\n----------\n");
                            sender.sendMessage(ChatColor.AQUA + "Имя: " + card.getName());
                            sender.sendMessage(ChatColor.AQUA + "Раса: " + card.getRace());
                            sender.sendMessage(ChatColor.AQUA + "Субкультура: " + card.getSubculture());
                            sender.sendMessage(ChatColor.AQUA + "Возрат: " + card.getAge());
                            sender.sendMessage(ChatColor.AQUA + "Пол: " + card.getGender());
                            sender.sendMessage(ChatColor.AQUA + "Религия: " + card.getReligion());
                            return;
                        }
                    }
                }

                player.sendMessage(ChatColor.RED + "Игрок не найден!");

            }
            else {
                player.sendMessage(ChatColor.RED + "Sorry! In order to use this command, you need the following permission: 'rp.card.show.others'");
            }

        }

    }
}
