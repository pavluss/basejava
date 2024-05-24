package ru.saparsky.basejava.sql;

import org.postgresql.util.PSQLException;
import ru.saparsky.basejava.exception.ExistStorageException;
import ru.saparsky.basejava.exception.StorageException;

import java.sql.SQLException;

public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static StorageException convertException(SQLException e) {
        if (e instanceof PSQLException) {
            //http://www.postgresql.org/docs/9.3/static/errcodes-appendix.html
            if (e.getSQLState().equals("23505")) { // 23505 - unique_violation
                return new ExistStorageException(null);
            }
        }
        return new StorageException(e);
    }
}
