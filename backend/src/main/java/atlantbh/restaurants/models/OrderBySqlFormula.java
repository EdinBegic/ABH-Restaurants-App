package atlantbh.restaurants.models;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Order;

// guide on http://blog.tremend.ro/how-to-order-by-a-custom-sql-formulaexpression-when-using-hibernate-criteria-api/
public class OrderBySqlFormula extends Order {
    private String sqlFormula;

    /**
     * Constructor for Order. * @param sqlFormula an SQL formula that will be appended to the resulting SQL query
     */
    protected OrderBySqlFormula(String sqlFormula) {
        super(sqlFormula, true);
        this.sqlFormula = sqlFormula;
    }

    /**
     * Custom order * * @param sqlFormula an SQL formula that will be appended to the resulting SQL query * @return Order
     */
    public static Order sqlFormula(String sqlFormula) {
        return new OrderBySqlFormula(sqlFormula);
    }

    public String toString() {
        return sqlFormula;
    }

    public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
        return sqlFormula;
    }
}