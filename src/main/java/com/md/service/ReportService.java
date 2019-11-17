package com.md.service;

import java.io.IOException;

public interface ReportService {

    String generateReport(final String subjectName, final String filePath) throws IOException;

}
