package org.embulk.input.jdbc.getter;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.embulk.spi.Column;
import org.embulk.spi.PageBuilder;
import org.embulk.spi.type.Type;
import org.embulk.spi.type.Types;

public class LongColumnGetter
        extends AbstractColumnGetter
{
    private long value;

    public LongColumnGetter(PageBuilder to, Type toType)
    {
        super(to, toType);
    }

    @Override
    protected void fetch(ResultSet from, int fromIndex) throws SQLException
    {
        value = from.getLong(fromIndex);
    }

    @Override
    protected Type getDefaultToType()
    {
        return Types.LONG;
    }

    @Override
    public void booleanColumn(Column column)
    {
        to.setBoolean(column, value > 0L);
    }

    @Override
    public void longColumn(Column column)
    {
        to.setLong(column, value);
    }

    @Override
    public void doubleColumn(Column column)
    {
        to.setDouble(column, value);
    }

    @Override
    public void stringColumn(Column column)
    {
        to.setString(column, Long.toString(value));
    }

}
