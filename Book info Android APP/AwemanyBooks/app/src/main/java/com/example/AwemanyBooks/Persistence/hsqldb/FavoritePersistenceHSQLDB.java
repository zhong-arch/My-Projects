package com.example.AwemanyBooks.Persistence.hsqldb;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.AwemanyBooks.Object.Book;
import com.example.AwemanyBooks.Business.BookList;
import com.example.AwemanyBooks.Persistence.FavoritePersistence;

public class FavoritePersistenceHSQLDB implements FavoritePersistence {

    private final String dbPath;

    public FavoritePersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    private Book fromResultSet(final ResultSet rs) throws SQLException {

        final String title = rs.getString("title");
        final String author = rs.getString("author");
        final String description = rs.getString("description");
        final String imgurl = rs.getString("imgurl");
        final String category = rs.getString("category");
        final String isbn = rs.getString("isbn");
        final double score = rs.getDouble("score");
        final int drawableRecource = rs.getInt("drawableresource");
        System.out.println("Resource is "+drawableRecource);
        final String azprice = rs.getString("azprice");
        final String azebprice = rs.getString("azebprice");
        final String chprice = rs.getString("chprice");
        final String chebprice = rs.getString("chebprice");
        final int views = rs.getInt("views");

        return new Book(title,author,description,imgurl,category,isbn,score,drawableRecource,azprice,azebprice,chprice,chebprice, views);
    }

    @Override
    public BookList getBookSequential( String uid ) {
        final BookList books = new BookList();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM books INNER JOIN favorite ON books.ISBN = favorite.ISBN WHERE username = '"+uid+"'");
            while (rs.next()) {
                final Book book = fromResultSet(rs);
                books.addBook(book);
            }
            rs.close();
            st.close();

            return books;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void insertBook(String uid, String bid) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO favorite VALUES(?, ?)");
            st.setString(1, uid);
            st.setString(2, bid);

            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
    @Override
    public void updateBook(String uid, String bid) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("UPDATE favorite SET username = ?, isbn = ?");
            st.setString(1, uid);
            st.setString(2, bid);
            st.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
    @Override
    public void deleteBook(String uid, String bid) {
        try (final Connection c = connection()) {
            final PreparedStatement sc = c.prepareStatement("DELETE FROM favorite WHERE username = ? AND isbn = ?");
            sc.setString(1, uid);
            sc.setString(2, bid);
            sc.executeUpdate();

        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void deleteBook(String uid) {

    }

}

