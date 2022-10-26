package roadiary.behavior.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import roadiary.behavior.member.authority.Authority;
import roadiary.behavior.member.authority.SessionKeys;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final Authority authority;

    public LoginInterceptor(Authority authority) {
        this.authority = authority;
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        HttpSession session = request.getSession();
        
        
        if (!authority.isItLoginStatus(request)) {
            session.setAttribute(SessionKeys.afterLoginPage, request.getRequestURI());
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, 
            Exception ex) throws Exception {}

}
