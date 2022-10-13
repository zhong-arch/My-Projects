package com.example.AwemanyBooks.Persistence.hsqldb;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.AwemanyBooks.Object.Book;
import com.example.AwemanyBooks.Persistence.BookPersistence;
import com.example.AwemanyBooks.Business.BookList;

public class BookPersistenceHSQLDB implements BookPersistence {

    private final String dbPath;

    public BookPersistenceHSQLDB(final String dbPath) {
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
        final String azprice = rs.getString("azprice");
        final String azebprice = rs.getString("azebprice");
        final String chprice = rs.getString("chprice");
        final String chebprice = rs.getString("chebprice");
        final int views = rs.getInt("views");

        return new Book(title,author,description,imgurl,category,isbn,score,drawableRecource,azprice,azebprice,chprice,chebprice, views);
    }

    @Override
    public BookList getBookSequential() {
        final BookList books = new BookList();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM books");
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
    public BookList getBooksTopViews() {
        final BookList books = new BookList();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT TOP 5 * FROM books ORDER BY views DESC");
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
    public BookList getBooksByCategory(String cg) {
        final BookList books = new BookList();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM books WHERE category = '"+cg+"'");

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
    public Book insertBook(Book currentBook) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("INSERT INTO books VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, currentBook.getTitle());
            st.setString(2, currentBook.getAuthor());
            st.setString(3, currentBook.getDescription());
            st.setString(4, currentBook.getImgUrl());
            st.setString(5, currentBook.getCategory());
            st.setString(6, currentBook.getIsbn());
            st.setDouble(7, currentBook.getScore());
            st.setInt(8,currentBook.getDrawableResource());
            st.setString(9, currentBook.getAzprice());
            st.setString(10,currentBook.getAzebprice());
            st.setString(11,currentBook.getChprice());
            st.setString(12,currentBook.getChebprice());
            st.setInt(13, currentBook.getViews());
            st.executeUpdate();
            return currentBook;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
    @Override
    public Book updateBook(Book currentBook) {
        String bIsbn = currentBook.getIsbn();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("UPDATE books SET title = ?, author = ?, description = ?, imgurl = ?, category = ?, isbn = ?, score = ?, drawableresource = ?, azprice = ?, azebprice = ?, chprice = ?, chebprice = ?, views = ? WHERE isbn = '"+bIsbn+"'");
            st.setString(1, currentBook.getTitle());
            st.setString(2, currentBook.getAuthor());
            st.setString(3, currentBook.getDescription());
            st.setString(4, currentBook.getImgUrl());
            st.setString(5, currentBook.getCategory());
            st.setString(6, currentBook.getIsbn());
            st.setDouble(7, currentBook.getScore());
            st.setInt(8,currentBook.getDrawableResource());
            st.setString(9, currentBook.getAzprice());
            st.setString(10,currentBook.getAzebprice());
            st.setString(11,currentBook.getChprice());
            st.setString(12,currentBook.getChebprice());
            st.setInt(13, currentBook.getViews());
            st.executeUpdate();
            return currentBook;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
    @Override
    public void updateRating(Book currentBook, double rating){
        String bIsbn = currentBook.getIsbn();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("UPDATE books SET score = ? WHERE isbn = '"+bIsbn+"'");
            double currR = currentBook.getScore();
            st.setDouble(1, rating);
            st.executeUpdate();
        }catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
    @Override
    public void deleteBook(Book currentBook) {
        try (final Connection c = connection()) {
            final PreparedStatement sc = c.prepareStatement("DELETE FROM books WHERE isbn = ?");
            sc.setString(1, currentBook.getIsbn());
            sc.executeUpdate();

        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
    @Override
    public BookList search(String key) {
        BookList books=new BookList();
        try(final Connection c=connection()){
            final PreparedStatement findAll=c.prepareCall(
                    "(SELECT FROM BOOKS WHERE TITLE LIKE %?%) UNION (SELECT FROM BOOKS WHERE AUTHOR LIKE %?%) UNION (SELECT FROM BOOKS WHERE ISBN=?)"
            );
            findAll.setString(1,key);
            findAll.setString(2,key);
            findAll.setString(3,key);
            ResultSet rs=findAll.executeQuery();
            while (rs.next()) {
                final Book book = fromResultSet(rs);
                books.addBook(book);
            }
            return books;
        }
        catch(final SQLException e){
            throw new PersistenceException(e);
        }
    }
}

