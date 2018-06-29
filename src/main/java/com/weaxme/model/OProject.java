package com.weaxme.model;

import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.type.ODocumentWrapper;

import java.time.LocalDateTime;

public class OProject extends ODocumentWrapper {
    public static final String CLASS_NAME = "OProject";

    public static final String OPROPERTY_NAME         = "name";
    public static final String OPROPERTY_DESCRIPTION  = "description";
    public static final String OPROPERTY_START        = "start";
    public static final String OPROPERTY_END          = "end";
    public static final String OPROPERTY_WORKERS      = "workers";
    public static final String OPROPERTY_WORK_REPORTS = "workReports";

    public OProject() {
        super(CLASS_NAME);
    }

    public OProject(ODocument iDocument) {
        super(iDocument);
    }

    public String getName() {
        return document.field(OPROPERTY_NAME);
    }

    public OProject setName(String name) {
        document.field(OPROPERTY_NAME, name);
        return this;
    }

    public String getDescription() {
        return document.field(OPROPERTY_DESCRIPTION);
    }

    public OProject setDescription(String description) {
        document.field(OPROPERTY_DESCRIPTION, description);
        return this;
    }

    public LocalDateTime getStart() {
        String start = document.field(OPROPERTY_START);
        return start != null ? LocalDateTime.parse(start) : null;
    }

    public OProject setStart(LocalDateTime time) {
        document.field(OPROPERTY_START, time.toString());
        return this;
    }


}
