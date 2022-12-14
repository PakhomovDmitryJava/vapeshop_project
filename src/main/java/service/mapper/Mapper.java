package service.mapper;

public interface Mapper<F, T> {

    T mapFrom(F fromObject);

}
