package org.openchain.db;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * www.openchain.info
 * User: Roman Mandeleil
 * Created on: 27/01/14 10:23
 */
@Entity
@Table(name = "BLOCK")
public class Block {

    @Column(name = "INDEX")
    Long   index;

    @Id
    @Column(name = "HASH")
    String hash;

    @Column(name = "PREV_HASH")
    String prevHash;


    @Column(name = "HEX_RAW", columnDefinition="TEXT")
    String hexRaw;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getHexRaw() {
        return hexRaw;
    }

    public void setHexRaw(String hexRaw) {
        this.hexRaw = hexRaw;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public void setPrevHash(String prevHash) {
        this.prevHash = prevHash;
    }
}
