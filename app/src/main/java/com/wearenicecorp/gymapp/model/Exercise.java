package com.wearenicecorp.gymapp.model;

/**
 * Created by JulioAndres on 4/10/17.
 */

public class Exercise {

    public static final int MESURE_SETS = 1;
    public static final int MESURE_TIME = 2;

    private String name;
    private String muscularGroup;
    private int mesureType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMuscularGroup() {
        return muscularGroup;
    }

    public void setMuscularGroup(String muscularGroup) {
        this.muscularGroup = muscularGroup;
    }

    public int getMesureType() {
        return mesureType;
    }

    public void setMesureType(int mesureType) {
        this.mesureType = mesureType;
    }
}
