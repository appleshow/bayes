package com.aps.bayes.dc.service;

import com.aps.bayes.comm.FILE;
import com.aps.bayes.comm.JSON;
import com.aps.bayes.dc.entity.CalculateData;
import com.aps.bayes.dc.entity.LoadData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 *
 */
@Component
public class Prepare implements CommandLineRunner {
    final private static Logger LOG = LogManager.getLogger(Prepare.class);

    @Override
    public void run(String... args) {
        try {
            final Resource resource = new ClassPathResource("/templates/dc.json");
            final File jsonFile = resource.getFile();
            final String jsonContent = FILE.readTxtFile(jsonFile);
            final JSON jsonTool = new JSON();

            if (!StringUtils.isEmpty(jsonContent)) {
                LoadData loadData = jsonTool.deserialize(jsonContent, LoadData.class);
                LOG.info(String.format("Load %d.", loadData.size()));

                CalculateData.initData(loadData);
            }
        } catch (IOException e) {
            LOG.error(e);
        }

    }
}
