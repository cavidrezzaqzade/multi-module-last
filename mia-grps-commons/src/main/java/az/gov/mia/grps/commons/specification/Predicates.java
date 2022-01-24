package az.gov.mia.grps.commons.specification;

import az.gov.mia.grps.commons.specification.model.Conjunction;
import az.gov.mia.grps.commons.specification.model.DateBeforeInclusive;
import az.gov.mia.grps.commons.specification.model.DateBetween2;
import az.gov.mia.grps.commons.specification.model.Equal;
import az.gov.mia.grps.commons.specification.model.In;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Rasim R. İmanov
 */
public class Predicates {

    private List<Predicate> predicates;

    public static Predicates parse(Specification specRoot) {

        Logger logger = LoggerFactory.getLogger(Predicates.class);
        if (specRoot == null) {
            return new Predicates();
        }
        logger.debug("Parse predicates for spec class:" + specRoot.getClass().getName());
        if (specRoot instanceof Conjunction) {
            return parseConjunction((Conjunction) specRoot);
        }

        return new Predicates();
    }

    public static Predicates create() {

        return new Predicates();
    }

    private static Predicates parseConjunction(Conjunction conjunction) {

        Logger logger = LoggerFactory.getLogger(Predicates.class);
        Collection<Specification> specs = conjunction.getSpecifications();
        logger.debug("Conjunction spec item size:" + specs.size());
        List<Predicate> predList = new ArrayList<>(specs.size());

        for (Specification spec : specs) {

            logger.debug("For spec:" + spec.getClass().getName());
            if (spec instanceof DateBeforeInclusive) {

                DateBeforeInclusive cspec = (DateBeforeInclusive) spec;
                predList.add(new Predicate(cspec.getPath(), Operator.LE,
                        new PredicateValue("date", cspec.getValue())));
            } else if (spec instanceof In) {

                In cspec = (In) spec;
                predList.add(new Predicate(cspec.getPath(), Operator.IN,
                        new PredicateValue("string[]", cspec.getValue())));
            } else if (spec instanceof Equal) {

                Equal cspec = (Equal) spec;
                logger.debug("Parsing for equal spec:" + cspec.getPath());
                predList.add(new Predicate(cspec.getPath(), Operator.EQ,
                        new PredicateValue("string", cspec.getValue())));
            } else if(spec instanceof DateBetween2 ){
                
                DateBetween2 dspec = (DateBetween2) spec;
                predList.add(new Predicate(dspec.getPath(), Operator.BETWEEN,
                        new PredicateValue("dateRange", new Date[]{dspec.getAfter(), dspec.getBefore()})));
                
            
            }
        }

        return new Predicates(predList);
    }

    @Override
    public String toString() {
        return "Predicates{" + "all=" + predicates + '}';
    }

    private Predicates() {
        this.predicates = new ArrayList<>();
    }

    private Predicates(List<Predicate> list) {
        this.predicates = list;
    }

    public List<Predicate> predicates() {
        return predicates;
    }

    public boolean contains(String path, Operator operator) {
        for (Predicate p : predicates) {
            if (p.path.equals(path) && p.operator == operator) {
                return true;
            }
        }
        return false;
    }

    public Predicate get(String path, Operator operator) {
        for (Predicate p : predicates) {
            if (p.path.equals(path) && p.operator == operator) {
                return p;
            }
        }
        return null;
    }

    public Predicates add(String path, Operator operator, String value) {
        Predicate predicate = new Predicate(path, operator, new PredicateValue("string", value));
        this.predicates.add(predicate);
        return this;
    }

    public Predicates add(String path, String value) {
        Predicate predicate = new Predicate(path, Operator.EQ, new PredicateValue("string", value));
        this.predicates.add(predicate);
        return this;
    }

    public static class Predicate {

        protected String path;
        protected Operator operator;
        protected PredicateValue value;

        public Predicate() {
        }

        public Predicate(String path, Operator operator, PredicateValue value) {
            this.path = path;
            this.operator = operator;
            this.value = value;
        }

        public String path() {
            return path;
        }

        public Operator operator() {
            return operator;
        }

        public PredicateValue value() {
            return value;
        }

        @Override
        public String toString() {
            return "Predicate{" + "path=" + path + ", operator=" + operator + '}';
        }

    }

    public static class PredicateValue {

        protected String type;
        protected Object value;

        public PredicateValue(String type, Object value) {
            this.type = type;
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public <T> T getValue() {
            return (T) value;
        }

    }

    public static enum Operator {

        EQ, BETWEEN, LIKE, GT, GE, LT, LE, IN
    }

    void test(Specification spec) {

        Predicates w = Predicates.parse(spec);

        for (Predicate predicate : w.predicates()) {

            PredicateValue val = predicate.value();

            if (predicate.operator().equals(Operator.EQ)) {

                Date[] dbw = predicate.value.getValue();
            }

        }
    }
}
