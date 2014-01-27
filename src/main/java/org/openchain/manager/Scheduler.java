package org.openchain.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

/**
 * www.openchain.info
 * User: Roman Mandeleil
 * Created on: 19/11/13 18:51
 */
@Component
@EnableScheduling
public class Scheduler {

    Logger log  = LoggerFactory.getLogger(getClass());

    @Autowired
    BlockQueueKeeper blockQueueKeeper;

    @Transactional
    @Scheduled(fixedRate = 1000 , initialDelay = 1000)
    public void checkNewBlocks() throws ParseException {

        blockQueueKeeper.checkQueue();
    }


}
