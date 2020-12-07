package dao;

import cms.web.servlet.base.BaseServlet;
import entity.Channel;
import tools.DB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ChannelDaoImpl implements ChannelDao {
    /**
     * 查询频道列表
     * @return
     */
    @Override
    public List<Channel> findAllChannel (Connection conn) {
        List<Channel> list = null;
        String sql = "select * from t_channel";
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            prst = conn.prepareStatement ( sql );
            rs = prst.executeQuery ();
            list =  DB.resultSet2List(rs);
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            DB.close ( rs );
            DB.close ( prst );
        }
        return list;
    }

    @Override
    public void addChannel (Channel channel, Connection conn) {
        String sql = "insert into t_channel(cname,description) value(?,?)";
        PreparedStatement prst = null;
        try {
            prst = conn.prepareStatement ( sql );
            prst.setString ( 1,channel.getCname () );
            prst.setString ( 2,channel.getDescription () );
            prst.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            DB.close ( prst );
        }

    }

    @Override
    public void deleteChannelById (int cid, Connection conn) {
        String sql = "delete from t_channel where cid = ?";
        PreparedStatement prst = null;
        try {
            prst = conn.prepareStatement ( sql );
            prst.setInt ( 1,cid );
            prst.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            DB.close ( prst );
        }
    }

    @Override
    public Channel findChannelById (int cid, Connection connection) {
        String sql = "select * from t_channel where cid=?";
        Channel channel = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            prst = connection.prepareStatement ( sql );
            prst.setInt ( 1,cid );
            rs = prst.executeQuery ();
            if (rs.next ()){
                channel = new Channel ();
                channel.setCid ( rs.getInt ( "cid" ) );
                channel.setCname ( rs.getString ( "cname" ) );
                channel.setDescription ( rs.getString ( "description" ) );
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            DB.close ( prst );
        }
        return channel;
    }

    @Override
    public void updateChannel (Channel channel, Connection conn) {
        String sql = "update t_channel set cname = ?,description = ? where cid = ?";

        PreparedStatement prst = null;
        try {
            prst = conn.prepareStatement ( sql );
            prst.setString ( 1,channel.getCname () );
            prst.setString ( 2,channel.getDescription () );
            prst.setInt ( 3,channel.getCid () );
            prst.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            DB.close ( prst );
        }
    }

    @Override
    public int findChannelCount (Connection conn) {
        int count = 0;
        String sql = "select count(*) from t_channel";
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            prst = conn.prepareStatement ( sql );
            rs = prst.executeQuery ();
            if (rs.next ()){
                count = rs.getInt ( 1 );
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            DB.close ( prst );
        }
        return count;
    }

    @Override
    public List<Channel> findPageChannel (int offset, int pagesize, Connection conn) {
        List<Channel> list = null;
        String sql = "select * from t_channel limit ? , ?";
        ResultSet rs = null;
        PreparedStatement prst = null;
        try {
            prst = conn.prepareStatement ( sql );
            prst.setInt ( 1,offset );
            prst.setInt ( 2,pagesize );
            rs = prst.executeQuery ();
            while (rs.next ()){
                if (list == null){
                    list = new ArrayList <> (  );
                }
                Channel channel = new Channel ();
                channel.setCid ( rs.getInt ( "cid" ) );
                channel.setCname ( rs.getString ( "cname" ) );
                channel.setDescription ( rs.getString ( "description" ) );
                list.add ( channel );
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        } finally {
            DB.close ( prst );
        }
        return  list;
    }

}
