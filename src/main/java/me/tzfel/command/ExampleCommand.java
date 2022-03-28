package me.tzfel.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandIssuer;
import co.aikar.commands.RegisteredCommand;
import co.aikar.commands.annotation.*;
import me.tzfel.utility.Formatters;
import org.bukkit.entity.Player;

@CommandAlias("example")
public class ExampleCommand extends BaseCommand {


  @Subcommand("hello")
  @CommandPermission("example.admin")
  @CommandAlias("hi")
  @CommandCompletion("hello!")
  @Syntax("&c&lError! &7Use /example hello")
  public void exampleHello(Player sender, String[] args) {
    sender.sendMessage(Formatters.format("&6Hello!"));
  }

  @Override
  public void showSyntax(CommandIssuer issuer, RegisteredCommand<?> cmd) {
    issuer.sendMessage(Formatters.format(cmd.getSyntaxText()));
  }
}
