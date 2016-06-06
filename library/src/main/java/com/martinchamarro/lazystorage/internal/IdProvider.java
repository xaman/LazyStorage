package com.martinchamarro.lazystorage.internal;

import com.martinchamarro.lazystorage.annotation.Id;
import com.martinchamarro.lazystorage.internal.exception.ObjectIdNotFoundException;
import com.martinchamarro.lazystorage.internal.getter.*;

import java.util.ArrayList;
import java.util.List;

public final class IdProvider {

    private static final String METHOD_GET_ID = "getId";
    private static final String FIELD_ID = "id";
    private static final String FIELD_PREFIXED_ID = "mId";
    private static final Class ID_ANNOTATION_CLASS = Id.class;

    private List<IdGetter> getters;

    public IdProvider() {
        getters = new ArrayList<>();
        getters.add(new MethodIdGetter(METHOD_GET_ID));
        getters.add(new FieldIdGetter(FIELD_ID));
        getters.add(new FieldIdGetter(FIELD_PREFIXED_ID));
        getters.add(new AnnotationIdGetter(ID_ANNOTATION_CLASS));
    }

    public String getId(Object object) throws ObjectIdNotFoundException {
        Object id = getIdFromGetters(object);
        if (id == null) throw new ObjectIdNotFoundException("The object does not have id, mId, getId() or @Id annotated field.");
        return String.valueOf(id);
    }

    private Object getIdFromGetters(Object object) {
        Object id = null;
        for (IdGetter getter : getters) {
            if ((id = getter.getId(object)) != null) break;
        }
        return id;
    }
}
