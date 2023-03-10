package n.v.c.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import n.v.c.entity.DiscountCode;
import n.v.c.repository.DiscountCodeRepository;

@Service
public class DiscountCodeService {

	@Autowired
	private DiscountCodeRepository discountCodeRepository;

	public boolean checkDiscountCode(String code) {
		List<DiscountCode> DiscountCodes = discountCodeRepository.findAllByCode(code);
		return DiscountCodes.size() > 0;
	}
}
