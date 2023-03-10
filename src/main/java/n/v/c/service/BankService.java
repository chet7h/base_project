package n.v.c.service;


import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import n.v.c.support.BankSupport;

@Service
public class BankService {

	@Autowired
	private BankSupport bankSupport;

	public int checkPayWhenRegister(String otp) {
		AtomicInteger totalPay = new AtomicInteger();
		bankSupport.checkPayByContent(otp).forEach(e -> {
			totalPay.getAndAdd(Integer.valueOf(e.getAmount()));
		});
		return totalPay.get();
	}

}
