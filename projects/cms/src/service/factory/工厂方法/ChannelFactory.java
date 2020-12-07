package service.factory.工厂方法;

import service.ChannelService;

public abstract class ChannelFactory {
    public abstract ChannelService createChannelService();
}
