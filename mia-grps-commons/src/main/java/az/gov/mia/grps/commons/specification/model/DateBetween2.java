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
public class DateBetween2<T> extends DateSpecification<T> {

    private Date after;
    private Date before;

    public DateBetween2(String path, String[] args, Converter converter) throws ParseException {
        super(path, args, converter);
        if (args == null || args.length != 1) {
            throw new IllegalArgumentException("expected 1 http params (date boundaries), but was: " + args);
        }
        
        String[] args0 = args[0].split(",");
        String afterDateStr = args0[0] + " 00:00:00";
        String beforeDateStr = args0[1] + " 23:59:59";
        this.after = converter.convertToDateTime(afterDateStr);
        this.before = converter.convertToDateTime(beforeDateStr);
    }

    public DateBetween2(String path, Date dt1, Date dt2) {
        super(path);
        this.after = dt1;
        this.before = dt2;
    }

    public Date getAfter() {
        return after;
    }

    public Date getBefore() {
        return before;
    }
    
    

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.between(this.<Date>path(root), after, before);
    }
}
