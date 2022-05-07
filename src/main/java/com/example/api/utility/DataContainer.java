package com.example.api.utility;

import com.example.ExamplePlugin;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nullable;

public class DataContainer {

  public static NamespacedKey key(String value) {
    return new NamespacedKey(ExamplePlugin.getInstance(), value);
  }

  public static <T> void set(PersistentDataHolder holder, String key, PersistentDataType<T, T> type, T value) {
    if(holder == null)return;
    holder.getPersistentDataContainer().set(DataContainer.key(key), type, value);
  }

  @Nullable
  public static <T> T get(PersistentDataHolder holder, String key, PersistentDataType<T, T> type){
    if(!DataContainer.has(holder, key, type)) return null;
    return holder.getPersistentDataContainer().get(DataContainer.key(key), type);
  }

  public static <T> boolean has(PersistentDataHolder holder, String key, PersistentDataType<T, T> type){
    if(holder == null) return false;
    return holder.getPersistentDataContainer().has(DataContainer.key(key), type);
  }

  public static PersistentDataAdapterContext getAdapterContext(PersistentDataHolder holder) {
    return holder.getPersistentDataContainer().getAdapterContext();
  }

  public static PersistentDataContainer newPersistentData(PersistentDataHolder holder) {
    return holder.getPersistentDataContainer().getAdapterContext().newPersistentDataContainer();
  }
}
