package snapshot.behavior.authority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class SessionAuthority implements Authority {
    
    @Override
    public boolean checkLogin(HttpServletRequest request) {

        HttpSession session = request.getSession();

        return false;
    }
}
