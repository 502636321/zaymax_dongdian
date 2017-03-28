package com.zaymax.dongdian.service.imp;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.zaymax.dongdian.domain.BaseCountry;
import com.zaymax.dongdian.domain.BaseCountry_;
import com.zaymax.dongdian.domain.BaseEmployer;
import com.zaymax.dongdian.domain.SysProperties;
import com.zaymax.dongdian.repository.BaseCountryRepository;
import com.zaymax.dongdian.repository.BaseEmployerRepository;
import com.zaymax.dongdian.repository.SysPropertiesRepository;
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
import java.util.Optional;

/**
 * Created by huiquan on 2017/3/2.
 */
@Service
public class BasicServiceImp implements BasicService {
    public static final Logger LOGGER = LoggerFactory.getLogger(BasicServiceImp.class);

    @Autowired //派往国别
    private BaseCountryRepository countryRepository;
    
    @Autowired //雇主
    private BaseEmployerRepository employerRepository;

    @Autowired
    private SysPropertiesRepository propertiesRepository;

    //------------派往国别-开始------------//
    @Override
    public Page<BaseCountry> findCountryPage(Pageable pageable, BaseCountry country) {
        return countryRepository.findAll(new Specification<BaseCountry>() {
            @Override
            public Predicate toPredicate(Root<BaseCountry> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.and(
                        criteriaBuilder.equal(root.get(BaseCountry_.deleted), Boolean.FALSE)
                );
                if (country != null) {
                    if (!Strings.isNullOrEmpty(country.getName())) {
                        predicate = criteriaBuilder.and(
                            criteriaBuilder.like(root.get(BaseCountry_.name), "%" + country.getName() + "%")
                        );
                    }
                }
                return predicate;
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
    public List<BaseCountry> findTopNameByCountry(String name) {
        List<BaseCountry> countries = Lists.newArrayList();
        if (!Strings.isNullOrEmpty(name)) {
            countries.addAll(countryRepository.findTop20ByNameLikeAndDeletedFalse("%" + (name == null ? "" : name) + "%"));
        }
        return countries;
    }

    @Override
    public int deleteCountry(String id) {
        return countryRepository.delete(Lists.newArrayList(id));
    }


    @Override
    public BaseCountry autoCreateCountry(String name) {
        BaseCountry country = null;
//        Optional<SysProperties> propertiesOptional = propertiesRepository.findTopByDeletedFalseOrderByLastModifiedDateDesc();
//        if (!Strings.isNullOrEmpty(name)) {
//            Optional<BaseCountry> countryOptional = countryRepository.findTopByNameAndDeletedFalse(name);
//            if (countryOptional.isPresent()) {
//                country = countryOptional.get();
//            } else {
//
//            }
//        }
        return country;
    }
    //------------派往国别-结束------------//
    //------------雇主-开始------------//
    @Override
    public Page<BaseEmployer> findEmployerPage(Pageable pageable, BaseEmployer employer) {
        return employerRepository.findAll(new Specification<BaseEmployer>() {
            @Override
            public Predicate toPredicate(Root<BaseEmployer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("deleted"), false));
                return null;
            }
        }, pageable);
    }

    @Override
    public BaseEmployer findEmployer(String id) {
        if (!Strings.isNullOrEmpty(id)) {
            return employerRepository.findOne(id);
        }
        return null;
    }

    @Override
    public BaseEmployer editEmployer(String id, BaseEmployer education) {
        if (!Strings.isNullOrEmpty(id)
                && education != null) {
            BaseEmployer editEmployer = employerRepository.findOne(id);
            if (editEmployer != null) {
                editEmployer.setName(education.getName()); //名称
                editEmployer.setDescription(education.getDescription()); //描述
                return employerRepository.save(editEmployer);
            }
        }
        return null;
    }

    @Override
    public BaseEmployer saveEmployer(BaseEmployer education) {
        if (education != null) {
            return employerRepository.save(education);
        }
        return null;
    }

    @Override
    public List<BaseEmployer> findAllEmployer() {
        return employerRepository.findByDeletedFalse();
    }

    @Override
    public int deleteEmployer(String id) {
        return employerRepository.delete(Lists.newArrayList(id));
    }
    //------------雇主-结束------------//
}
