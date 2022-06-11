package com.gsnotes.web.models;

public class NotesModel {

    private long idModule;

    private String session;

    public long getModule() {
        return idModule;
    }

    public void setModule(long idModule) {
        this.idModule = idModule;
    }

    public String getSession() {
        return session; }

    public void setSession(String session) {
        this.session = session;
    }


    @Override
    public String toString() {
        return "NotesModel [module=" + idModule + ", session=" + session + "]";
    }


}


