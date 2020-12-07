package dao;

import cms.B;
import entity.Article;
import tools.DB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDaoImpl implements ArticleDao {

    @Override
    public List<Article> findAllArticle (Connection conn) {
        String sql = "select * from t_article";
        List<Article> list = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            prst = conn.prepareStatement ( sql );
            rs = prst.executeQuery ();
            while (rs.next ()){
                if (list == null){
                    list = new ArrayList <> (  );
                }
                Article article = new Article ();
                article.setAid ( rs.getInt ( "aid" ) );
                article.setAuthor ( rs.getString ( "author" ) );
                article.setCid ( rs.getInt ( "cid" ) );
                article.setContent ( rs.getString ( "content" ));
                article.setCreateTime ( rs.getDate ( "createTime" ) );
                article.setSource ( rs.getString ( "source" ) );
                article.setTitle ( rs.getString ( "title" ) );
                list.add ( article );
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }finally {
            DB.close ( prst );
        }
        return list;
    }

    @Override
    public void addArticle (Article article, Connection conn) {
        String sql = "insert into t_article(title,content,source,author,createTime,cid)" +
                "values(?,?,?,?,?,?)";
        PreparedStatement prst = null;
        try {
            prst = conn.prepareStatement ( sql );
            prst.setString ( 1,article.getTitle () );
            prst.setString ( 2,article.getContent () );
            prst.setString ( 3,article.getSource () );
            prst.setString ( 4,article.getAuthor () );
            prst.setDate ( 5, new Date ( article.getCreateTime ().getTime ()));
            prst.setInt ( 6,article.getCid () );
            prst.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }finally {
            DB.close ( prst );
        }
    }

    @Override
    public void deleteArticleById (int aid, Connection conn) {
        String sql = "select * from t_article where aid = ?";
        PreparedStatement prst = null;
        try {
            prst = conn.prepareStatement ( sql );
            prst.setInt ( 1,aid );
        } catch (SQLException e) {
            e.printStackTrace ();
        }finally {
            DB.close ( prst );
        }
    }

    @Override
    public Article findArticleById (int aid, Connection conn) {
        String sql = "select * from t_article";
        Article article =null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            prst = conn.prepareStatement ( sql );
//            prst.setInt ( 1,aid );
            rs = prst.executeQuery ();
            if (rs.next ()){
                article = new Article ();
                article.setAid ( rs.getInt ( "aid" ) );
                article.setAuthor ( rs.getString ( "author" ) );
                article.setCid ( rs.getInt ( "cid" ) );
                article.setContent ( rs.getString ( "content" ));
                article.setCreateTime ( rs.getDate ( "createTime" ) );
                article.setSource ( rs.getString ( "source" ) );
                article.setTitle ( rs.getString ( "title" ) );
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }finally {
            DB.close ( prst );
        }
        return article;
    }

    @Override
    public void updateArticle (Article article, Connection conn) {
        String sql = "update t_article set title = ? , content = ?,source = ?," +
                "author = ?,createTime = ?, cid = ? where aid = ?";
        PreparedStatement prst = null;
        try {
            prst = conn.prepareStatement ( sql );
            prst.setString ( 1,article.getTitle () );
            prst.setString ( 2,article.getContent () );
            prst.setString ( 3,article.getSource () );
            prst.setString ( 4,article.getAuthor () );
            prst.setDate ( 5, new Date ( article.getCreateTime ().getTime ()) );
            prst.setInt ( 6,article.getCid () );
            prst.setInt ( 7,article.getAid () );
            prst.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }finally {
            DB.close ( prst );
        }
    }

    @Override
    public int findArticleCount (Connection conn) {
        int count = 0;
        String sql = "select count(*) from t_article";
        ResultSet rs = null;
        PreparedStatement prst = null;
        try {
            prst = conn.prepareStatement ( sql );
            rs = prst.executeQuery ();
            if (rs.next ()){
                count = rs.getInt ( 1 );
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }finally {
            DB.close ( prst );
            DB.close ( rs );
        }
        return count;
    }

    @Override
    public List <Article> findPageArticle (int offset, int pagesize, Connection conn) {
        String sql = "select * from t_article limit ?,?";
        List<Article> list = null;
        ResultSet rs = null;
        PreparedStatement prst = null;
        try {
            prst = conn.prepareStatement ( sql );
            prst.setInt ( 1,offset );
            prst.setInt ( 2,pagesize );
            rs = prst.executeQuery ();
            while (rs.next ()) {
                if (list == null) {
                    list = new ArrayList <> ();
                }
                Article article = new Article ();
                article.setAid ( rs.getInt ( "aid" ) );
                article.setAuthor ( rs.getString ( "author" ) );
                article.setCid ( rs.getInt ( "cid" ) );
                article.setContent ( rs.getString ( "content" ));
                article.setCreateTime ( rs.getDate ( "createTime" ) );
                article.setSource ( rs.getString ( "source" ) );
                article.setTitle ( rs.getString ( "title" ) );
                list.add ( article );
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }finally {
            DB.close ( prst );
            DB.close ( rs );
        }
        return list;
    }

    @Override
    public List <Article> findArticleByChannelId (int cid, int num, Connection conn) {
        List<Article> as = null;
        String sql = "select *from t_article where cid = ? limit ?,?";
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            prst = conn.prepareStatement ( sql );
            prst.setInt ( 1,cid );
            prst.setInt ( 2,0 );
            prst.setInt ( 3,num );
            rs = prst.executeQuery ();
            while (rs.next ()){
                if (as == null){
                    as = new ArrayList <> (  );
                }
                Article article = new Article ();
                article.setAid ( rs.getInt ( "aid" ) );
                article.setAuthor ( rs.getString ( "author" ) );
                article.setCid ( rs.getInt ( "cid" ) );
                article.setContent ( rs.getString ( "content" ));
                article.setCreateTime ( rs.getDate ( "createTime" ) );
                article.setSource ( rs.getString ( "source" ) );
                article.setTitle ( rs.getString ( "title" ) );
                as.add ( article );
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }finally {
            DB.close ( prst );
        }
        return as;
    }
}
