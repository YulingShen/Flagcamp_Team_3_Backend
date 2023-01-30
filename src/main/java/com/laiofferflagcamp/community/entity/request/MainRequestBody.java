package com.laiofferflagcamp.community.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.laiofferflagcamp.community.entity.db.Maintenance;

public class MainRequestBody {

        @JsonProperty("maintenance")
        private Maintenance maintenance;

        public Maintenance getMaintenance() {
            return maintenance;

        }




}
