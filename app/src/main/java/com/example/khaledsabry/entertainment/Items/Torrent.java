package com.example.khaledsabry.entertainment.Items;

/**
 * Created by KhALeD SaBrY on 14-Jul-18.
 */

public class Torrent {
    private String title;
    private String magnet;
    private String size;
    private String seeders;
    private String leechers;
    private String quailty;
    private String sourceName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMagnet() {
        return magnet;
    }

    public void setMagnet(String magnet) {
        this.magnet = magnet;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSeeders() {
        return seeders;
    }

    public void setSeeders(String seeders) {
        this.seeders = seeders;
    }

    public String getLeechers() {
        return leechers;
    }

    public void setLeechers(String leechers) {
        this.leechers = leechers;
    }

    public String getQuailty() {
        return quailty;
    }

    public void setQuailty(String quailty) {
        this.quailty = quailty;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
}
