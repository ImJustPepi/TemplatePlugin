package com.example;

import co.aikar.commands.PaperCommandManager;
import co.aikar.taskchain.BukkitTaskChainFactory;
import co.aikar.taskchain.TaskChain;
import co.aikar.taskchain.TaskChainFactory;
import com.example.global.command.ExampleCommand;
import com.example.global.listener.discord.DiscordListener;
import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin {

  private static ExamplePlugin instance;

  @Override
  public void onEnable() {
    instance = this;
    initPlugin();
    taskChainFactory = BukkitTaskChainFactory.create(this);
  }


  @Override
  public void onDisable() {

  }


  public void initPlugin() {
    //Discord
    new DiscordListener();

    //Comands!
    PaperCommandManager commandManager = new PaperCommandManager(this);
    commandManager.registerCommand(new ExampleCommand());
  }


  //TaskChain!
  private static TaskChainFactory taskChainFactory;

  public static <T> TaskChain<T> newChain() {
    return taskChainFactory.newChain();

  }
  public static <T> TaskChain<T> newSharedChain(String name) {
    return taskChainFactory.newSharedChain(name);
  }

  public static ExamplePlugin getInstance() {
    return instance;
  }
}
