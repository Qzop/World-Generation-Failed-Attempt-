package me.worldgen.fairuhc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Commands implements Listener
{
	public String regenworld = "regeneratemap";
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player p = (Player) sender;
		
		if(label.equalsIgnoreCase(regenworld))
		{
			if(sender instanceof Player)
			{
				if(p.hasPermission("world.regen"))
				{
					String name = "uhc_world";
					
				}
			}
		}
		
		return false;
	}
}