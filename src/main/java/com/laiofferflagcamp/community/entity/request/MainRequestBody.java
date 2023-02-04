package com.laiofferflagcamp.community.entity.request;

import com.laiofferflagcamp.community.entity.db.Maintenance;

public class MainRequestBody {

    private Maintenance maintenance;

    public Maintenance getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenance maintenance) {
        this.maintenance = maintenance;
    }
}
