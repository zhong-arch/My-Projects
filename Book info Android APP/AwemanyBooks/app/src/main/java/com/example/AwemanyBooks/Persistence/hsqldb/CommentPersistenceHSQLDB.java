package com.example.AwemanyBooks.Persistence.hsqldb;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.example.AwemanyBooks.Business.CommentList;
import com.example.AwemanyBooks.Object.Comment;
import com.example.AwemanyBooks.Persistence.CommentPersistence;

public class CommentPersistenceHSQLDB implements CommentPersistence {

    private final String dbPath;

    public CommentPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Comment fromResultSet(final ResultSet rs) throws SQLException {

        final String uid = rs.getString("USERNAME");
        final String bid = rs.getString("ISBN");
        final String comment = rs.getString("COMMENT");
        final Date time = rs.getDate("TIME");
        final double score = rs.getDouble("score");

        return new Comment( uid, bid, comment,time, score );
    }

    @Override
    public CommentList getComments(String bid ) {
        final CommentList comments= new CommentList();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM review WHERE ISBN = '"+bid+"'");
            while (rs.next()) {
                final Comment comment = fromResultSet(rs);
                comments.add( comment );
            }
            rs.close();
            st.close();

            return comments;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void insertComment(String uid, String bid, String comment, java.sql.Date time, double score) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO review VALUES(?, ?, ?, ?, ?)");
            st.setString(1, uid);
            st.setString(2, bid);
            st.setString( 3, comment );
            st.setDate( 4, time );
            st.setDouble( 5, score );
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
    @Override
    public void updateComment(String uid, String bid, String comment, java.sql.Date time, double score) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("UPDATE review SET comment = ?,time = ?,score = ? WHERE username = '"+uid+"'");
            st.setString( 1, comment );
            st.setDate( 2, time );
            st.setDouble( 3, score );
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
    @Override
    public void deleteComment(String uid, String bid) {
        try (final Connection c = connection()) {
            final PreparedStatement sc = c.prepareStatement("DELETE FROM review WHERE username = ? AND isbn = ?");
            sc.setString(1, uid);
            sc.setString(2, bid);
            sc.executeUpdate();

        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void deleteComment(String uid) {

    }

}

