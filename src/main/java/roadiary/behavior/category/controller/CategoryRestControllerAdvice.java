package roadiary.behavior.category.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import roadiary.behavior.common.ErrorResult;
import roadiary.behavior.member.service.authority.SessionKeys;

@Slf4j
@RestControllerAdvice("roadiary.behavior.category.controller")
public class CategoryRestControllerAdvice {
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalStateException(IllegalArgumentException e, HttpServletRequest request) {
        long userId = Long.valueOf(request.getSession().getAttribute(SessionKeys.loginUserId).toString());
        log.error("user={}", userId, e);

        return new ErrorResult(e.getMessage());
    } 
    
}
