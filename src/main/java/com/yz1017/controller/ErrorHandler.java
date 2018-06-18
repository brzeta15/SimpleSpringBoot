package com.yz1017.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yz1017.util.ErrorDTO;


@ControllerAdvice
public class ErrorHandler {

  private static final Logger LOG = LoggerFactory.getLogger(ErrorHandler.class);


  /**
   * Handler to process the potential errors
   * 
   * @param req             related request
   * @param resp            related response
   * @param ex              related exception
   * @return                set the status to 404 with message
   */
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseEntity<ErrorDTO> error(HttpServletRequest req, HttpServletResponse resp, Exception ex) {
      ErrorDTO response = new ErrorDTO(ex.getMessage());
      
      LOG.info("Experienced Exception {}", ex.getMessage());

      return new ResponseEntity<ErrorDTO>(response, HttpStatus.NOT_FOUND);
  }
}
