package entity;


import java.util.Date;

public class Article {
    private int aid;
    private String title;
    private String content;
    private String source;
    private Date createTime;
    private String author;
    private int cid;

    public int getAid () {
        return aid;
    }

    public void setAid (int aid) {
        this.aid = aid;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getContent () {
        return content;
    }

    public void setContent (String content) {
        this.content = content;
    }

    public String getSource () {
        return source;
    }

    public void setSource (String source) {
        this.source = source;
    }

    public Date getCreateTime () {
        return createTime;
    }

    public void setCreateTime (Date createTime) {
        this.createTime = createTime;
    }

    public String getAuthor () {
        return author;
    }

    public void setAuthor (String author) {
        this.author = author;
    }

    public int getCid () {
        return cid;
    }

    public void setCid (int cid) {
        this.cid = cid;
    }
}
