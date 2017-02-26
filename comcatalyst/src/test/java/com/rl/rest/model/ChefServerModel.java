package com.rl.rest.model;

import java.io.File;

/**
 * Created by jagadeesh on 22/2/17.
 */
public class ChefServerModel {
    private String configname;
    private String loginname;
    private String url;

    public File getUserpemfile() {
        return userpemfile;
    }

    public void setUserpemfile(File userpemfile) {
        this.userpemfile = userpemfile;
    }

    public File getKniferbfile() {
        return kniferbfile;
    }

    public void setKniferbfile(File kniferbfile) {
        this.kniferbfile = kniferbfile;
    }

    private File userpemfile;
    private String userpemfile_filename;
    private File kniferbfile;
    private String kniferbfile_filename;
    private String folderpath;

    public String getConfigname() {
        return configname;
    }

    public void setConfigname(String configname) {
        this.configname = configname;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public String getUserpemfile_filename() {
        return userpemfile_filename;
    }

    public void setUserpemfile_filename(String userpemfile_filename) {
        this.userpemfile_filename = userpemfile_filename;
    }


    public String getKniferbfile_filename() {
        return kniferbfile_filename;
    }

    public void setKniferbfile_filename(String kniferbfile_filename) {
        this.kniferbfile_filename = kniferbfile_filename;
    }

    public String getFolderpath() {
        return folderpath;
    }

    public void setFolderpath(String folderpath) {
        this.folderpath = folderpath;
    }


}
