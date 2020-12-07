package service;

import dao.ChannelDao;
import dao.ChannelDaoImpl;
import entity.Channel;
import tools.DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ChannelServiceImpl extends BaseService implements ChannelService{
    private ChannelDao channelDao;
    public void setChannelDao(ChannelDao channelDao){
        this.channelDao = channelDao;
    }
    /**
     *
     * @return
     */
    public List<Channel> findAllChannel(){
        List<Channel> list = null;
        Connection conn = DB.getConn ();
        try {
            conn.setAutoCommit ( false );
            //dao
           list = channelDao.findAllChannel(conn);
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

    public void addChannel (Channel channel) {
       Connection conn = DB.getConn ();
        try {
            conn.setAutoCommit ( false );
            //dao
            channelDao.addChannel ( channel,conn );
            conn.commit ();
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            DB.close ( conn );
        }
    }

    /**
     * cid删除频道
     * @param cid
     */
    
    public void deleteByCId (int cid) {
        Connection conn = DB.getConn ();
        try {
            conn.setAutoCommit ( false );
            //dao
           channelDao.deleteChannelById (cid,conn );
            conn.commit ();
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            DB.close ( conn );
        }
    }

    public Channel findChannelById (int cid) {
        Channel channel = null;
        Connection connection = DB.getConn ();
        //dao
        channel = channelDao.findChannelById(cid,connection);
        return channel;
    }

    public void updateChannel(Channel channel){
        Connection conn = DB.getConn ();
        try {
            conn.setAutoCommit ( false );
            channelDao.updateChannel ( channel,conn );

            conn.commit ();
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            DB.close (conn);
        }
    }

    public int findChannelCount () {
        int count = 0;
        Connection conn = DB.getConn ();
        try {
            count = channelDao.findChannelCount (conn);
        } finally {
            DB.close ( conn );
        }
        return count;
    }

    public List <Channel> findPageChannel (int offset, int pagesize) {
        List<Channel> channels = null;
        Connection conn = DB.getConn ();
        channels = channelDao.findPageChannel (offset,pagesize,conn);
        return channels;
    }
}
