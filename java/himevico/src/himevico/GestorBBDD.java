/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package himevico;

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
import trabajadores.Logistica;
import trabajadores.Trabajador;
import vehiculo.Vehiculo;
import viajes.Viaje;

/**
 *
 * @author Sheila y Asier
 */
public class GestorBBDD {

    private static Connection Conexion;
    static Statement stmt = null;
    static Statement stmt2 = null;
    static Statement stmt3 = null;

    /**
     * Método connect hace la conexión a la base de datos
     *
     * @param user
     * @param pass
     * @param db_name
     * @throws Exception
     */
    public void connect(String user, String pass, String db_name) throws Exception {
        String sql = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://ireland.izabil.net:3306/" + db_name, user, pass);
            stmt = Conexion.createStatement();
            stmt2 = Conexion.createStatement();
            stmt3 = Conexion.createStatement();

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
        String sql = null;
        ResultSet rs = null;
        List<Logistica> logiticos = new ArrayList<>();
        sql = "SELECT * FROM trabajador;";
        System.out.println(sql);
        try {
            rs = stmt2.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);

        }
        while (rs.next()) {

            logiticos.add(new Logistica(rs.getInt("id")));

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
        String sql = null;
        ResultSet rs = null;
        List<Centro> centros = new ArrayList<>();
        sql = "SELECT * FROM centro;";
        System.out.println(sql);
        try {
            rs = stmt2.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);

        }
        while (rs.next()) {

            centros.add(new Centro(rs.getInt("id")));

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
        String sql = null;
        ResultSet rs = null;
        List<Vehiculo> vehiculos = new ArrayList<>();
        sql = "SELECT `matricula`, `marca`, `modelo`, `color`, `fechaAlta` FROM vehiculo;";
        System.out.println(sql);
        try {
            rs = stmt3.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);

        }
        while (rs.next()) {

            vehiculos.add(new Vehiculo(rs.getString("matricula")));

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
        String sql = null;
        ResultSet rs = null;
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
        String sql = null;
        ResultSet rs = null;
        sql = "SELECT * FROM `trabajador` WHERE `dni` = '" + usuario + "' AND `contrasena` = MD5('" + password + "');";
        System.out.println(sql);
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);

        }

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
        String sql = null;
        ResultSet rs = null;
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
        String sql = null;
        ResultSet rs = null;
        sql = "SELECT `id`, `dni`, `nombre`, `apellido1`, `apellido2`, `calle`, `numero`, `piso`, `mano`, `telPersonal`, `telEmpresa`, `salario`, `fechaNacimiento`, `idCategoria`, `idCentro` FROM `trabajador` WHERE `id` = " + logistica.getIdTrabajador() + ";";
        rs = executeQuery(sql);

        rs.next();
        logistica.setIdTrabajador(rs.getInt("id"));
        logistica.setDni(rs.getString("dni"));
        logistica.setNombre(rs.getString("nombre"));
        logistica.setApellido1(rs.getString("apellido1"));
        logistica.setApellido2(rs.getString("apellido2"));
        logistica.setCalle(rs.getString("calle"));
        logistica.setPortal(rs.getInt("numero"));
        logistica.setPiso(rs.getInt("piso"));
        logistica.setMano(rs.getString("mano").charAt(0));
        logistica.setTelEmpresa(rs.getString("telEmpresa"));
        logistica.setTelPersonal(rs.getString("telPersonal"));
        logistica.setSalario(rs.getDouble("salario"));
        //TODO: FECHA
        //logistica.setFechaNacimiento(rs.getDate("fechaNacimiento"));
        logistica.setCentro(new Centro(rs.getInt("idCentro")));

    }

    /**
     * Método que devuelve el DNI de un trabajador de logística
     *
     * @param logistica
     * @throws SQLException
     */
    public static void getTrabajadorLogisticaDni(Logistica logistica) throws SQLException {
        String sql = null;
        ResultSet rs = null;
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
        String sql = null;
        ResultSet rs = null;
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
        String sql = null;
        ResultSet rs = null;
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
        String sql = null;
        ResultSet rs = null;
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
        String sql = null;
        ResultSet rs = null;
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
        String sql = null;
        ResultSet rs = null;
        //TODO Añadir soporte KM
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
        String sql = null;
        ResultSet rs = null;
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
        String sql = null;
        ResultSet rs = null;
        sql = "DELETE FROM `centro` WHERE `centro`.`id` = " + centro.getIdCentro();
        executeUpdate(sql);
    }

    /**
     * Método que elimina un trabajador de la base de datos
     *
     * @param trabajador
     */
    public static void eliminar(Trabajador trabajador) {
        String sql = null;
        ResultSet rs = null;
        sql = "DELETE FROM `trabajador` WHERE `trabajador`.`id` = " + trabajador.getIdTrabajador();
        executeUpdate(sql);
    }

    private static void executeUpdate(String sql) {
        ResultSet rs = null;
        System.out.println(sql);
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private static ResultSet executeQuery(String sql) {
        ResultSet rs = null;
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
        String sql = null;
        ResultSet rs = null;

        sql = "INSERT INTO `incidencia` (`id`, `idParte`, `mensaje`, `fecha`, `resuelta`) VALUES (NULL, '" + parte.getIdParte() + "', '" + mensaje + "', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);";
        executeUpdate(sql);

    }

    /**
     * Método que inserta un vehículo en la base de datos
     *
     * @param vehiculo
     */
    public static void crearVehiculo(Vehiculo vehiculo) {
        String sql = null;
        ResultSet rs = null;

        sql = "INSERT INTO `vehiculo` (`matricula`, `marca`, `modelo`, `color`, `fechaAlta`) VALUES ('" + vehiculo.getMatricula() + "', '" + vehiculo.getMarca() + "', '" + vehiculo.getModelo() + "', '" + vehiculo.getColor() + "', CURRENT_TIMESTAMP);";
        executeUpdate(sql);
    }

    /**
     * Método que inserta un viaje en la base de datos
     *
     * @param viaje
     */
    public static void crearViaje(Viaje viaje) {
        String sql = null;
        ResultSet rs = null;

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
        String sql = null;
        ResultSet rs = null;

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
        String sql = null;
        ResultSet rs = null;
        List<Viaje> viajes = new ArrayList<>();
        sql = "SELECT * FROM viaje where `idParte` = " + idParte + ";";
        System.out.println(sql);
        try {
            rs = stmt2.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);

        }
        while (rs.next()) {

            viajes.add(new Viaje(rs.getInt("id")));
        }
        return viajes;
    }

    /**
     * Devuelve una lista de partes. 
     * @param fecha1
     * @param fecha2
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static List<Parte> listarPartes(String fecha1, String fecha2) throws SQLException, Exception {
        String sql = null;
        ResultSet rs = null;
        List<Parte> partes = new ArrayList<>();
        sql = "SELECT `id`, `idPersona`, `fecha`, `kilometrosInicio`, `kilometrosFin`, "
                + "`gasoil`, `peajes`, `dietas`, `otros`, `eliminado`, `validado` "
                + "FROM `parte` WHERE (`fecha` BETWEEN '" + fecha1 + " 00:00' AND '" + fecha2 + " 23:59');";
        System.out.println(sql);
        try {
            rs = stmt3.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);

        }
        while (rs.next()) {

            partes.add(new Parte(rs.getInt("id")));
        }
        return partes;
    }

    /**
     * Devuelve una lista de partes. 
     * @param fecha1
     * @param fecha2
     * @param logistica
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static List<Parte> listarPartes(String fecha1, String fecha2, Logistica logistica) throws SQLException, Exception {
        String sql = null;
        ResultSet rs = null;
        List<Parte> partes = new ArrayList<>();
        sql = "SELECT `id`, `idPersona`, `fecha`, `kilometrosInicio`, `kilometrosFin`, "
                + "`gasoil`, `peajes`, `dietas`, `otros`, `eliminado`, `validado` "
                + "FROM `parte` WHERE (`fecha` BETWEEN '" + fecha1 + " 00:00' AND '" + fecha2 + " 23:59') "
                + "AND `idPersona` = " + logistica.getIdTrabajador() + ";";
        System.out.println(sql);
        try {
            rs = stmt3.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);

        }
        while (rs.next()) {

            partes.add(new Parte(rs.getInt("id")));
        }
        return partes;
    }

    /**
     * Método que devuelve la fecha actual del servidor de la base de datos
     *
     * @return un String
     * @throws SQLException
     * @throws Exception
     */
    public static String getDate() throws SQLException, Exception {
        String sql = null;
        ResultSet rs = null;
        sql = "SELECT CURDATE() hoy FROM DUAL;";
        System.out.println(sql);
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);

        }
        rs.next();
        return rs.getString("hoy");
    }

    /**
     * Método que cierra un parte
     *
     * @param parte
     * @throws SQLException
     * @throws Exception
     */
    public static void cerrarParte(Parte parte) throws SQLException, Exception {
        String sql = null;
        ResultSet rs = null;
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
        String sql = null;
        ResultSet rs = null;
        sql = "SELECT COUNT(`id`) abierto, `id` FROM `parte` WHERE `validado` = 0 AND idPersona = " + idPersona + " ORDER BY `parte`.`fecha` DESC LIMIT 1";
        System.out.println(sql);
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e);

        }
        rs.next();
        return rs.getInt("id");
    }

    /**
     * Método que devuelve un parte
     *
     * @param parte
     * @throws SQLException
     */
    public static void getParte(Parte parte) throws SQLException {
        String sql = null;
        ResultSet rs = null;
        sql = "SELECT `id`, `idPersona`, `fecha`, `kilometrosInicio`, `kilometrosFin`,"
                + "`gasoil`, `peajes`, `dietas`, `otros`, `eliminado`, `validado`  "
                + "FROM `parte` WHERE `id` = " + parte.getIdParte() + ";";
        rs = stmt2.executeQuery(sql);
        rs.next();
        parte.setIdParte(rs.getInt("id"));
        parte.setTrabajador(new Logistica(rs.getInt("idPersona")));
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
        String sql = null;
        ResultSet rs = null;
        sql = "SELECT SUM(TIMESTAMPDIFF(MINUTE,`horaInicio`,`horaFin`)) MINS FROM `viaje` WHERE `idParte` = " + parte.getIdParte() + ";";
        System.out.println(sql);
        rs = executeQuery(sql);
        rs.next();
        return rs.getInt("MINS");

    }

    /**
     * Devuelve una incidencia. 
     * @param incidencia
     * @throws SQLException
     */
    public static void getIncidencia(Incidencia incidencia) throws SQLException {

        String sql = null;
        ResultSet rs = null;

        sql = "SELECT `id`, `idParte`, `mensaje`, `fecha`, `resuelta`  FROM `incidencia` WHERE `id` = " + incidencia.getIdIncidencia() + ";";
        System.out.println(sql);
        rs = executeQuery(sql);
        rs.next();
        incidencia.setIdIncidencia(rs.getInt("id"));
        incidencia.setFecha(rs.getDate("fecha"));
        incidencia.setMensaje(rs.getString("mensaje"));
        incidencia.setResuelta(rs.getDate("resuelta"));

    }

    /**
     * Devuelve una lista incidencia. 
     * @param parte
     * @return
     * @throws SQLException
     */
    public static Incidencia getIncidencia(Parte parte) throws SQLException {

        String sql = null;
        ResultSet rs = null;

        sql = "SELECT `id`, `idParte`, `mensaje`, `fecha`, `resuelta`  FROM `incidencia` WHERE `idParte` = " + parte.getIdParte() + ";";
        System.out.println(sql);
        rs = executeQuery(sql);
        rs.next();
        return new Incidencia(rs.getInt("id"), rs.getString("mensaje"), rs.getDate("fecha"), rs.getDate("resuelta"));

    }

    /**
     * Devuelve un verdadero o falso dependiendo si existe incidencia para un parte. 
     * @param parte
     * @return
     */
    public static boolean existeParteIncidencia(Parte parte) {
        String sql = null;
        ResultSet rs = null;
        sql = "SELECT `id` FROM `incidencia` WHERE `idParte` = " + parte.getIdParte() + ";";
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
     * Actualiza el trabajador en BBDD
     * @param trabajador
     */
    public static void actualizarTrabajador(Trabajador trabajador) {
        String sql = null;
        ResultSet rs = null;
        sql = "UPDATE `trabajador` SET "
                + "`dni` = '" + trabajador.getDni() + "', "
                + "`nombre` = '" + trabajador.getNombre() + "', "
                + "`apellido1` = '" + trabajador.getApellido1() + "', "
                + "`apellido2` = '" + trabajador.getApellido2() + "', "
                + "`calle` = '" + trabajador.getCalle() + "', "
                + "`numero` = '" + trabajador.getPortal() + "', "
                + "`piso` = '" + trabajador.getPiso() + "', "
                + "`mano` = '" + trabajador.getMano() + "', "
                + "`telPersonal` = '" + trabajador.getTelPersonal() + "', "
                + "`telEmpresa` = '" + trabajador.getTelEmpresa() + "', "
                + "`salario` = '" + trabajador.getSalario() + "', "
                + "`fechaNacimiento` = '" + (trabajador.getFechaNacimiento().getYear() + 1900) + "-" + (trabajador.getFechaNacimiento().getMonth() + 1) + "-" + trabajador.getFechaNacimiento().getDate() + "', "
                + "`idCentro` = '" + trabajador.getCentro().getIdCentro() + "', "
                + "`contrasena` = MD5('" + trabajador.getContrasena() + "') "
                + "WHERE `trabajador`.`id` = " + trabajador.getIdTrabajador() + ";";
        executeUpdate(sql);
    }

    /**
     * Actualiza un centro en la BBDD
     * @param centro
     */
    public static void actualizarCentro(Centro centro) {
        String sql = null;
        ResultSet rs = null;
        sql = "UPDATE `centro` SET "
                + "`nombre` = '" + centro.getNombre() + "', "
                + "`calle` = '" + centro.getCalle() + "', "
                + "`numero` = '" + centro.getNumero() + "', "
                + "`piso` = '" + centro.getPiso() + "', "
                + "`mano` = '" + centro.getMano() + "', "
                + "`codPostal` = '" + centro.getCodPostal() + "', "
                + "`ciudad` = '" + centro.getCiudad() + "', "
                + "`provincia` = '" + centro.getProvincia() + "', "
                + "`telefono` = '" + centro.getTelefono() + "' "
                + "WHERE `centro`.`id` = " + centro.getIdCentro() + ";";
        executeUpdate(sql);
    }

}
