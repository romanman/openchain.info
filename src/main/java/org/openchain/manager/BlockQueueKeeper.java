package org.openchain.manager;

import com.google.bitcoin.params.TestNet3Params;
import org.hibernate.SessionFactory;
import org.openchain.db.Block;
import org.openchain.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * www.openchain.info
 * User: Roman Mandeleil
 * Created on: 27/01/14 12:08
 */
@Component
public class BlockQueueKeeper {

    Logger log  = LoggerFactory.getLogger(getClass());
    Queue<Block> testNetBlocks = new ConcurrentLinkedQueue<Block>();

    @Autowired
    public SessionFactory sessionFactory;


    public void add(Block block){
        testNetBlocks.add(block);
    }


    @Transactional
    @SuppressWarnings("unchecked")
    public void checkQueue(){

        log.info("queue size: {}", testNetBlocks.size());

        com.google.bitcoin.core.Block genessisBlock =
                TestNet3Params.get().getGenesisBlock();

        Block firstBlock = (Block)
            sessionFactory.getCurrentSession().get(Block.class, genessisBlock.getHash().toString());

        if (firstBlock == null){
            log.info("SAVE first block");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try{genessisBlock.bitcoinSerialize(baos);} catch (IOException e) {} ;

            byte[] bytes = baos.toByteArray();
            String hexRaw = Util.bytesToHex(bytes);

            firstBlock = new Block();
            firstBlock.setIndex(0L);
            firstBlock.setPrevHash("00000000000000000000000000000000000000000000000000000000000000");
            firstBlock.setHash(genessisBlock.getHash().toString());
            firstBlock.setHexRaw(hexRaw);

            sessionFactory.getCurrentSession().save(firstBlock);

        }



        // Check if the block table is empty save the genesis block

        while(testNetBlocks.size() > 0){

            Block block = testNetBlocks.remove();

            Block prevBlock = (Block)
                    sessionFactory.getCurrentSession().get(Block.class, block.getPrevHash());

            long index = -1;
            if (prevBlock == null) {
//                log.info("*** SOMETHING VERY BAD HAPPENED");
//                System.exit(-1);
            } else {

                index = prevBlock.getIndex() + 1;
            }

            block.setIndex(index);
            sessionFactory.getCurrentSession().save(block);
        }
    }


}
