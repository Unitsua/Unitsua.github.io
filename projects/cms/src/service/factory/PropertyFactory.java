package service.factory;

import java.io.InputStream;
import java.util.Properties;

public class PropertyFactory {
    /**
     * 这是创建Bean的工厂方法
     *  key  属性是文件中键的值
     */
    public Object getBean(String key){
        Object obj = null;
        try {
            //获取属性配置文件的输入流
            InputStream input = this.getClass ().getClassLoader ().getResourceAsStream ( "factory.properties" );
            //创建属性集合对象
            Properties prop = new Properties (  );
            //根据属性配置文件的输入把属性中的键值加载进属性集合对象中
            prop.load ( input );
            //根据键取出属性中文件中的值
            String servicename = prop.getProperty ( key );
            //根据配置的类获得反射类
            Class c = Class.forName ( servicename );
            //用反射类的默认构造实例化配置的对象
            obj = c.newInstance ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return obj;
    }

}
