package dao;

import entity.Channel;

import java.sql.Connection;
import java.util.List;

public interface ChannelDao {
    List<Channel> findAllChannel (Connection conn);

    void addChannel (Channel channel, Connection conn);

    void deleteChannelById (int cid, Connection conn);

    Channel findChannelById (int cid, Connection connection);

    void updateChannel (Channel channel, Connection conn);

    int findChannelCount (Connection conn);

    List<Channel> findPageChannel (int offset, int pagesize, Connection conn);
}
