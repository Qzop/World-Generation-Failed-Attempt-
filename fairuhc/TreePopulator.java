package me.worldgen.fairuhc;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;

public class TreePopulator extends BlockPopulator
{
	@Override
	public void populate(World world, Random random, Chunk chunk) 
	{
		int amount = random.nextInt(35) + 25;
		
		for(int i = 0; i < amount; i++)
		{
			int cX = chunk.getX() * 16;
			int cZ = chunk.getZ() * 16;
			int x = cX + random.nextInt(30) + 22;
			int z = cZ + random.nextInt(30) + 22;
			
			if(world.getBiome(x, z) == Biome.FOREST || world.getBiome(x, z) == Biome.FOREST_HILLS)
			{
				if(random.nextInt(100) < 75)
				{
					world.generateTree(new Location(world, x, world.getHighestBlockYAt(x, z), z), TreeType.TREE);
				}
				else if(random.nextInt(100) > 75)
				{
					world.generateTree(new Location(world, x, world.getHighestBlockYAt(x, z), z), TreeType.BIRCH);
				}
			}
		}
		
		for(int i = 0; i < amount; i++)
		{
			int cX = chunk.getX() * 16;
			int cZ = chunk.getZ() * 16;
			int x = cX + random.nextInt(30) + 22;
			int z = cZ + random.nextInt(30) + 22;
			
			if(world.getBiome(x, z) == Biome.BIRCH_FOREST || world.getBiome(x, z) == Biome.BIRCH_FOREST_HILLS)
			{
				world.generateTree(new Location(world, x, world.getHighestBlockYAt(x, z), z), TreeType.BIRCH);
			}
		}
	}
}
