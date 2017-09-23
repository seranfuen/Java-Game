package es.sergioangelverbo.engine;

public class Score {
	private int coins;
	private int score;
	private int enemiesKilled;
	private int lives;
	
	public Score(int lives) {
		this.lives = lives;
	}
	
	public int getCoins() {
		return coins;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getEnemiesKilled() {
		return enemiesKilled;
	}
	
	public int getRemainingLives() {
		return lives;
	}
	
	public void increaseCoins() {
		coins++;
	}
	
	public void increaseLives() {
		lives++;
	}
	
	public void decreaseLives() {
		lives--;
	}
	
	public void increaseScore(int increaseValue) {
		score += increaseValue;
	}
}
