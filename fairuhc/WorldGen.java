package me.worldgen.fairuhc;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.GrassSpecies;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.event.Listener;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.material.LongGrass;
import org.bukkit.util.noise.PerlinNoiseGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import net.minecraft.server.v1_8_R3.BiomeDesert;

public class WorldGen extends ChunkGenerator implements Listener 
{
	@Override
	public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome)
	{
		ChunkData chunk = createChunkData(world);
		SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
		PerlinNoiseGenerator perlin = new PerlinNoiseGenerator(new Random(world.getSeed()));
		LongGrass grass = new LongGrass();
		
		
		grass.setSpecies(GrassSpecies.NORMAL);
		generator.setScale(0.10D);
		
		
		
		for(int x = 0; x < 16; x++)
		{
			for(int z = 0; z < 16; z++)
			{
				if(biome.getBiome(x, z) == Biome.JUNGLE || biome.getBiome(x, z) == Biome.JUNGLE_EDGE || biome.getBiome(x, z) == Biome.JUNGLE_EDGE_MOUNTAINS || biome.getBiome(x, z) == Biome.JUNGLE_HILLS || biome.getBiome(x, z) == Biome.JUNGLE_MOUNTAINS)
				{
					biome.setBiome(x, z, Biome.SAVANNA);
				}
				else if(biome.getBiome(x, z) == Biome.ROOFED_FOREST || biome.getBiome(x, z) == Biome.ROOFED_FOREST_MOUNTAINS)
				{
					biome.setBiome(x, z, Biome.FOREST);
				}
				else if(biome.getBiome(x, z) == Biome.OCEAN)
				{
					biome.setBiome(x, z, Biome.PLAINS);
				}
				else if(biome.getBiome(x, z) == Biome.DEEP_OCEAN)
				{
					biome.setBiome(x, z, Biome.DESERT);
				}
				else if(biome.getBiome(x, z) == Biome.FROZEN_OCEAN)
				{
					biome.setBiome(x, z, Biome.PLAINS);
				}
				else if(biome.getBiome(x, z) == Biome.MESA || biome.getBiome(x, z) == Biome.MESA_PLATEAU || biome.getBiome(x, z) == Biome.MESA_PLATEAU_FOREST || biome.getBiome(x, z) == Biome.MESA_PLATEAU_FOREST_MOUNTAINS || biome.getBiome(x, z) == Biome.MESA_PLATEAU_MOUNTAINS)
				{
					biome.setBiome(x, z, Biome.FOREST);
				}
				else if(biome.getBiome(x, z) == Biome.COLD_TAIGA_MOUNTAINS)
				{
					biome.setBiome(x, z, Biome.BIRCH_FOREST);
				}
				else if(biome.getBiome(x, z) == Biome.EXTREME_HILLS || biome.getBiome(x, z) == Biome.EXTREME_HILLS_MOUNTAINS || biome.getBiome(x, z) == Biome.EXTREME_HILLS_PLUS || biome.getBiome(x, z) == Biome.EXTREME_HILLS_PLUS_MOUNTAINS)
				{
					biome.setBiome(x, z, Biome.FOREST);
				}
				
				int nx = chunkX * 16 + x;
				int nz = chunkZ * 16 + z;
				
				double noise  = 64;
				double p_noise = perlin.noise(nx * .02 , nz * .02, 2, 2, .90) * 6; 
				
				if(biome.getBiome(x, z) == Biome.BIRCH_FOREST_HILLS || biome.getBiome(x, z) == Biome.FOREST_HILLS || biome.getBiome(x, z) == Biome.DESERT_HILLS)
				{
					p_noise = perlin.noise(nx * .04, nz * .04, 2, 2, .90) * 10;
				}
				
				noise = noise + p_noise;

				double level = 64;
				
				if(noise > 64)
				{
					level = noise;
				}
				
				for(int y = 0; y < noise; y++)
				{
					if(y == 0)
					{
						chunk.setBlock(x, 0, z, Material.BEDROCK);
					}
					else if(y <= noise)
					{
						if(y >= noise - 1)
						{
							if(biome.getBiome(x, z) == Biome.PLAINS)
							{
								chunk.setBlock(x, y, z, Material.GRASS);
								chunk.setBlock(x, y - 1, z, Material.DIRT);
								chunk.setBlock(x, y - 2, z, Material.DIRT);
								chunk.setBlock(x, y - 3, z, Material.DIRT);
								
								if(random.nextInt(100) < 2 && chunk.getType(x, y, z) == Material.GRASS)
								{
									chunk.setBlock(x, y + 1, z, Material.YELLOW_FLOWER);
								}
								else if(random.nextInt(100) < 10 && chunk.getType(x, y, z) == Material.GRASS)
								{
									chunk.setBlock(x, y + 1, z, grass);
								}
							}
							else if(biome.getBiome(x, z) == Biome.RIVER)
							{
								chunk.setBlock(x, y, z, Material.AIR);
								chunk.setBlock(x, y - 1, z, Material.AIR);
								chunk.setBlock(x, y - 2, z, Material.AIR);
								chunk.setBlock(x, y - 3, z, Material.AIR);
								chunk.setBlock(x, y - 4, z, Material.AIR);
								chunk.setBlock(x, y - 5, z, Material.AIR);
								chunk.setBlock(x, y - 6, z, Material.AIR);
								chunk.setBlock(x, y - 7, z, Material.AIR);
								
								chunk.setBlock(x, y - 2, z, Material.STATIONARY_WATER);
								chunk.setBlock(x, y - 3, z, Material.STATIONARY_WATER);
								chunk.setBlock(x, y - 4, z, Material.STATIONARY_WATER);
								chunk.setBlock(x, y - 5, z, Material.STATIONARY_WATER);
								chunk.setBlock(x, y - 6, z, Material.STATIONARY_WATER);
								chunk.setBlock(x, y - 7, z, Material.STATIONARY_WATER);
							}
							else if(biome.getBiome(x, z) == Biome.COLD_TAIGA)
							{
								chunk.setBlock(x, y, z, Material.GRASS);
							}
							else if(biome.getBiome(x, z) == Biome.COLD_TAIGA_HILLS)
							{
								chunk.setBlock(x, y, z, Material.GRASS);
							}
							else if(biome.getBiome(x, z) == Biome.SUNFLOWER_PLAINS)
							{
								chunk.setBlock(x, y, z, Material.GRASS);
								
								if(random.nextInt(100) < 2 && chunk.getType(x, y, z) == Material.GRASS)
								{
									chunk.setBlock(x, y + 1, z, Material.DOUBLE_PLANT);
								}
								else if(random.nextInt(100) < 10 && chunk.getType(x, y, z) == Material.GRASS)
								{
									chunk.setBlock(x, y + 1, z, grass);
								}
							}
							else if(biome.getBiome(x, z) == Biome.FOREST || biome.getBiome(x, z) == Biome.FOREST_HILLS)
							{
								chunk.setBlock(x, y, z, Material.GRASS);
								chunk.setBlock(x, y - 1, z, Material.DIRT);
								chunk.setBlock(x, y - 2, z, Material.DIRT);
								chunk.setBlock(x, y - 3, z, Material.DIRT);
								
								if(random.nextInt(100) < 5 && chunk.getType(x, y, z) == Material.GRASS)
								{
									chunk.setBlock(x, y + 1, z, grass);
								} 
								else if(random.nextDouble() < .01)
								{
									chunk.setBlock(x, y + 1, z, Material.RED_ROSE);
								}
								else if(random.nextDouble() < .01)
								{
									chunk.setBlock(x, y + 1, z, Material.YELLOW_FLOWER);
								}
								else if(random.nextDouble() < .01)
								{
									
								}
							}
							else if(biome.getBiome(x, z) == Biome.DESERT || biome.getBiome(x, z) == Biome.DESERT_HILLS)
							{
								chunk.setBlock(x, y, z, Material.SAND);
								chunk.setBlock(x, y - 1, z, Material.SAND);
								chunk.setBlock(x, y - 2, z, Material.SAND);
								chunk.setBlock(x, y - 3, z, Material.SAND);
								chunk.setBlock(x, y - 4, z, Material.SANDSTONE);
								chunk.setBlock(x, y - 5, z, Material.SANDSTONE);
								
								if(random.nextInt(100) < 3)
								{
									chunk.setBlock(x, y - 6, z, Material.SANDSTONE);
								}
								
								if(random.nextInt(100) == 1 && chunk.getType(x, y, z) == Material.SAND)
								{
									chunk.setBlock(x, y + 1, z, Material.LONG_GRASS);
								}
								else if(random.nextInt(100) == 1 && chunk.getType(x, y, z) == Material.SAND)
								{
									if(random.nextDouble() >= .1 && random.nextDouble() < .15)
									{
										chunk.setBlock(x, y + 1, z, Material.CACTUS);
									}
									else if(random.nextDouble() >= .05 && random.nextDouble() < .1)
									{
										chunk.setBlock(x, y + 1, z, Material.CACTUS);
										chunk.setBlock(x, y + 2, z, Material.CACTUS);
									}
									else if(random.nextDouble() < 0.05)
									{
										chunk.setBlock(x, y + 1, z, Material.CACTUS);
										chunk.setBlock(x, y + 2, z, Material.CACTUS);
										chunk.setBlock(x, y + 3, z, Material.CACTUS);
									}
								}
							}
							else if(biome.getBiome(x, z) == Biome.SWAMPLAND)
							{
								chunk.setBlock(x, y, z, Material.GRASS);
								chunk.setBlock(x, y - 1, z, Material.DIRT);
								chunk.setBlock(x, y - 2, z, Material.DIRT);
								chunk.setBlock(x, y - 3, z, Material.DIRT);
								
								if(random.nextInt(100) < 2 && chunk.getType(x, y, z) == Material.GRASS)
								{
									chunk.setBlock(x, y + 1, z, Material.YELLOW_FLOWER);
								}
								
								if(random.nextInt(100) < 2 && chunk.getType(x, y, z) == Material.GRASS)
								{
									chunk.setBlock(x, y + 1, z, grass);
								}
							}
							else if(biome.getBiome(x, z) == Biome.SAVANNA)
							{
								chunk.setBlock(x, y, z, Material.GRASS);
								chunk.setBlock(x, y - 1, z, Material.DIRT);
								chunk.setBlock(x, y - 2, z, Material.DIRT);
								chunk.setBlock(x, y - 3, z, Material.DIRT);
							}
							else if(biome.getBiome(x, z) == Biome.BIRCH_FOREST || biome.getBiome(x, z) == Biome.BIRCH_FOREST_HILLS)
							{
								chunk.setBlock(x, y, z, Material.GRASS);
								chunk.setBlock(x, y - 1, z, Material.DIRT);
								chunk.setBlock(x, y - 2, z, Material.DIRT);
								chunk.setBlock(x, y - 3, z, Material.DIRT);
								
								if(random.nextInt(100) < 2 && chunk.getType(x, y, z) == Material.GRASS)
								{
									chunk.setBlock(x, y + 1, z, grass);
								}
								
							}
							else if(biome.getBiome(x, z) == Biome.BEACH)
							{
								chunk.setBlock(x, y, z, Material.SAND);
							}
						}
						else
						{
							chunk.setBlock(x, y, z, Material.STONE);
						}
					}
				}
			}
		}
		
		return chunk;
	}
	
	@Override
	public List<BlockPopulator> getDefaultPopulators(World world) 
	{
	    return Arrays.asList((BlockPopulator) new LakeGen(), (BlockPopulator) new TreePopulator());
	}
}