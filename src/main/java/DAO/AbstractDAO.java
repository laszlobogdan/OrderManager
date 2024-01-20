package DAO;
import Connection.ConnectionFactory;
import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.sql.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @Author: Laszlo Bogdan Gheorghe
 * @Since: May 15 , 2023
 * @Source http://www.java-blog.com/mapping-javaobjects-database-reflection-generics
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * Method that returns a mysql query for selecting an object from a table where id =field
     * @param field
     * @return String
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Method that return a my sql query for returning all of the objects in a table
     * @return String
     */
    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * Method that fills a JTable model
     * @param model
     */
    public void FillTable(DefaultTableModel model)
    {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        String query =createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            resultSet= statement.executeQuery(query);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                model.addColumn(columnName);
            }
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = resultSet.getObject(i);
                }
                model.addRow(rowData);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that returns a list from a table with all the objects
     * @return List
     */
    public List<T> findAll() {
        // TODO:
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        String query =createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Method that returns a query for inserting an object in a table
     * @return String
     */
    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        Field[] fields=type.getDeclaredFields();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" (");
        for(int i=1;i<fields.length;i++){
            if(i!= fields.length-1) {
                sb.append(fields[i].getName()).append(", ");
            }else{
                sb.append(fields[i].getName()).append(") ");
            }
        }
        sb.append(" VALUES ");
        sb.append("(");
        for(int i=1;i<fields.length;i++){
            if(i!= fields.length-1) {
                sb.append("?,");
            }else{
                sb.append("?)");
            }
        }
        return sb.toString();
    }

    /**
     * Method that inserts an object in a table
     * @param t
     * @return boolean
     */
    public boolean insert(T t){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery();
        try {
            Field[] fields=type.getDeclaredFields();
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            PropertyDescriptor propertyDescriptor=null;
            for (int i=1;i< fields.length;i++) {
                propertyDescriptor=new PropertyDescriptor(fields[i].getName(),type);
                if (fields[i].getType().getSimpleName().equals("int")) {
                    statement.setInt(i,(int)propertyDescriptor.getReadMethod().invoke(t));
                }else if (fields[i].getType().getSimpleName().equals("String")) {
                        statement.setString(i, (String) propertyDescriptor.getReadMethod().invoke(t));
                }
            }
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return false;
    }

    /**
     * Method that returns an object from a table by its id
     * @param id
     * @return T
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Method that creates a query for updateing an object from a table
     * @param id
     * @return String
     */
    private String createUpdateQuery(int id) {
        StringBuilder sb = new StringBuilder();
        Field[] fields=type.getDeclaredFields();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        int count=0;
        for(Field f:fields){
            if(count!=0) {
                sb.append(f.getName());
                if (count != fields.length - 1) {
                    sb.append("=?,");
                } else {
                    sb.append("=?");
                }
            }
            count++;
        }
        sb.append(" WHERE ");
        sb.append("id=").append(String.valueOf(id));
        return sb.toString();
    }

    /**
     * Method that updates an object from a table
     * @param id
     * @param t
     * @return boolean
     */
    public boolean update(int id,T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createUpdateQuery(id);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            Field[] fields=type.getDeclaredFields();
            PropertyDescriptor propertyDescriptor=null;
            for (int i=1;i< fields.length;i++) {
                propertyDescriptor=new PropertyDescriptor(fields[i].getName(),type);
                if (fields[i].getType().getSimpleName().equals("int")) {
                    statement.setInt(i,(int)propertyDescriptor.getReadMethod().invoke(t));
                }else if (fields[i].getType().getSimpleName().equals("String")) {
                        statement.setString(i, (String) propertyDescriptor.getReadMethod().invoke(t));
                }
            }
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return false;
    }

    /**
     * Method that creates a query for deleteing an object from a table
     * @param id
     * @return String
     */
    private String createDeleteQuery(int id) {
        StringBuilder sb = new StringBuilder();
        Field[] fields=type.getDeclaredFields();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ");
        sb.append("id=").append(id);
        return sb.toString();
    }

    /**
     * Method that deletes an object from a table
     * @param id
     * @return boolean
     */
    public boolean delete(int id) {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createDeleteQuery(id);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return false;
    }
}
