package es.sergioangelverbo.test;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import es.sergioangelverbo.engine.Stage;
import es.sergioangelverbo.engine.Stage.Blocks;
import es.sergioangelverbo.engine.Stage.Direction;
import es.sergioangelverbo.engine.Stage.Enemies;
import es.sergioangelverbo.model.entity.Ground;
import es.sergioangelverbo.model.entity.Mario;
import es.sergioangelverbo.model.entity.Position;
import es.sergioangelverbo.model.entity.QuestionBlock;
import es.sergioangelverbo.model.entity.Size;
import es.sergioangelverbo.model.entity.QuestionBlock.PowerupType;

public class EntityTest extends JFrame {
	
	long elapsed = 0;
	
	private final int updateMs = 10;
	private static final int width = 800;
	private static final int height = 600;
	
	class Painter extends JPanel {

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
		panel.setSize(width, height);
		this.add(panel);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(null);
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
        Timer tim = new Timer(updateMs, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateState();	
			}
		});
        stage = new Stage(1200, new Size(2550, 1200), new Size(width, height), 
        		new Mario(new Position(399, 699)));
        stage.addGround(new Ground(new Position(0, 1120), 1200, 51));
        stage.addGround(new Ground(new Position(1200, 1080), 250, 200));
        stage.addGround(new Ground(new Position(1200+250+400, 1120), 300, 51));
        stage.addBlock(Blocks.WOODEN, 1200+250-48, 1080-47, null);
        stage.addBlock(Blocks.WOODEN, 1200+250-48, 1080-47*2, null);
        stage.addEnemy(Enemies.GOOMBA, 1300, 1000, Direction.LEFT);
        int bp = 920;
        stage.addBlock(Blocks.QUESTION, 200, bp, null);
        stage.addBlock(new QuestionBlock(new Position(248, bp), PowerupType.Coin));
        stage.addBlock(new QuestionBlock(new Position(248+48, bp), PowerupType.Coin));
        stage.addBlock(new QuestionBlock(new Position(248+48*2, bp), PowerupType.Coin));
        stage.addBlock(new QuestionBlock(new Position(248+48*3, bp), PowerupType.Coin));
        stage.addEnemy(Stage.Enemies.GOOMBA, 300, 1120-46, Direction.LEFT);
        stage.addEnemy(Stage.Enemies.GOOMBA, 390, 1120-46, Direction.LEFT);
        stage.addBlock(Blocks.WOODEN,0, 1120-47, null);
        stage.addBlock(Blocks.WOODEN, 600, 1120-47, null);
        stage.addBlock(Blocks.WOODEN, 600, 1120-47*2, null);
        stage.addBlock(Blocks.QUESTION, 850, bp-60, PowerupType.Coin);
        //stage.addBlock(Blocks.WOODEN, 600, 1120-47*2, null);
        tim.start();
	}
	
	private void updateState() {
		elapsed += updateMs; 
		stage.update(updateMs);
		panel.updateUI();
	}


	
	
	
	
}
