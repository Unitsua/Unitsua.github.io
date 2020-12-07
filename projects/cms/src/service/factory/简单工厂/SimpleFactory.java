package service.factory.简单工厂;

import service.ChannelService;
import service.ChannelServiceForOracle;
import service.ChannelServiceImpl;

public class SimpleFactory {
    public static final int CHANNEL_SERVICE_FOR_ORACLE = 1;
    public static final int CHANNEL_SERVICE_FOR_MYSQL = 2;
    public ChannelService createChannelService(int type){
        if (type == CHANNEL_SERVICE_FOR_ORACLE){
            return new ChannelServiceForOracle ();
        }
        if (type == CHANNEL_SERVICE_FOR_MYSQL){
            return (ChannelService) new ChannelServiceImpl ();
        }
        return null;
    }
}
