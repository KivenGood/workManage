package com.xawl.Filteres;
/**
 * Created by doter on 2017/7/26.
 */

import com.alibaba.fastjson.JSON;
import com.xawl.Vo.ResultData;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: doter
 * \* Date: 2017/7/26
 * \* Time: 16:04
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class UserFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Integer uid = (Integer) request.getSession().getAttribute("uid");
        Integer type = (Integer) request.getSession().getAttribute("type");
        if(uid!=null&&type>=1){
            filterChain.doFilter(request, servletResponse);
        }else{
            servletResponse.getWriter().print(JSON.toJSONString(new ResultData(ResultData.I_NoLogin)));
        }
    }

    @Override
    public void destroy() {

    }
}
