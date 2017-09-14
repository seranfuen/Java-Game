package test;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import engine.Stage;
import engine.Stage.Blocks;
import engine.Stage.Direction;
import engine.Stage.Enemies;
import entity.Enemy;
import entity.Goomba;
import entity.Ground;
import entity.Mario;
import entity.Position;
import entity.QuestionBlock;
import entity.Size;

public class EntityTest extends JFrame {
	
	long elapsed = 0;
	
	class Painter extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = -4754525898910099399L;
		
		public Painter() {

		}

		@Override
		public void paint(Graphics g) {
			stage.paint(g, elapsed);	
		}
	}

	private static final long serialVersionUID = -6775906147717968593L;

	private JPanel panel;
	private Stage stage;
	public EntityTest() {
		panel = new Painter();
		panel.setSize(800, 800);
		this.add(panel);
		setSize(800, 800);
		setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				stage.keyReleased(e.getKeyCode());
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				stage.keyPressed(e.getKeyCode());
			}
		});
        Timer tim = new Timer(30, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateState();	
			}
		});
        stage = new Stage(1200, new Size(2550, 1200), new Size(800, 800), 
        		new Mario(new Position(399, 699)));
        stage.addGround(new Ground(new Position(0, 1120), 1200, 51));
        stage.addGround(new Ground(new Position(1200, 1080), 250, 200));
        stage.addGround(new Ground(new Position(1200+250+400, 1120), 300, 51));
        stage.addBlock(Blocks.WOODEN, 1200+250-48, 1080-47, null);
        stage.addBlock(Blocks.WOODEN, 1200+250-48, 1080-47*2, null);
        stage.addEnemy(Enemies.GOOMBA, 1300, 1000, Direction.LEFT);
        int bp = 920;
        stage.addBlock(Blocks.QUESTION, 200, bp, null);
        stage.addBlock(new QuestionBlock(new Position(248, bp)));
        stage.addBlock(new QuestionBlock(new Position(248+48, bp)));
        stage.addBlock(new QuestionBlock(new Position(248+48*2, bp)));
        stage.addBlock(new QuestionBlock(new Position(248+48*3, bp)));
        stage.addEnemy(Stage.Enemies.GOOMBA, 300, 1120-46, Direction.LEFT);
        stage.addEnemy(Stage.Enemies.GOOMBA, 390, 1120-46, Direction.LEFT);
        stage.addBlock(Blocks.WOODEN,0, 1120-47, null);
        stage.addBlock(Blocks.WOODEN, 600, 1120-47, null);
        stage.addBlock(Blocks.WOODEN, 600, 1120-47*2, null);
        stage.addBlock(Blocks.QUESTION, 850, bp-60, null);
        //stage.addBlock(Blocks.WOODEN, 600, 1120-47*2, null);
        tim.start();
	}
	
	private void updateState() {
		elapsed += 30; 
		stage.update(30);
		panel.updateUI();
		
	}


	
	
	
	
}
