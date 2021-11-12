package com.bank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id="bug-fixes")
public class BugFixesReportIndicator {

	static Map<String, List<String>> bugsMap = new HashMap<>();
	
	@PostConstruct
	public void initialize() {
		bugsMap.put("v1", Arrays.asList("Window close not working", "Browser extension not getting added"));
		bugsMap.put("v2", Arrays.asList("OLX app is restarting", "OLX database is down"));
	}
	
	@ReadOperation
	public Map<String, List<String>> getBugFixesInfo() {
		return bugsMap;
	}
	@ReadOperation
	public List<String> getBugInfo(@Selector String version) {
		return bugsMap.get(version);
	}
	@WriteOperation
	public void addBug(@Selector String version, String bugs) {
		bugsMap.put(version, Arrays.asList(bugs.split(",")));
	}
	@DeleteOperation
	public void deleteBug(@Selector String version) {
		bugsMap.remove(version);
	}
}
//http://localhost:9000/olx-monitor/bug-fixes/v2 - GET
//http://localhost:9000/olx-monitor/bug-fixes - GET





