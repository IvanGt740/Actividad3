package modelo2;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;




import javax.swing.JOptionPane;

public class Docente extends Persona {
    
    
    
    
    private double salario;
    private String codigo_docente, fecha_ingreso_laborar, fecha_ingreso_registro;
    // Cambiado a private

    Conexion cn;
    public Docente() {}

    public Docente(double salario,String codigo_docente, String fecha_ingreso_laborar, String fecha_ingreso_registro, String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nit, nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.codigo_docente = codigo_docente;
        this.salario = salario;
        this.fecha_ingreso_laborar = fecha_ingreso_laborar;
        this.fecha_ingreso_registro = fecha_ingreso_registro;
        
        
       // this.sueldo = sueldo;
       // this.bono = bono;
       // Calcula el total en el constructor
    }

    public String getCodigo_docente() {
        return codigo_docente;
    }

    public void setCodigo_docente(String codigo_docente) {
        this.codigo_docente = codigo_docente;
         
    }
    //

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
         
    }

   //
    
    public String getFecha_ingreso_laborar() {
        return fecha_ingreso_laborar;
    }

    public void setFecha_ingreso_laborar(String fecha_ingreso_laborar) {
        this.fecha_ingreso_laborar = fecha_ingreso_laborar ;
         
    }
    
    //
    
    public String getFecha_ingreso_registro() {
        return fecha_ingreso_registro;
    }

    public void setFecha_ingreso_registro(String fecha_ingreso_registro) {
        this.fecha_ingreso_registro = fecha_ingreso_registro ;
         
    }
  
    

   

   /* @Override
    protected String[] crear() {
        try {
            String[] datos = new String[9];
            datos[0] = getNit();
            datos[1] = getNombres();
            datos[2] = getApellidos();
            datos[3] = getDireccion();
            datos[4] = getTelefono();
            datos[5] = getFecha_nacimiento();
            datos[6] = String.valueOf(getSueldo());
            datos[7] = String.valueOf(getBono());
            datos[8] = String.valueOf(getTotal()); // Asegúrate de que esto sea el total correcto
            return datos;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error en Query", JOptionPane.ERROR_MESSAGE);
            return null;
        }
     }
    
    */
    
    @Override
    public void crear(){
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "INSERT INTO PERSONA(Nit,Nombres,Apellidos,direccion,telefono,fecha_nacimiento) VALUES (?,?,?,?,?,?);";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
            int executar = parametro.executeUpdate();
            System.out.println("Ingreso exitoso... " + Integer.toString (executar));
            
            
            
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            
            System.out.println("Error en crear "+ ex.getMessage());
        }
        
    }
    
public DefaultTableModel leer(){
    DefaultTableModel tabla = new DefaultTableModel();
    try{
        cn = new Conexion();
        cn.abrir_conexion();
        String query = "SELECT * FROM PERSONA;";
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
       String encabezado[] = {"Nit","Nombres","Apellidos","direccion","telefono","Fecha_Nacimiento"};
       tabla.setColumnIdentifiers(encabezado);
       String datos[] = new String[7];
       while (consulta.next()){
           
           datos[0] = consulta.getString("nit");
           datos[1] = consulta.getString("nombres");
           datos[2] = consulta.getString("apellidos");
           datos[3] = consulta.getString("direccion");
           datos[4] = consulta.getString("telefono");
           datos[5] = consulta.getString("fecha_nacimiento");
           tabla.addRow(datos);
       }
        
        cn.cerrar_conexion();
    }catch (SQLException ex){
        cn.cerrar_conexion();
        System.out.println("Error leer: " + ex.getMessage());
   
    }
    
    return tabla;
}

@Override
public void actualizar(){
    try{
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "UPDATE clientes SET codigo = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ? WHERE id_cliente = ?;";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
            //parametro.setInt(7, getId());
            
            int executar = parametro.executeUpdate();
            System.out.println("Modificación exitosa... " + Integer.toString (executar));
            
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            
            System.out.println("Error en actualizar: "+ ex.getMessage());
        }



}
@Override
public void borrar(){
    try{
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "DELETE FROM clientes WHERE id_cliente = ?;";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            
           // parametro.setInt(1, getId());
            
            int executar = parametro.executeUpdate();
            System.out.println("Eliminación exitosa... " + Integer.toString (executar));
            
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            
            System.out.println("Error en borrar: "+ ex.getMessage());
        }



}
}

