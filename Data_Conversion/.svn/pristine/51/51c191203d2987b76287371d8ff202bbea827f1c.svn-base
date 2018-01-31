package com.appstek.dc.util;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.transform.BasicTransformerAdapter;

@SuppressWarnings("serial")
public class AliasToEntityLinkedMapResultTransformer extends BasicTransformerAdapter implements Serializable {
	public static final AliasToEntityLinkedMapResultTransformer INSTANCE = new AliasToEntityLinkedMapResultTransformer();

    private AliasToEntityLinkedMapResultTransformer() {

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Object transformTuple(Object[] tuple, String[] aliases) {
        Map result = new LinkedHashMap(tuple.length);
        for (int i = 0; i < tuple.length; i++) {
            String alias = aliases[i];
            if (alias != null) {
                result.put(alias, tuple[i]);
            }
        }

        return result;
    }

    private Object readResolve() {
        return INSTANCE;
    }

    public boolean equals(Object other) {
        return other != null && AliasToEntityLinkedMapResultTransformer.class.isInstance(other);
    }

    public int hashCode() {
        return getClass().getName().hashCode();
    }
}


