package ilion.util.persistencia;

@SuppressWarnings({ "rawtypes" })
public class GenericDaoImpl implements IGenericDao {

  HibernateUtil util = new HibernateUtil();

  @Override
  public Object consultar(Class clazz, Long id) {
    return util.findById(clazz, id);
  }

  @Override
  public Object inserir(Object object) {
    return util.save(object);
  }

  @Override
  public void atualizar(Object object) {
    util.update(object);
  }

  @Override
  public void excluir(Object object) {
    util.delete(object);
  }

}
