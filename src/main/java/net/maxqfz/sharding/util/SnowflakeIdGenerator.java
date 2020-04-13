package net.maxqfz.sharding.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * Id generator based on timestamp
 *
 * FIXME: What to do if instance time is set back
 */
public class SnowflakeIdGenerator implements IdentifierGenerator {
    protected final long epoch = 1586000000000L;
    private volatile byte i = 0;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object)
            throws HibernateException {
        return nextId();
    }

    private synchronized long nextId() {
        long timestamp = System.currentTimeMillis() - epoch;
        //TODO: add instance id when running several instances
        long id = (timestamp << 8) + i++;
        System.out.println(id);
        return id;
    }
}
