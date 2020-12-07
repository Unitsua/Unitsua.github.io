package service.factory.工厂方法;

import service.ChannelService;
import service.ChannelServiceImpl;

public class ChannelFactoryForJdbc extends ChannelFactory {
    @Override
    public ChannelService createChannelService () {
        return new ChannelServiceImpl ();
    }
}
