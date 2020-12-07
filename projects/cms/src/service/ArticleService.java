package service;

import entity.Article;
import entity.Channel;

import java.util.List;

public interface ArticleService {
    public List<Article> findPageArticle (int offset,int pagesize);

    /**
     * 查询所有文章
     * @return
     */
    public List<Article> findAllArticle();

    public void addArticle (Article article);

    /**
     * 根据文章aid删除频道
     * @param aid
     */

    public void deleteByAId (int aid);

    //input
    public Article findArticleById (int aid);

    public void updateArticle(Article article);

    public int findArticleCount ();

    List<Article> findArticleByChannelId (int cid, int num);
}
