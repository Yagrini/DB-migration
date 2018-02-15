
public class Converter {

    public static String toMysqlType(String type,int size){
        if(size>255) return "TEXT";
        switch(type) {
            case "INT":
            		return "INT";
            case "BIGINT":
                return "BIGINT";
            case "BINARY":
                return "BINARY";
            case "BIT":
                return "TINYINT";
            case "BOOLEAN":
                return "BOOLEAN";
            case "CHAR":
                return "CHAR";
            case "CLOB":
                return "TEXT";
            case "DATE":
                return "DATE";
            case "DECIMAL":
                return "DECIMAL";
            case "DOUBLE":
                return "DOUBLE";
            case "INTEGER":
                return "INTEGER";
            case "INT UNSIGNED":
                return "INTEGER";
            case "LONGNVARCHAR":
                return "TEXT";
            case "LONGVARBINARY":
                return "BYTEA";
            case "LONGVARCHAR":
                return "TEXT";
            case "NULL":
                return "NULL";
            case "NUMERIC":
                return "NUMERIC";
            case "SMALLINT":
                return "SMALLINT";
            case "TIME":
                return "TIME";
            case "TIMESTAMP":
                return "TIME";
            case "TINYINT":
                return "SMALLINT";
            case "VARBINARY":
                return "BLOB";
            case "VARCHAR":
                return "VARCHAR";
        }
        return "TEXT";
    }

    public static String toPostgresqlType(String type,int size){
        if(size>255) return "TEXT";
        switch(type) {
	        case "INT":
	        		return "INT";
            case "BIGINT":
                return "BIGINT";
            case "BINARY":
                return "BIT VARYING";
            case "BIT":
                return "BIT";
            case "BOOLEAN":
                return "BOOLEAN";
            case "CHAR":
                return "CHARACTER";
            case "CLOB":
                return "TEXT";
            case "DATE":
                return "DATE";
            case "DECIMAL":
                return "DECIMAL";
            case "DOUBLE":
                return "DOUBLE PRECISION";
            case "INTEGER":
                return "INTEGER";
            case "LONGNVARCHAR":
                return "TEXT";
            case "LONGVARBINARY":
                return "BYTEA";
            case "LONGVARCHAR":
                return "TEXT";
            case "NULL":
                return "NULL";
            case "NUMERIC":
                return "NUMERIC";
            case "SMALLINT":
                return "SMALLINT";
            case "TIME":
                return "TIME";
            case "TIMESTAMP":
                return "TIME";
            case "TINYINT":
                return "SMALLINT";
            case "VARBINARY":
                return "BYTEA";
            case "VARCHAR":
                return "VARCHAR";

        }
        return "TEXT";
    }

    public static String toOracleType(String type,int size){
        if(size>255) return "TEXT";
        switch(type) {
	        case "INT":
	        		return "INT";
            case "BIGINT":
                return "BIGINT";
            case "BINARY":
                return "RAW";
            case "BIT":
                return "NUMBER(3)";
            case "BOOLEAN":
                return "INT";
            case "CHAR":
                return "CHAR";
            case "CLOB":
                return "CLOB";
            case "DATE":
                return "DATE";
            case "DECIMAL":
                return "NUMBER";
            case "DOUBLE":
                return "NUMBER";
            case "INTEGER":
                return "NUMBER";
            case "LONGNVARCHAR":
                return "LONG";
            case "LONGVARBINARY":
                return "BLOB";
            case "LONGVARCHAR":
                return "LONG";
            case "NULL":
                return "NULL";
            case "NUMERIC":
                return "INT";
            case "SMALLINT":
                return "NUMBER";
            case "TIME":
                return "LONG";
            case "TIMESTAMP":
                return "TIMESTAMP";
            case "TINYINT":
                return "NUMBER";
            case "VARBINARY":
                return "BYTEA";
            case "VARCHAR":
                return "LONG";

        }
        return "LONG";
    }

    public static String toServerType(String type,int size){
        if(size>255) return "TEXT";
        switch(type) {
	        case "INT":
	    			return "INT";
            case "BIGINT":
                return "bigint";
            case "BINARY":
                return "binary";
            case "BIT":
                return "bit";
            case "BOOLEAN":
                return "bit";
            case "CHAR":
                return "char";
            case "CLOB":
                return "VARBINARY(MAX)";
            case "DATE":
                return "date";
            case "DECIMAL":
                return "decimal";
            case "DOUBLE":
                return "float";
            case "INTEGER":
                return "int";
            case "LONGNVARCHAR":
                return "xml";
            case "LONGVARBINARY":
                return "VARBINARY(MAX)";
            case "LONGVARCHAR":
                return "text";
            case "NULL":
                return "NULL";
            case "NUMERIC":
                return "numeric";
            case "SMALLINT":
                return "int";
            case "TIME":
                return "time";
            case "TIMESTAMP":
                return "datetime";
            case "TINYINT":
                return "int";
            case "VARBINARY":
                return "varbinary";
            case "VARCHAR":
                return "varchar";
        }
        return "text";
    }

    public static String toSqliteType(String type,int size){
        if(size>255) return "TEXT";
        switch(type) {
	        case "INT":
	        		return "INT";
            case "BIGINT":
                return "INTEGER";
            case "BINARY":
                return "TEXT";
            case "BIT":
                return "INTEGER";
            case "BOOLEAN":
                return "INTEGER";
            case "CHAR":
                return "CHAR";
            case "CLOB":
                return "TEXT";
            case "DATE":
                return "TEXT";
            case "DECIMAL":
                return "REAL";
            case "DOUBLE":
                return "REAL";
            case "INTEGER":
                return "INTEGER";
            case "LONGNVARCHAR":
                return "TEXT";
            case "LONGVARBINARY":
                return "BLOB";
            case "LONGVARCHAR":
                return "TEXT";
            case "NULL":
                return "NULL";
            case "NUMERIC":
                return "REAL";
            case "SMALLINT":
                return "INTEGER";
            case "TIME":
                return "TEXT";
            case "TIMESTAMP":
                return "TEXT";
            case "TINYINT":
                return "INTEGER";
            case "VARBINARY":
                return "BLOB";
            case "VARCHAR":
                return "TEXT";
        }
        return "TEXT";

    }
}