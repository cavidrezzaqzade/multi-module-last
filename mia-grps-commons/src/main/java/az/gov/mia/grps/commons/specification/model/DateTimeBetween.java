package az.gov.mia.grps.commons.specification.model;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import az.gov.mia.grps.commons.specification.Converter;

/**
 * Filters with {@code path between arg1 and arg2} where-clause.
 *
 * @author Tomasz Kaczmarzyk
 */
public class DateTimeBetween<T> extends DateSpecification<T> {

    private Date after;
    private Date before;

    public DateTimeBetween(String path, String[] args, Converter converter) throws ParseException {
        super(path, args, converter);
        if (args == null || args.length != 2) {
            throw new IllegalArgumentException("expected 2 http params (date boundaries), but was: " + args);
        }
        String afterDateStr = args[0];
        String beforeDateStr = args[1];
        this.after = converter.convertToDateTime(afterDateStr);
        this.before = converter.convertToDateTime(beforeDateStr);
    }
    
    
    public DateTimeBetween(String path, Date after, Date before, Converter converter) throws ParseException {
        super(path, null, converter);       
        this.after = after;
        this.before = before;
    }
    

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.between(this.<Date>path(root), after, before);
    }
}
