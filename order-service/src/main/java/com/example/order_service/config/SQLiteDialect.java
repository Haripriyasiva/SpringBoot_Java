package com.example.order_service.config;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupport;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;
import java.sql.Types;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect() {
        super();

        // Map SQL column types to SQLite types
        registerColumnType(Types.BIT, "integer");
        registerColumnType(Types.TINYINT, "tinyint");
        registerColumnType(Types.SMALLINT, "smallint");
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.BIGINT, "bigint");
        registerColumnType(Types.FLOAT, "float");
        registerColumnType(Types.REAL, "real");
        registerColumnType(Types.DOUBLE, "double");
        registerColumnType(Types.NUMERIC, "numeric");
        registerColumnType(Types.DECIMAL, "decimal");
        registerColumnType(Types.CHAR, "char");
        registerColumnType(Types.VARCHAR, "text");
        registerColumnType(Types.LONGVARCHAR, "text");
        registerColumnType(Types.BLOB, "blob");
    }

    private void registerColumnType(int bit, String integer) {
    }

    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new SQLiteIdentityColumnSupport();
    }

    @Override
    public boolean supportsTemporaryTables() {
        return true;
    }


    public String getCreateTemporaryTableString() {
        return "create temporary table if not exists";
    }


    public boolean dropTemporaryTableAfterUse() {
        return false;
    }

    @Override
    public boolean supportsCurrentTimestampSelection() {
        return true;
    }

    @Override
    public boolean isCurrentTimestampSelectStringCallable() {
        return false;
    }

    @Override
    public String getCurrentTimestampSelectString() {
        return "select current_timestamp";
    }

    // Custom Identity support for SQLite
    public static class SQLiteIdentityColumnSupport extends IdentityColumnSupportImpl {
        @Override
        public boolean supportsIdentityColumns() {
            return true;
        }

        @Override
        public String getIdentitySelectString(String table, String column, int type) {
            return "select last_insert_rowid()";
        }

        @Override
        public String getIdentityColumnString(int type) {
            return "integer";
        }
    }
}
