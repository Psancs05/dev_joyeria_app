package modelo.DAO;

//Interface de del modelo DAO
public interface DAO {
    public boolean create(Object objeto);

    public Object search(Object objeto);

    public boolean update(Object objeto);

    public boolean delete(Object objeto);

    public boolean exist(Object objeto);
}
