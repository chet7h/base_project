package n.v.c.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import n.v.c.entity.DiscountCode;

@Repository
@Transactional
public interface DiscountCodeRepository  extends JpaRepository<DiscountCode, Long>  {

	List<DiscountCode> findAllByCode(String code);

}
