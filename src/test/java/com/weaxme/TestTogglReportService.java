package com.weaxme;

import com.fasterxml.jackson.databind.JsonNode;
import com.weaxme.model.OWorkReport;
import com.weaxme.service.ITogglReportService;
import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class TestTogglReportService {

    private static final Logger LOG = LoggerFactory.getLogger(TestTogglReportService.class);

    private ITogglReportService service;

    private final String username = "username";
    private final String password = "api_token";
    private final String userAgent = "userAgent";
    private final String workspaceId = "workspaceId";

    private int currentPage = 0;
    private int pageCount = 1;

    @Before
    public void init() {
        service = new Retrofit.Builder()
                .baseUrl("https://toggl.com/reports/api/v2/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build().create(ITogglReportService.class);
    }

    @Test
    public void testWeeklyRequest() {
        String auth = createAuthorizationHeader();
        service.getWeeklyReport(auth, userAgent, workspaceId)
                .subscribe(node -> {
                    LOG.info("node: {}", node);
                });
    }

    @Test
    public void testDetailedWeeklyRequest() {
        String auth = createAuthorizationHeader();
        service.getDetailedWeeklyReport(auth, userAgent, workspaceId, 0)
                .map(node -> node.get("data"))
                .flatMapObservable(Observable::fromIterable)
                .map(this::toWorkReport).toList()
                .subscribe(reports -> {
                    LOG.info("Work reports:");
                    reports.stream().map(OWorkReport::toString).forEach(LOG::info);
                });
    }

    private OWorkReport toWorkReport(JsonNode jsonReport) {
        OWorkReport report = new OWorkReport();
        report.setId(jsonReport.get("id").longValue())
                .setDescription(jsonReport.get("description").textValue())
                .setStart(LocalDateTime.parse(jsonReport.get("start").textValue(), DateTimeFormatter.ISO_DATE_TIME))
                .setEnd(LocalDateTime.parse(jsonReport.get("end").textValue(), DateTimeFormatter.ISO_DATE_TIME))
                .setTime(toSeconds(jsonReport.get("dur").asLong()));
        return report;
    }

    private long toSeconds(long milliseconds) {
        return TimeUnit.MILLISECONDS.toSeconds(milliseconds);
    }

    private String createAuthorizationHeader() {
        String credentials = username + ":" + password;
        byte [] bytes = Base64.getEncoder().encode(credentials.getBytes());
        return new String(bytes);
    }
}
