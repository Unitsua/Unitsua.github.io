package service;


import dao.ArticleDao;
import entity.Article;
import tools.DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ArticleServiceImpl extends BaseService implements ArticleService {

    private ArticleDao articleDao;
    public void setArticleDao(ArticleDao articleDao){
        this.articleDao = articleDao;
    }


    @Override
    public List<Article> findPageArticle (int offset, int pagesize) {
        List<Article> articles = null;
        Connection conn = DB.getConn ();
        articles = articleDao.findPageArticle (offset,pagesize,conn);
        return articles;
    }

    @Override
    public List <Article> findAllArticle () {
        List<Article> list = null;
        Connection conn = DB.getConn ();
        try {
            conn.setAutoCommit ( false );
            //dao
            list = articleDao.findAllArticle (conn);
            conn.commit ();
        } catch (SQLException e) {
            try {
                conn.rollback ();
            } catch (SQLException e1) {
                e1.printStackTrace ();
            }
            e.printStackTrace ();
            DB.close(conn);
        }
        return list;
    }

    @Override
    public void addArticle (Article article) {
        Connection conn = DB.getConn ();
        try {
            conn.setAutoCommit ( false );
            articleDao.addArticle ( article,conn );
            conn.commit ();
        } catch (SQLException e) {
            e.printStackTrace ();
        } try {
            conn.rollback ();
        } catch (SQLException e1) {
            e1.printStackTrace ();
        } finally {
            DB.close ( conn );
        }
    }

    @Override
    public void deleteByAId (int aid) {
        Connection conn = DB.getConn ();
        try {
            conn.setAutoCommit ( false );
            //dao
            articleDao.deleteArticleById ( aid,conn );
            conn.commit ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }try {
            conn.rollback ();
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            DB.close ( conn );
        }
    }

    @Override
    public Article findArticleById (int aid) {
        Article article = null;
        Connection conn = DB.getConn ();
        //dao
        article = articleDao.findArticleById ( aid,conn );
        return article;
    }

    @Override
    public void updateArticle (Article article) {
        Connection conn = DB.getConn ();
        try {
            conn.setAutoCommit ( false );
            articleDao.updateArticle ( article,conn );
            conn.commit ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        try {
            conn.rollback ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }finally {
            DB.close (conn);
        }
    }

    @Override
    public int findArticleCount () {
        int count = 0;
        Connection conn = DB.getConn ();
        try {
            count = articleDao.findArticleCount (conn);
        } finally {
            DB.close ( conn );
        }
        return count;
    }

    @Override
    public List <Article> findArticleByChannelId (int cid, int num) {
        List<Article> as = null;
        Connection conn = DB.getConn ();

        try {
            as = articleDao.findArticleByChannelId(cid,num,conn);
        } catch (Exception e) {
            e.printStackTrace ();
        }finally {
            DB.close ( conn );
        }
        return as;
    }

}
