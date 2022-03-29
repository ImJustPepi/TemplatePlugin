package com.example.command;

import co.aikar.commands.*;
import co.aikar.commands.annotation.*;
import com.example.utility.Formatters;
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
    issuer.sendMessage(MessageType.SYNTAX, MessageKeys.INVALID_SYNTAX,Formatters.format(cmd.getSyntaxText()));
  }
}
