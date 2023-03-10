package n.v.c.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import n.v.c.support.SeleniumSupport;

@Component
public class CheckRegisterScheduler {

	@Autowired
	private SeleniumSupport seleniumSupport;

	//@Scheduled(fixedRate = 50000)
	public void cronJobSch() {
		System.out.println(seleniumSupport.checkLogin(""));
	}
}
