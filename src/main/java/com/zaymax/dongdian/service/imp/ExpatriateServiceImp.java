package com.zaymax.dongdian.service.imp;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.zaymax.dongdian.domain.*;
import com.zaymax.dongdian.domain.enums.CfgSettlementState;
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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Formatter;

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

    private DecimalFormat formatter = new DecimalFormat("000000");

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
            expatriate.setNumber(formatter.format(expatriateRepository.countDistinctByIdIsNotNull()));
            if (expatriate.getCountry() != null
                    && !Strings.isNullOrEmpty(expatriate.getCountry().getId())) {
                expatriate.setCountry(countryRepository.findOne(expatriate.getCountry().getId()));
            } else {
                expatriate.setCountry(null);
            }
            if (expatriate.getEmployer() != null
                    && !Strings.isNullOrEmpty(expatriate.getEmployer().getId())) {
                expatriate.setEmployer(employerRepository.findOne(expatriate.getEmployer().getId()));
            } else {
                expatriate.setEmployer(null);
            }
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
                    if (socialInsurance.getSettlementState() != null) {
                        switch (socialInsurance.getSettlementState()) {
                            case By:
                                editSocialInsurance.setSettlementState(CfgSettlementState.By);
                                editSocialInsurance.setSettlementDate(socialInsurance.getSettlementDate());
                                break;
                            case Settled:
                                editSocialInsurance.setSettlementState(CfgSettlementState.Settled);
                                editSocialInsurance.setSettlementDate(null);
                                break;
                        }
                    } else {
                        editSocialInsurance.setSettlementState(null);
                    }
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
                    if (commercialInsurance.getSettlementState() != null) {
                        switch (commercialInsurance.getSettlementState()) {
                            case By:
                                editCommercialInsurance.setSettlementState(CfgSettlementState.By);
                                editCommercialInsurance.setSettlementDate(commercialInsurance.getSettlementDate());
                                break;
                            case Settled:
                                editCommercialInsurance.setSettlementState(CfgSettlementState.Settled);
                                editCommercialInsurance.setSettlementDate(null);
                                break;
                        }
                    } else {
                        editCommercialInsurance.setSettlementState(null);
                    }
                }
            }
            //管理费
            SysManageCost editManageCost = editExpatriate1.getManageCost();
            if (editManageCost == null) {
                editExpatriate1.setManageCost(expatriate.getManageCost());
            } else {
                SysManageCost manageCost = expatriate.getManageCost();
                if (manageCost != null) {
                    if (manageCost.getSettlementState() != null) {
                        switch (manageCost.getSettlementState()) {
                            case By:
                                editManageCost.setSettlementState(CfgSettlementState.By);
                                editManageCost.setSettlementDate(manageCost.getSettlementDate());
                                break;
                            case Settled:
                                editManageCost.setSettlementState(CfgSettlementState.Settled);
                                editManageCost.setSettlementDate(null);
                                break;
                        }
                    } else {
                        editManageCost.setSettlementState(null);
                    }
                }
            }
            //服务费
            SysServiceCost editServiceCost = editExpatriate1.getServiceCost();
            if (editServiceCost == null) {
                editExpatriate1.setServiceCost(expatriate.getServiceCost());
            } else {
                SysServiceCost serviceCost = expatriate.getServiceCost();
                if (serviceCost != null) {
                    if (serviceCost.getSettlementState() != null) {
                        switch (serviceCost.getSettlementState()) {
                            case By:
                                editServiceCost.setSettlementState(CfgSettlementState.By);
                                editServiceCost.setSettlementDate(serviceCost.getSettlementDate());
                                break;
                            case Settled:
                                editServiceCost.setSettlementState(CfgSettlementState.Settled);
                                editServiceCost.setSettlementDate(null);
                                break;
                        }
                    }
                } else {
                    editServiceCost.setSettlementState(null);
                }
            }
            //工资
            SysWages editWages = editExpatriate1.getWages();
            if (editWages == null) {
                editExpatriate1.setWages(expatriate.getWages());
            } else {
                SysWages wages = expatriate.getWages();
                if (wages != null) {
                    editWages.setAmount(wages.getAmount()); //金额
                    editWages.setPaymentDate(wages.getPaymentDate()); //发放时间
                    if (wages.getSettlementState() != null) {
                        switch (wages.getSettlementState()) {
                            case By:
                                editWages.setSettlementState(CfgSettlementState.By);
                                editWages.setSettlementDate(wages.getSettlementDate());
                                break;
                            case Settled:
                                editWages.setSettlementState(CfgSettlementState.Settled);
                                editWages.setSettlementDate(null);
                                break;
                        }
                    } else {
                        editWages.setSettlementState(null);
                    }
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
