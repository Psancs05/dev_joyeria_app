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
        System.out.println("Proveedores: " + lista.toString());
        return lista;
    }

    public boolean registrarProveedorVO(String CIF, String nombre) {
        ProveedorVO nuevoProveedor = new ProveedorVO(CIF, nombre);
        boolean response = this.proveedorDAO.create(nuevoProveedor);

        System.out.println("Proveedor registrado: " + nuevoProveedor.toString());

        return response;
    }

    // TODO!: checkear que parametros se le pasa a este metodo
    public boolean actualizarProveedorVO(ProveedorVO proveedor, String CIF, String nombre) {

        ProveedorVO proveedorActual = new ProveedorVO(CIF, nombre);
        boolean response = this.proveedorDAO.update(proveedorActual);

        System.out.println("Proveedor actualizado: " + proveedor.toString());
        return response;
    }

    // TODO: No se que hace este metodo
    public void generarProveedorActualizado() {
    }

    public boolean retirarProveedorVO(ProveedorVO proveedor) {
        boolean response = this.proveedorDAO.delete(proveedor);

        System.out.println("Proveedor retirado: " + proveedor.toString());
        return response;
    }

    public ProveedorVO getProveedorPorNombre(String nombre) {
        ProveedorVO proveedor = this.proveedorDAO.getProveedorPorNombre(nombre);
        System.out.println("Proveedor encontrado: " + proveedor.toString());
        return proveedor;
    }

    public ArrayList<ProveedorVO> solicitarProveedores() {
        return this.proveedorDAO.getListaProveedores();
    }
}