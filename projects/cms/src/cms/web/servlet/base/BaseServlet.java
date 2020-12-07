package cms.web.servlet.base;

import service.factory.PropertyFactory;
import tools.SystemContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends AbstractBaseServlet {

    @Override
    public void init () {
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

    @Override
    protected void execute (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //取出想调用的方法
        String methodName = req.getParameter ( "method" );
        try {
            //如果方法名为空，就默认调用process方法
            if (methodName == null || "".equals ( methodName )){
                process(req,resp);
            }else {
                //执行方法名为methodName变量值的方法
                Class c = this.getClass (); //得到子类的反射类
                Method[] methods = c.getMethods ();//得到gai反射类所有方法
                for (Method m : methods){
                    if (methodName.equals ( m.getName () )){
                        m.invoke ( this,req,resp );     //通过方法反射对象调用当前的set方法的实例
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    public abstract void process (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

}
