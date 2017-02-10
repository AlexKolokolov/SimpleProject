package org.kolokolov.simpleproject.hstore;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;


public class HstoreUserType implements UserType {

	@Override
	public int[] sqlTypes() {
		return new int[] {Types.CLOB};
	}

	@Override
	public Class returnedClass() {
		return Map.class;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		 Map m1 = (Map) x;
	     Map m2 = (Map) y;
	     return m1.equals(m2);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		String col = names[0];
        String val = rs.getString(col);
        return HstoreHelper.toMap(val);
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		String s = HstoreHelper.toString((Map) value);
        st.setObject(index, s, Types.OTHER);

	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		Map m = (Map) value;
        return new HashMap(m);
	}

	@Override
	public boolean isMutable() {
		return true;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return deepCopy(original);
	}

}
