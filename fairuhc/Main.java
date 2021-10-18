package me.worldgen.fairuhc;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener
{
	@Override
	public void onEnable()
	{	
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "WorldGen Enabled");
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable()
	{
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "WorldGen Disabled");
	}
	
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
	{
		return new WorldGen();
	}
}
