package az.gov.mia.grps.commons.specification.model;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

/**
 * @author Tomasz Kaczmarzyk
 */
public abstract class PathSpecification<T> implements Specification<T> {

    protected String path;

    public PathSpecification(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @SuppressWarnings("unchecked")
    protected <F> Path<F> path(Root<T> root) {
        Path<?> expr = null;
        for (String field : path.split("\\.")) {
            if (expr == null) {
                expr = root.get(field);
            } else {
                expr = expr.get(field);
            }
        }
        return (Path<F>) expr;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((path == null) ? 0 : path.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PathSpecification<?> other = (PathSpecification<?>) obj;
        if (path == null) {
            if (other.path != null) {
                return false;
            }
        } else if (!path.equals(other.path)) {
            return false;
        }
        return true;
    }
}
