package tools;

import service.factory.PropertyFactory;

/**
 * 饿汉式：在程序启动或单间模式类被加载的时候，单间模式实例就已经被创建
 */
public class SystemContext {
    private static PropertyFactory factory = new PropertyFactory ();
    public static PropertyFactory getFactory () {
        return factory;
    }
    public static  void  setFactory(PropertyFactory f){
        factory = f;
    }
}
