package service.factory.抽象工厂;

import dao.ChannelDaoImpl;
import service.ChannelService;

public abstract class ProductFactory {
    public abstract ChannelService createChannelService ();
    public abstract ChannelDaoImpl createChannelDao();
}
