package com.aranga.hbm;

import org.hibernate.dialect.SQLServerDialect;
import org.hibernate.type.StringType;

import java.sql.Types;


/**
 * Override SQLServerDialect to define UTF types which are
 * part of JDBC 4.0 but strangely absent.
 */
public class SQLServer2K5Dialect extends SQLServerDialect
{

    /**
     * Register NVARCHAR, NCHAR and LONGNVARCHAR.
     */
    public SQLServer2K5Dialect()
    {
        registerHibernateType(Types.NVARCHAR, StringType.INSTANCE.getName());
        registerHibernateType(Types.NCHAR, StringType.INSTANCE.getName());
        registerHibernateType(Types.LONGNVARCHAR, StringType.INSTANCE.getName());
    }

}
