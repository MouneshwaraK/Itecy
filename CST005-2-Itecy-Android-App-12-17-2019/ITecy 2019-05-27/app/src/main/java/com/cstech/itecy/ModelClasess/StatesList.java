package com.cstech.itecy.ModelClasess;

public class StatesList {
    private int stateId;
    private String stateName;

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public String toString() {
        return "StatesList{" +
                "stateId=" + stateId +
                ", stateName='" + stateName + '\'' +
                '}';
    }
}
