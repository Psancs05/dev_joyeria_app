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

    // * Getters y Setters
    public ProveedorDAO getProveedorDAO() {
        return proveedorDAO;
    }

    public void setProveedorDAO(ProveedorDAO proveedorDAO) {
        this.proveedorDAO = proveedorDAO;
    }

    // * Metodos de la logica
    public ArrayList<ProveedorVO> getProveedores() {
        ArrayList<ProveedorVO> lista = proveedorDAO.getListaProveedores();
        return lista;
    }

    public boolean registrarProveedorVO(String CIF, String nombre) {
        ProveedorVO nuevoProveedor = new ProveedorVO(CIF, nombre);
        boolean response = this.proveedorDAO.create(nuevoProveedor);

        return response;
    }

    public void actualizarProveedorVO(ProveedorVO proveedor, String CIF, String nombre) {

        ProveedorVO proveedorActual = new ProveedorVO(CIF, nombre);
        boolean response = this.proveedorDAO.update(proveedorActual);
        
    }

    public boolean retirarProveedorVO(ProveedorVO proveedor) {
        boolean response = this.proveedorDAO.delete(proveedor);
        return response;
    }

    public ProveedorVO getProveedorPorNombre(String nombre) {
        ProveedorVO proveedor = this.proveedorDAO.getProveedorPorNombre(nombre);
        return proveedor;
    }

    public ArrayList<ProveedorVO> solicitarProveedores() {
        return this.proveedorDAO.getListaProveedores();
    }
}