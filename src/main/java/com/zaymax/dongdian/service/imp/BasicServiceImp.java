package com.zaymax.dongdian.service.imp;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.zaymax.dongdian.domain.BaseCountry;
import com.zaymax.dongdian.repository.BaseCountryRepository;
import com.zaymax.dongdian.service.BasicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by huiquan on 2017/3/2.
 */
@Service
public class BasicServiceImp implements BasicService {
    public static final Logger LOGGER = LoggerFactory.getLogger(BasicServiceImp.class);

    @Autowired //国家
    private BaseCountryRepository countryRepository;

    //------------学历-开始------------//
    @Override
    public Page<BaseCountry> findCountryPage(Pageable pageable, BaseCountry country) {
        return countryRepository.findAll(new Specification<BaseCountry>() {
            @Override
            public Predicate toPredicate(Root<BaseCountry> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("deleted"), false));
                return null;
            }
        }, pageable);
    }

    @Override
    public BaseCountry findCountry(String id) {
        if (!Strings.isNullOrEmpty(id)) {
            return countryRepository.findOne(id);
        }
        return null;
    }

    @Override
    public BaseCountry editCountry(String id, BaseCountry education) {
        if (!Strings.isNullOrEmpty(id)
                && education != null) {
            BaseCountry editCountry = countryRepository.findOne(id);
            if (editCountry != null) {
                editCountry.setName(education.getName()); //名称
                editCountry.setDescription(education.getDescription()); //描述
                return countryRepository.save(editCountry);
            }
        }
        return null;
    }

    @Override
    public BaseCountry saveCountry(BaseCountry education) {
        if (education != null) {
            return countryRepository.save(education);
        }
        return null;
    }

    @Override
    public List<BaseCountry> findAllCountry() {
        return countryRepository.findByDeletedFalse();
    }

    @Override
    public int deleteCountry(String id) {
        return countryRepository.delete(Lists.newArrayList(id));
    }
    //------------学历-结束------------//

}
