package ilion.util.persistencia;

@SuppressWarnings("rawtypes")
public interface IGenericDao {

  public Object consultar(Class clazz, Long id);

  public Object inserir(Object object);

  public void atualizar(Object object);

  public void excluir(Object object);

}
