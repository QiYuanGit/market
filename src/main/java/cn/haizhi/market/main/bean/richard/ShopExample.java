package cn.haizhi.market.main.bean.richard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ShopExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShopExample() {
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

        protected void addCriterionForJDBCTime(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value.getTime()), property);
        }

        protected void addCriterionForJDBCTime(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Time> timeList = new ArrayList<java.sql.Time>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                timeList.add(new java.sql.Time(iter.next().getTime()));
            }
            addCriterion(condition, timeList, property);
        }

        protected void addCriterionForJDBCTime(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Time(value1.getTime()), new java.sql.Time(value2.getTime()), property);
        }

        public Criteria andShopIdIsNull() {
            addCriterion("shop_id is null");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNotNull() {
            addCriterion("shop_id is not null");
            return (Criteria) this;
        }

        public Criteria andShopIdEqualTo(Long value) {
            addCriterion("shop_id =", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotEqualTo(Long value) {
            addCriterion("shop_id <>", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThan(Long value) {
            addCriterion("shop_id >", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThanOrEqualTo(Long value) {
            addCriterion("shop_id >=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThan(Long value) {
            addCriterion("shop_id <", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThanOrEqualTo(Long value) {
            addCriterion("shop_id <=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdIn(List<Long> values) {
            addCriterion("shop_id in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotIn(List<Long> values) {
            addCriterion("shop_id not in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdBetween(Long value1, Long value2) {
            addCriterion("shop_id between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotBetween(Long value1, Long value2) {
            addCriterion("shop_id not between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNull() {
            addCriterion("shop_name is null");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNotNull() {
            addCriterion("shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("shop_name =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotEqualTo(String value) {
            addCriterion("shop_name <>", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThan(String value) {
            addCriterion("shop_name >", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("shop_name >=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThan(String value) {
            addCriterion("shop_name <", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThanOrEqualTo(String value) {
            addCriterion("shop_name <=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("shop_name like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotLike(String value) {
            addCriterion("shop_name not like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIn(List<String> values) {
            addCriterion("shop_name in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotIn(List<String> values) {
            addCriterion("shop_name not in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameBetween(String value1, String value2) {
            addCriterion("shop_name between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotBetween(String value1, String value2) {
            addCriterion("shop_name not between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopAddressIsNull() {
            addCriterion("shop_address is null");
            return (Criteria) this;
        }

        public Criteria andShopAddressIsNotNull() {
            addCriterion("shop_address is not null");
            return (Criteria) this;
        }

        public Criteria andShopAddressEqualTo(String value) {
            addCriterion("shop_address =", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressNotEqualTo(String value) {
            addCriterion("shop_address <>", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressGreaterThan(String value) {
            addCriterion("shop_address >", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressGreaterThanOrEqualTo(String value) {
            addCriterion("shop_address >=", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressLessThan(String value) {
            addCriterion("shop_address <", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressLessThanOrEqualTo(String value) {
            addCriterion("shop_address <=", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressLike(String value) {
            addCriterion("shop_address like", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressNotLike(String value) {
            addCriterion("shop_address not like", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressIn(List<String> values) {
            addCriterion("shop_address in", values, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressNotIn(List<String> values) {
            addCriterion("shop_address not in", values, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressBetween(String value1, String value2) {
            addCriterion("shop_address between", value1, value2, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressNotBetween(String value1, String value2) {
            addCriterion("shop_address not between", value1, value2, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopPhoneIsNull() {
            addCriterion("shop_phone is null");
            return (Criteria) this;
        }

        public Criteria andShopPhoneIsNotNull() {
            addCriterion("shop_phone is not null");
            return (Criteria) this;
        }

        public Criteria andShopPhoneEqualTo(String value) {
            addCriterion("shop_phone =", value, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneNotEqualTo(String value) {
            addCriterion("shop_phone <>", value, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneGreaterThan(String value) {
            addCriterion("shop_phone >", value, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("shop_phone >=", value, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneLessThan(String value) {
            addCriterion("shop_phone <", value, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneLessThanOrEqualTo(String value) {
            addCriterion("shop_phone <=", value, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneLike(String value) {
            addCriterion("shop_phone like", value, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneNotLike(String value) {
            addCriterion("shop_phone not like", value, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneIn(List<String> values) {
            addCriterion("shop_phone in", values, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneNotIn(List<String> values) {
            addCriterion("shop_phone not in", values, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneBetween(String value1, String value2) {
            addCriterion("shop_phone between", value1, value2, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopPhoneNotBetween(String value1, String value2) {
            addCriterion("shop_phone not between", value1, value2, "shopPhone");
            return (Criteria) this;
        }

        public Criteria andShopNoteIsNull() {
            addCriterion("shop_note is null");
            return (Criteria) this;
        }

        public Criteria andShopNoteIsNotNull() {
            addCriterion("shop_note is not null");
            return (Criteria) this;
        }

        public Criteria andShopNoteEqualTo(String value) {
            addCriterion("shop_note =", value, "shopNote");
            return (Criteria) this;
        }

        public Criteria andShopNoteNotEqualTo(String value) {
            addCriterion("shop_note <>", value, "shopNote");
            return (Criteria) this;
        }

        public Criteria andShopNoteGreaterThan(String value) {
            addCriterion("shop_note >", value, "shopNote");
            return (Criteria) this;
        }

        public Criteria andShopNoteGreaterThanOrEqualTo(String value) {
            addCriterion("shop_note >=", value, "shopNote");
            return (Criteria) this;
        }

        public Criteria andShopNoteLessThan(String value) {
            addCriterion("shop_note <", value, "shopNote");
            return (Criteria) this;
        }

        public Criteria andShopNoteLessThanOrEqualTo(String value) {
            addCriterion("shop_note <=", value, "shopNote");
            return (Criteria) this;
        }

        public Criteria andShopNoteLike(String value) {
            addCriterion("shop_note like", value, "shopNote");
            return (Criteria) this;
        }

        public Criteria andShopNoteNotLike(String value) {
            addCriterion("shop_note not like", value, "shopNote");
            return (Criteria) this;
        }

        public Criteria andShopNoteIn(List<String> values) {
            addCriterion("shop_note in", values, "shopNote");
            return (Criteria) this;
        }

        public Criteria andShopNoteNotIn(List<String> values) {
            addCriterion("shop_note not in", values, "shopNote");
            return (Criteria) this;
        }

        public Criteria andShopNoteBetween(String value1, String value2) {
            addCriterion("shop_note between", value1, value2, "shopNote");
            return (Criteria) this;
        }

        public Criteria andShopNoteNotBetween(String value1, String value2) {
            addCriterion("shop_note not between", value1, value2, "shopNote");
            return (Criteria) this;
        }

        public Criteria andShopSaleIsNull() {
            addCriterion("shop_sale is null");
            return (Criteria) this;
        }

        public Criteria andShopSaleIsNotNull() {
            addCriterion("shop_sale is not null");
            return (Criteria) this;
        }

        public Criteria andShopSaleEqualTo(Integer value) {
            addCriterion("shop_sale =", value, "shopSale");
            return (Criteria) this;
        }

        public Criteria andShopSaleNotEqualTo(Integer value) {
            addCriterion("shop_sale <>", value, "shopSale");
            return (Criteria) this;
        }

        public Criteria andShopSaleGreaterThan(Integer value) {
            addCriterion("shop_sale >", value, "shopSale");
            return (Criteria) this;
        }

        public Criteria andShopSaleGreaterThanOrEqualTo(Integer value) {
            addCriterion("shop_sale >=", value, "shopSale");
            return (Criteria) this;
        }

        public Criteria andShopSaleLessThan(Integer value) {
            addCriterion("shop_sale <", value, "shopSale");
            return (Criteria) this;
        }

        public Criteria andShopSaleLessThanOrEqualTo(Integer value) {
            addCriterion("shop_sale <=", value, "shopSale");
            return (Criteria) this;
        }

        public Criteria andShopSaleIn(List<Integer> values) {
            addCriterion("shop_sale in", values, "shopSale");
            return (Criteria) this;
        }

        public Criteria andShopSaleNotIn(List<Integer> values) {
            addCriterion("shop_sale not in", values, "shopSale");
            return (Criteria) this;
        }

        public Criteria andShopSaleBetween(Integer value1, Integer value2) {
            addCriterion("shop_sale between", value1, value2, "shopSale");
            return (Criteria) this;
        }

        public Criteria andShopSaleNotBetween(Integer value1, Integer value2) {
            addCriterion("shop_sale not between", value1, value2, "shopSale");
            return (Criteria) this;
        }

        public Criteria andShopIconIsNull() {
            addCriterion("shop_icon is null");
            return (Criteria) this;
        }

        public Criteria andShopIconIsNotNull() {
            addCriterion("shop_icon is not null");
            return (Criteria) this;
        }

        public Criteria andShopIconEqualTo(String value) {
            addCriterion("shop_icon =", value, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconNotEqualTo(String value) {
            addCriterion("shop_icon <>", value, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconGreaterThan(String value) {
            addCriterion("shop_icon >", value, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconGreaterThanOrEqualTo(String value) {
            addCriterion("shop_icon >=", value, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconLessThan(String value) {
            addCriterion("shop_icon <", value, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconLessThanOrEqualTo(String value) {
            addCriterion("shop_icon <=", value, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconLike(String value) {
            addCriterion("shop_icon like", value, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconNotLike(String value) {
            addCriterion("shop_icon not like", value, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconIn(List<String> values) {
            addCriterion("shop_icon in", values, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconNotIn(List<String> values) {
            addCriterion("shop_icon not in", values, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconBetween(String value1, String value2) {
            addCriterion("shop_icon between", value1, value2, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconNotBetween(String value1, String value2) {
            addCriterion("shop_icon not between", value1, value2, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopDescIsNull() {
            addCriterion("shop_desc is null");
            return (Criteria) this;
        }

        public Criteria andShopDescIsNotNull() {
            addCriterion("shop_desc is not null");
            return (Criteria) this;
        }

        public Criteria andShopDescEqualTo(String value) {
            addCriterion("shop_desc =", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescNotEqualTo(String value) {
            addCriterion("shop_desc <>", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescGreaterThan(String value) {
            addCriterion("shop_desc >", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescGreaterThanOrEqualTo(String value) {
            addCriterion("shop_desc >=", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescLessThan(String value) {
            addCriterion("shop_desc <", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescLessThanOrEqualTo(String value) {
            addCriterion("shop_desc <=", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescLike(String value) {
            addCriterion("shop_desc like", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescNotLike(String value) {
            addCriterion("shop_desc not like", value, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescIn(List<String> values) {
            addCriterion("shop_desc in", values, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescNotIn(List<String> values) {
            addCriterion("shop_desc not in", values, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescBetween(String value1, String value2) {
            addCriterion("shop_desc between", value1, value2, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopDescNotBetween(String value1, String value2) {
            addCriterion("shop_desc not between", value1, value2, "shopDesc");
            return (Criteria) this;
        }

        public Criteria andShopGradeIsNull() {
            addCriterion("shop_grade is null");
            return (Criteria) this;
        }

        public Criteria andShopGradeIsNotNull() {
            addCriterion("shop_grade is not null");
            return (Criteria) this;
        }

        public Criteria andShopGradeEqualTo(Integer value) {
            addCriterion("shop_grade =", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeNotEqualTo(Integer value) {
            addCriterion("shop_grade <>", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeGreaterThan(Integer value) {
            addCriterion("shop_grade >", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("shop_grade >=", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeLessThan(Integer value) {
            addCriterion("shop_grade <", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeLessThanOrEqualTo(Integer value) {
            addCriterion("shop_grade <=", value, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeIn(List<Integer> values) {
            addCriterion("shop_grade in", values, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeNotIn(List<Integer> values) {
            addCriterion("shop_grade not in", values, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeBetween(Integer value1, Integer value2) {
            addCriterion("shop_grade between", value1, value2, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("shop_grade not between", value1, value2, "shopGrade");
            return (Criteria) this;
        }

        public Criteria andShopStateIsNull() {
            addCriterion("shop_state is null");
            return (Criteria) this;
        }

        public Criteria andShopStateIsNotNull() {
            addCriterion("shop_state is not null");
            return (Criteria) this;
        }

        public Criteria andShopStateEqualTo(Integer value) {
            addCriterion("shop_state =", value, "shopState");
            return (Criteria) this;
        }

        public Criteria andShopStateNotEqualTo(Integer value) {
            addCriterion("shop_state <>", value, "shopState");
            return (Criteria) this;
        }

        public Criteria andShopStateGreaterThan(Integer value) {
            addCriterion("shop_state >", value, "shopState");
            return (Criteria) this;
        }

        public Criteria andShopStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("shop_state >=", value, "shopState");
            return (Criteria) this;
        }

        public Criteria andShopStateLessThan(Integer value) {
            addCriterion("shop_state <", value, "shopState");
            return (Criteria) this;
        }

        public Criteria andShopStateLessThanOrEqualTo(Integer value) {
            addCriterion("shop_state <=", value, "shopState");
            return (Criteria) this;
        }

        public Criteria andShopStateIn(List<Integer> values) {
            addCriterion("shop_state in", values, "shopState");
            return (Criteria) this;
        }

        public Criteria andShopStateNotIn(List<Integer> values) {
            addCriterion("shop_state not in", values, "shopState");
            return (Criteria) this;
        }

        public Criteria andShopStateBetween(Integer value1, Integer value2) {
            addCriterion("shop_state between", value1, value2, "shopState");
            return (Criteria) this;
        }

        public Criteria andShopStateNotBetween(Integer value1, Integer value2) {
            addCriterion("shop_state not between", value1, value2, "shopState");
            return (Criteria) this;
        }

        public Criteria andLimitPriceIsNull() {
            addCriterion("limit_price is null");
            return (Criteria) this;
        }

        public Criteria andLimitPriceIsNotNull() {
            addCriterion("limit_price is not null");
            return (Criteria) this;
        }

        public Criteria andLimitPriceEqualTo(BigDecimal value) {
            addCriterion("limit_price =", value, "limitPrice");
            return (Criteria) this;
        }

        public Criteria andLimitPriceNotEqualTo(BigDecimal value) {
            addCriterion("limit_price <>", value, "limitPrice");
            return (Criteria) this;
        }

        public Criteria andLimitPriceGreaterThan(BigDecimal value) {
            addCriterion("limit_price >", value, "limitPrice");
            return (Criteria) this;
        }

        public Criteria andLimitPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("limit_price >=", value, "limitPrice");
            return (Criteria) this;
        }

        public Criteria andLimitPriceLessThan(BigDecimal value) {
            addCriterion("limit_price <", value, "limitPrice");
            return (Criteria) this;
        }

        public Criteria andLimitPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("limit_price <=", value, "limitPrice");
            return (Criteria) this;
        }

        public Criteria andLimitPriceIn(List<BigDecimal> values) {
            addCriterion("limit_price in", values, "limitPrice");
            return (Criteria) this;
        }

        public Criteria andLimitPriceNotIn(List<BigDecimal> values) {
            addCriterion("limit_price not in", values, "limitPrice");
            return (Criteria) this;
        }

        public Criteria andLimitPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("limit_price between", value1, value2, "limitPrice");
            return (Criteria) this;
        }

        public Criteria andLimitPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("limit_price not between", value1, value2, "limitPrice");
            return (Criteria) this;
        }

        public Criteria andSendPriceIsNull() {
            addCriterion("send_price is null");
            return (Criteria) this;
        }

        public Criteria andSendPriceIsNotNull() {
            addCriterion("send_price is not null");
            return (Criteria) this;
        }

        public Criteria andSendPriceEqualTo(BigDecimal value) {
            addCriterion("send_price =", value, "sendPrice");
            return (Criteria) this;
        }

        public Criteria andSendPriceNotEqualTo(BigDecimal value) {
            addCriterion("send_price <>", value, "sendPrice");
            return (Criteria) this;
        }

        public Criteria andSendPriceGreaterThan(BigDecimal value) {
            addCriterion("send_price >", value, "sendPrice");
            return (Criteria) this;
        }

        public Criteria andSendPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("send_price >=", value, "sendPrice");
            return (Criteria) this;
        }

        public Criteria andSendPriceLessThan(BigDecimal value) {
            addCriterion("send_price <", value, "sendPrice");
            return (Criteria) this;
        }

        public Criteria andSendPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("send_price <=", value, "sendPrice");
            return (Criteria) this;
        }

        public Criteria andSendPriceIn(List<BigDecimal> values) {
            addCriterion("send_price in", values, "sendPrice");
            return (Criteria) this;
        }

        public Criteria andSendPriceNotIn(List<BigDecimal> values) {
            addCriterion("send_price not in", values, "sendPrice");
            return (Criteria) this;
        }

        public Criteria andSendPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("send_price between", value1, value2, "sendPrice");
            return (Criteria) this;
        }

        public Criteria andSendPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("send_price not between", value1, value2, "sendPrice");
            return (Criteria) this;
        }

        public Criteria andWorkTimeIsNull() {
            addCriterion("work_time is null");
            return (Criteria) this;
        }

        public Criteria andWorkTimeIsNotNull() {
            addCriterion("work_time is not null");
            return (Criteria) this;
        }

        public Criteria andWorkTimeEqualTo(Date value) {
            addCriterionForJDBCTime("work_time =", value, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeNotEqualTo(Date value) {
            addCriterionForJDBCTime("work_time <>", value, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeGreaterThan(Date value) {
            addCriterionForJDBCTime("work_time >", value, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("work_time >=", value, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeLessThan(Date value) {
            addCriterionForJDBCTime("work_time <", value, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCTime("work_time <=", value, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeIn(List<Date> values) {
            addCriterionForJDBCTime("work_time in", values, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeNotIn(List<Date> values) {
            addCriterionForJDBCTime("work_time not in", values, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("work_time between", value1, value2, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCTime("work_time not between", value1, value2, "workTime");
            return (Criteria) this;
        }

        public Criteria andIsRecomIsNull() {
            addCriterion("is_recom is null");
            return (Criteria) this;
        }

        public Criteria andIsRecomIsNotNull() {
            addCriterion("is_recom is not null");
            return (Criteria) this;
        }

        public Criteria andIsRecomEqualTo(Boolean value) {
            addCriterion("is_recom =", value, "isRecom");
            return (Criteria) this;
        }

        public Criteria andIsRecomNotEqualTo(Boolean value) {
            addCriterion("is_recom <>", value, "isRecom");
            return (Criteria) this;
        }

        public Criteria andIsRecomGreaterThan(Boolean value) {
            addCriterion("is_recom >", value, "isRecom");
            return (Criteria) this;
        }

        public Criteria andIsRecomGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_recom >=", value, "isRecom");
            return (Criteria) this;
        }

        public Criteria andIsRecomLessThan(Boolean value) {
            addCriterion("is_recom <", value, "isRecom");
            return (Criteria) this;
        }

        public Criteria andIsRecomLessThanOrEqualTo(Boolean value) {
            addCriterion("is_recom <=", value, "isRecom");
            return (Criteria) this;
        }

        public Criteria andIsRecomIn(List<Boolean> values) {
            addCriterion("is_recom in", values, "isRecom");
            return (Criteria) this;
        }

        public Criteria andIsRecomNotIn(List<Boolean> values) {
            addCriterion("is_recom not in", values, "isRecom");
            return (Criteria) this;
        }

        public Criteria andIsRecomBetween(Boolean value1, Boolean value2) {
            addCriterion("is_recom between", value1, value2, "isRecom");
            return (Criteria) this;
        }

        public Criteria andIsRecomNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_recom not between", value1, value2, "isRecom");
            return (Criteria) this;
        }

        public Criteria andRecomOrderIsNull() {
            addCriterion("recom_order is null");
            return (Criteria) this;
        }

        public Criteria andRecomOrderIsNotNull() {
            addCriterion("recom_order is not null");
            return (Criteria) this;
        }

        public Criteria andRecomOrderEqualTo(Integer value) {
            addCriterion("recom_order =", value, "recomOrder");
            return (Criteria) this;
        }

        public Criteria andRecomOrderNotEqualTo(Integer value) {
            addCriterion("recom_order <>", value, "recomOrder");
            return (Criteria) this;
        }

        public Criteria andRecomOrderGreaterThan(Integer value) {
            addCriterion("recom_order >", value, "recomOrder");
            return (Criteria) this;
        }

        public Criteria andRecomOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("recom_order >=", value, "recomOrder");
            return (Criteria) this;
        }

        public Criteria andRecomOrderLessThan(Integer value) {
            addCriterion("recom_order <", value, "recomOrder");
            return (Criteria) this;
        }

        public Criteria andRecomOrderLessThanOrEqualTo(Integer value) {
            addCriterion("recom_order <=", value, "recomOrder");
            return (Criteria) this;
        }

        public Criteria andRecomOrderIn(List<Integer> values) {
            addCriterion("recom_order in", values, "recomOrder");
            return (Criteria) this;
        }

        public Criteria andRecomOrderNotIn(List<Integer> values) {
            addCriterion("recom_order not in", values, "recomOrder");
            return (Criteria) this;
        }

        public Criteria andRecomOrderBetween(Integer value1, Integer value2) {
            addCriterion("recom_order between", value1, value2, "recomOrder");
            return (Criteria) this;
        }

        public Criteria andRecomOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("recom_order not between", value1, value2, "recomOrder");
            return (Criteria) this;
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