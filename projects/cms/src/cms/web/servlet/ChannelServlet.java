package cms.web.servlet;

import cms.web.servlet.base.BaseServlet;
import entity.Channel;
import service.ChannelService;
import service.ChannelServiceImpl;
import vo.PageVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//http:localhost:8080/channel?method=list
//http:localhost:8080/channel?method=addInput
//http:localhost:8080/channel?method=add
@WebServlet(name = "ChannelServlet",urlPatterns = "/backend/channel")
public class ChannelServlet extends BaseServlet {
    private ChannelService channelService ;
    public void setChannelService(ChannelService channelService){
        this.channelService = channelService;
    }
    /**
     * 默认查询列表
     * @param request 请求
     * @param response  响应
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void process (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建PageVO
        PageVo<Channel> vo = new PageVo <> ();
        vo.setOffset ( (Integer) request.getAttribute ( "offset") );
        vo.setPagesize ( (Integer) request.getAttribute ( "pagesize" ) );
        //查询频道总记录数
        int count = channelService.findChannelCount();
        //把总记录数保存起来
        vo.setCount ( count );
        //查询所有频道
        List<Channel> channels = channelService.findPageChannel(vo.getOffset (),vo.getPagesize ());//返回所有频道
        //查询所有列表放入request中
        vo.setDatas ( channels );
        request.setAttribute ( "vo",vo );
        //转发到频道列表页
        request.getRequestDispatcher ( "/backend/channel/list.jsp" ).forward ( request,response );
    }

    public void addInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher ( "/backend/channel/addInput.jsp" ).forward ( request,response );
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cname = request.getParameter ( "cname" );
        String description = request.getParameter ( "description" );

        //创建频道对象
        Channel channel = new Channel ();
        channel.setCname ( cname);
        channel.setDescription ( description );
        //创建ChannelService,调用添加频道的方法
        channelService.addChannel(channel);
        //转到成功页面
        request.getRequestDispatcher ( "/backend/channel/success.jsp" ).forward ( request,response );
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //单行删除
//        int cid = Integer.parseInt ( request.getParameter ( "cid" ) );
//
//        ChannelServiceImpl service = new ChannelServiceImpl ();
//        service.deleteByCId(cid);

        /*多行删除*/
        String[] cids = request.getParameterValues ( "cid" );

        if (cids != null && cids.length > 0){
            ChannelServiceImpl service = new ChannelServiceImpl ();
            for (String cid : cids){
                service.deleteByCId(Integer.parseInt ( cid ));
            }
        }
        //转到成功页面
        request.getRequestDispatcher ( "/backend/channel/success.jsp" ).forward ( request,response );
    }

    public void updateInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cid = Integer.parseInt (request.getParameter ( "cid" ));
        Channel channel = channelService.findChannelById(cid);
        request.setAttribute ( "c",channel );
        request.getRequestDispatcher ( "/backend/channel/updateInput.jsp" ).forward ( request,response );
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //先取出频道id
        int cid = Integer.parseInt ( request.getParameter ( "cid" ) );
        String cname = request.getParameter ( "cname" );
        String description = request.getParameter ( "description" );

        //先查询出，我们要更新的对象
        Channel channel =  channelService.findChannelById ( cid );

        //判断提交频道名称和我们查询到的频道名称是否不相等
        if (cname != null && !cname.equals ( channel.getCname ())){
            //不相等，则更新
            channel.setCname ( cname );
        }
        //判断提交频道描述和我们查询到的频道名称是否不相等
        if (description != null && !description.equals ( channel.getDescription ())){
            //不相等，则更新
            channel.setDescription ( description );
        }
        //更新
        channelService.updateChannel ( channel );

        //转到成功页面
        request.getRequestDispatcher ( "/backend/channel/success.jsp" ).forward ( request,response );
    }
}
