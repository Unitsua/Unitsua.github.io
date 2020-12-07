package cms.web.servlet;

import cms.web.servlet.base.BaseServlet;
import entity.Article;
import entity.Channel;
import service.ArticleService;
import service.ChannelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "IndexServlet",urlPatterns = "/index.html")
public class IndexServlet extends BaseServlet {

    private ChannelService channelService = null;
    public void setChannelService (ChannelService channelService) {
        this.channelService = channelService;
    }

    private ArticleService articleService = null;
    public void setArticleService(ArticleService articleService){
        this.articleService = articleService;
    }
    @Override
    public void process (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher ( "/index_back.jsp" ).forward ( req,resp );
    }

    /**
     *查询所有频道，存入request，转发channelList.jsp
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void channelList (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Channel> cs = channelService.findAllChannel ();
        req.setAttribute ( "cs",cs );
        req.getRequestDispatcher ( "/index/channelList.jsp" ).include ( req,resp );
    }

    /**
     * 查出两个头条文章，存入request，转发headline.jsp
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void headline (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Article> as = articleService.findPageArticle ( 1,4 );
        req.setAttribute ( "as",as );
        req.getRequestDispatcher ( "/index/headline.jsp" ).include ( req,resp );
    }

    /**
     * 查出四个频道及每个频道下前14条文章，存入request，转发channelArticle.jsp
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void channelArticle (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Channel,List<Article>> cas = new HashMap <> (  );
        List<Channel> cs = channelService.findPageChannel ( 0,2 );
        for (Channel c : cs){
            List<Article> as = articleService.findArticleByChannelId(c.getCid (),14);
            cas.put ( c,as );
        }
        req.setAttribute ( "cas",cas );
        req.getRequestDispatcher ( "/index/channelArticle.jsp" ).include ( req,resp );
    }
}
