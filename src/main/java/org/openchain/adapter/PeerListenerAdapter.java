package org.openchain.adapter;

import com.google.bitcoin.core.*;

import java.util.List;

/**
 * www.openchain.info
 * User: Roman Mandeleil
 * Created on: 27/01/14 10:38
 */
public class PeerListenerAdapter implements PeerEventListener{


    @Override
    public void onBlocksDownloaded(Peer peer, Block block, int blocksLeft) {
    }

    @Override
    public void onChainDownloadStarted(Peer peer, int blocksLeft) {
    }

    @Override
    public void onPeerConnected(Peer peer, int peerCount) {
    }

    @Override
    public void onPeerDisconnected(Peer peer, int peerCount) {
    }

    @Override
    public Message onPreMessageReceived(Peer peer, Message m) {
        return null;
    }

    @Override
    public void onTransaction(Peer peer, Transaction t) {

    }

    @Override
    public List<Message> getData(Peer peer, GetDataMessage m) {
        return null;
    }
}
