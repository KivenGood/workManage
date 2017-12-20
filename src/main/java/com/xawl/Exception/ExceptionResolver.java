package com.xawl.Exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xawl.Vo.ResultData;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionResolver implements HandlerExceptionResolver {

	@Override
	@ResponseBody
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
		MyException exception=null;
		ResultData resultData =null;
		//异常解析
		if(ex instanceof MyException){
			exception=(MyException)ex;
			resultData = new ResultData(exception.getState(),exception.getMessage());
		}else{
			resultData=new ResultData(24,ex.getMessage());
		}
		//异常转到错误页面

		try {
			response.getWriter().print(JSON.toJSONString(resultData));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	

}
