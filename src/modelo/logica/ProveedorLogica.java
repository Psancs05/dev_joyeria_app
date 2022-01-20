package modelo.logica;

import java.util.ArrayList;

import modelo.DAO.ProveedorDAO;
import modelo.VO.ProveedorVO;

public class ProveedorLogica {
    // * Atributos
    private ProveedorDAO proveedorDAO;

    // * Constructor
    public ProveedorLogica() {
        this.proveedorDAO = new ProveedorDAO();
    }

    
    /** 
     * Gey
     * @return ProveedorDAO
     */
    public ProveedorDAO getProveedorDAO() {
        return proveedorDAO;
    }

    
    /** 
     * Set
     * @param proveedorDAO
     */
    public void setProveedorDAO(ProveedorDAO proveedorDAO) {
        this.proveedorDAO = proveedorDAO;
    }

    
    /** 
     * Get
     * @return ArrayList<ProveedorVO>
     */
    public ArrayList<ProveedorVO> getProveedores() {
        ArrayList<ProveedorVO> lista = proveedorDAO.getListaProveedores();
        System.out.println("Proveedores: " + lista.toString());
        return lista;
    }

    
    /** 
     * Metodo que guarda un proveedor de la base de datos
     * @param CIF cif
     * @param nombre nombre
     * @return boolean true si se ha podido registrar
     */
    public boolean registrarProveedorVO(String CIF, String nombre) {
        ProveedorVO nuevoProveedor = new ProveedorVO(CIF, nombre);
        boolean response = this.proveedorDAO.create(nuevoProveedor);
        if (response)
            System.out.println("Proveedor registrado: " + nuevoProveedor.toString());

        return response;
    }

    
    /** 
     * Metodo que actualiza el proveedor pasado por parametro con los nuevos datos
     * @param proveedor proveedor a actualizar
     * @param CIF cif
     * @param nombre nombre
     */
    public void actualizarProveedorVO(ProveedorVO proveedor, String CIF, String nombre) {

        ProveedorVO proveedorActual = new ProveedorVO(CIF, nombre);
        boolean response = this.proveedorDAO.update(proveedorActual);
        if (response)
            System.out.println("Proveedor actualizado: " + proveedor.toString());
    }

    
    /** 
     * Metodo que elimina un proveedor pasado por parametro
     * @param proveedor proveedor a eliminar
     * @return boolean true si se ha podido eliminar
     */
    public boolean retirarProveedorVO(ProveedorVO proveedor) {
        boolean response = this.proveedorDAO.delete(proveedor);
        if (response)
            System.out.println("Proveedor retirado: " + proveedor.toString());
        
        return response;
    }

    
    /** 
     * Metodo que devuelve un proveedor que coincide con nombre pasado por parametro
     * @param nombre nombre
     * @return ProveedorVO proveedor con nombre
     */
    public ProveedorVO getProveedorPorNombre(String nombre) {
        ProveedorVO proveedor = this.proveedorDAO.getProveedorPorNombre(nombre);
        System.out.println("Proveedor encontrado: " + proveedor.toString());
        return proveedor;
    }

    
    /** 
     * Metodo que devuelve todos los proveedores
     * @return ArrayList<ProveedorVO> listado de proveedores
     */
    public ArrayList<ProveedorVO> solicitarProveedores() {
        return this.proveedorDAO.getListaProveedores();
    }
}