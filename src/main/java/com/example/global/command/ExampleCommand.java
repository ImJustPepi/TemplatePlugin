package com.example.global.command;

import co.aikar.commands.*;
import co.aikar.commands.annotation.*;
import co.aikar.taskchain.TaskChain;
import com.example.ExamplePlugin;
import com.example.api.utility.Formatters;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;


@CommandAlias("example")
public class ExampleCommand extends BaseCommand {


  @Subcommand("hello")
  @CommandPermission("example.admin")
  @CommandAlias("hi")
  @CommandCompletion("hello!")
  @Syntax("&c&lError! &7Use /example hello.")
  public void exampleHello(Player sender, String[] args) {
    sender.sendMessage(Formatters.format("&6Hello!"));
  }


  @Subcommand("taskchain")
  @CommandPermission("example.staff")
  @CommandAlias("testtaskchain")
  @Syntax("&c&lError! &7Use /example taskchain.")
  public void taskChainExample(Player sender) {
    var task = ExamplePlugin.newChain();

    for(int i = 0; i < 3; i++) {
      task.delay(100, TimeUnit.MILLISECONDS).sync(() -> sender.sendMessage(Formatters.format("&9Hello! &c" + sender.getName())));
    }
    task.sync(TaskChain::abort).execute();
  }





  @Override
  public void showSyntax(CommandIssuer issuer, RegisteredCommand<?> cmd) {
    issuer.sendMessage(MessageType.SYNTAX, MessageKeys.INVALID_SYNTAX,Formatters.format(cmd.getSyntaxText()));
  }
}
