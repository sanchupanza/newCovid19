package com.sanchit.covid19tracker.Adapters;

import com.amulyakhare.textdrawable.TextDrawable;

public class CustomePojo {
    public int user;
    public int nature;
    public String stateName;


    public CustomePojo(int user, int nature, String stateName) {
        this.user = user;
        this.nature = nature;
        this.stateName = stateName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getNature() {
        return nature;
    }

    public void setNature(int nature) {
        this.nature = nature;
    }
}
