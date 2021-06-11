package com.gcstudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.gcstudios.entities.Player;
import com.gcstudios.main.Game;

public class UI {

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.PLAIN,20));
		g.drawString("Cheeses: " + Game.cur_cheese + "/" + Game.max_cheese, 30, 30);
		if(Player.life == 1) {
			g.drawImage(Game.spritesheet.getSprite(0, 16, 16, 16), 300-32, 5, 16*2,16*2, null);
		}
		else if(Player.life == 2) {
			g.drawImage(Game.spritesheet.getSprite(0, 16, 16, 16), 300-32, 5, 16*2,16*2, null);
			g.drawImage(Game.spritesheet.getSprite(0, 16, 16, 16), 332-32, 5, 16*2,16*2, null);
		}
		else if(Player.life == 3) {
			g.drawImage(Game.spritesheet.getSprite(0, 16, 16, 16), 300-32, 5, 16*2,16*2, null);
			g.drawImage(Game.spritesheet.getSprite(0, 16, 16, 16), 332-32, 5, 16*2,16*2, null);
			g.drawImage(Game.spritesheet.getSprite(0, 16, 16, 16), 364-32, 5, 16*2,16*2, null);
		}
		if(Player.life == 4) {
			g.drawImage(Game.spritesheet.getSprite(0, 16, 16, 16), 300-32, 5, 16*2,16*2, null);
			g.drawImage(Game.spritesheet.getSprite(0, 16, 16, 16), 332-32, 5, 16*2,16*2, null);
			g.drawImage(Game.spritesheet.getSprite(0, 16, 16, 16), 364-32, 5, 16*2,16*2, null);
			g.drawImage(Game.spritesheet.getSprite(0, 16, 16, 16), 396-32, 5, 16*2,16*2, null);
		}
		else if(Player.life == 5) {
			g.drawImage(Game.spritesheet.getSprite(0, 16, 16, 16), 300-32, 5, 16*2,16*2, null);
			g.drawImage(Game.spritesheet.getSprite(0, 16, 16, 16), 332-32, 5, 16*2,16*2, null);
			g.drawImage(Game.spritesheet.getSprite(0, 16, 16, 16), 364-32, 5, 16*2,16*2, null);
			g.drawImage(Game.spritesheet.getSprite(0, 16, 16, 16), 396-32, 5, 16*2,16*2, null);
			g.drawImage(Game.spritesheet.getSprite(0, 16, 16, 16), 428-32, 5, 16*2,16*2, null);
		}
		else if(Player.life == 6) {
			g.drawImage(Game.spritesheet.getSprite(0, 16, 16, 16), 300-32, 5, 16*2,16*2, null);
			g.drawImage(Game.spritesheet.getSprite(0, 16, 16, 16), 332-32, 5, 16*2,16*2, null);
			g.drawImage(Game.spritesheet.getSprite(0, 16, 16, 16), 364-32, 5, 16*2,16*2, null);
			g.drawImage(Game.spritesheet.getSprite(0, 16, 16, 16), 396-32, 5, 16*2,16*2, null);
			g.drawImage(Game.spritesheet.getSprite(0, 16, 16, 16), 428-32, 5, 16*2,16*2, null);
			g.drawImage(Game.spritesheet.getSprite(0, 16, 16, 16), 460-32, 5, 16*2,16*2, null);
		}
	}
	
}
