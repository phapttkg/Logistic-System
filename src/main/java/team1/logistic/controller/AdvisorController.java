package team1.logistic.controller;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import team1.logistic.exception.ResourceNotFoundException;



@ControllerAdvice
public class AdvisorController extends ResponseEntityExceptionHandler{
	public static final String DEFAULT_ERROR_VIEW = "error";
	@ExceptionHandler(value = Exception.class)
    public  ModelAndView handleCityNotFoundException(HttpServletRequest req, Exception e) throws Exception {
		if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
      throw e;
		ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", e);
	    mav.addObject("url", req.getRequestURL());
	    mav.setViewName(DEFAULT_ERROR_VIEW);
		System.out.println("advice");
	    return mav;
        
    }
}
