package n.v.c.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "discount_code")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscountCode {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "percent", nullable = false)
	private int percent;

}
