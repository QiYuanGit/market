package cn.haizhi.market.main.bean.richard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GroupProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GroupProductExample() {
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

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Long value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Long value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Long value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Long value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Long value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Long value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Long> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Long> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Long value1, Long value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Long value1, Long value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductOpriceIsNull() {
            addCriterion("product_oprice is null");
            return (Criteria) this;
        }

        public Criteria andProductOpriceIsNotNull() {
            addCriterion("product_oprice is not null");
            return (Criteria) this;
        }

        public Criteria andProductOpriceEqualTo(BigDecimal value) {
            addCriterion("product_oprice =", value, "productOprice");
            return (Criteria) this;
        }

        public Criteria andProductOpriceNotEqualTo(BigDecimal value) {
            addCriterion("product_oprice <>", value, "productOprice");
            return (Criteria) this;
        }

        public Criteria andProductOpriceGreaterThan(BigDecimal value) {
            addCriterion("product_oprice >", value, "productOprice");
            return (Criteria) this;
        }

        public Criteria andProductOpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("product_oprice >=", value, "productOprice");
            return (Criteria) this;
        }

        public Criteria andProductOpriceLessThan(BigDecimal value) {
            addCriterion("product_oprice <", value, "productOprice");
            return (Criteria) this;
        }

        public Criteria andProductOpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("product_oprice <=", value, "productOprice");
            return (Criteria) this;
        }

        public Criteria andProductOpriceIn(List<BigDecimal> values) {
            addCriterion("product_oprice in", values, "productOprice");
            return (Criteria) this;
        }

        public Criteria andProductOpriceNotIn(List<BigDecimal> values) {
            addCriterion("product_oprice not in", values, "productOprice");
            return (Criteria) this;
        }

        public Criteria andProductOpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_oprice between", value1, value2, "productOprice");
            return (Criteria) this;
        }

        public Criteria andProductOpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_oprice not between", value1, value2, "productOprice");
            return (Criteria) this;
        }

        public Criteria andProductNpriceIsNull() {
            addCriterion("product_nprice is null");
            return (Criteria) this;
        }

        public Criteria andProductNpriceIsNotNull() {
            addCriterion("product_nprice is not null");
            return (Criteria) this;
        }

        public Criteria andProductNpriceEqualTo(BigDecimal value) {
            addCriterion("product_nprice =", value, "productNprice");
            return (Criteria) this;
        }

        public Criteria andProductNpriceNotEqualTo(BigDecimal value) {
            addCriterion("product_nprice <>", value, "productNprice");
            return (Criteria) this;
        }

        public Criteria andProductNpriceGreaterThan(BigDecimal value) {
            addCriterion("product_nprice >", value, "productNprice");
            return (Criteria) this;
        }

        public Criteria andProductNpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("product_nprice >=", value, "productNprice");
            return (Criteria) this;
        }

        public Criteria andProductNpriceLessThan(BigDecimal value) {
            addCriterion("product_nprice <", value, "productNprice");
            return (Criteria) this;
        }

        public Criteria andProductNpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("product_nprice <=", value, "productNprice");
            return (Criteria) this;
        }

        public Criteria andProductNpriceIn(List<BigDecimal> values) {
            addCriterion("product_nprice in", values, "productNprice");
            return (Criteria) this;
        }

        public Criteria andProductNpriceNotIn(List<BigDecimal> values) {
            addCriterion("product_nprice not in", values, "productNprice");
            return (Criteria) this;
        }

        public Criteria andProductNpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_nprice between", value1, value2, "productNprice");
            return (Criteria) this;
        }

        public Criteria andProductNpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_nprice not between", value1, value2, "productNprice");
            return (Criteria) this;
        }

        public Criteria andProductStockIsNull() {
            addCriterion("product_stock is null");
            return (Criteria) this;
        }

        public Criteria andProductStockIsNotNull() {
            addCriterion("product_stock is not null");
            return (Criteria) this;
        }

        public Criteria andProductStockEqualTo(Integer value) {
            addCriterion("product_stock =", value, "productStock");
            return (Criteria) this;
        }

        public Criteria andProductStockNotEqualTo(Integer value) {
            addCriterion("product_stock <>", value, "productStock");
            return (Criteria) this;
        }

        public Criteria andProductStockGreaterThan(Integer value) {
            addCriterion("product_stock >", value, "productStock");
            return (Criteria) this;
        }

        public Criteria andProductStockGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_stock >=", value, "productStock");
            return (Criteria) this;
        }

        public Criteria andProductStockLessThan(Integer value) {
            addCriterion("product_stock <", value, "productStock");
            return (Criteria) this;
        }

        public Criteria andProductStockLessThanOrEqualTo(Integer value) {
            addCriterion("product_stock <=", value, "productStock");
            return (Criteria) this;
        }

        public Criteria andProductStockIn(List<Integer> values) {
            addCriterion("product_stock in", values, "productStock");
            return (Criteria) this;
        }

        public Criteria andProductStockNotIn(List<Integer> values) {
            addCriterion("product_stock not in", values, "productStock");
            return (Criteria) this;
        }

        public Criteria andProductStockBetween(Integer value1, Integer value2) {
            addCriterion("product_stock between", value1, value2, "productStock");
            return (Criteria) this;
        }

        public Criteria andProductStockNotBetween(Integer value1, Integer value2) {
            addCriterion("product_stock not between", value1, value2, "productStock");
            return (Criteria) this;
        }

        public Criteria andProductUnitIsNull() {
            addCriterion("product_unit is null");
            return (Criteria) this;
        }

        public Criteria andProductUnitIsNotNull() {
            addCriterion("product_unit is not null");
            return (Criteria) this;
        }

        public Criteria andProductUnitEqualTo(String value) {
            addCriterion("product_unit =", value, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitNotEqualTo(String value) {
            addCriterion("product_unit <>", value, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitGreaterThan(String value) {
            addCriterion("product_unit >", value, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitGreaterThanOrEqualTo(String value) {
            addCriterion("product_unit >=", value, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitLessThan(String value) {
            addCriterion("product_unit <", value, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitLessThanOrEqualTo(String value) {
            addCriterion("product_unit <=", value, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitLike(String value) {
            addCriterion("product_unit like", value, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitNotLike(String value) {
            addCriterion("product_unit not like", value, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitIn(List<String> values) {
            addCriterion("product_unit in", values, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitNotIn(List<String> values) {
            addCriterion("product_unit not in", values, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitBetween(String value1, String value2) {
            addCriterion("product_unit between", value1, value2, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductUnitNotBetween(String value1, String value2) {
            addCriterion("product_unit not between", value1, value2, "productUnit");
            return (Criteria) this;
        }

        public Criteria andProductDescIsNull() {
            addCriterion("product_desc is null");
            return (Criteria) this;
        }

        public Criteria andProductDescIsNotNull() {
            addCriterion("product_desc is not null");
            return (Criteria) this;
        }

        public Criteria andProductDescEqualTo(String value) {
            addCriterion("product_desc =", value, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescNotEqualTo(String value) {
            addCriterion("product_desc <>", value, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescGreaterThan(String value) {
            addCriterion("product_desc >", value, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescGreaterThanOrEqualTo(String value) {
            addCriterion("product_desc >=", value, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescLessThan(String value) {
            addCriterion("product_desc <", value, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescLessThanOrEqualTo(String value) {
            addCriterion("product_desc <=", value, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescLike(String value) {
            addCriterion("product_desc like", value, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescNotLike(String value) {
            addCriterion("product_desc not like", value, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescIn(List<String> values) {
            addCriterion("product_desc in", values, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescNotIn(List<String> values) {
            addCriterion("product_desc not in", values, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescBetween(String value1, String value2) {
            addCriterion("product_desc between", value1, value2, "productDesc");
            return (Criteria) this;
        }

        public Criteria andProductDescNotBetween(String value1, String value2) {
            addCriterion("product_desc not between", value1, value2, "productDesc");
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

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Long value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Long value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Long value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Long value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Long value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Long> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Long> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Long value1, Long value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Long value1, Long value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
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