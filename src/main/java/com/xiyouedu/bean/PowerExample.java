package com.xiyouedu.bean;

import java.util.ArrayList;
import java.util.List;

public class PowerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PowerExample() {
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

        public Criteria andPowerIdIsNull() {
            addCriterion("power_id is null");
            return (Criteria) this;
        }

        public Criteria andPowerIdIsNotNull() {
            addCriterion("power_id is not null");
            return (Criteria) this;
        }

        public Criteria andPowerIdEqualTo(Integer value) {
            addCriterion("power_id =", value, "powerId");
            return (Criteria) this;
        }

        public Criteria andPowerIdNotEqualTo(Integer value) {
            addCriterion("power_id <>", value, "powerId");
            return (Criteria) this;
        }

        public Criteria andPowerIdGreaterThan(Integer value) {
            addCriterion("power_id >", value, "powerId");
            return (Criteria) this;
        }

        public Criteria andPowerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("power_id >=", value, "powerId");
            return (Criteria) this;
        }

        public Criteria andPowerIdLessThan(Integer value) {
            addCriterion("power_id <", value, "powerId");
            return (Criteria) this;
        }

        public Criteria andPowerIdLessThanOrEqualTo(Integer value) {
            addCriterion("power_id <=", value, "powerId");
            return (Criteria) this;
        }

        public Criteria andPowerIdIn(List<Integer> values) {
            addCriterion("power_id in", values, "powerId");
            return (Criteria) this;
        }

        public Criteria andPowerIdNotIn(List<Integer> values) {
            addCriterion("power_id not in", values, "powerId");
            return (Criteria) this;
        }

        public Criteria andPowerIdBetween(Integer value1, Integer value2) {
            addCriterion("power_id between", value1, value2, "powerId");
            return (Criteria) this;
        }

        public Criteria andPowerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("power_id not between", value1, value2, "powerId");
            return (Criteria) this;
        }

        public Criteria andPowernameIsNull() {
            addCriterion("powername is null");
            return (Criteria) this;
        }

        public Criteria andPowernameIsNotNull() {
            addCriterion("powername is not null");
            return (Criteria) this;
        }

        public Criteria andPowernameEqualTo(String value) {
            addCriterion("powername =", value, "powername");
            return (Criteria) this;
        }

        public Criteria andPowernameNotEqualTo(String value) {
            addCriterion("powername <>", value, "powername");
            return (Criteria) this;
        }

        public Criteria andPowernameGreaterThan(String value) {
            addCriterion("powername >", value, "powername");
            return (Criteria) this;
        }

        public Criteria andPowernameGreaterThanOrEqualTo(String value) {
            addCriterion("powername >=", value, "powername");
            return (Criteria) this;
        }

        public Criteria andPowernameLessThan(String value) {
            addCriterion("powername <", value, "powername");
            return (Criteria) this;
        }

        public Criteria andPowernameLessThanOrEqualTo(String value) {
            addCriterion("powername <=", value, "powername");
            return (Criteria) this;
        }

        public Criteria andPowernameLike(String value) {
            addCriterion("powername like", value, "powername");
            return (Criteria) this;
        }

        public Criteria andPowernameNotLike(String value) {
            addCriterion("powername not like", value, "powername");
            return (Criteria) this;
        }

        public Criteria andPowernameIn(List<String> values) {
            addCriterion("powername in", values, "powername");
            return (Criteria) this;
        }

        public Criteria andPowernameNotIn(List<String> values) {
            addCriterion("powername not in", values, "powername");
            return (Criteria) this;
        }

        public Criteria andPowernameBetween(String value1, String value2) {
            addCriterion("powername between", value1, value2, "powername");
            return (Criteria) this;
        }

        public Criteria andPowernameNotBetween(String value1, String value2) {
            addCriterion("powername not between", value1, value2, "powername");
            return (Criteria) this;
        }

        public Criteria andPowerurlIsNull() {
            addCriterion("powerurl is null");
            return (Criteria) this;
        }

        public Criteria andPowerurlIsNotNull() {
            addCriterion("powerurl is not null");
            return (Criteria) this;
        }

        public Criteria andPowerurlEqualTo(String value) {
            addCriterion("powerurl =", value, "powerurl");
            return (Criteria) this;
        }

        public Criteria andPowerurlNotEqualTo(String value) {
            addCriterion("powerurl <>", value, "powerurl");
            return (Criteria) this;
        }

        public Criteria andPowerurlGreaterThan(String value) {
            addCriterion("powerurl >", value, "powerurl");
            return (Criteria) this;
        }

        public Criteria andPowerurlGreaterThanOrEqualTo(String value) {
            addCriterion("powerurl >=", value, "powerurl");
            return (Criteria) this;
        }

        public Criteria andPowerurlLessThan(String value) {
            addCriterion("powerurl <", value, "powerurl");
            return (Criteria) this;
        }

        public Criteria andPowerurlLessThanOrEqualTo(String value) {
            addCriterion("powerurl <=", value, "powerurl");
            return (Criteria) this;
        }

        public Criteria andPowerurlLike(String value) {
            addCriterion("powerurl like", value, "powerurl");
            return (Criteria) this;
        }

        public Criteria andPowerurlNotLike(String value) {
            addCriterion("powerurl not like", value, "powerurl");
            return (Criteria) this;
        }

        public Criteria andPowerurlIn(List<String> values) {
            addCriterion("powerurl in", values, "powerurl");
            return (Criteria) this;
        }

        public Criteria andPowerurlNotIn(List<String> values) {
            addCriterion("powerurl not in", values, "powerurl");
            return (Criteria) this;
        }

        public Criteria andPowerurlBetween(String value1, String value2) {
            addCriterion("powerurl between", value1, value2, "powerurl");
            return (Criteria) this;
        }

        public Criteria andPowerurlNotBetween(String value1, String value2) {
            addCriterion("powerurl not between", value1, value2, "powerurl");
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