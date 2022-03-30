package com.example.listener.discord;

import com.example.ExamplePlugin;
import com.example.utility.Formatters;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import javax.security.auth.login.LoginException;

public class DiscordListener extends ListenerAdapter implements Listener {

  @Getter private static JDA bot;
  private static TextChannel textChannel;

  /**
   * NO Tested
   */


  public DiscordListener() {
    try {
      bot = JDABuilder.createDefault("token_here").setActivity(Activity.playing("Minecraft")).addEventListeners().build();
      textChannel = bot.getTextChannelById("minecraft-discord-id");
    } catch (LoginException e) {
      e.printStackTrace();
    }
    Bukkit.getServer().getPluginManager().registerEvents(this, ExamplePlugin.getInstance());
  }


  @Override
  public void onMessageReceived(MessageReceivedEvent e) {
    if(e.getTextChannel().getId().equals(textChannel.getId())) {
      if(e.isWebhookMessage() || e.getAuthor().isBot() || e.getAuthor().isSystem())return;

      Bukkit.broadcastMessage(Formatters.format("&8" + e.getAuthor().getName() + " &7>> " + e.getMessage().getContentRaw()));
    }
  }

  @EventHandler
  public void onChat(AsyncPlayerChatEvent e) {
    textChannel.sendMessage(e.getPlayer() + " >> " + e.getMessage()).queue();
  }
}
