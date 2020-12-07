package cms.web.servlet;

import cms.web.servlet.base.BaseServlet;
import entity.Article;
import entity.Channel;
import service.ArticleService;
import service.ArticleServiceImpl;
import service.ChannelService;
import vo.PageVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ArticleServlet",urlPatterns = "/backend/article")
public class ArticleServlet extends BaseServlet {
    private ChannelService channelService = null;
    private ArticleService articleService = null ;
    public void setChannelService(ChannelService channelService){
        this.channelService = channelService;
    }
    public void setArticleService(ArticleService articleService){
        this.articleService = articleService;
    }
    @Override
    public void process (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建PageVO
        PageVo<Article> vo = new PageVo <> ();
        vo.setOffset ( (Integer) req.getAttribute ( "offset") );
        vo.setPagesize ( (Integer) req.getAttribute ( "pagesize" ) );

        //查询频道总记录数
        int count = articleService.findArticleCount();
        //把总记录数保存起来
        vo.setCount ( count );
        //查询所有频道
        List<Article> datas = articleService.findPageArticle (vo.getOffset (),vo.getPagesize ());//返回所有频道
        //查询所有列表放入request中
        vo.setDatas ( datas );
        req.setAttribute ( "vo",vo );
        //转发到频道列表页
        req.getRequestDispatcher ( "/backend/article/list.jsp" ).forward ( req,resp );
    }

    public void addInput (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 查询出所有频道，供界面的频道列表使用
         */
        List<Channel> channels = channelService.findAllChannel ();
        req.setAttribute ( "c",channels );
        req.getRequestDispatcher ( "/backend/article/addInput.jsp" ).forward ( req,resp );
    }

    public void add (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Article article = new Article ();
        article.setTitle ( req.getParameter ( "title" ) );
        article.setContent ( req.getParameter ( "content" ) );
        article.setSource ( req.getParameter ( "source" ) );
        article.setCreateTime ( new Date() );
        article.setAuthor ( req.getParameter ( "author" ) );
        article.setCid ( Integer.parseInt ( req.getParameter ( "cid" ) ) );
        articleService.addArticle ( article );
        //转到成功页面
        req.getRequestDispatcher ( "/backend/article/success.jsp" ).forward ( req,resp );
    }

    public void updateInput (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 查询出所有频道，供界面的频道列表使用
         */
        List<Channel> channels = channelService.findAllChannel ();
        req.setAttribute ( "c",channels );
        //先取出文章id
        int aid = Integer.parseInt (req.getParameter ( "aid" ));
        Article article = articleService.findArticleById(aid);
        //把查询到的频道对象放入request
        req.setAttribute ( "a",article );
        req.getRequestDispatcher ( "/backend/article/updateInput.jsp" ).forward ( req,resp );
    }

    public void update (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Article article = new Article();
        article.setAid(Integer.parseInt(req.getParameter("aid")));
        article.setTitle(req.getParameter("title"));
        article.setContent(req.getParameter("content"));
        article.setSource(req.getParameter("source"));
        article.setCreateTime(new Date());
        article.setAuthor(req.getParameter("author"));
        article.setCid(Integer.parseInt(req.getParameter("cid")));

        //更新
        articleService.updateArticle(article);
        //转到成功页面
        req.getRequestDispatcher ( "/backend/article/success.jsp" ).forward ( req,resp );

    }

    public void delete (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*多行删除*/
        String[] aids = req.getParameterValues ( "aid" );
        //创建ArticleService对象，调用根据文章id删除频道的方法
        if (aids != null && aids.length > 0){
            for (String aid : aids){
                articleService.deleteByAId(Integer.parseInt ( aid ));
            }
        }
        //转到成功页面
        req.getRequestDispatcher ( "/backend/article/success.jsp" ).forward ( req,resp );
    }

}
