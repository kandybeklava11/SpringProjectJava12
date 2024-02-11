package java12.service;

import java.util.List;

public interface GenericService <T,ID> {
    // ID is Long

    //save
    T save(T entity);

    // find
    T findById(ID id);

    // getAll
    List<T> getAll();

    T updateById(ID id, T newEntity);

    String deleteById(ID id);
    T assing(ID id,ID IdEntity);
}
