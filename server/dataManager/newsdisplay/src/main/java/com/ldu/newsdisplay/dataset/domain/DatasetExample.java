package com.ldu.newsdisplay.dataset.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DatasetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DatasetExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andDatasetIdIsNull() {
            addCriterion("dataset_id is null");
            return (Criteria) this;
        }

        public Criteria andDatasetIdIsNotNull() {
            addCriterion("dataset_id is not null");
            return (Criteria) this;
        }

        public Criteria andDatasetIdEqualTo(Long value) {
            addCriterion("dataset_id =", value, "datasetId");
            return (Criteria) this;
        }

        public Criteria andDatasetIdNotEqualTo(Long value) {
            addCriterion("dataset_id <>", value, "datasetId");
            return (Criteria) this;
        }

        public Criteria andDatasetIdGreaterThan(Long value) {
            addCriterion("dataset_id >", value, "datasetId");
            return (Criteria) this;
        }

        public Criteria andDatasetIdGreaterThanOrEqualTo(Long value) {
            addCriterion("dataset_id >=", value, "datasetId");
            return (Criteria) this;
        }

        public Criteria andDatasetIdLessThan(Long value) {
            addCriterion("dataset_id <", value, "datasetId");
            return (Criteria) this;
        }

        public Criteria andDatasetIdLessThanOrEqualTo(Long value) {
            addCriterion("dataset_id <=", value, "datasetId");
            return (Criteria) this;
        }

        public Criteria andDatasetIdIn(List<Long> values) {
            addCriterion("dataset_id in", values, "datasetId");
            return (Criteria) this;
        }

        public Criteria andDatasetIdNotIn(List<Long> values) {
            addCriterion("dataset_id not in", values, "datasetId");
            return (Criteria) this;
        }

        public Criteria andDatasetIdBetween(Long value1, Long value2) {
            addCriterion("dataset_id between", value1, value2, "datasetId");
            return (Criteria) this;
        }

        public Criteria andDatasetIdNotBetween(Long value1, Long value2) {
            addCriterion("dataset_id not between", value1, value2, "datasetId");
            return (Criteria) this;
        }

        public Criteria andNewsCountIsNull() {
            addCriterion("news_count is null");
            return (Criteria) this;
        }

        public Criteria andNewsCountIsNotNull() {
            addCriterion("news_count is not null");
            return (Criteria) this;
        }

        public Criteria andNewsCountEqualTo(Integer value) {
            addCriterion("news_count =", value, "newsCount");
            return (Criteria) this;
        }

        public Criteria andNewsCountNotEqualTo(Integer value) {
            addCriterion("news_count <>", value, "newsCount");
            return (Criteria) this;
        }

        public Criteria andNewsCountGreaterThan(Integer value) {
            addCriterion("news_count >", value, "newsCount");
            return (Criteria) this;
        }

        public Criteria andNewsCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("news_count >=", value, "newsCount");
            return (Criteria) this;
        }

        public Criteria andNewsCountLessThan(Integer value) {
            addCriterion("news_count <", value, "newsCount");
            return (Criteria) this;
        }

        public Criteria andNewsCountLessThanOrEqualTo(Integer value) {
            addCriterion("news_count <=", value, "newsCount");
            return (Criteria) this;
        }

        public Criteria andNewsCountIn(List<Integer> values) {
            addCriterion("news_count in", values, "newsCount");
            return (Criteria) this;
        }

        public Criteria andNewsCountNotIn(List<Integer> values) {
            addCriterion("news_count not in", values, "newsCount");
            return (Criteria) this;
        }

        public Criteria andNewsCountBetween(Integer value1, Integer value2) {
            addCriterion("news_count between", value1, value2, "newsCount");
            return (Criteria) this;
        }

        public Criteria andNewsCountNotBetween(Integer value1, Integer value2) {
            addCriterion("news_count not between", value1, value2, "newsCount");
            return (Criteria) this;
        }

        public Criteria andFakeCountIsNull() {
            addCriterion("fake_count is null");
            return (Criteria) this;
        }

        public Criteria andFakeCountIsNotNull() {
            addCriterion("fake_count is not null");
            return (Criteria) this;
        }

        public Criteria andFakeCountEqualTo(Integer value) {
            addCriterion("fake_count =", value, "fakeCount");
            return (Criteria) this;
        }

        public Criteria andFakeCountNotEqualTo(Integer value) {
            addCriterion("fake_count <>", value, "fakeCount");
            return (Criteria) this;
        }

        public Criteria andFakeCountGreaterThan(Integer value) {
            addCriterion("fake_count >", value, "fakeCount");
            return (Criteria) this;
        }

        public Criteria andFakeCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("fake_count >=", value, "fakeCount");
            return (Criteria) this;
        }

        public Criteria andFakeCountLessThan(Integer value) {
            addCriterion("fake_count <", value, "fakeCount");
            return (Criteria) this;
        }

        public Criteria andFakeCountLessThanOrEqualTo(Integer value) {
            addCriterion("fake_count <=", value, "fakeCount");
            return (Criteria) this;
        }

        public Criteria andFakeCountIn(List<Integer> values) {
            addCriterion("fake_count in", values, "fakeCount");
            return (Criteria) this;
        }

        public Criteria andFakeCountNotIn(List<Integer> values) {
            addCriterion("fake_count not in", values, "fakeCount");
            return (Criteria) this;
        }

        public Criteria andFakeCountBetween(Integer value1, Integer value2) {
            addCriterion("fake_count between", value1, value2, "fakeCount");
            return (Criteria) this;
        }

        public Criteria andFakeCountNotBetween(Integer value1, Integer value2) {
            addCriterion("fake_count not between", value1, value2, "fakeCount");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterionForJDBCDate("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterionForJDBCDate("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterionForJDBCDate("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterionForJDBCDate("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
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