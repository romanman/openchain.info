package org.openchain.manager;

import com.google.bitcoin.core.*;
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
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * www.openchain.info
 * User: Roman Mandeleil
 * Created on: 12/11/13 15:55
 */
@Component
public class InitManager {


    @Autowired
    BTCNetworkManager btcNetworkManager;


    @PostConstruct
    public void init() throws BlockStoreException {


        btcNetworkManager.init();

    }



}
