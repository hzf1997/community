//package com.hzf.community.interceptor;
//
//import com.hzf.community.dao.UserDao;
//import com.hzf.community.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//@Component
//public class LoginInterceptor implements HandlerInterceptor {
//    @Autowired
//    private UserDao userDao;
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Cookie[] cookies = request.getCookies();
//        HttpSession session = request.getSession();
//        for (Cookie cookie : cookies) {
//            if ("token".equals(cookie.getName())){
//                String token = cookie.getValue();
//                System.out.println(token);
//                System.out.println(userDao);
//                User byToken = userDao.findByToken(token);
//                if (byToken!=null){
//                    session.setAttribute("user",byToken);
//                }
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//
//    }
//}
