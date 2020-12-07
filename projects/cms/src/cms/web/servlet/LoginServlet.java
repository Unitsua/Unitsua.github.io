package cms.web.servlet;

import cms.web.servlet.base.BaseServlet;
import entity.User;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "LoginServlet",urlPatterns = "/backend/main")
public class LoginServlet extends BaseServlet {
    private LoginService loginService ;
    public void setLoginService(LoginService loginService){
        this.loginService = loginService;
    }

    //比较验证码的方法
    private  boolean isEq(ArrayList<String> chk , String chknum){
        for (int i=0; i< chk.size (); i++){
            String ck = chk.get ( i );//Session的验证码中的第i个字符
            String cn = String.valueOf ( chknum.charAt ( i ) ); //客户端第i个字符串
            if (!cn.equals ( ck )) {
                return false;
            }
        }
        return  true;
    }
    @Override
    public void process (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter ( "user" );
        String password = request.getParameter ( "pwd" );
        //取出验证码
        String chkNumber = request.getParameter ( "chknumber" );
        //取出session的验证码
        ArrayList<String> serchk = (ArrayList <String>) request.getSession ().getAttribute ( "checkcode" );
        if (chkNumber != null && chkNumber != "" && isEq ( serchk,chkNumber )){
            //校验用户名
            User u = loginService.chkUser(user);
            if (u != null){
                if (password != null && password.equals ( u.getPassword () )){
                    //跳转到后台页面
                    //存储令牌
                    request.getSession ().setAttribute ( "user",u );
                    request.getRequestDispatcher ( "/backend/main.jsp" ).forward ( request,response );
                }
                else{
                    //跳转到登陆页面
                    request.setAttribute ( "error","密码或者用户名不正确，请重新输入！" );
                    request.getRequestDispatcher ( "/backend/login.jsp" ).forward ( request,response );
                }
            }
        }else {
            //跳转到登陆页面
            request.setAttribute ( "error","验证码不正确，请重新输入！" );
            request.getRequestDispatcher ( "/backend/login.jsp" ).forward ( request,response );
        }
    }

    public void loginout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //删除令牌属性
        request.getSession ().removeAttribute ( "user" );
        request.getRequestDispatcher ( "/backend/login.jsp" ).forward ( request,response );
    }
}
