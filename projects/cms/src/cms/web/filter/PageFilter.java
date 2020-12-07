package cms.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "PageFilter",urlPatterns = "/backend/*")
public class PageFilter implements Filter {

    public void doFilter (ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        String regx = ".+\\/backend\\/.+List.*";
//        String regx1= ".+\\/backend\\/.+list\\.jsp.*";
        String url = ((HttpServletRequest)req).getRequestURL ().toString ();
//        if (url.matches ( regx ) || url.matches ( regx1 ))
        {
            //在request中存储每页记录数
            req.setAttribute ( "pagesize",3 );
            //取当前页的值
            String of = req.getParameter ( "pager.offset" );
            //如果为空，说明是第一次访问，页码置为0，也就是第一页的第一条记录的索引
            if (of == null || "".equals ( of )){
                req.setAttribute ( "offset",0 );
            }else {
                req.setAttribute ( "offset",Integer.parseInt ( of ) );
            }
        }
        chain.doFilter ( req, resp );
    }

}
