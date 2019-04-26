package jspbook.ch14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
	private static final Logger LOG = LoggerFactory.getLogger(LogTest.class);
	
	// main 메서드에서 간단한 로그 메시지 처리 확인
	public static void main(String[] args) {
		String msg = "HELLO LOG";
		LOG.info("test log");
		LOG.warn("TEST LOG : {}", msg);
		LOG.debug("debug level test");
		LOG.trace("trace level test");
	}
}
