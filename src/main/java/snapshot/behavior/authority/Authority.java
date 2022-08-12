package snapshot.behavior.authority;

import javax.servlet.http.HttpServletRequest;

public interface Authority {
    
    public boolean checkLogin(HttpServletRequest request);

}
