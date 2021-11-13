package com.revobank.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.revobank.dto.filters.AccountFilter;
import com.revobank.model.Account;

@Component
public class AccountSpecification {

	public Specification<Account> accounts(AccountFilter filter) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicateList = new ArrayList<>();

			if (!ObjectUtils.isEmpty(filter.getName())) {
				predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")),
						filter.getName().toUpperCase().concat("%")));
			}

			if (!ObjectUtils.isEmpty(filter.getDocument())) {
				predicateList
						.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("document")), filter.getDocument()));
			}

			if (!ObjectUtils.isEmpty(filter.getJobTitle())) {
				predicateList.add(criteriaBuilder.equal(root.get("jobTitle"), filter.getJobTitle()));
			}

			if (!ObjectUtils.isEmpty(filter.getInitialBirthday())) {
				predicateList
						.add(criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"), filter.getInitialBirthday()));
			}

			if (!ObjectUtils.isEmpty(filter.getFinalBirthday())) {
				predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("birthday"), filter.getFinalBirthday()));
			}

			return criteriaBuilder.and(predicateList.toArray(Predicate[]::new));
		};

	}
}
