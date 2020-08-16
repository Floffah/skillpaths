package dev.floffah.skillpaths.commands;

import dev.floffah.skillpaths.SkillPaths;
import dev.floffah.skillpaths.gui.GUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Skills implements CommandExecutor {
    SkillPaths main;

    public Skills(SkillPaths mn) {
        main = mn;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            GUI gui = new GUI(main);
            gui.openInv((Player) sender);
        } else {
            sender.sendMessage("You must be a player to execute this command");
        }
        return true;
    }
}
