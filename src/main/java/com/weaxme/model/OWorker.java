package com.weaxme.model;

import com.orientechnologies.orient.core.metadata.security.OUser;

public class OWorker extends OUser {
    public static final String CLASS_NAME = "OWorker";

    public static final String OPROPERTY_FIRST_NAME      = "firstName";
    public static final String OPROPERTY_LAST_NAME       = "lastName";
    public static final String OPROPERTY_EMAIL           = "email";
    public static final String OPROPERTY_PROJECTS        = "projects";
    public static final String OPROPERTY_WORK_REPORTS    = "workReports";
    public static final String OPROPERTY_TOGGL_API_TOKEN = "togglApiToken";
}
