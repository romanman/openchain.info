package org.openchain.manager;

import com.google.bitcoin.core.Block;
import com.google.bitcoin.core.FullPrunedBlockChain;
import com.google.bitcoin.core.Peer;
import com.google.bitcoin.core.PeerGroup;
import com.google.bitcoin.discovery.DnsDiscovery;
import com.google.bitcoin.params.TestNet3Params;
import com.google.bitcoin.store.BlockStoreException;
import com.google.bitcoin.store.FullPrunedBlockStore;
import com.google.bitcoin.store.H2FullPrunedBlockStore;
import com.google.common.base.Splitter;
import org.openchain.adapter.PeerListenerAdapter;
import org.openchain.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * www.openchain.info
 * User: Roman Mandeleil
 * Created on: 27/01/14 11:45
 */
@Component
@EnableAsync
public class BTCNetworkManager {

    final TestNet3Params netParams = new TestNet3Params();

    Logger log  = LoggerFactory.getLogger(getClass());

    @Autowired
    BlockQueueKeeper blockQueueKeeper;


    @Async
    public void init() throws BlockStoreException {



        FullPrunedBlockStore blockStore =
                new H2FullPrunedBlockStore(netParams, "./db/testnet-blockchain", 0);


        FullPrunedBlockChain blockChain  = new  FullPrunedBlockChain(netParams,  blockStore);
        blockChain.setVerifySignatures(false);


        // Starting P2P work on BTC chain.
        PeerGroup peerGroup = new PeerGroup(netParams, blockChain);

        peerGroup.addEventListener(new PeerListenerAdapter() {

            @Override
            public void onBlocksDownloaded(Peer peer, Block block, int blocksLeft) {

/*
                log.info("hash: {}",        block.getHashAsString());
                log.info("prevHash: {}",    block.getPrevBlockHash().toString());
                log.info("time: {}",        block.getTime().toString());
                log.info("nonce: {}",       block.getNonce());
                log.info("merkle root: {}", block.getMerkleRoot());
*/


                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try{block.bitcoinSerialize(baos);} catch (IOException e) {} ;


                byte[] bytes = baos.toByteArray();
                String hexRaw = Util.bytesToHex(bytes);

/*
                Iterable<String> resultStrings =  Splitter.fixedLength(32).split(hexRaw);
                for (String str : resultStrings) log.info(str);
*/

/*
                log.info("-----");
                log.info("");
*/

                org.openchain.db.Block newBlock = new org.openchain.db.Block();

                newBlock.setHash(block.getHash().toString());
                newBlock.setPrevHash(block.getPrevBlockHash().toString());
                newBlock.setHexRaw(hexRaw);

                blockQueueKeeper.add(newBlock);
            }
        });


        peerGroup.addPeerDiscovery(new DnsDiscovery(netParams));
        peerGroup.startAndWait();
        peerGroup.downloadBlockChain();

    }

}
