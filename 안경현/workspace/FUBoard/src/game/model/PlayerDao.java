package game.model;

public class  PlayerDao{
	private static PlayerDao instance = null;
	private PlayerDao(){}
	public static PlayerDao getInstance(){
		if(instance == null){
			synchronized(PlayerDao.class){
				instance = new PlayerDao();
			}
		}
		return instance;
	}
}