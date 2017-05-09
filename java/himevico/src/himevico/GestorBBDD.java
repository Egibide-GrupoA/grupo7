/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package himevico;

import vehiculo.Vehiculo;
import trabajadores.Trabajador;
import trabajadores.Logistica;
import viajes.Viaje;
import centros.Centro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import login.VLogin;
import partes.Parte;
import partes.VParteAdministracion;
import trabajadores.Administracion;

/**
 *
 * @author Sheila y Asier
 */
public class GestorBBDD {

    private static Connection Conexion;
    static String sql;
    static ResultSet rs;
    static ResultSet rs2;
    static Statement stmt = null;
    static Statement stmt2 = null;

    /**
     * Método connect hace la conexión a la base de datos
     *
     * @param user
     * @param pass
     * @param db_name
     * @throws Exception
     */
    public void connect(String user, String pass, String db_name) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://ireland.izabil.net:3306/" + db_name, user, pass);
            stmt = Conexion.createStatement();
            //TODO: ARREGLAR PARCHE RAPIDO
            stmt2 = Conexion.createStatement();
            System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestorBBDD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GestorBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método listar trabajadores de tipo logística, hace la consulta a la base
     * de datos
     *
     * @return lista de objetos de tipo logística
     * @throws SQLException
     * @throws Exception
     */
    public static List<Logistica> listarTrabajadoresLogistica() throws SQLException, Exception {

        List<Logistica> logiticos = new ArrayList<>();
        sql = "SELECT * FROM trabajador;";
        System.out.println(sql);
        try {
            rs2 = stmt2.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);

        }
        while (rs2.next()) {

            logiticos.add(new Logistica(rs2.getInt("id")));

            System.out.println("TEST1");
        }
        return logiticos;
    }

    /**
     * Método listar centros, hace la consulta a la base de datos
     *
     * @return lista de objetos de tipo centros
     * @throws SQLException
     * @throws Exception
     */
    public static List<Centro> listarCentros() throws SQLException, Exception {

        List<Centro> centros = new ArrayList<>();
        sql = "SELECT * FROM centro;";
        System.out.println(sql);
        try {
            rs2 = stmt2.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);

        }
        while (rs2.next()) {

            centros.add(new Centro(rs2.getInt("id")));

            System.out.println("TEST1");
        }
        return centros;
    }

    /**
     * Método listar vehículos, hace la consulta a la base de datos
     *
     * @return lista de objetos de tipo vehículo
     * @throws SQLException
     * @throws Exception
     */
    public static List<Vehiculo> listarVehiculos() throws SQLException, Exception {
        List<Vehiculo> vehiculos = new ArrayList<>();
        sql = "SELECT `matricula`, `marca`, `modelo`, `color`, `fechaAlta` FROM vehiculo;";
        System.out.println(sql);
        try {
            rs2 = stmt2.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);

        }
        while (rs2.next()) {

            vehiculos.add(new Vehiculo(rs2.getString("matricula")));

            System.out.println("TEST1");
        }
        return vehiculos;
    }

    /**
     * Método que devuelve vehículo
     *
     * @param vehiculo
     * @throws SQLException
     */
    public static void getVehiculo(Vehiculo vehiculo) throws SQLException {

        sql = "SELECT `matricula`, `marca`, `modelo`, `color`, `fechaAlta` FROM `vehiculo` WHERE `matricula` = '" + vehiculo.getMatricula() + "';";
        System.out.println(sql);
        rs = executeQuery(sql);
        rs.next();
        vehiculo.setMatricula(rs.getString("matricula"));
        vehiculo.setMarca(rs.getString("marca"));
        vehiculo.setModelo(rs.getString("modelo"));
        vehiculo.setColor(rs.getString("color"));
        vehiculo.setFechaAlta(rs.getDate("fechaAlta"));

    }

    /**
     * Método que comprueba si el usuario existe o no
     *
     * @param usuario
     * @param password
     * @return si el usuario existe o no
     */
    public static boolean comprobarUsuario(String usuario, String password) {

        sql = "SELECT * FROM `trabajador` WHERE `dni` = '" + usuario + "' AND `contrasena` = MD5('" + password + "');";
        System.out.println(sql);
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);

        }
        try {
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(VLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    /**
     * Método que comprueba el tipo de usuario
     *
     * @param dni
     * @return el tipo de usuario
     * @throws SQLException
     */
    public static String tipoUsuario(String dni) throws SQLException {

        sql = "SELECT categoria.nombre tipoUsuario FROM `trabajador` INNER JOIN categoria ON trabajador.idCategoria = categoria.id WHERE trabajador.dni = '" + dni + "';";
        System.out.println(sql);
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);

        }
        rs.next();
        return rs.getString("tipoUsuario");
    }

    /**
     * Metodo que devuelve un trabajador de logística
     *
     * @param logistica
     * @throws SQLException
     */
    public static void getTrabajadorLogistica(Logistica logistica) throws SQLException {

        sql = "SELECT `id`, `dni`, `nombre`, `apellido1`, `apellido2`, `calle`, `numero`, `piso`, `mano`, `telPersonal`, `telEmpresa`, `salario`, `fechaNacimiento`, `idCategoria`, `idCentro` FROM `trabajador` WHERE `id` = " + logistica.getIdTrabajador() + ";";
        rs = executeQuery(sql);
        rs.next();
        logistica.setIdTrabajador(rs.getInt("id"));
        logistica.setNombre(rs.getString("nombre"));
        logistica.setCalle(rs.getString("calle"));
        //logistica.setNumero(rs.getInt("numero"));
        logistica.setPiso(rs.getInt("piso"));
        logistica.setMano(rs.getString("mano").charAt(0));
        //logistica.setCodPostal(rs.getInt("codPostal"));
        //logistica.setCiudad(rs.getString("ciudad"));
        //logistica.setProvincia(rs.getString("provincia"));
        //logistica.setTelefono(rs.getString("telefono"));
    }

    /**
     * Método que devuelve el DNI de un trabajador de logística
     *
     * @param logistica
     * @throws SQLException
     */
    public static void getTrabajadorLogisticaDni(Logistica logistica) throws SQLException {

        sql = "SELECT `id`, `dni`, `nombre`, `apellido1`, `apellido2`, `calle`, `numero`, `piso`, `mano`, `telPersonal`, `telEmpresa`, `salario`, `fechaNacimiento`, `idCategoria`, `idCentro` FROM `trabajador` WHERE `dni` = " + logistica.getDni() + ";";
        rs = executeQuery(sql);
        rs.next();
        logistica.setIdTrabajador(rs.getInt("id"));
        logistica.setNombre(rs.getString("nombre"));
        logistica.setCalle(rs.getString("calle"));
        //logistica.setNumero(rs.getInt("numero"));
        logistica.setPiso(rs.getInt("piso"));
        logistica.setMano(rs.getString("mano").charAt(0));
        //logistica.setCodPostal(rs.getInt("codPostal"));
        //logistica.setCiudad(rs.getString("ciudad"));
        //logistica.setProvincia(rs.getString("provincia"));
        //logistica.setTelefono(rs.getString("telefono"));
    }

    /**
     * Método que devuelve un trabajador de administración
     *
     * @param administracion
     * @throws SQLException
     */
    public static void getTrabajadorAdministracion(Administracion administracion) throws SQLException {

        sql = "SELECT `id`, `dni`, `nombre`, `apellido1`, `apellido2`, `calle`, `numero`, `piso`, `mano`, `telPersonal`, `telEmpresa`, `salario`, `fechaNacimiento`, `idCategoria`, `idCentro` FROM `trabajador` WHERE `id` = " + administracion.getIdTrabajador() + ";";
        rs = executeQuery(sql);
        rs.next();
        //logistica.setIdCentro(rs.getInt("id"));
        administracion.setNombre(rs.getString("nombre"));
        administracion.setCalle(rs.getString("calle"));
        //logistica.setNumero(rs.getInt("numero"));
        administracion.setPiso(rs.getInt("piso"));
        administracion.setMano(rs.getString("mano").charAt(0));
        //logistica.setCodPostal(rs.getInt("codPostal"));
        //logistica.setCiudad(rs.getString("ciudad"));
        //logistica.setProvincia(rs.getString("provincia"));
        //logistica.setTelefono(rs.getString("telefono"));
    }

    /**
     * Método que devuelve un centro
     *
     * @param centro
     * @throws SQLException
     */
    public static void getCentro(Centro centro) throws SQLException {

        sql = "SELECT `id` ,`nombre`, `calle`, `numero`, `codPostal`, `ciudad`, `piso`, `mano`,  `provincia`, `telefono` FROM `centro` WHERE `id` = " + centro.getIdCentro() + ";";
        rs = executeQuery(sql);
        rs.next();
        centro.setIdCentro(rs.getInt("id"));
        centro.setNombre(rs.getString("nombre"));
        centro.setCalle(rs.getString("calle"));
        centro.setNumero(rs.getInt("numero"));
        centro.setPiso(rs.getInt("piso"));
        centro.setMano(rs.getString("mano").charAt(0));
        centro.setCodPostal(rs.getInt("codPostal"));
        centro.setCiudad(rs.getString("ciudad"));
        centro.setProvincia(rs.getString("provincia"));
        centro.setTelefono(rs.getString("telefono"));
    }

    /**
     * Método que inserta un trabajador de logística en la base de datos
     *
     * @param trabajador
     * @return true si se ha hecho la insert
     */
    public static boolean crearTrabajadorLogistica(Logistica trabajador) {
        Centro centro = trabajador.getCentro();
        sql = "INSERT INTO `trabajador` (`id`, `dni`, `nombre`, `apellido1`, "
                + "`apellido2`, `calle`, `numero`, `piso`, `mano`, `telPersonal`, "
                + "`telEmpresa`, `salario`, `fechaNacimiento`, `idCategoria`, "
                + "`idCentro`, `contrasena`) VALUES ("
                + "NULL, '"
                + trabajador.getDni() + "', '"
                + trabajador.getNombre() + "', '"
                + trabajador.getApellido1() + "', '"
                + trabajador.getApellido2() + "', '"
                + trabajador.getCalle() + "', '"
                + trabajador.getPortal() + "', '"
                + trabajador.getPiso() + "', '"
                + trabajador.getMano() + "', '"
                + trabajador.getTelPersonal() + "', '"
                + trabajador.getTelEmpresa() + "', "
                + trabajador.getSalario() + ", '"
                + (trabajador.getFechaNacimiento().getYear() + 1900) + "-" + (trabajador.getFechaNacimiento().getMonth() + 1) + "-" + trabajador.getFechaNacimiento().getDate() + "', '"
                + "1', '"
                + centro.getIdCentro()
                + "', MD5('" + trabajador.getContrasena() + "'));";
        executeUpdate(sql);
        return true;
    }

    /**
     * Método que inserta un trabajador de administración en la base de datos
     *
     * @param trabajador
     * @return si se ha hecho la insert
     */
    public static boolean crearTrabajadorAdministracion(Administracion trabajador) {
        Centro centro = trabajador.getCentro();
        sql = "INSERT INTO `trabajador` (`id`, `dni`, `nombre`, `apellido1`, "
                + "`apellido2`, `calle`, `numero`, `piso`, `mano`, `telPersonal`, "
                + "`telEmpresa`, `salario`, `fechaNacimiento`, `idCategoria`, "
                + "`idCentro`, `contrasena`) VALUES ("
                + "NULL, '"
                + trabajador.getDni() + "', '"
                + trabajador.getNombre() + "', '"
                + trabajador.getApellido1() + "', '"
                + trabajador.getApellido2() + "', '"
                + trabajador.getCalle() + "', '"
                + trabajador.getPortal() + "', '"
                + trabajador.getPiso() + "', '"
                + trabajador.getMano() + "', '"
                + trabajador.getTelPersonal() + "', '"
                + trabajador.getTelEmpresa() + "', "
                + trabajador.getSalario() + ", '"
                + (trabajador.getFechaNacimiento().getYear() + 1900) + "-" + (trabajador.getFechaNacimiento().getMonth() + 1) + "-" + trabajador.getFechaNacimiento().getDate() + "', '"
                + "2', '"
                + centro.getIdCentro()
                + "', MD5('" + trabajador.getContrasena() + "'));";
        executeUpdate(sql);
        return true;
    }

    /**
     * Método que inserta un parte en la base de datos
     *
     * @param parte
     */
    public static void crearParte(Parte parte) {
        sql = "INSERT INTO `parte` (`id`, `idPersona`, `fecha`, `kilometrosInicio`, `kilometrosFin`, `gasoil`, `peajes`, `dietas`, `otros`, `eliminado`, `validado`) VALUES "
                + "(NULL, '" + parte.getTrabajador().getIdTrabajador() + "',  CURDATE(), NULL, NULL, NULL, NULL, NULL, NULL, '0', '0');";
        executeUpdate(sql);
    }

    /**
     * Método que inserta un centro en la base de datos
     *
     * @param centro
     */
    public static void crearCentro(Centro centro) {

        sql = "INSERT INTO `centro` (`id`, `nombre`, `calle`, `numero`, `piso`, `mano`, `codPostal`,`ciudad`, `provincia`, `telefono`) VALUES ("
                + "NULL, '" + centro.getNombre() + "', '" + centro.getCalle() + "', '" + centro.getNumero() + "', '" + centro.getPiso() + "', '" + centro.getMano() + "', '" + centro.getCodPostal() + "', '" + centro.getCiudad() + "', '" + centro.getProvincia() + "', '" + centro.getTelefono() + "');";
        executeUpdate(sql);
    }

    /**
     * Método que elimina un centro de la base de datos
     *
     * @param centro
     */
    public static void eliminar(Centro centro) {
        sql = "DELETE FROM `centro` WHERE `centro`.`id` = " + centro.getIdCentro();
        executeUpdate(sql);
    }

    /**
     * Método que elimina un trabajador de la base de datos
     *
     * @param trabajador
     */
    public static void eliminar(Trabajador trabajador) {
        sql = "DELETE FROM `trabajador` WHERE `trabajador`.`id` = " + trabajador.getIdTrabajador();
        executeUpdate(sql);
    }

    private static void executeUpdate(String sql) {
        System.out.println(sql);
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private static ResultSet executeQuery(String sql) {
        System.out.println(sql);
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return rs;
    }

    /**
     * Método que inserta una incidencia en la base de datos
     *
     * @param parte
     * @param mensaje
     */
    public static void crearIncidencia(Parte parte, String mensaje) {

        sql = "INSERT INTO `incidencia` (`id`, `idParte`, `mensaje`, `fecha`, `resuelta`) VALUES (NULL, '" + parte.getIdParte() + "', '" + mensaje + "', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);";
        executeUpdate(sql);
    }

    /**
     * Método que inserta un vehículo en la base de datos
     *
     * @param vehiculo
     */
    public static void crearVehiculo(Vehiculo vehiculo) {

        sql = "INSERT INTO `vehiculo` (`matricula`, `marca`, `modelo`, `color`, `fechaAlta`) VALUES ('" + vehiculo.getMatricula() + "', '" + vehiculo.getMarca() + "', '" + vehiculo.getModelo() + "', '" + vehiculo.getColor() + "', CURRENT_TIMESTAMP);";
        executeUpdate(sql);
    }

    /**
     * Método que inserta un viaje en la base de datos
     *
     * @param viaje
     */
    public static void crearViaje(Viaje viaje) {

        sql = "INSERT INTO `viaje` (`id`, `idParte`, `horaInicio`, `horaFin`, `matricula`, `albaran`) VALUES (NULL, " + viaje.getParte().getIdParte() + ", '" + viaje.getHoraInicio().getHours() + ":" + viaje.getHoraInicio().getMinutes() + ":00', '" + viaje.getHoraFin().getHours() + ":" + viaje.getHoraFin().getMinutes() + ":00', '" + viaje.getMatricula() + "', '" + viaje.getAlbaran() + "' );";
        executeUpdate(sql);
    }

    /**
     * Método devuelve un viaje
     *
     * @param viaje
     * @throws SQLException
     */
    public static void getViaje(Viaje viaje) throws SQLException {

        sql = "SELECT `id`, `idParte`, `horaInicio`, `horaFin`, `matricula`, `albaran` FROM `viaje` WHERE `id` = " + viaje.getIdViaje() + ";";
        System.out.println(sql);
        rs = executeQuery(sql);
        rs.next();
        viaje.setIdViaje(rs.getInt("id"));
        viaje.setHoraInicio(rs.getTime("horaInicio"));
        viaje.setHoraFin(rs.getTime("horaFin"));
        viaje.setAlbaran(rs.getString("albaran"));
        viaje.setVehiculo(new Vehiculo(rs.getString("matricula")));

    }

    /**
     * Método listar viajes, hace la consulta a la base de datos
     *
     * @param idParte
     * @return una lista de tipo viajes
     * @throws SQLException
     * @throws Exception
     */
    public static List<Viaje> listarViajes(int idParte) throws SQLException, Exception {
        List<Viaje> viajes = new ArrayList<>();
        sql = "SELECT * FROM viaje where `idParte` = " + idParte + ";";
        System.out.println(sql);
        try {
            rs2 = stmt2.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);

        }
        while (rs2.next()) {

            viajes.add(new Viaje(rs2.getInt("id")));
        }
        return viajes;
    }

    /**
     * Método que devuelve la fecha actual del servidor de la base de datos
     *
     * @return un String
     * @throws SQLException
     * @throws Exception
     */
    public static String getDate() throws SQLException, Exception {
        sql = "SELECT CURDATE() hoy FROM DUAL;";
        System.out.println(sql);
        try {
            rs2 = stmt2.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);

        }
        rs2.next();
        return rs2.getString("hoy");
    }

    /**
     * Método que cierra un parte
     *
     * @param parte
     * @throws SQLException
     * @throws Exception
     */
    public static void cerrarParte(Parte parte) throws SQLException, Exception {
        sql = "UPDATE `parte` SET `kilometrosInicio` = '" + parte.getKilometrosInicio() + "', `kilometrosFin` = '" + parte.getKilometrosFin() + "', `gasoil` = '" + parte.getGasoil() + "', `peajes` = '" + parte.getPeajes() + "', `dietas` = '" + parte.getDietas() + "', `otros` = '" + parte.getOtros() + "', `validado` = 1 WHERE `parte`.`id` = " + parte.getIdParte() + ";";
        executeUpdate(sql);
    }

    /**
     * Método que devuelve el último parte abierto
     *
     * @param idPersona
     * @return un int
     * @throws SQLException
     * @throws Exception
     */
    public static int ultimoParteAbierto(int idPersona) throws SQLException, Exception {
        sql = "SELECT COUNT(`id`) abierto, `id` FROM `parte` WHERE `validado` = 0 AND idPersona = " + idPersona + " ORDER BY `parte`.`fecha` DESC LIMIT 1";
        System.out.println(sql);
        try {
            rs2 = stmt2.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);

        }
        rs2.next();
        return rs2.getInt("id");
    }

    /**
     * Método que devuelve un parte
     *
     * @param parte
     * @throws SQLException
     */
    public static void getParte(Parte parte) throws SQLException {

        sql = "SELECT `id`, `idPersona`, `fecha`, `kilometrosInicio`, `kilometrosFin`,"
                + "`gasoil`, `peajes`, `dietas`, `otros`, `eliminado`, `validado`  "
                + "FROM `parte` WHERE `id` = " + parte.getIdParte() + ";";
        rs = executeQuery(sql);
        rs.next();
        parte.setIdParte(rs.getInt("id"));
        //parte.setIdPersona(rs.getString("idPersona"));
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd");
        Date fecha = null;
        try {
            fecha = DATE_FORMAT.parse(rs.getString("fecha"));
        } catch (ParseException ex) {
            Logger.getLogger(VParteAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        parte.setFecha(fecha);
        parte.setKilometrosInicio(rs.getInt("kilometrosInicio"));
        parte.setKilometrosFin(rs.getInt("kilometrosFin"));
        parte.setGasoil(rs.getDouble("gasoil"));
        parte.setPeajes(rs.getDouble("peajes"));
        parte.setDietas(rs.getDouble("dietas"));
        parte.setOtros(rs.getDouble("otros"));
        parte.setEliminado(rs.getBoolean("eliminado"));
        parte.setValidado(rs.getBoolean("validado"));

    }

    /**
     * Método que devuelve los minutos de un parte
     *
     * @param parte
     * @return un int
     * @throws SQLException
     */
    public static int getMinutos(Parte parte) throws SQLException {

        sql = "SELECT SUM(TIMESTAMPDIFF(MINUTE,`horaInicio`,`horaFin`)) MINS FROM `viaje` WHERE `idParte` = " + parte.getIdParte() + ";";
        System.out.println(sql);
        rs = executeQuery(sql);
        rs.next();
        return rs.getInt("MINS");

    }

}
