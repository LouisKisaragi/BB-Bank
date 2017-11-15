package freeboard.model;

public class BoardDao {
	private static BoardDao instance = null;
	private BoardDao(){}
	public static BoardDao getInstance(){
		if(instance == null){
			synchronized(BoardDao.class){
				instance = new BoardDao();
			}
		}
		return instance;
	}
	//이제부터 여기에 게시판에서 필요한 작업 기능들을 메서도르 추가하게 된다.
}
