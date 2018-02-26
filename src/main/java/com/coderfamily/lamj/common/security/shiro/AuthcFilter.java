package com.coderfamily.lamj.common.security.shiro;

import com.coderfamily.lamj.common.data.ResponseCode;
import com.coderfamily.lamj.common.data.Result;
import com.coderfamily.lamj.common.util.NullUtil;
import com.coderfamily.lamj.common.util.TokenUtil;
import com.coderfamily.lamj.dao.UserMapper;
import com.coderfamily.lamj.model.UserEntity;
import com.google.gson.Gson;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 是否已经登陆以及token是否过期鉴权
 *
 * @author ZhangDL
 * @date 2018/1/26 13:52
 */
public class AuthcFilter extends PassThruAuthenticationFilter {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        boolean isSuccess = false;
        if (isLoginRequest(request, response)) {
            isSuccess = true;
        } else {
            saveRequest(request);
            HttpServletRequest hsr = (HttpServletRequest) request;
            String authc = hsr.getHeader("Authorization");
            if (NullUtil.isNotNull(authc)) {
                String userName = TokenUtil.getUsername(authc);
                UserEntity user = userMapper.selectUserByUserAccount(userName);
                Result result = null;
                if (!TokenUtil.verify(authc, userName, user.getPassword())) {
                    result = Result.init(ResponseCode.unauthenticated.getCode(), ResponseCode.unauthenticated.getMsg());
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().append(new Gson().toJson(result));
                    response.getWriter().flush();
                    response.getWriter().close();
                    isSuccess = false;
                } else {
                    isSuccess = true;
                }
            }
        }
        return isSuccess;
    }
}
