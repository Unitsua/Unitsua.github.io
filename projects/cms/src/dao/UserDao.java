package dao;

import entity.User;
import tools.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public User findUserByUserName (String user, Connection conn) {
        User u = null;
        String sql = "select * from t_user where username=?";
        PreparedStatement prst = null;
        ResultSet rs = null;
        try {
            prst = conn.prepareStatement ( sql );
            prst.setString ( 1,user );
            rs = prst.executeQuery ();   //的到结果集
            if (rs.next ()){
                u = new User ();
                u.setUid ( rs.getInt ( "uid" ) );
                u.setUsername ( rs.getString ( "username" ) );
                u.setPassword ( rs.getString ( "password" ) );
                u.setCreateTime ( rs.getTime ( "createTime" ) );
                u.setUname ( rs.getString ( "uname" ) );
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        DB.close ( rs );
        DB.close ( prst );
        return u;
    }
}
