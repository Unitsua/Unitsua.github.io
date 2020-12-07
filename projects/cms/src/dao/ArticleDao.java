package dao;

import entity.Article;

import java.sql.Connection;
import java.util.List;

public interface ArticleDao {
    List<Article> findAllArticle (Connection conn);

    void addArticle (Article article, Connection conn);

    void deleteArticleById (int aid, Connection conn);

    Article findArticleById (int aid, Connection conn);

    void updateArticle (Article article, Connection conn);

    int findArticleCount (Connection conn);

    List<Article> findPageArticle (int offset, int pagesize, Connection conn);

    List<Article> findArticleByChannelId (int cid, int num, Connection conn);
}
