package service;

import entity.Channel;
import java.util.List;

public interface ChannelService {
    /**
     *
     * @return
     */
    public List<Channel> findAllChannel();

    public void addChannel (Channel channel);

    /**
     * cid删除频道
     * @param cid
     */

    public void deleteByCId (int cid);

    //input
    public Channel findChannelById (int cid);

    public void updateChannel(Channel channel);

    public int findChannelCount ();

    public List <Channel> findPageChannel (int offset, int pagesize);
}
