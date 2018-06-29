package com.weaxme.model;

import com.orientechnologies.orient.core.db.record.OIdentifiable;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.type.ODocumentWrapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OWorkReport extends ODocumentWrapper {

    public static final String CLASS_NAME = "OWorkReport";

    public static final String OPROPERTY_DESCRIPTION = "description";
    public static final String OPROPERTY_PROJECT     = "project";
    public static final String OPROPERTY_START       = "start";
    public static final String OPROPERTY_END         = "end";
    public static final String OPROPERTY_TIME        = "time";
    public static final String OPROPERTY_ID          = "id";

    public OWorkReport() {
        super(CLASS_NAME);
    }

    public OWorkReport(ODocument iDocument) {
        super(iDocument);
    }

    public Long getId() {
        return document.field(OPROPERTY_ID);
    }

    public OWorkReport setId(Long id) {
        document.field(OPROPERTY_ID, id);
        return this;
    }

    public String getDescription() {
        return document.field(OPROPERTY_DESCRIPTION);
    }

    public OWorkReport setDescription(String description) {
        document.field(OPROPERTY_DESCRIPTION, description);
        return this;
    }

    public OProject getProject() {
        OIdentifiable identifiable = document.field(OPROPERTY_PROJECT);
        ODocument doc = identifiable != null ? identifiable.getRecord() : null;
        return doc != null ? new OProject(doc) : null;
    }

    public OWorkReport setProject(OProject project) {
        return setProject(project.getDocument());
    }

    public OWorkReport setProject(ODocument doc) {
        document.field(OPROPERTY_PROJECT, doc);
        return this;
    }

    public LocalDateTime getStart() {
        String start = document.field(OPROPERTY_START);
        return start != null ? LocalDateTime.parse(start, DateTimeFormatter.ISO_DATE_TIME) : null;
    }

    public OWorkReport setStart(LocalDateTime time) {
        document.field(OPROPERTY_START, time.toString());
        return this;
    }

    public LocalDateTime getEnd() {
        String end = document.field(OPROPERTY_END);
        return end != null ? LocalDateTime.parse(end, DateTimeFormatter.ISO_DATE_TIME) : null;
    }

    public OWorkReport setEnd(LocalDateTime time) {
        document.field(OPROPERTY_END, time.toString());
        return this;
    }

    public Long getTime() {
        Long time = document.field(OPROPERTY_TIME);
        return time != null ? time : 0L;
    }

    public OWorkReport setTime(Long time) {
        document.field(OPROPERTY_TIME, time);
        return this;
    }
}
