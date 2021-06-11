package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.world.World;

public class Player extends Entity{
	
	public boolean right,up,left,down;
	private int frames = 0, maxFrames = 5, index = 0, maxIndex = 3, hurtFrames = 0, hIndex = 3, hMaxIndex = 4;
	private boolean invencible = false;
	private BufferedImage sprites[];
	public static int life = 0;

	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		sprites = new BufferedImage[5];
		sprites[0] = Game.spritesheet.getSprite(32, 0, 16, 16);
		sprites[1] = Game.spritesheet.getSprite(48, 0, 16, 16);
		sprites[2] = Game.spritesheet.getSprite(64, 0, 16, 16);
		sprites[3] = Game.spritesheet.getSprite(80, 0, 16, 16);
		sprites[4] = Game.spritesheet.getSprite(16, 16, 16, 16);
	}
	
	public void tick(){
		depth = 1;
		if(right && World.isFree((int)(x+speed),this.getY())) {
			x+=speed;
		}
		else if(left && World.isFree((int)(x-speed),this.getY())) {
			x-=speed;
		}
		if(up && World.isFree(this.getX(),(int)(y-speed))){
			y-=speed;
		}
		else if(down && World.isFree(this.getX(),(int)(y+speed))){
			y+=speed;
		}
		
		checkCollisionCheeseDot();
		checkCollisionCheese();
		checkCollisionEnemy();
		
		frames++;
		if(frames>maxFrames) {
			frames=0;
			index++;
			if(index>maxIndex) {
				index = 0;
			}
		}
		
		if(Game.cur_cheese == Game.max_cheese) {
			World.restartGame("/level1.png");
		}
		
		if(life < 0) {
			World.restartGame("/level1.png");
		}
		
		if(invencible) {
			hurtFrames++;
			hIndex++;
			if(hurtFrames>60) {
				hurtFrames = 0;
				invencible = false;
			}
		}
		
		if(hIndex > hMaxIndex) {
			hIndex = 3;
		}
	}
	
	public void render(Graphics g) {
		if(invencible) {
			g.drawImage(sprites[hIndex], this.getX(), this.getY(), null);
		}else {
			g.drawImage(sprites[index], this.getX(), this.getY(), null);
		}
	}
	
	public void checkCollisionCheeseDot() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof CheeseDot) {
				if(Entity.isColidding(this, e)) {
					Game.entities.remove(i);
					Game.cur_cheese++;
					return;
				}
			}
		}
	}
	
	public void checkCollisionCheese() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Cheese) {
				if(Entity.isColidding(this, e)) {
					Game.entities.remove(i);
					Game.cur_cheese++;
					life++;
					return;
				}
			}
		}
	}
	
	public void checkCollisionEnemy() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Enemy) {
				if(Entity.isColidding(this, e)) {
					if(!invencible) {
						life--;
						invencible = true;
					}
					return;
				}
			}
		}
	}
}
