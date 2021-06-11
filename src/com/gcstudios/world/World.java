package com.gcstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.gcstudios.entities.Cheese;
import com.gcstudios.entities.CheeseDot;
import com.gcstudios.entities.Enemy;
import com.gcstudios.entities.Entity;
import com.gcstudios.entities.Player;
import com.gcstudios.main.Game;

public class World {

	public static Tile[] tiles;
	public static int WIDTH,HEIGHT;
	public static final int TILE_SIZE = 16;
	
	
	public World(String path){
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(),pixels, 0, map.getWidth());
			for(int xx = 0; xx < map.getWidth(); xx++){
				for(int yy = 0; yy < map.getHeight(); yy++){
					int pixelAtual = pixels[xx + (yy * map.getWidth())];
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					if(pixelAtual == 0xFF000000){
						//Floor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					}else if(pixelAtual == 0xFFFFFFFF){
						//Parede
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL);
					}else if(pixelAtual == 0xFF0026FF) {
						//Player
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);
					}else if(pixelAtual == 0xFFFF0000) {
						//Instanciar inimigo e adicionar a lista das entities
					}else if(pixelAtual == 0xFFFFD800) {
						Cheese cheese = new Cheese(xx*16, yy*16, 16, 16, 0, Entity.cheese);
						Game.entities.add(cheese);
						Game.max_cheese++;
					}else if(pixelAtual == 0xFFD3B000) {
						CheeseDot cheesedot = new CheeseDot(xx*16, yy*16, 16, 16, 0, Entity.cheesedot);
						Game.entities.add(cheesedot);
						Game.max_cheese++;
					}else if(pixelAtual == 0xFF191919) {
						Enemy blckcat = new Enemy(xx*16, yy*16, 16, 16, 2, Entity.blckcat);
						Game.entities.add(blckcat);
						System.out.println("debug");
					}else if(pixelAtual == 0xFFFF6A00) {
						Enemy orngcat = new Enemy(xx*16, yy*16, 16, 16, 2, Entity.orngcat);
						Game.entities.add(orngcat);
					}else if(pixelAtual == 0xFF5B2400) {
						Enemy siamcat = new Enemy(xx*16, yy*16, 16, 16, 2, Entity.siamcat);
						Game.entities.add(siamcat);
					}else if(pixelAtual == 0xFFE5E5E5) {
						Enemy whtecat = new Enemy(xx*16, yy*16, 16, 16, 2, Entity.whtecat);
						Game.entities.add(whtecat);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isFree(int xnext,int ynext){
		
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		int x4 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y4 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile));
	}
	
	public static void restartGame(String level){
		Game.player = new Player(0,0,16,16,1,Game.spritesheet.getSprite(32, 0,16,16));
		Game.entities.clear();
		Game.entities.add(Game.player);
		Game.cur_cheese = 0;
		Game.max_cheese =0;
		Player.life = 0;
		Game.world = new World(level);
		return;
	}
	
	public void render(Graphics g){
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}
	
}
