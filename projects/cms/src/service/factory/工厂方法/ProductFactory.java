package service.factory.工厂方法;

import dao.ChannelDao;
import service.ChannelService;

public abstract class ProductFactory {
    public abstract ChannelService createChannelService();
    public abstract ChannelDao createChannelDao();
}
