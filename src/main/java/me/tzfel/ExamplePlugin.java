package me.tzfel;

import co.aikar.commands.PaperCommandManager;
import me.tzfel.command.ExampleCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin {

  private static ExamplePlugin instance;

  @Override
  public void onEnable() {
    initPlugin();
    instance = this;

  }


  @Override
  public void onDisable() {

  }


  public void initPlugin() {


    //Comands!
    PaperCommandManager commandManager = new PaperCommandManager(this);
    commandManager.registerCommand(new ExampleCommand());
  }




  public static ExamplePlugin getInstance() {
    return instance;
  }
}
