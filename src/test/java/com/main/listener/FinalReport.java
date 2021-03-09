package com.main.listener;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.reporters.IReporterConfig;
import org.testng.xml.XmlSuite;

import java.util.List;

public class FinalReport implements IReporter {
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

    }

    @Override
    public IReporterConfig getConfig() {
        return null;
    }
}
