package han.oose.dea.spotitube.datasource.exceptions;

public interface ExceptionMapper {
    /**
     * Maps the given exceptions to an exception that can be handled by JAX-RS, using the exception message
     *
     * @param e Exception to be mapped
     */
    void mapException(Exception e);
}