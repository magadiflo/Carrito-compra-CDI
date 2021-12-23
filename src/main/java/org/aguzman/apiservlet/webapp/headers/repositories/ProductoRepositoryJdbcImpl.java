package org.aguzman.apiservlet.webapp.headers.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.aguzman.apiservlet.webapp.headers.models.Categoria;
import org.aguzman.apiservlet.webapp.headers.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductoRepositoryJdbcImpl implements Repository<Producto> {

    @Inject
    @Named("conn")
    private Connection conn;

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Statement st = this.conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT p.*, c.nombre AS categoria FROM productos AS p " +
                     "INNER JOIN categorias AS c ON(p.categoria_id = c.id) ORDER BY p.id ASC")) {
            while (rs.next()) {
                Producto p = this.getProducto(rs);
                productos.add(p);
            }
        }
        return productos;
    }

    @Override
    public Producto porId(Long id) throws SQLException {
        Producto producto = null;
        try (PreparedStatement ps = this.conn.prepareStatement("SELECT p.*, c.nombre AS categoria " +
                "FROM productos AS p " +
                "INNER JOIN categorias AS c ON(p.categoria_id = c.id) WHERE p.id = ?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    producto = this.getProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        String sql;
        if (producto.getId() != null && producto.getId() > 0) {
            sql = "UPDATE productos SET nombre = ?, precio = ?, sku = ?, categoria_id = ? WHERE id = ?";
        } else {
            sql = "INSERT INTO productos(nombre, precio, sku, categoria_id, fecha_registro) VALUES(?, ?, ?, ?, ?)";
        }
        try (PreparedStatement ps = this.conn.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getPrecio());
            ps.setString(3, producto.getSku());
            ps.setLong(4, producto.getCategoria().getId());
            if (producto.getId() != null && producto.getId() > 0) {
                ps.setLong(5, producto.getId());
            } else {
                ps.setDate(5, Date.valueOf(producto.getFechaRegistro()));
            }
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (PreparedStatement ps = this.conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    private Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        Categoria c = new Categoria();
        p.setId(rs.getLong("id"));
        p.setNombre(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setSku(rs.getString("sku"));
        p.setFechaRegistro(rs.getDate("fecha_registro").toLocalDate());
        c.setId(rs.getLong("categoria_id"));
        c.setNombre(rs.getString("categoria"));
        p.setCategoria(c);
        return p;
    }

}
