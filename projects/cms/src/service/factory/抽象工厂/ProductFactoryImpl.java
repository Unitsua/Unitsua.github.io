package service.factory.抽象工厂;

import dao.ChannelDao;
import service.ChannelService;
import service.factory.工厂方法.ProductFactory;

public class ProductFactoryImpl extends ProductFactory {
    @Override
    public ChannelService createChannelService () {
        return null;
    }

    @Override
    public ChannelDao createChannelDao () {
        return null;
    }
}
