package com.sparks.editable_profile.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * The class is implemented to globally configure exception handling at the controller end
 * Created by Nandak on Dec, 2019
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String MESSAGE = "message";

    @ExceptionHandler(RecordNotFoundException.class)
    public ModelAndView handleRecordNotFoundException(HttpServletRequest request, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(MESSAGE, ex.getMessage());
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.addObject("status_code", HttpStatus.NOT_FOUND);
        return modelAndView;
    }

    @ExceptionHandler(DuplicateRecordFoundException.class)
    public ModelAndView handleDuplicateRecordFoundException(HttpServletRequest request, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(MESSAGE, ex.getMessage());
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.addObject("status_code", HttpStatus.CONFLICT);
        return modelAndView;
    }

    @ExceptionHandler(ValidationsFatalException.class)
    public ResponseEntity<ModelAndView> handleValidationErrors(HttpServletRequest request, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(MESSAGE, ((ValidationsFatalException) ex).getDetails());
        modelAndView.addObject("url", request.getRequestURL());
        return new ResponseEntity<> (modelAndView, HttpStatus.BAD_REQUEST);
    }
}
