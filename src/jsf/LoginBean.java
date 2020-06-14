package jsf;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import dao.Impl.LoginDAO;
import util.SessionUtils;

public class LoginBean {
	
	private String pwd;
	private String msg;
	private String user;
	private LoginDAO loginDAO = new LoginDAO();
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	public String autentificarUsuario() throws Exception
	{
		boolean valid = loginDAO.validar(user, pwd);
		if (valid)
		{
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		    externalContext.redirect("/AerolineaPeru/Aeropuerto.xhtml");
		    //Thread.sleep(2000);
			return "admin";
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Usuario o contraseña incorrecto",
							"Intenta nuevamente con credenciales correctos"));
			return "login";
		}
	}

	//Cerrar sesion
	public String logout()
	{
		//HttpSession session = SessionUtils.getSession();
		//session.invalidate();
		//return "login";
		
		HttpSession session = SessionUtils.getSession();
		user = "";
		pwd = "";
		session.invalidate();
		return "login";
	}
	
	
	
	
    
}
