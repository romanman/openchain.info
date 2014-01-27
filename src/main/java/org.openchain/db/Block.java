package org.openchain.db;

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
@Table(name = "BID")
public class Block {


    @Id
    @Column(name = "HASH")
    String hash;

    @Column(name = "INDEX")
    Long   index;

    @Column(name = "HEX_RAW")
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
}
