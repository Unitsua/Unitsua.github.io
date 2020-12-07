package cms.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

@WebServlet(name = "CheckCodeServlet",urlPatterns = "/backend/images/checkcodes.png")
public class CheckCodeServlet extends HttpServlet {
    //生成随机数的函数
    private int generRandomInt(int start,int range){
        return new Random ().nextInt (range - start + start);
    }

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //随机生成验证码
        String str = "abcdefghlmnopqrstuvwxyz0123456789";
        //获取这个串的长度
        int len = str.length ();
        //创建一个字符缓存，存验证码
        ArrayList<String> cs = new ArrayList <> (  );

        for (int i=0; i<4; i++){
            //生成一个0-len 之间的随机数
            int index = generRandomInt ( 0,len );
            //获取随机字符
            char c = str.charAt ( index );
            //追加进字符缓存
            cs.add ( String.valueOf ( c ) );
        }

        //存入Session
        request.getSession ().setAttribute ( "checkcode",cs );

        //生成验证码图片
        //生成图片缓存
        BufferedImage bufferedImage = new BufferedImage ( 48,20,BufferedImage.TYPE_INT_RGB );
        //拿到画笔
        Graphics2D g2 = (Graphics2D) bufferedImage.getGraphics ();
        //设置画笔前景
        g2.setColor ( Color.WHITE );
        //因为缓存默认背景是黑色，该图片背景为白色
        g2.fillRect ( 0,0,48,20 );
        //字符的出事偏移量
        int offset = 5;
        //每个字符占的宽度
        int width = 11;
        //生成位置
        int x = offset;
        int y = 18;
        //在白色背景上面画出验证码
        for (int i=0; i<4; i++){
            //先要把前景改为其他颜色
            //随机生成红绿蓝透明度四个值，每个在0-255之间
            int r = generRandomInt ( 0,50 );
            int g = generRandomInt ( 0,50);
            int b = generRandomInt ( 0,50 );
            int a = 255;
            Color c = new Color (r,g,b,a  );
            //把前景色设置成随机数的色彩
            g2.setColor ( c );
            //画字符
            g2.drawString ( cs.get ( i ),x + (width * i),y );
        }
        //
        response.setContentType ( "images/png" );
        //生成图片并写到客户端
        ImageIO.write ( bufferedImage,"png",response.getOutputStream () );
    }
}

