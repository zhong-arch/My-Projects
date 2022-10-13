package com.example.AwemanyBooks.Persistence.hsqldb;

import com.example.AwemanyBooks.Persistence.Userpersistence;

import java.sql.*;



public class UserPersistenceHSQLDB implements Userpersistence {
    private final String dbpath;
    public UserPersistenceHSQLDB(final String dbpath){
        this.dbpath=dbpath;
    }
    private Connection connection()throws SQLException{
        return DriverManager.getConnection("jdbc:hsqldb:file:"+dbpath+";shutdown=true","SA","");
    }
        @Override
    public boolean checkDuplicate(String username) {
        boolean result=false;
        try (final Connection c=connection()){
            final PreparedStatement st= c.prepareStatement("SELECT * FROM USER WHERE USERNAME= ?");
            st.setString(1,username);
            final ResultSet rs=st.executeQuery();
            result=!rs.next();
        }
        catch(SQLException e){
            throw new PersistenceException(e);
        }
        return result;
    }
    @Override
    public boolean matchRecord(String username, int hashedPw) {
        try(final Connection c=connection()){

            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT HASHEDPW FROM USER WHERE USERNAME='"+username+"'");
            if(rs.next()){
                return hashedPw==rs.getInt("HASHEDPW");
            }
            else{
                return false;
            }
        }
        catch(SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public void insertUser(String username, int hashedPassword) {
        try(final Connection c=connection()){
            final PreparedStatement st= c.prepareStatement("INSERT INTO USER VALUES(?,?)");
            st.setString(1,username);
            st.setInt(2,hashedPassword);
            st.executeUpdate();
        }
        catch (SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public boolean deleteUser(String username) {
        try(final Connection c=connection()){
            final PreparedStatement onDelete=c.prepareStatement("DELETE FROM USER WHERE USERNAME=?");
            onDelete.setString(1,username);
            onDelete.executeUpdate();

            final PreparedStatement st =c.prepareStatement("DELETE FROM USER WHERE USERNAME=?");
            st.setString(1,username);
            int result=st.executeUpdate();
            return result==1;
        }
        catch (SQLException e){
            throw new PersistenceException(e);
        }
    }

    @Override
    public int changePassword(String username, int oldHashedPw, int newHashedPw) {
        try(final Connection c= connection()){
            final PreparedStatement st=c.prepareStatement("SELECT HASHEDPW FROM USER WHERE USERNAME=?");
            st.setString(1,username);
            ResultSet rs=st.executeQuery();
            if(!rs.next()){
                //the result is empty
                return 2;
            }
            int old=rs.getInt("HASHEDPW");
            if(old==oldHashedPw){
                final PreparedStatement update=c.prepareStatement("UPDATE USER SET USER.HASHEDPW=? WHERE USER.USERNAME=?");
                update.setInt(1,newHashedPw);
                update.setString(2,username);
                update.executeUpdate();
                return 0;
            }
            else{
                return 1;
            }
        }
        catch (SQLException e){
            throw new PersistenceException(e);
        }
    }
}
