package com.weaxme.service;

import com.weaxme.model.OProject;
import com.weaxme.model.OWorkReport;

public interface IDBService {
    OWorkReport getWorkReportById(String id);
    OProject getProjectByName(String name);
}
