package cms.web.filter;

import entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "TodenFilter"/*,urlPatterns = "/backend/*"*/)
public class TokenFilter implements Filter {

    public void doFilter (ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //把ServletRequest强制转换成HttpServletRequset
        HttpServletRequest request = (HttpServletRequest) req;
        //取令牌
        User u = (User) request.getSession ().getAttribute ( "user" );
        //创建过滤规则字符串
        String regx = "http:\\/\\/.+:8080\\/backend\\/login\\.jsp.*";
        //排除.jpg,.gif,.png图片
        String regx1 = ".+(\\.jpg|\\.gif|\\.png|\\.css|\\.js)\\.*";
        String regx2 = "http:\\/\\/.+:8080\\/backend\\/login.*";
        String url = request.getRequestURL ().toString ();
        if (u != null || url.matches ( regx )
                    || url.matches ( regx1 )
                || url.matches ( regx2 )){
            chain.doFilter ( request, resp );   //向实际请求的Servlet转
        } else{
            //否则转到登录页面
            request.setAttribute ( "error","请先登录" );
            request.getRequestDispatcher ( "/backend/login.jsp" ).forward ( request,resp );
        }
    }

}
