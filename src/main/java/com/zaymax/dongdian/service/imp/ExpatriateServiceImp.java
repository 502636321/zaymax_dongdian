package com.zaymax.dongdian.service.imp;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.zaymax.dongdian.domain.*;
import com.zaymax.dongdian.repository.BaseCountryRepository;
import com.zaymax.dongdian.repository.BaseEmployerRepository;
import com.zaymax.dongdian.repository.SysExpatriateRepository;
import com.zaymax.dongdian.service.BasicService;
import com.zaymax.dongdian.service.ExpatriateService;
import com.zaymax.dongdian.service.SystemService;
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

/**
 * Created by soy50 on 2017/3/26.
 */
@Service
public class ExpatriateServiceImp implements ExpatriateService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ExpatriateServiceImp.class);

    @Autowired
    private SysExpatriateRepository expatriateRepository;

    @Autowired //派往国别
    private BaseCountryRepository countryRepository;

    @Autowired //雇主
    private BaseEmployerRepository employerRepository;

    @Autowired
    private BasicService basicService;

    @Override
    public Page<SysExpatriate> findExpatriatePage(Pageable pageable, SysExpatriate expatriate) {
        return expatriateRepository.findAll(new Specification<SysExpatriate>() {
            @Override
            public Predicate toPredicate(Root<SysExpatriate> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get(SysExpatriate_.deleted), Boolean.FALSE));
                if (expatriate != null) {
                    if (!Strings.isNullOrEmpty(expatriate.getName())) {
                        predicate = criteriaBuilder.and(
                                criteriaBuilder.like(root.get(SysExpatriate_.name), "%" + expatriate.getName() + "%")
                        );
                    }
                }
                return predicate;
            }
        }, pageable);
    }

    @Override
    public SysExpatriate saveExpatriate(SysExpatriate expatriate) {
        if (expatriate != null) {
            return expatriateRepository.save(expatriate);
        }
        return null;
    }

    @Override
    public SysExpatriate findExpatriate(String id) {
        if (!Strings.isNullOrEmpty(id)) {
            return expatriateRepository.findOne(id);
        }
        return null;
    }

    @Override
    public SysExpatriate editExpatriate(String id, SysExpatriate expatriate) {
        SysExpatriate editExpatriate1 = null;
        if (!Strings.isNullOrEmpty(id)
                && (editExpatriate1 = expatriateRepository.findOne(id)) != null) {
            editExpatriate1.setName(expatriate.getName()); //名字
            editExpatriate1.setGender(expatriate.getGender()); //性别
            editExpatriate1.setCardNO(expatriate.getCardNO()); //身份证号
            editExpatriate1.setPassportNO(expatriate.getPassportNO()); //护照号
            editExpatriate1.setContactMobile(expatriate.getContactMobile()); //联系电话
            if (expatriate.getCountry() != null
                    && !Strings.isNullOrEmpty(expatriate.getCountry().getId())) {
                editExpatriate1.setCountry(countryRepository.findOne(expatriate.getCountry().getId()));//外派国别
            }
            if (expatriate.getEmployer() != null
                    && !Strings.isNullOrEmpty(expatriate.getEmployer().getId())) {
                editExpatriate1.setEmployer(employerRepository.findOne(expatriate.getEmployer().getId())); //雇主
            }
            editExpatriate1.setAddress(expatriate.getAddress());
            editExpatriate1.setContractPeriod(expatriate.getContractPeriod()); //合同周期
            editExpatriate1.setExpatriateDate(expatriate.getExpatriateDate()); //外派时间

            //社会保险
            SysSocialInsurance editSocialInsurance = editExpatriate1.getSocialInsurance();
            if (editSocialInsurance == null) {
                editExpatriate1.setSocialInsurance(expatriate.getSocialInsurance());
            } else {
                SysSocialInsurance socialInsurance = expatriate.getSocialInsurance();
                if (socialInsurance != null) {
                    editSocialInsurance.setInsuranceDate(socialInsurance.getInsuranceDate());
                    editSocialInsurance.setPersonalCode(socialInsurance.getPersonalCode());
                    editSocialInsurance.setRadices(socialInsurance.getRadices());
                    editSocialInsurance.setCompanyRadices(socialInsurance.getCompanyRadices());
                    editSocialInsurance.setPersonalRadices(socialInsurance.getPersonalRadices());
                }
            }
            //商业保险
            SysCommercialInsurance editCommercialInsurance = editExpatriate1.getCommercialInsurance();
            if (editCommercialInsurance == null) {
                editExpatriate1.setCommercialInsurance(expatriate.getCommercialInsurance());
            } else {
                SysCommercialInsurance commercialInsurance = expatriate.getCommercialInsurance();
                if (commercialInsurance != null) {
                    editCommercialInsurance.setPremium(commercialInsurance.getPremium()); //保费
                    editCommercialInsurance.setPaid(commercialInsurance.getPaid()); //保额
                    editCommercialInsurance.setStartPeriod(commercialInsurance.getStartPeriod()); //保险期限（开始）
                    editCommercialInsurance.setEndPeriod(commercialInsurance.getEndPeriod()); //保险期限（结束）
                }
            }
            return expatriateRepository.save(editExpatriate1);
        }
        return editExpatriate1;
    }

    @Override
    public int deleteExpatriate(String id) {
        if (!Strings.isNullOrEmpty(id)) {
            return expatriateRepository.delete(Lists.newArrayList(id));
        }
        return 0;
    }


}
