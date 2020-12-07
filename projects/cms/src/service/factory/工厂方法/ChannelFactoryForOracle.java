package service.factory.工厂方法;

import service.ChannelService;
import service.ChannelServiceForOracle;

public class ChannelFactoryForOracle extends ChannelFactory {
    @Override
    public ChannelService createChannelService () {
        return new ChannelServiceForOracle ();
    }
}
