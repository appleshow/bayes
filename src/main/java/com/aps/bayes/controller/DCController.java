package com.aps.bayes.controller;

import com.aps.bayes.dc.entity.CalculateData;
import com.aps.bayes.dc.entity.DCAnswer;
import com.aps.bayes.dc.entity.KeyProbability;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 *
 */
@Controller
@RequestMapping(value = "/dc/")
public class DCController extends ExceptionController {
    final private static Logger LOG = LogManager.getLogger(DCController.class);

    /**
     * @param abc
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "abc/{abc}", method = RequestMethod.GET)
    public DCAnswer abcAnswer(@PathVariable(name = "abc") String abc) {
        final DCAnswer dcAnswer = CalculateData.abcAnswer(abc);

        LOG.info(dcAnswer);

        return dcAnswer;
    }
}
