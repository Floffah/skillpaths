package dev.floffah.skillpaths.commands;

import dev.floffah.skillpaths.SkillPaths;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SkillPath implements CommandExecutor {
    SkillPaths main;

    public SkillPath(SkillPaths mn) {
        main = mn;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length <= 0) {
            sender.sendMessage(help(sender));
            return true;
        }
        return true;
    }

    private BaseComponent help(CommandSender sender) {
        BaseComponent helpmsg = new TextComponent();

        BaseComponent lline = new TextComponent("---------------{");
        lline.setColor(ChatColor.GOLD);
        BaseComponent rline = new TextComponent("}---------------");
        rline.setColor(ChatColor.GOLD);

        BaseComponent helpname = new TextComponent("SkillPaths Help");
        helpname.setColor(ChatColor.YELLOW);
        helpname.setBold(true);

        helpmsg.addExtra(lline);
        helpmsg.addExtra(helpname);
        helpmsg.addExtra(rline);

        BaseComponent info = new TextComponent("\n\n Hi " + sender.getName() + "! You can click a command to autofill it.\n\n");
        info.setColor(ChatColor.GREEN);
        helpmsg.addExtra(info);

        helpmsg.addExtra(helpcmd("start", "Starts your skill tree."));

        helpmsg.addExtra("\n");

        helpmsg.addExtra(lline);
        helpmsg.addExtra(helpname);
        helpmsg.addExtra(rline);

        return helpmsg;
    }

    private BaseComponent helpcmd(String name, String desc) {
        BaseComponent helpcmd = new TextComponent();

        BaseComponent cmd = new TextComponent(" /skillpaths " + name);
        cmd.setColor(ChatColor.AQUA);

        ClickEvent cmde = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/skillpaths " + name);

        BaseComponent ht = new TextComponent("Auto fill /skillpaths " + name);
        ht.setColor(ChatColor.DARK_AQUA);
        BaseComponent hta[] = {ht};
        HoverEvent cmdh = new HoverEvent(HoverEvent.Action.SHOW_TEXT, hta);

        cmd.setHoverEvent(cmdh);
        cmd.setClickEvent(cmde);

        BaseComponent d = new TextComponent(" - " + desc + "\n");
        d.setColor(ChatColor.DARK_AQUA);

        helpcmd.addExtra(cmd);
        helpcmd.addExtra(d);

        return helpcmd;
    }
}
