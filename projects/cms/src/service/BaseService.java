package service;

import service.factory.PropertyFactory;
import tools.SystemContext;

import java.lang.reflect.Method;

public abstract class BaseService {
    public BaseService(){
            PropertyFactory factory = SystemContext.getFactory();
            //得到Servlet（子类）的反射类
            Class c = this.getClass ();
            //取出当前Servlet（子类）中的方法
            Method[] ms = c.getMethods ();
            try {
                for (Method m : ms){    //迭代所有方法，找出set打头的方法
                    String methodName = m.getName ();   //取出当前迭代方法的名字
                    //判断是否set开头
                    if (methodName.startsWith ( "set" )){
                        //找出后截出set后面的字符串并把第一个字母改为小写
                        String key = methodName.substring ( 3,4 ).toLowerCase ()+methodName.substring ( 4 );
                        //根据约定set方法对应的属性名和属性文件中的key名一致，通过工厂得到产品
                        Object obj = factory.getBean ( key );
                        if (obj != null){
                            //判断是否确实得到产品实例
                            m.invoke ( this,obj );//通过方法反射对象调用当前的set方法的实例
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace ();
            }
    }
}
