package com.xawl.Filteres;


import com.alibaba.fastjson.JSON;
import com.xawl.Vo.ResultData;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class rootFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Integer uid = (Integer) request.getSession().getAttribute("uid");
        Integer type = (Integer) request.getSession().getAttribute("type");
        if(uid!=null&&type>=3){
            filterChain.doFilter(request, servletResponse);
        }else{
            servletResponse.getWriter().print(JSON.toJSONString(new ResultData(ResultData.I_NoAllow)));
        }
    }

    @Override
    public void destroy() {

    }
}
