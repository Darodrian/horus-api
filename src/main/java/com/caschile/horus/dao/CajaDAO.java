package com.caschile.horus.dao;

import com.caschile.horus.conexion.Conexion;
import com.caschile.horus.model.CajaDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CajaDAO extends Conexion {

    public List CajaAnualByCodigo(Integer codigo, Integer año) {
        List<CajaDTO> coleccion = new ArrayList<>();
        try (Connection con = conexion()) {
            String query = "SELECT \n" +
                    "DISTINCT \n" +
                    "YEAR(PC.Fecha_Pago) as Año, \n" +
                    "PC.Numero_Caja as Caja, \n" +
                    "COALESCE((SELECT SUM(PCE.Total_a_Pagar) as Valor_Pagado FROM Permisos_de_Circulacion as PCE WHERE MONTH(PCE.Fecha_Pago) = 1 AND YEAR(PCE.Fecha_Pago) = "+año+" AND PCE.Numero_Caja = Pc.Numero_Caja), 0) as Enero, \n" +
                    "COALESCE((SELECT SUM(PCE.Total_a_Pagar) as Valor_Pagado FROM Permisos_de_Circulacion as PCE WHERE MONTH(PCE.Fecha_Pago) = 2 AND YEAR(PCE.Fecha_Pago) = "+año+" AND PCE.Numero_Caja = PC.Numero_Caja), 0) as Febrero, \n" +
                    "COALESCE((SELECT SUM(PCE.Total_a_Pagar) as Valor_Pagado FROM Permisos_de_Circulacion as PCE WHERE MONTH(PCE.Fecha_Pago) = 3 AND YEAR(PCE.Fecha_Pago) = "+año+" AND PCE.Numero_Caja = PC.Numero_Caja), 0) as Marzo \n" +
                    "FROM Permisos_de_Circulacion as PC \n" +
                    "WHERE YEAR(PC.Fecha_Pago) = "+año+" \n" +
                    "AND PC.Numero_Caja = "+codigo+" \n" +
                    "GROUP BY PC.Numero_Caja ,YEAR(PC.Fecha_Pago)";
            PreparedStatement state = con.prepareStatement(query);
            ResultSet res = state.executeQuery();
            while (res.next()) {
                List<Integer> lista = new ArrayList<>();
                CajaDTO caja = new CajaDTO();
                caja.setName(res.getString(1));
                caja.setCaja(res.getInt(2));
                lista.add(res.getInt(3));
                lista.add(res.getInt(4));
                lista.add(res.getInt(5));
                caja.setData(lista);
                coleccion.add(caja);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return coleccion;
    }

}
