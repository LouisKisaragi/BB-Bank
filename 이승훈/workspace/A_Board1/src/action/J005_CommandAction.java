package action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//여기서 만든 interface가 나중에 온갖 클래스들이 상속받게 되는 interface다.
public interface J005_CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable;

}