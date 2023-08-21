package com.caschile.horus.dao;

import com.caschile.horus.conexion.Conexion;
import com.caschile.horus.model.CajaDTO;
import com.caschile.horus.model.DiaMesDTO;

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
                    "COALESCE((SELECT SUM(PCE.Total_a_Pagar) as Valor_Pagado FROM Permisos_de_Circulacion as PCE WHERE MONTH(PCE.Fecha_Pago) = 1 AND YEAR(PCE.Fecha_Pago) = YEAR(PC.Fecha_Pago) AND PCE.Numero_Caja = PC.Numero_Caja), 0) as Enero, \n" +
                    "COALESCE((SELECT SUM(PCE.Total_a_Pagar) as Valor_Pagado FROM Permisos_de_Circulacion as PCE WHERE MONTH(PCE.Fecha_Pago) = 2 AND YEAR(PCE.Fecha_Pago) = YEAR(PC.Fecha_Pago) AND PCE.Numero_Caja = PC.Numero_Caja), 0) as Febrero, \n" +
                    "COALESCE((SELECT SUM(PCE.Total_a_Pagar) as Valor_Pagado FROM Permisos_de_Circulacion as PCE WHERE MONTH(PCE.Fecha_Pago) = 3 AND YEAR(PCE.Fecha_Pago) = YEAR(PC.Fecha_Pago) AND PCE.Numero_Caja = PC.Numero_Caja), 0) as Marzo, \n" +
                    "COALESCE((SELECT COUNT(*) as Total_Ventas FROM Permisos_de_Circulacion as PCE WHERE MONTH(PCE.Fecha_Pago) = 1 AND YEAR(PCE.Fecha_Pago) = YEAR(PC.Fecha_Pago) AND PCE.Numero_Caja = PC.Numero_Caja), 0) as Enero, \n" +
                    "COALESCE((SELECT COUNT(*) as Total_Ventas FROM Permisos_de_Circulacion as PCE WHERE MONTH(PCE.Fecha_Pago) = 2 AND YEAR(PCE.Fecha_Pago) = YEAR(PC.Fecha_Pago) AND PCE.Numero_Caja = PC.Numero_Caja), 0) as Febrero, \n" +
                    "COALESCE((SELECT COUNT(*) as Total_Ventas FROM Permisos_de_Circulacion as PCE WHERE MONTH(PCE.Fecha_Pago) = 3 AND YEAR(PCE.Fecha_Pago) = YEAR(PC.Fecha_Pago) AND PCE.Numero_Caja = PC.Numero_Caja), 0) as Marzo \n" +
                    "FROM Permisos_de_Circulacion as PC \n" +
                    "WHERE YEAR(PC.Fecha_Pago) = "+año+" \n" +
                    "AND PC.Numero_Caja = "+codigo+" \n" +
                    "GROUP BY PC.Numero_Caja, YEAR(PC.Fecha_Pago)";
            PreparedStatement state = con.prepareStatement(query);
            ResultSet res = state.executeQuery();
            while (res.next()) {
                List<Integer> lista = new ArrayList<>();
                CajaDTO caja = new CajaDTO();
                caja.setName(res.getString(1));
                lista.add(res.getInt(2));
                lista.add(res.getInt(3));
                lista.add(res.getInt(4));
                lista.add(res.getInt(5));
                lista.add(res.getInt(6));
                lista.add(res.getInt(7));
                caja.setData(lista);
                coleccion.add(caja);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return coleccion;
    }

    public List CajaDiariaByMes(Integer codigo, Integer año, Integer mes) {
        List<DiaMesDTO> coleccion = new ArrayList<>();
        try (Connection con = conexion()) {
            String query = "DECLARE @BeginDate DATETIME; \n" +
                    "DECLARE @EndDate DATETIME; \n" +
                    "SET @BeginDate = CONVERT(DATE, DATEADD(month, DATEDIFF(month, 0, CONVERT(DATE, '"+año+"-"+mes+"-01')), 0)); \n" +
                    "SET @EndDate = EOMONTH('"+año+"-"+mes+"-01'); \n" +
                    "WITH CTE(DateRange) AS \n" +
                    "( \n" +
                    "SELECT @BeginDate AS DateRange UNION ALL \n" +
                    "SELECT DATEADD(DAY, 1, DateRange) \n" +
                    "FROM CTE \n" +
                    "WHERE DATEADD(DAY, 1, DateRange) <= @EndDate \n" +
                    ") \n" +
                    "SELECT CONVERT(DATE, DATEADD(DAY, 0, DATEDIFF(DAY, 0, CTE.DateRange))) AS DateRange \n" +
                    ", COALESCE(PC.Total_a_Pagar, 0) AS Total_a_Pagar \n" +
                    "FROM CTE \n" +
                    "OUTER APPLY ( \n" +
                    "SELECT SUM(Total_a_Pagar) Total_a_Pagar \n" +
                    "FROM dbo.Permisos_de_Circulacion PC \n" +
                    "WHERE DATEDIFF(DAY, PC.Fecha_Pago, CTE.DateRange) = 0 \n" +
                    "AND PC.Numero_Caja = "+codigo+" \n" +
                    ") PC;";
            PreparedStatement state = con.prepareStatement(query);
            ResultSet res = state.executeQuery();
            while (res.next()) {
                DiaMesDTO diaMes = new DiaMesDTO();
                diaMes.setName(res.getString(1));
                diaMes.setData(res.getInt(2));
                coleccion.add(diaMes);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return coleccion;
    }
}
