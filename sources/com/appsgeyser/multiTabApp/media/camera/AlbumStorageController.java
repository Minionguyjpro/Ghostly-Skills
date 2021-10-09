package com.appsgeyser.multiTabApp.media.camera;

import java.text.SimpleDateFormat;

public class AlbumStorageController {
    private String _albumName;
    private AlbumStorageDirFactory _albumStorageDirFactory = null;

    public AlbumStorageController(String str) {
        if (str == null || str.equals("")) {
            str = "album_" + new SimpleDateFormat("yyyy-MM-dd").toString();
        }
        this._albumName = str;
    }
}
