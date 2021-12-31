package modelo.DAO;

public interface DAO {
    public boolean create(Object objeto);

    public Object search(Object objeto);

    public boolean update(Object objeto);

    public boolean delete(Object objeto);
}
