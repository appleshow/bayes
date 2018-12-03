package com.aps.bayes.controller;

import com.aps.bayes.dc.entity.Error;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <dl>
 * <dt>com.aps.env.controller.ExceptionController</dt>
 * <dd>MapString:</dd>
 * <dd>Copyright:  Copyright (C) 2017</dd>
 * <dd>Company:    DXC Technology </dd>
 * <dd>CreateDate: 2018/3/19</dd>
 * </dl>
 *
 * @author appleshow
 */
@RestController
public abstract class ExceptionController {
    /**
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public Error exception(HttpServletRequest request, Exception e) {
        Error error = new Error();

        error.setErrorCode(100);
        error.setErrorMessage(e.getMessage());

        return error;
    }
}
