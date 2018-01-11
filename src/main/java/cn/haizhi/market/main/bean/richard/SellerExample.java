package cn.haizhi.market.main.bean.richard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SellerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SellerExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSellerIdIsNull() {
            addCriterion("seller_id is null");
            return (Criteria) this;
        }

        public Criteria andSellerIdIsNotNull() {
            addCriterion("seller_id is not null");
            return (Criteria) this;
        }

        public Criteria andSellerIdEqualTo(Long value) {
            addCriterion("seller_id =", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotEqualTo(Long value) {
            addCriterion("seller_id <>", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdGreaterThan(Long value) {
            addCriterion("seller_id >", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("seller_id >=", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdLessThan(Long value) {
            addCriterion("seller_id <", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdLessThanOrEqualTo(Long value) {
            addCriterion("seller_id <=", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdIn(List<Long> values) {
            addCriterion("seller_id in", values, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotIn(List<Long> values) {
            addCriterion("seller_id not in", values, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdBetween(Long value1, Long value2) {
            addCriterion("seller_id between", value1, value2, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotBetween(Long value1, Long value2) {
            addCriterion("seller_id not between", value1, value2, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerNameIsNull() {
            addCriterion("seller_name is null");
            return (Criteria) this;
        }

        public Criteria andSellerNameIsNotNull() {
            addCriterion("seller_name is not null");
            return (Criteria) this;
        }

        public Criteria andSellerNameEqualTo(String value) {
            addCriterion("seller_name =", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameNotEqualTo(String value) {
            addCriterion("seller_name <>", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameGreaterThan(String value) {
            addCriterion("seller_name >", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameGreaterThanOrEqualTo(String value) {
            addCriterion("seller_name >=", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameLessThan(String value) {
            addCriterion("seller_name <", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameLessThanOrEqualTo(String value) {
            addCriterion("seller_name <=", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameLike(String value) {
            addCriterion("seller_name like", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameNotLike(String value) {
            addCriterion("seller_name not like", value, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameIn(List<String> values) {
            addCriterion("seller_name in", values, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameNotIn(List<String> values) {
            addCriterion("seller_name not in", values, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameBetween(String value1, String value2) {
            addCriterion("seller_name between", value1, value2, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerNameNotBetween(String value1, String value2) {
            addCriterion("seller_name not between", value1, value2, "sellerName");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneIsNull() {
            addCriterion("seller_phone is null");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneIsNotNull() {
            addCriterion("seller_phone is not null");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneEqualTo(String value) {
            addCriterion("seller_phone =", value, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneNotEqualTo(String value) {
            addCriterion("seller_phone <>", value, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneGreaterThan(String value) {
            addCriterion("seller_phone >", value, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("seller_phone >=", value, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneLessThan(String value) {
            addCriterion("seller_phone <", value, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneLessThanOrEqualTo(String value) {
            addCriterion("seller_phone <=", value, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneLike(String value) {
            addCriterion("seller_phone like", value, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneNotLike(String value) {
            addCriterion("seller_phone not like", value, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneIn(List<String> values) {
            addCriterion("seller_phone in", values, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneNotIn(List<String> values) {
            addCriterion("seller_phone not in", values, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneBetween(String value1, String value2) {
            addCriterion("seller_phone between", value1, value2, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhoneNotBetween(String value1, String value2) {
            addCriterion("seller_phone not between", value1, value2, "sellerPhone");
            return (Criteria) this;
        }

        public Criteria andSellerPhotoIsNull() {
            addCriterion("seller_photo is null");
            return (Criteria) this;
        }

        public Criteria andSellerPhotoIsNotNull() {
            addCriterion("seller_photo is not null");
            return (Criteria) this;
        }

        public Criteria andSellerPhotoEqualTo(String value) {
            addCriterion("seller_photo =", value, "sellerPhoto");
            return (Criteria) this;
        }

        public Criteria andSellerPhotoNotEqualTo(String value) {
            addCriterion("seller_photo <>", value, "sellerPhoto");
            return (Criteria) this;
        }

        public Criteria andSellerPhotoGreaterThan(String value) {
            addCriterion("seller_photo >", value, "sellerPhoto");
            return (Criteria) this;
        }

        public Criteria andSellerPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("seller_photo >=", value, "sellerPhoto");
            return (Criteria) this;
        }

        public Criteria andSellerPhotoLessThan(String value) {
            addCriterion("seller_photo <", value, "sellerPhoto");
            return (Criteria) this;
        }

        public Criteria andSellerPhotoLessThanOrEqualTo(String value) {
            addCriterion("seller_photo <=", value, "sellerPhoto");
            return (Criteria) this;
        }

        public Criteria andSellerPhotoLike(String value) {
            addCriterion("seller_photo like", value, "sellerPhoto");
            return (Criteria) this;
        }

        public Criteria andSellerPhotoNotLike(String value) {
            addCriterion("seller_photo not like", value, "sellerPhoto");
            return (Criteria) this;
        }

        public Criteria andSellerPhotoIn(List<String> values) {
            addCriterion("seller_photo in", values, "sellerPhoto");
            return (Criteria) this;
        }

        public Criteria andSellerPhotoNotIn(List<String> values) {
            addCriterion("seller_photo not in", values, "sellerPhoto");
            return (Criteria) this;
        }

        public Criteria andSellerPhotoBetween(String value1, String value2) {
            addCriterion("seller_photo between", value1, value2, "sellerPhoto");
            return (Criteria) this;
        }

        public Criteria andSellerPhotoNotBetween(String value1, String value2) {
            addCriterion("seller_photo not between", value1, value2, "sellerPhoto");
            return (Criteria) this;
        }

        public Criteria andSellerAddressIsNull() {
            addCriterion("seller_address is null");
            return (Criteria) this;
        }

        public Criteria andSellerAddressIsNotNull() {
            addCriterion("seller_address is not null");
            return (Criteria) this;
        }

        public Criteria andSellerAddressEqualTo(String value) {
            addCriterion("seller_address =", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressNotEqualTo(String value) {
            addCriterion("seller_address <>", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressGreaterThan(String value) {
            addCriterion("seller_address >", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressGreaterThanOrEqualTo(String value) {
            addCriterion("seller_address >=", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressLessThan(String value) {
            addCriterion("seller_address <", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressLessThanOrEqualTo(String value) {
            addCriterion("seller_address <=", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressLike(String value) {
            addCriterion("seller_address like", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressNotLike(String value) {
            addCriterion("seller_address not like", value, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressIn(List<String> values) {
            addCriterion("seller_address in", values, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressNotIn(List<String> values) {
            addCriterion("seller_address not in", values, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressBetween(String value1, String value2) {
            addCriterion("seller_address between", value1, value2, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerAddressNotBetween(String value1, String value2) {
            addCriterion("seller_address not between", value1, value2, "sellerAddress");
            return (Criteria) this;
        }

        public Criteria andSellerStateIsNull() {
            addCriterion("seller_state is null");
            return (Criteria) this;
        }

        public Criteria andSellerStateIsNotNull() {
            addCriterion("seller_state is not null");
            return (Criteria) this;
        }

        public Criteria andSellerStateEqualTo(Integer value) {
            addCriterion("seller_state =", value, "sellerState");
            return (Criteria) this;
        }

        public Criteria andSellerStateNotEqualTo(Integer value) {
            addCriterion("seller_state <>", value, "sellerState");
            return (Criteria) this;
        }

        public Criteria andSellerStateGreaterThan(Integer value) {
            addCriterion("seller_state >", value, "sellerState");
            return (Criteria) this;
        }

        public Criteria andSellerStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("seller_state >=", value, "sellerState");
            return (Criteria) this;
        }

        public Criteria andSellerStateLessThan(Integer value) {
            addCriterion("seller_state <", value, "sellerState");
            return (Criteria) this;
        }

        public Criteria andSellerStateLessThanOrEqualTo(Integer value) {
            addCriterion("seller_state <=", value, "sellerState");
            return (Criteria) this;
        }

        public Criteria andSellerStateIn(List<Integer> values) {
            addCriterion("seller_state in", values, "sellerState");
            return (Criteria) this;
        }

        public Criteria andSellerStateNotIn(List<Integer> values) {
            addCriterion("seller_state not in", values, "sellerState");
            return (Criteria) this;
        }

        public Criteria andSellerStateBetween(Integer value1, Integer value2) {
            addCriterion("seller_state between", value1, value2, "sellerState");
            return (Criteria) this;
        }

        public Criteria andSellerStateNotBetween(Integer value1, Integer value2) {
            addCriterion("seller_state not between", value1, value2, "sellerState");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberIsNull() {
            addCriterion("idcard_number is null");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberIsNotNull() {
            addCriterion("idcard_number is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberEqualTo(String value) {
            addCriterion("idcard_number =", value, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberNotEqualTo(String value) {
            addCriterion("idcard_number <>", value, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberGreaterThan(String value) {
            addCriterion("idcard_number >", value, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberGreaterThanOrEqualTo(String value) {
            addCriterion("idcard_number >=", value, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberLessThan(String value) {
            addCriterion("idcard_number <", value, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberLessThanOrEqualTo(String value) {
            addCriterion("idcard_number <=", value, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberLike(String value) {
            addCriterion("idcard_number like", value, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberNotLike(String value) {
            addCriterion("idcard_number not like", value, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberIn(List<String> values) {
            addCriterion("idcard_number in", values, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberNotIn(List<String> values) {
            addCriterion("idcard_number not in", values, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberBetween(String value1, String value2) {
            addCriterion("idcard_number between", value1, value2, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardNumberNotBetween(String value1, String value2) {
            addCriterion("idcard_number not between", value1, value2, "idcardNumber");
            return (Criteria) this;
        }

        public Criteria andIdcardFpictureIsNull() {
            addCriterion("idcard_fpicture is null");
            return (Criteria) this;
        }

        public Criteria andIdcardFpictureIsNotNull() {
            addCriterion("idcard_fpicture is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardFpictureEqualTo(String value) {
            addCriterion("idcard_fpicture =", value, "idcardFpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardFpictureNotEqualTo(String value) {
            addCriterion("idcard_fpicture <>", value, "idcardFpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardFpictureGreaterThan(String value) {
            addCriterion("idcard_fpicture >", value, "idcardFpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardFpictureGreaterThanOrEqualTo(String value) {
            addCriterion("idcard_fpicture >=", value, "idcardFpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardFpictureLessThan(String value) {
            addCriterion("idcard_fpicture <", value, "idcardFpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardFpictureLessThanOrEqualTo(String value) {
            addCriterion("idcard_fpicture <=", value, "idcardFpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardFpictureLike(String value) {
            addCriterion("idcard_fpicture like", value, "idcardFpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardFpictureNotLike(String value) {
            addCriterion("idcard_fpicture not like", value, "idcardFpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardFpictureIn(List<String> values) {
            addCriterion("idcard_fpicture in", values, "idcardFpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardFpictureNotIn(List<String> values) {
            addCriterion("idcard_fpicture not in", values, "idcardFpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardFpictureBetween(String value1, String value2) {
            addCriterion("idcard_fpicture between", value1, value2, "idcardFpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardFpictureNotBetween(String value1, String value2) {
            addCriterion("idcard_fpicture not between", value1, value2, "idcardFpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardBpictureIsNull() {
            addCriterion("idcard_bpicture is null");
            return (Criteria) this;
        }

        public Criteria andIdcardBpictureIsNotNull() {
            addCriterion("idcard_bpicture is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardBpictureEqualTo(String value) {
            addCriterion("idcard_bpicture =", value, "idcardBpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardBpictureNotEqualTo(String value) {
            addCriterion("idcard_bpicture <>", value, "idcardBpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardBpictureGreaterThan(String value) {
            addCriterion("idcard_bpicture >", value, "idcardBpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardBpictureGreaterThanOrEqualTo(String value) {
            addCriterion("idcard_bpicture >=", value, "idcardBpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardBpictureLessThan(String value) {
            addCriterion("idcard_bpicture <", value, "idcardBpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardBpictureLessThanOrEqualTo(String value) {
            addCriterion("idcard_bpicture <=", value, "idcardBpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardBpictureLike(String value) {
            addCriterion("idcard_bpicture like", value, "idcardBpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardBpictureNotLike(String value) {
            addCriterion("idcard_bpicture not like", value, "idcardBpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardBpictureIn(List<String> values) {
            addCriterion("idcard_bpicture in", values, "idcardBpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardBpictureNotIn(List<String> values) {
            addCriterion("idcard_bpicture not in", values, "idcardBpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardBpictureBetween(String value1, String value2) {
            addCriterion("idcard_bpicture between", value1, value2, "idcardBpicture");
            return (Criteria) this;
        }

        public Criteria andIdcardBpictureNotBetween(String value1, String value2) {
            addCriterion("idcard_bpicture not between", value1, value2, "idcardBpicture");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}