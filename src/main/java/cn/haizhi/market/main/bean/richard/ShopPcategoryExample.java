package cn.haizhi.market.main.bean.richard;

import java.util.ArrayList;
import java.util.List;

public class ShopPcategoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ShopPcategoryExample() {
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

        public Criteria andJoinIdIsNull() {
            addCriterion("join_id is null");
            return (Criteria) this;
        }

        public Criteria andJoinIdIsNotNull() {
            addCriterion("join_id is not null");
            return (Criteria) this;
        }

        public Criteria andJoinIdEqualTo(Long value) {
            addCriterion("join_id =", value, "joinId");
            return (Criteria) this;
        }

        public Criteria andJoinIdNotEqualTo(Long value) {
            addCriterion("join_id <>", value, "joinId");
            return (Criteria) this;
        }

        public Criteria andJoinIdGreaterThan(Long value) {
            addCriterion("join_id >", value, "joinId");
            return (Criteria) this;
        }

        public Criteria andJoinIdGreaterThanOrEqualTo(Long value) {
            addCriterion("join_id >=", value, "joinId");
            return (Criteria) this;
        }

        public Criteria andJoinIdLessThan(Long value) {
            addCriterion("join_id <", value, "joinId");
            return (Criteria) this;
        }

        public Criteria andJoinIdLessThanOrEqualTo(Long value) {
            addCriterion("join_id <=", value, "joinId");
            return (Criteria) this;
        }

        public Criteria andJoinIdIn(List<Long> values) {
            addCriterion("join_id in", values, "joinId");
            return (Criteria) this;
        }

        public Criteria andJoinIdNotIn(List<Long> values) {
            addCriterion("join_id not in", values, "joinId");
            return (Criteria) this;
        }

        public Criteria andJoinIdBetween(Long value1, Long value2) {
            addCriterion("join_id between", value1, value2, "joinId");
            return (Criteria) this;
        }

        public Criteria andJoinIdNotBetween(Long value1, Long value2) {
            addCriterion("join_id not between", value1, value2, "joinId");
            return (Criteria) this;
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

        public Criteria andPcategoryIdIsNull() {
            addCriterion("pcategory_id is null");
            return (Criteria) this;
        }

        public Criteria andPcategoryIdIsNotNull() {
            addCriterion("pcategory_id is not null");
            return (Criteria) this;
        }

        public Criteria andPcategoryIdEqualTo(Long value) {
            addCriterion("pcategory_id =", value, "pcategoryId");
            return (Criteria) this;
        }

        public Criteria andPcategoryIdNotEqualTo(Long value) {
            addCriterion("pcategory_id <>", value, "pcategoryId");
            return (Criteria) this;
        }

        public Criteria andPcategoryIdGreaterThan(Long value) {
            addCriterion("pcategory_id >", value, "pcategoryId");
            return (Criteria) this;
        }

        public Criteria andPcategoryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("pcategory_id >=", value, "pcategoryId");
            return (Criteria) this;
        }

        public Criteria andPcategoryIdLessThan(Long value) {
            addCriterion("pcategory_id <", value, "pcategoryId");
            return (Criteria) this;
        }

        public Criteria andPcategoryIdLessThanOrEqualTo(Long value) {
            addCriterion("pcategory_id <=", value, "pcategoryId");
            return (Criteria) this;
        }

        public Criteria andPcategoryIdIn(List<Long> values) {
            addCriterion("pcategory_id in", values, "pcategoryId");
            return (Criteria) this;
        }

        public Criteria andPcategoryIdNotIn(List<Long> values) {
            addCriterion("pcategory_id not in", values, "pcategoryId");
            return (Criteria) this;
        }

        public Criteria andPcategoryIdBetween(Long value1, Long value2) {
            addCriterion("pcategory_id between", value1, value2, "pcategoryId");
            return (Criteria) this;
        }

        public Criteria andPcategoryIdNotBetween(Long value1, Long value2) {
            addCriterion("pcategory_id not between", value1, value2, "pcategoryId");
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