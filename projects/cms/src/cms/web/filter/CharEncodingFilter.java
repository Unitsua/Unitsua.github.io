package cms.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "CharEncodingFilter",urlPatterns = "/*")
public class CharEncodingFilter implements Filter {

    public void doFilter (ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding ( "utf-8" );
        resp.setCharacterEncoding ( "utf-8" );
        chain.doFilter ( req, resp );
    }

}
