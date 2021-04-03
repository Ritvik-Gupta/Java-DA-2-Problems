package services.Constrained;

@FunctionalInterface
public interface IConstrainedFor<T extends Exception>  {
   Constrained with(String value) throws T;
}
