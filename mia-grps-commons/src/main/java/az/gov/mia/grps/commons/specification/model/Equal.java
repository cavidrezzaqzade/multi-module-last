package az.gov.mia.grps.commons.specification.model;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import az.gov.mia.grps.commons.specification.Converter;

public class Equal<T> extends PathSpecification<T> {

    protected String expectedValue;
    private Converter converter;

    public Equal(String path, String[] httpParamValues, Converter converter) {
        super(path);
        if (httpParamValues == null || httpParamValues.length != 1) {
            throw new IllegalArgumentException();
        }
        this.expectedValue = httpParamValues[0];
        this.converter = converter;
    }

    public Equal(String path, String paramValue) {
        this(path, paramValue, Converter.DEFAULT);
    }
    
    public Equal(String path, String paramValue, Converter converter) {
        super(path);
        if (paramValue == null) {
            throw new IllegalArgumentException();
        }
        this.expectedValue = paramValue;
        this.converter = converter;
    }

    public Equal(String path, Long paramValue, Converter converter) {
        super(path);
        if (paramValue == null) {
            throw new IllegalArgumentException();
        }
        this.expectedValue = "" + paramValue;
        this.converter = converter;
    }
    
    public Equal(String path, Long paramValue) {
        this(path, paramValue, Converter.LongConverter());
    }

    public String getValue() {
        return expectedValue;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        Class<?> typeOnPath = path(root).getModel().getBindableJavaType();

        return cb.equal(path(root), converter.convert(expectedValue, typeOnPath));
    }

}
